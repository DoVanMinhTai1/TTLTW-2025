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
        totalElement.innerText = (valueProvisional.totalAmount + (isNaN(transportValue) ? 0 : transportValue)).toLocaleString('vi-VN', {maximumFractionDigits: 0}).replace(/\./g, ',')+"đ";


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
    }).replace(/\./g, ',')+"đ";
    // Tính tổng tiền
    const totalAmount = provisionalValue + parseFloat(valueTransport);

    // Cập nhật tổng tiền với định dạng chuẩn
    const totalElement = document.getElementById("total");
    totalElement.innerText = totalAmount.toLocaleString('vi-VN', {
        maximumFractionDigits: 0
    }).replace(/\./g, ',')+"đ";
}
let currentOrderId = null;

async function order(userId, addressId,fromCart) {
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

        cartMap[productId] = { quantity: quantity, price: price };
        productList.push({
            productId: productId
        })
    });

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
        document.getElementById("code").innerText=result;
        // Hiển thị thông báo đặt hàng thành công
        const newAddress = document.getElementById("OrderSuccessful");
        const overlay = document.createElement('div');
        overlay.className = "overlay";
        overlay.id = "overlay";
        document.body.appendChild(overlay);
        newAddress.style.display = "block";
        if(fromCart === 'true') {
            $(document).ready(function () {
                $.ajax({
                    url: '/web/RemoveCartList',
                    type: 'POST',
                    data: JSON.stringify(
                        productList
                    ),
                    success: function () {

                    },
                    error: function () {

                    }
                })
            })
        }
        currentOrderId = result;
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
        body: JSON.stringify({ orderId: currentOrderId })
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
