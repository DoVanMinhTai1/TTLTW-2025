document.addEventListener("DOMContentLoaded", () => {
    const confirmButton = document.getElementById("add-later"); // Nút bấm để gọi hàm order

    if (confirmButton) {
        confirmButton.addEventListener("click", addToCart);
    } else {
        console.error("Button với ID 'add-later' không tồn tại.");
    }
});

// js cho button dat hang
function addToCart() {
    // Lấy hình ảnh
    const image = document.querySelector('.main-image ul li');
    const imgSource = image.querySelector('img').src;

// Lấy tiêu đề sản phẩm
    const headline = document.querySelector('.detail-headline h3').innerText;

// Lấy giá sản phẩm và chuyển đổi thành số
    const priceText = document.querySelector('.detail-price h4').innerText;
    const price = parseFloat(priceText.replace(/[^0-9.-]+/g, "")); // Loại bỏ đơn vị tiền tệ nếu có

// Lấy số lượng và chuyển đổi thành số nguyên
    const quantityText = document.querySelector('.quantity').innerText;
    const quantity = parseInt(quantityText, 10);

// Tính tổng tiền
    const totalamount = price * quantity;
    const information = {
        Image: imgSource,
        Headline: headline,
        Price: price,
        Quantity: quantity,
        Totalamount: totalamount,
    };
    // Lưu vào localStorage
    localStorage.setItem("information", JSON.stringify(information));
    // Kiểm tra xem dữ liệu đã lưu thành công chưa
    const storedInformation = JSON.parse(localStorage.getItem("information"));
    console.log("Dữ liệu đã lưu trong localStorage:", storedInformation);
}