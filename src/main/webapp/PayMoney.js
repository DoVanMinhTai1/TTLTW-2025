
document.addEventListener("DOMContentLoaded", function () {
    const provinceSelect = document.getElementById("Conscious");
    const districtSelect = document.getElementById("District");
    const wardSelect = document.getElementById("Commune");

    const provinceIdInput = document.getElementById("ProvinceID");
    const districtIdInput = document.getElementById("DistrictID");
    const wardCodeInput = document.getElementById("WardCode");

    fetch("/web/addressData?type=provinces")
        .then(res => res.json())
        .then(data => {
            provinceSelect.innerHTML = `<option disabled selected>Chọn tỉnh</option>`;
            data.forEach(p => {
                provinceSelect.innerHTML += `<option value="${p.ProvinceID}">${p.ProvinceName}</option>`;
            });
            provinceSelect.disabled = false;
        })
        .catch(err => {
            console.error("Lỗi khi tải tỉnh:", err);
            alert("Không thể tải danh sách tỉnh. Vui lòng thử lại.");
        });

    // Sự kiện chọn tỉnh → load quận
    provinceSelect.addEventListener("change", function () {
        const provinceId = this.value;
        provinceIdInput.value = provinceId;
        districtSelect.innerHTML = `<option disabled selected>Chọn quận</option>`;
        wardSelect.innerHTML = `<option disabled selected>Chọn phường</option>`;
        districtSelect.disabled = true;
        wardSelect.disabled = true;
        districtIdInput.value = "";
        wardCodeInput.value = "";
        resetTransportFee();

        if (provinceId) {
            fetch(`/web/addressData?type=districts&parentId=${provinceId}`)
                .then(res => res.json())
                .then(data => {
                    districtSelect.innerHTML = `<option disabled selected>Chọn quận</option>`;
                    data.forEach(d => {
                        districtSelect.innerHTML += `<option value="${d.DistrictID}">${d.DistrictName}</option>`;
                    });
                    districtSelect.disabled = false;
                })
                .catch(err => {
                    console.error("Lỗi khi tải quận:", err);
                    alert("Không thể tải danh sách quận. Vui lòng thử lại.");
                });
        }
    });

            // Sự kiện chọn tỉnh → load quận
            districtSelect.addEventListener("change", function () {
                const districtId = this.value;
                districtIdInput.value = districtId;
                wardSelect.innerHTML = `<option disabled selected>Chọn phường</option>`;
                wardSelect.disabled = true;
                wardCodeInput.value = "";
                resetTransportFee();

                if (districtId) {
                    fetch(`/web/addressData?type=wards&parentId=${districtId}`)
                        .then(res => res.json())
                        .then(data => {
                            wardSelect.innerHTML = `<option disabled selected>Chọn phường</option>`;
                            data.forEach(w => {
                                wardSelect.innerHTML += `<option value="${w.WardCode}">${w.WardName}</option>`;
                            });
                            wardSelect.disabled = false;
                        })
                        .catch(err => {
                            console.error("Lỗi khi tải phường:", err);
                            alert("Không thể tải danh sách phường. Vui lòng thử lại.");
                        });
                }
            });

            // Sự kiện chọn quận → load phường
            wardSelect.addEventListener("change", function () {
                const wardCode = this.value;
                wardCodeInput.value = wardCode;
                calculateShippingFee();
            });

    const urlParams = new URLSearchParams(window.location.search);
    const responseCode = urlParams.get('vnp_ResponseCode');
    const orderId = urlParams.get('vnp_TxnRef');
    const fromCart = sessionStorage.getItem('fromCart');
    const productList = JSON.parse(sessionStorage.getItem('productList'));

    if (window.location.pathname.includes('/vnpay_return') && responseCode === '00' && fromCart === 'true' && productList && productList.length > 0) {
        $.ajax({
            url: '/web/RemoveCartList',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(productList),
            timeout: 5000,
            success: function () {
                console.log("Giỏ hàng đã được xóa sau khi thanh toán VNPay.");
                sessionStorage.removeItem('productList');
                sessionStorage.removeItem('fromCart');
                sessionStorage.removeItem('userId');
                alert("Thanh toán thành công và giỏ hàng đã được xóa!");
            },
            error: function () {
                console.error("Lỗi khi xóa giỏ hàng sau thanh toán VNPay.");
                alert("Thanh toán thành công nhưng không thể xóa giỏ hàng. Vui lòng liên hệ hỗ trợ.");
            }
        });
    } else if (window.location.pathname.includes('/vnpay_return') && responseCode !== '00') {
        console.log("Thanh toán VNPay thất bại. Mã lỗi: ", responseCode);
        alert("Thanh toán thất bại. Mã lỗi: " + responseCode);
    }
        });
