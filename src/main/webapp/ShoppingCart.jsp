<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/6/2024
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>ShoppingCart</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ShoppingCart.css">
</head>
<body>
<c:if test="${not empty error}">
    <script type="text/javascript">
        alert("${error}");
    </script>
</c:if>

<div class="header" id="header">
    <div class="header container">
        <a href="showHome"><img id="logo" src="Img/snapedit_1730861562696.png" alt="Shopping Cart Image"
                                style="width: 150px"> </a>
        <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">
        <i class="fas fa-phone"></i>
        <div class="headerphone">HOTLINE: 0327237467</div>
        <c:set var="currentUser" value="${sessionScope.user}"/> <!-- Lấy user từ session -->
        <c:choose>
            <c:when test="${not empty currentUser}">
                <a href="showCustomerPage?uId=${sessionScope.user.id}" style="text-decoration: none">
                    <div class="headercontendangnhap">
                            ${not empty currentUser.username ? currentUser.username : currentUser.given_name}
                    </div>
                </a>
                <div class="line"></div>
                <a href="logout" style="text-decoration: none">
                    <div class="headercontendangki">Đăng Xuất</div>
                </a>
            </c:when>
            <c:otherwise>
                <a href="showLogin" style="text-decoration: none">
                    <div class="headercontendangnhap">
                        Đăng Nhập
                    </div>
                </a>
                <div class="line"></div>
                <a href="showLogin" style="text-decoration: none">
                    <div class="headercontendangki">Đăng Kí</div>
                </a>
            </c:otherwise>
        </c:choose>
        <!--        gio hang-->
        <div class="shopping_cart">
            <div class="shopping_cart_swap">
                <i class="fa-solid fa-basket-shopping"></i>
                <span class="shopping_notice">${sessionScope.cart!=null?sessionScope.cart.totalQuantity:0}</span>
            </div>
        </div>
        <div class="shoppingtext">Giỏ hàng</div>
    </div>
    <div class="pathline"></div>
    <div class="Navigationbar">
        <div class="Navigationbar container">
            <span class="NavigationbarHome">Trang Chủ</span>
            <span class="Navigationbar/">/</span>
            <span class="NavigationbarShoppingCard">Giỏ Hàng</span>
        </div>
    </div>
</div>
<div class="Shopping_Cartlist_Content">
    <div class="Shopping_Cartlist_Content container">
        <div class="Shopping_Cartlist_Content_Text">Giỏ Hàng Của Bạn</div>
        <div class="Shopping_Cartlist_Content_Item">
            <table id="productTable">
                <thead>
                <tr>
                    <th>Chọn Sản Phẩm</th>
                    <th>Hình ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Đơn Giá</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cartItemWithProduct}" var="cartItem">
                    <tr id="row-${cartItem.productId}">
                        <td>
                            <input class="product-checkbox" name="selectedProducts" type="checkbox" data-price="${cartItem.price}" value="${cartItem.productId}"
                            />
                        </td>
                        <td><img src="${cartItem.image}" alt="" class="Shopping_Cartlist_Content_ImgRauCuQua"></td>
                        <td>${cartItem.name}</td>
                        <td id="price-${cartItem.productId}"><fmt:formatNumber value="${cartItem.price}" type="number"
                                                                               maxFractionDigits="0"/>đ
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <button
                                                onclick="handleDecreaseCartItem(${cartItem.productId},${cartItem.quantity})"
                                                style="text-decoration: none; color: black;">-
                                        </button>

                                    </td>


                                    <td><span id="cartItemQuantity-${cartItem.productId}">${cartItem.quantity}</span>
                                    </td>
                                        <%--          <td><a href="UpdateCart?pid=${cartItem.id}&quantity=${cartItem.quantity}&expression=minus" style="text-decoration: none; color: black;">-</a></td>--%>
                                    <td>
                                            <%--            <a --%>
                                            <%--                    href=--%>
                                            <%--  "UpdateCart?pid=${cartItem.id}&quantity=${cartItem.quantity}&expression=plus"--%>
                                            <%--                    style="text-decoration: none; color: black;">+</a>--%>


                                        <button
                                                onclick="handleIncreaseCartItem(${cartItem.productId},${cartItem.quantity})"
                                                style="text-decoration: none; color: black;">+
                                        </button>

                                    </td>

                                </tr>
                            </table>
                        </td>
                        <td id="total-${cartItem.productId}"><fmt:formatNumber
                                value="${cartItem.price*cartItem.quantity}" type="number" maxFractionDigits="0"/>đ
                        </td>
                        <td>
                                <%--      <a href="del-cart?pid=${cartItem.id}"><i class="fa-solid fa-trash-can" ></i> </a></td>--%>
                            <button onclick="handleDeleteCartItem(${cartItem.productId})"><i
                                    class="fa-solid fa-trash-can"></i></button>
                    </tr>
                    <c:set var="firstProductId" value="${cartItem.productId}"/>

                </c:forEach>
                <tr>
                    <th colspan="7">
                        <div class="Totalpayment">
                            <div class="Shopping_Cartlist_Content_Totalpayment">Tổng tiền thanh toán:</div>
                            <div class="Shopping_Cartlist_Content_Value"><span id="totalPrice">0</span> VNĐ
                            </div>
                        </div>
                    </th>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="d">
    <button type="submit" class=""><a href="showHome">Tiếp tục mua hàng</a></button>
    <button type="submit" class="ContinueButton" id="ContinueButton" onclick="showPay()">Thanh toán ngay</button>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq"
        crossorigin="anonymous"></script>

