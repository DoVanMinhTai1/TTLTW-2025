<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/6/2024
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Page</title>
    <link rel="stylesheet" href="../Css/Customer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="../Js/Customer.js" defer></script>
</head>
<body>
<div class="CustomerPage">
    <div class="Header">
        <div class="Container">
            <a href="../home.html"><img id="logo" src="../Img/snapedit_1730861562696.png" alt="Shopping Cart Image" style="width: 150px"></a>
            <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">
            <i class="fas fa-phone"></i>
            <div class="headerphone">HOTLINE: 0327237467</div>
            <div class="headercontendangnhap">Đăng nhập</div>
            <div class="line"></div>
            <div class="headercontendangki">Đăng kí</div>
            <div class="shopping_cart">
                <div class="shopping_cart_swap">
                    <i class="fa-solid fa-basket-shopping"></i>
                </div>
            </div>
            <div class="shoppingtext"><a href="../ShoppingCart/ShoppingCart.html">Giỏ hàng</a></div>
        </div>
    </div>
    <div class="pathline"></div>
    <div class="CustomerPageConttent">
        <div class="Container">
            <div class="CustomerPageConttentHeader">
                <span>Trang chủ</span>
                <span>/</span>
                <span class="text">Trang khách hàng</span>
            </div>
            <div class="CustomerPageConttenNavigationbar">
                <div class="NavigationbarTitle">TRANG TÀI KHOẢN</div>
                <div class="NavigationbarHello">Xin chào, <span>Nguyễn Vỹ</span> !</div>
                <div class="NavigationbarSelect" id="option1" onclick="navigationbarClick('option1')">Thông tin tài
                    khoản
                </div>
                <div class="NavigationbarSelect" id="option2" onclick="navigationbarClick('option2')">Đơn hàng của bạn
                </div>
                <div class="NavigationbarSelect" id="option3" onclick="navigationbarClick('option3')">Đổi mật khẩu</div>
                <div class="NavigationbarSelect" id="option4" onclick="navigationbarClick('option4')">Sổ địa chỉ</div>
                <div class="NavigationbarSelect" id="option5" onclick="navigationbarClick('option5')">Đăng xuất</div>
            </div>
            <div class="CustomerPageConttenNavigationbarContent" id="CustomerPageConttenNavigationbarContent">
                <div class="AccountInformation">
                    <div class="AccountInformationTitle">THÔNG TIN TÀI KHOẢN</div>
                    <div class="AccountInformationContent">Họ tên: <span>Nguyễn Vỹ</span></div>
                    <div class="AccountInformationContent">Email: <span>nguyenvy310804@gmail.com</span></div>
                    <div class="AccountInformationContent">Điện thoại: <span>0327237467</span></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