// });
//reset phí vc
function resetTransportFee() {
    const transportElement = document.getElementById("transportValue");
    const ghnFeeElement = document.getElementById("ghnFee");
    const provisionalValueText = document.getElementById("provisional").innerText.replace(/,/g, '').replace('đ', '');
    const provisionalValue = parseFloat(provisionalValueText) || 0;

    transportElement.innerText = "0đ";
    ghnFeeElement.innerText = "0";
    document.getElementById("total").innerText = provisionalValue.toLocaleString('vi-VN', { maximumFractionDigits: 0 }) + "đ";
}
// Tính phí vận chuyển
async function calculateShippingFee() {
    const districtId = document.getElementById("DistrictID").value;
    const wardCode = document.getElementById("WardCode").value;
    const totalWeight = 1000; // Trọng lượng cố định: 1kg
    const length = 30; // Chiều dài cố định: 30cm
    const width = 20; // Chiều rộng cố định: 20cm
    const height = 10; // Chiều cao cố định: 10cm

    if (districtId && wardCode) {
        try {
            const response = await fetch("/web/calculateShippingFee", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `toDistrictId=${districtId}&toWardCode=${wardCode}&weight=${totalWeight}&length=${length}&width=${width}&height=${height}`
            });
            const result = await response.json();

            if (result.code === 200) {
                const fee = result.data.total;
                const transportElement = document.getElementById("transportValue");
                const ghnFeeElement = document.getElementById("ghnFee");
                transportElement.innerText = fee.toLocaleString('vi-VN', { maximumFractionDigits: 0 }) + "đ";
                ghnFeeElement.innerText = fee.toLocaleString('vi-VN', { maximumFractionDigits: 0 });

                // Cập nhật tổng tiền
                const provisionalValueText = document.getElementById("provisional").innerText.replace(/,/g, '').replace('đ', '');
                const provisionalValue = parseFloat(provisionalValueText) || 0;
                const totalAmount = provisionalValue + fee;
                document.getElementById("total").innerText = totalAmount.toLocaleString('vi-VN', { maximumFractionDigits: 0 }) + "đ";
            } else {
                alert("Lỗi khi tính phí vận chuyển: " + result.message);
                resetTransportFee();
            }
        } catch (error) {
            console.error("Lỗi khi tính phí vận chuyển:", error);
            alert("Không thể tính phí vận chuyển. Vui lòng thử lại.");
            resetTransportFee();
        }
    } else {
        resetTransportFee();
    }
}

async function discount(total,userId) {
    var discountCode = document.getElementById("DiscountCode").value;
    const response = await fetch(`/web/applyPromotion?total=${total}&discountCode=${discountCode}&uId=${userId}`);
    const valueProvisional = await response.json();
    if (valueProvisional.status === "success") {
        alert(valueProvisional.message);  // Thông báo "Áp mã thành công"

        const provisionalElement = document.getElementById("provisional");
        provisionalElement.innerText = valueProvisional.totalAmount.toLocaleString('vi-VN', {maximumFractionDigits: 0}).replace(/\./g, ',');
        const transportValue = parseFloat(document.getElementById("transportValue").innerText.replace(/,/g, '').replace('đ', ''));
        const totalElement = document.getElementById("total");
        totalElement.innerText = (valueProvisional.totalAmount + (isNaN(transportValue) ? 0 : transportValue)).toLocaleString('vi-VN', {maximumFractionDigits: 0}).replace(/\./g, ',') + "đ";


    } else {
        alert(valueProvisional.message);  // Thông báo "Mã khuyến mãi không tồn tại"
    }

}

async function submitForm() {
    var selectedOption = document.querySelector('input[name="option"]:checked');
    var valueTransport = selectedOption.value;
    // Lấy giá trị tạm tính và loại bỏ dấu phân cách (nếu có)
    const provisionalValueText = document.getElementById("provisional").innerText.replace(/,/g, '').replace('đ', '');
    const provisionalValue = parseFloat(provisionalValueText) || 0;

    // Cập nhật phí vận chuyển với định dạng
    const transportElement = document.getElementById("transportValue");
    transportElement.innerText = parseFloat(valueTransport).toLocaleString('vi-VN', {
        maximumFractionDigits: 0
    }).replace(/\./g, ',') + "đ";
    // Tính tổng tiền
    const totalAmount = provisionalValue + parseFloat(valueTransport);

    // Cập nhật tổng tiền với định dạng chuẩn
    const totalElement = document.getElementById("total");
    totalElement.innerText = totalAmount.toLocaleString('vi-VN', {
        maximumFractionDigits: 0
    }).replace(/\./g, ',') + "đ";
}

let currentOrderId = null;