<script>
    function showPay() {
        const userId = "${sessionScope.user.id}";
        const selectedCartItem  = [];

        document.querySelectorAll(".product-checkbox:checked").forEach(checkbox => {
            const productId = checkbox.value;
            const productQuantity = document.getElementById('cartItemQuantity-' + productId);
            const quantity = parseInt(productQuantity.textContent);
            selectedCartItem.push("cart" + encodeURIComponent("[") + productId + encodeURIComponent("]") + "=" + quantity);
        })
        if (selectedCartItem.length === 0) {
            alert("Vui lòng chọn ít nhất một sản phẩm.");
            return;
        }
        const encodedQuery = selectedCartItem.join("&");
        const queryString = `uId=` + userId + `&` + encodedQuery;
        window.location.href = "showPay?" + queryString;
    }

    document.addEventListener("DOMContentLoaded", function () {
        const checkBoxes = document.querySelectorAll(".product-checkbox");
        const totalPriceCheckBox = document.getElementById("totalPrice");
        checkBoxes.forEach(checkBox => {
            checkBox.addEventListener("change", updateTotalPrice);
        });

        function updateTotalPrice() {
            let total = 0;
            checkBoxes.forEach(checkbox => {
                if(checkbox.checked) {
                    const productId = checkbox.value;
                    const productQuantity = document.getElementById('cartItemQuantity-' + productId);
                    const quantity = parseInt(productQuantity.textContent);
                    const productPrice = parseFloat(checkbox.getAttribute("data-price"));
                    total +=  quantity * productPrice;

                }
            });
            totalPriceCheckBox.textContent = total.toLocaleString();
        }
    })


    function handleIncreaseCartItem(productId, quantity) {
        const quantitySpan = document.getElementById('cartItemQuantity-' + productId);
        let currentQuantity = parseInt(quantitySpan.textContent);

        const newQuantity = currentQuantity + 1;
        $.ajax({
            url: "/web/UpdateCart",
            type: "POST",
            data: JSON.stringify({
                productId: productId,
                quantity: newQuantity
            }),
            success: function () {
                const cartItemQuantity = document.getElementById('cartItemQuantity-' + productId);
                console.log('Found element:', cartItemQuantity);
                if (cartItemQuantity) {

                    cartItemQuantity.textContent = newQuantity;
                }

                updateQuantityWithPrice(productId);
                console.log('cat nhat thành công');
            },
            error: function () {
                console.log('Đã có lỗi xảy ra khi cat nhat');
            }
        })
    }

    function handleDecreaseCartItem(productId, quantity) {
        const quantitySpan = document.getElementById('cartItemQuantity-' + productId);
        let currentQuantity = parseInt(quantitySpan.textContent);

        const newQuantity2 = currentQuantity - 1;
        if (newQuantity2 < 1) {
            $.ajax({
                url: "/web/del-cart",
                type: "POST",
                data: JSON.stringify({
                    productId: productId
                }),
                success: function () {
                    const row = document.getElementById('row-' + productId);
                    if (row) row.remove();
                    console.log('Xoá thành công');
                },
                error: function () {
                    console.log('Đã có lỗi xảy ra khi xoá');
                }
            })
        } else {

            $.ajax({
                url: "/web/UpdateCart",
                type: "POST",
                data: JSON.stringify({
                    productId: productId,
                    quantity: newQuantity2
                }),
                success: function () {
                    const cartItemQuantity = document.getElementById('cartItemQuantity-' + productId);
                    console.log('Found element:', cartItemQuantity);
                    if (cartItemQuantity) {

                        cartItemQuantity.textContent = newQuantity2;
                    }
                    updateQuantityWithPrice(productId);
                    console.log('cat nhat thành công');
                },
                error: function () {
                    console.log('Đã có lỗi xảy ra khi cat nhat');
                }
            })

        }

    }

    function updateQuantityWithPrice(productId) {
        const price = document.getElementById('price-' + productId).textContent;
        const quantity = document.getElementById('cartItemQuantity-' + productId).textContent;
        console.log('price', price);
        console.log('quantity', quantity);
        const cleanNumber = parseInt(price.replace(/\./g, '').replace(/[^\d]/g, ''));

        let totalPriceAndQuantity = cleanNumber * quantity
        console.log('totalPriceQuantity', totalPriceAndQuantity);
        const formatted = new Intl.NumberFormat('vi-VN').format(totalPriceAndQuantity) + 'đ';

        document.getElementById('total-' + productId).textContent = formatted;
    }

    function handleDeleteCartItem(productId) {
        $.ajax({
            url: "/web/del-cart",
            type: "POST",
            data: JSON.stringify({
                productId: productId
            }),
            success: function () {
                const row = document.getElementById('row-' + productId);
                if (row) row.remove();
                console.log('Xoá thành công');
            },
            error: function () {
                console.log('Đã có lỗi xảy ra khi xoá');
            }
        })
    }
</script>
</html>
