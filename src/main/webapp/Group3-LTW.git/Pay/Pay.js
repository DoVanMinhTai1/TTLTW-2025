document.addEventListener("DOMContentLoaded", () => {
    const confirmButton = document.getElementById("ConfirmAddressButton"); // Nút bấm để gọi hàm order

    if (confirmButton) {
        confirmButton.addEventListener("click", order);
    } else {
        console.error("Button với ID 'ConfirmAddressButton' không tồn tại.");
    }
});

// js cho button dat hang
function order() {
    const address = document.getElementById("Address").value.trim();
    const province = document.getElementById("Conscious").value.trim();
    const district = document.getElementById("District").value.trim();
    const commune = document.getElementById("Commune").value.trim();
    const total = document.getElementById("total").textContent.trim();
    // Kiểm tra xem các trường có được nhập đầy đủ hay không
    if (!address || !province || !district || !commune) {
        alert("Vui lòng nhập đầy đủ thông tin địa chỉ.");
        return;
    }
    const randomNumber = Math.floor(Math.random() * 1000000); // Tạo số ngẫu nhiên từ 0 đến 999999
    const formattedNumber = randomNumber.toString().padStart(6, '0');
    const code= `#${formattedNumber}`;

    const today = new Date();
    const formattedDate = today.toLocaleDateString('vi-VN'); // 'vi-VN' cho định dạng Việt Nam
    console.log(formattedDate); // Ví dụ: 22/11/2024

    // Tạo chuỗi địa chỉ
    const deliveryAddress = `${address}, ${commune}, ${district}, ${province}`;
    const information = {
        Address: deliveryAddress,
        DateBooked: formattedDate,
        Total: total,
        Code: code,
    };
    console.log("Địa chỉ:", deliveryAddress);

    // Lưu vào localStorage
    localStorage.setItem("information", JSON.stringify(information));

    // Kiểm tra xem dữ liệu đã lưu thành công chưa
    const storedInformation = JSON.parse(localStorage.getItem("information"));
    console.log("Dữ liệu đã lưu trong localStorage:", storedInformation);

    const newAddress = document.getElementById("OrderSuccessful");
    // Thêm một lớp overlay để làm tối nền
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    // Hiển thị form thêm địa chỉ
    newAddress.style.display = "block";
}

// js cap nhat du lieu tu trang gio hang
document.addEventListener('DOMContentLoaded', () => {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];

    // Cập nhật số lượng sản phẩm trong tiêu đề
    const orderTitle = document.querySelector('.PayRightContentTitle');
    if (orderTitle) {
        orderTitle.textContent = `Đơn hàng (${cart.length} sản phẩm)`;
    }

    // Kiểm tra xem giỏ hàng có dữ liệu không
    if (cart.length > 0) {
        const cartItemsList = document.getElementById('cartItems');
        if (cartItemsList) {
            cart.forEach(product => {
                const listItem = document.createElement('li');
                listItem.className = 'PayRightContent_item';
                listItem.innerHTML = `
                    <div class="PayRightContent_item_imgnotice">
                        <img src="${product.image}" alt="Product Image" class="PayRightContent_img_item">
                        <span class="PayRightContent_item_notice">${product.quantity}</span>
                    </div>
                    <div class="PayRightContent_item_info">
                        <div class="PayRightContent_item_header">
                            <div class="PayRightContent_item_name">${product.name}</div>
                            <div class="PayRightContent_item_price">${product.price}</div>
                        </div>
                    </div>
                `;
                cartItemsList.appendChild(listItem);
            });
        }
    } else {
        console.log('Giỏ hàng trống!');
        // Có thể thêm thông báo nếu giỏ hàng trống
        const cartItemsList = document.getElementById('cartItems');
        if (cartItemsList) {
            const emptyMessage = document.createElement('li');
            emptyMessage.textContent = 'Giỏ hàng của bạn hiện tại trống.';
            cartItemsList.appendChild(emptyMessage);
        }
    }
});