async function order(userId, addressId, fromCart) {
    const paymentMethod = document.querySelector('input[name="Payment"]:checked').value;
    // Chọn tất cả các sản phẩm
    const items = document.querySelectorAll('.PayRightContent_item');
    const userId1 = userId;
    // Tạo object để lưu productId, quantity, và price
    const cartMap = {};
    const rawTotal = document.getElementById('total').innerText;
    const numericTotal = parseInt(rawTotal.replace(/[^\d]/g, ''));
    // Duyệt qua từng sản phẩm và thêm vào cartMap
    const productList = [];
    items.forEach(item => {
        const productId = parseInt(item.getAttribute('data-id'));
        const quantity = parseInt(item.getAttribute('data-quantity'));
        const price = parseFloat(item.getAttribute('data-price')); // Lấy giá

        cartMap[productId] = {quantity: quantity, price: price};
        productList.push({
            productId: productId
        })
    });

    console.log(numericTotal)
    addressId = parseInt(addressId);
    const vnpayUrl = `/web/create-vnpay-payment`;
    const addOrderUrl = `/web/addOrder`;
    const requestBody = {
        userId: userId1,
        addressId: addressId,
        cartMap: cartMap,
        total: numericTotal
    };
    try {
        if (paymentMethod === "Paybycard") {
            // Lưu productList và fromCart vào sessionStorage trước khi chuyển hướng
            sessionStorage.setItem('productList', JSON.stringify(productList));
            sessionStorage.setItem('fromCart', fromCart);
            sessionStorage.setItem('userId', userId1);
            // Xử lý thanh toán qua VNPay
            // Gửi yêu cầu đến endpoint /create-vnpay-payment để tạo URL thanh toán
            console.log("Sending request to:", vnpayUrl);
            const response = await fetch(vnpayUrl, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestBody)
            });
            // Kiểm tra phản hồi từ server
            if (!response.ok) {
                const error = await response.json();
                if (response.status === 404) {
                    // Sản phẩm không tồn tại trong kho
                    showModalProductNotFound(error);
                } else if (response.status === 400) {
                    // Số lượng sản phẩm không đủ
                    showModalProductErrorQuantity(error);
                } else {
                    // Lỗi khác (ví dụ: không hỗ trợ khu vực)
                    alert("Lỗi: " + error.message|| "Không thể tạo thanh toán VNPay");
                }
                return; // Thoát hàm nếu có lỗi
            }
            // Nhận URL thanh toán và orderId từ server
            const result = await response.json();
            currentOrderId = result.orderId; // Lưu orderId để xuất PDF
            window.location.href = result.paymentUrl; // Chuyển hướng đến cổng VNPay
        } else {
            const response = await fetch(`/web/addOrder`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    userId: userId1,
                    addressId: addressId,
                    cartMap: cartMap,
                    total: numericTotal
                })
            });

            if (response.ok) {
                const result = await response.json();
                console.log("Kết quả:", result);
                document.getElementById("code").innerText = result;
                // Hiển thị thông báo đặt hàng thành công
                const newAddress = document.getElementById("OrderSuccessful");
                const overlay = document.createElement('div');
                overlay.className = "overlay";
                overlay.id = "overlay";
                document.body.appendChild(overlay);
                newAddress.style.display = "block";
                if (fromCart === 'true') {
                    // $(document).ready(function () {
                        $.ajax({
                            url: '/web/RemoveCartList',
                            type: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify(
                                productList
                            ),
                            success: function () {

                            },
                            error: function () {

                            }
                        })
                    // })
                }
                currentOrderId = result;
            } else if (response.status === 404) {
                const notFoundProducts = await response.json();
                showModalProductNotFound(notFoundProducts)
            } else {
                const errorProducts = await response.json();
                showModalProductErrorQuantity(errorProducts);
            }
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Lỗi khi xử lý đơn hàng.");
    }

    function showModalProductNotFound(products) {
        const modalBody = document.getElementById('modalProductErrorBody');
        let html = `<p>Các sản phẩm sau không có trong kho:</p><ul>`;
        products.forEach(product => {
            html += `<li>${product.name} </li>`;
        });
        html += `</ul>`;

        modalBody.innerHTML = html;

        const myModal = new bootstrap.Modal(document.getElementById('productQuantityErrorModal'));
        myModal.show();
    }

    function showModalProductErrorQuantity(products) {
        const modalBody = document.getElementById('modalProductErrorBody');
        let html = `<p>Các sản phẩm sau không đủ số lượng trong kho:</p><ul>`;
        products.forEach(product => {
            html += `<li>${product.product.name} - Chỉ còn ${product.quantity} sản phẩm</li>`;
        });
        html += `</ul>`;

        modalBody.innerHTML = html;

        const myModal = new bootstrap.Modal(document.getElementById('productQuantityErrorModal'));
        myModal.show();

    }

}

function exportPdf() {
    if (!currentOrderId) {
        alert("Cannot export PDF without an order ID.");
        return;
    }

    fetch('/web/exportPdf', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({orderId: currentOrderId})

    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to generate PDF');
            }
            return response.blob();
        })
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'order.pdf';
            document.body.appendChild(a);
            a.click();
            a.remove();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Export failed');
        });
}
