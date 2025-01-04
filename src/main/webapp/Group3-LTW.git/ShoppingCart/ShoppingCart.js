document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("productTable");
    const rows = table.querySelectorAll("tbody > tr");

    const products = [];

    rows.forEach((row) => {
        const cells = row.querySelectorAll("td");
        console.log(cells);
        if (cells.length >= 6) { // Đảm bảo số ô tối thiểu
            const product = {
                image: cells[0]?.querySelector("img")?.src || "No Image", // Lấy URL ảnh
                name: cells[1]?.textContent.trim() || "N/A", // Lấy tên
                price: cells[2]?.textContent.trim() || "0đ", // Lấy giá
                quantity: cells[3]?.querySelector("table td:nth-child(2)")?.textContent.trim() || "0", // Lấy số lượng
                total: cells[7]?.textContent.trim() || "0đ", // Lấy thành tiền
            };
            products.push(product);
        }
    });
    console.log("Danh sách sản phẩm:", products);
    localStorage.setItem('cart', JSON.stringify(products));
    // Kiểm tra xem dữ liệu đã lưu thành công chưa
    const storedCart = JSON.parse(localStorage.getItem('cart'));
    console.log("Dữ liệu đã lưu trong localStorage:", storedCart);
    const checkoutButton = document.getElementById("ContinueButton");
    checkoutButton.addEventListener("click", () => {
        // Chuyển hướng sang trang thanh toán
        window.location.href = "http://localhost:63342/Group3-LTW.git/Pay/Pay.html?_ijt=6spcve1hcpgk4uoege01ljfcf5&_ij_reload=RELOAD_ON_SAVE";
    });
});

function deleteProduct(element) {
    element.closest("tr").remove();
}

document.addEventListener('DOMContentLoaded', () => {
    const cart = JSON.parse(localStorage.getItem('information')) || [];
    if (cart) {
        const cartItemsList = document.querySelector('tbody');
        const totalPaymentRow = document.querySelector('.Totalpayment').closest('tr');
        if (cartItemsList && totalPaymentRow) {
            const listItem = document.createElement('tr');
            listItem.className = 'List_item';
            listItem.innerHTML = `
                    <td><img src="${cart.Image}" alt="" class="Shopping_Cartlist_Content_ImgRauCuQua"></td>
                    <td>${cart.Headline}</td>
                    <td>${cart.Price}.000đ</td>
                    <td>
                        <table>
                            <tr>
                                <td>+</td>
                                <td>${cart.Quantity}</td>
                                <td>-</td>
                            </tr>
                        </table>
                    </td>
                    <td>${cart.Totalamount}.000đ</td>
                    <td><i class="fa-solid fa-trash-can" onclick="deleteProduct(this)"></i></td>
                `;
            cartItemsList.insertBefore(listItem, totalPaymentRow);
        }
    }
});