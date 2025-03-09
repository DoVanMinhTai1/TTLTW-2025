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
    <title>ShoppingCart</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        html, body {
            padding: 0;
            margin: 0;
            font-size: 16px;
            color: black;
            font-family: sans-serif, Tahoma;
        }

        .container {
            width: 1200px;
            margin-left: auto;
            margin-right: auto;
        }

        .header .container {
            display: flex;
        }

        #search {
            width: 300px;
            height: 30px;
            border: solid 1px gray;
            border-radius: 35px;
            padding-left: 15px;
            margin: 20px 50px 0 130px;
        }

        .fas {
            margin-right: 5px;
            margin-top: 30px;
            color: #7cc652;
        }

        .headerphone {
            margin: 30px 50px 0 0;
            color: #7cc652;
        }

        .headercontendangnhap {
            margin: 30px 5px;
            color: gray;
        }

        .headercontendangki {
            margin: 30px 30px 0 5px;
            color: gray;
        }

        .line {
            border: solid 1px gray;
            height: 15px;
            margin-top: 30px;
        }

        .fa-solid {
            color: #7cc652;

        }

        .shoppingtext {
            margin-top: 30px;
            color: #7cc652;
        }

        .pathline {
            width: 100%;
            border: solid 1px gray;
        }

        /*su li gio hang khi di chuot vo gio haang*/
        .shopping_cart {
            height: 30px;
            margin: 30px 5px 0 0;
            padding: 0 5px;
        }

        .shopping_cart_swap {
            display: inline-block;
            position: relative;
            cursor: pointer;
        }

        .shopping_cartlist {
            position: absolute;
            top: 150%;
            right: -15px;
            background-color: #f5f5f5;;
            /*background-color: white;*/
            width: 300px;
            border-radius: 5px;
            box-shadow: 0 1px 3.125rem 0 rgba(0, 0, 0, 0.2);
            display: none;
            cursor: default;
        }

        .shopping_cart_swap:hover .shopping_cartlist {
            display: block;
        }

        .shopping_notice {
            position: absolute;
            top: -13px;
            left: 15px;
            padding: 2px 5px;
            color: white;
            font-size: 0.6rem;
            background-color: #00845c;
            border-radius: 8px;
            border: 1px solid green;
        }


        .shopping_cartlist::after {
            content: "";
            position: absolute;
            right: 9px;
            top: -20px;
            border-width: 10px 15px;
            border-style: solid;
            border-color: transparent transparent white transparent;
            cursor: pointer;
        }

        /*Trong trươờng hợp co  .shopping_cartlist--no_cart*/
        .shopping_cartlist--no_cart .imgno_cart {
            display: block;
        }

        .imgno_cart {
            width: 70%;
            margin-left: 42px;
            display: none;
        }

        .shopping_cartlist--no_cart {
            padding: 20px 0;
        }

        .shopping_cartlist_item {
            display: flex;
            padding: 30px 0;
            border-bottom: 1px dashed #ebebeb
        }

        .header_Shop {
            text-align: center;
            background-color: #7cc652;
            padding: 10px 0;
        }

        .shopping_cartlist_header {
            color: white;
        }

        .header_Shop .headeritem {
            font-size: 20px;
            color: white;

        }

        .shopping_cartlist_List_item {
            padding: 0;
            list-style: none;
            margin: 0;
            max-height: 300px; /* Chiều cao tối đa hiển thị, tùy chỉnh theo ý muốn */
            overflow-y: auto;
        }

        .shopping_cartlist_item_imgnotice {
            position: relative;
        }

        .shopping_cartlist_item_notice {
            position: absolute;
            top: -10px;
            left: 45px;
            padding: 2px 5px;
            color: white;
            font-size: 0.6rem;
            background-color: #00845c;
            border-radius: 8px;
            border: 1px solid green;
        }

        .shopping_cartlist_img_item {
            margin: 0 5px;
            width: 50px;
            border: solid 0.5px gainsboro;
            margin-top: 2px;
            cursor: pointer;
        }

        .shopping_cartlist_item_name {
            display: inline-block;
            width: 190px;
            margin-right: 5px;
        }

        .fa-trash-can {
            color: #00845c;
            cursor: pointer;
        }

        .shopping_cartlist_item_body {
            margin: 5px 0;
        }

        .shopping_cartlist_item_plus {
            display: inline-block;
            padding: 0 0 5px 4px;
            background-color: #cde2c0;
            width: 12px;
            height: 13px;
            border-radius: 100%;
            cursor: pointer;
        }

        .shopping_cartlist_item_price {
            color: #00845c;
            font-weight: 600;
        }

        .shopping_cartlist_item_quantity {
            color: #00845c;
            font-weight: 600;
        }

        .shopping_cartlist_item_minus {
            display: inline-block;
            padding: 0 0 5px 4px;
            background-color: #cde2c0;
            width: 12px;
            height: 13px;
            border-radius: 100%;
            cursor: pointer;
        }

        .shopping_cartlist_button {
            float: right;
            margin: 10px 10px 10px 0;
            background-color: #7cc652;
            color: white;
            border: none;
            height: 30px;
            width: 70px;
            border-radius: 30px;
            cursor: pointer;
        }

        .Navigationbar {
            margin: 15px auto 30px auto;
        }

        .NavigationbarHome {
            color: gray;
        }

        .Navigationbar\/ {
            margin: 0 10px;
        }

        .NavigationbarShoppingCard {
            color: #7cc652;
        }

        .Shopping_Cartlist_Content_Text {
            font-size: 20px;
            font-weight: 600;
            margin-bottom: 15px;
        }

        .Shopping_Cartlist_Content_ImgRauCuQua {
            width: 128px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid gainsboro;
        }

        th:nth-child(1), td:nth-child(1), th:nth-child(3), td:nth-child(3), th:nth-child(4), td:nth-child(4), th:nth-child(5), td:nth-child(5) {
            width: 133px;
            text-align: center;
        }

        th:nth-child(2), td:nth-child(2) {
            width: 488px;
            text-align: center;
        }

        th:nth-child(6), td:nth-child(6) {
            width: 48px;
            text-align: center;
        }

        .Totalpayment {
            padding-left: 800px;
        }

        .Shopping_Cartlist_Content_Totalpayment {
            display: inline-block;
        }

        .Shopping_Cartlist_Content_Value {
            display: inline-block;
            margin-left: 10px;
        }

        .d {
            height: 80px;
            margin-right: 180px;
        }
        .d a{
            text-decoration: none;
            color: white;
        }
        .d button {
            float: right;
            margin-top: 20px;
            margin-left: 5px;
            height: 40px;
            width: 170px;
            padding: 6px;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            background-color: #7cc652;
            color: white;
        }
        .d .ContinueButton{
            background-color: #00845c;
        }
        .d button:hover {
            background-color: #f5af1a
        }


    </style>
</head>
<body>
<div class="header" id="header">
    <div class="header container">
        <img id="logo" src="../Img/snapedit_1730861562696.png" alt="Shopping Cart Image" style="width: 150px">
        <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">
        <i class="fas fa-phone"></i>
        <div class="headerphone">HOTLINE: 0327237467</div>
        <div class="headercontendangnhap">Đăng nhập</div>
        <div class="line"></div>
        <div class="headercontendangki">Đăng kí</div>
        <!--        gio hang-->
        <div class="shopping_cart">
            <div class="shopping_cart_swap">
                <i class="fa-solid fa-basket-shopping"></i>
                <span class="shopping_notice">3</span>
                <!-- khi nào giỏ hàng khong co gi thi them vo shopping_cartlist--no_cart-->
                <div class="shopping_cartlist ">
                    <img src="ảnh giỏ hàng trống.jpg" alt="" class="imgno_cart">
                    <div class="header_Shop">
                        <i class="fa-solid fa-basket-shopping headeritem"></i>
                        <span class="shopping_cartlist_header">GIỎ HÀNG</span>
                    </div>
                    <ul id="cartItems" class="shopping_cartlist_List_item">
                        <li class="shopping_cartlist_item">
                            <div class="shopping_cartlist_item_imgnotice">
                                <img src="../Img/bap.webp" alt="" class="shopping_cartlist_img_item">
                                <span class="shopping_cartlist_item_notice">3</span>
                            </div>
                            <div class="shopping_cartlist_item_info">
                                <div class="shopping_cartlist_item_header">
                                    <div class="shopping_cartlist_item_name">Băp ngọt</div>
                                    <i class="fa-solid fa-trash-can"></i>
                                </div>
                                <div class="shopping_cartlist_item_body">
                                    <span class="shopping_cartlist_item_price">150.000đ</span>
                                </div>
                                <div class="shopping_cartlist_item_fotter">
                                    <div class="shopping_cartlist_item_plus">+</div>
                                    <span class="shopping_cartlist_item_quantity">1</span>
                                    <div class="shopping_cartlist_item_minus">-</div>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <button type="submit" class="shopping_cartlist_button"> Đặt Hàng</button>
                </div>
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
                    <th>Hình ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Đơn Giá</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                    <th>Xóa</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><img src="../Img/RauMuong.webp" alt="" class="Shopping_Cartlist_Content_ImgRauCuQua"></td>
                    <td>Rau Muống</td>
                    <td>10.000đ</td>
                    <td>
                        <table>
                            <tr>
                                <td>+</td>
                                <td>1</td>
                                <td>-</td>
                            </tr>
                        </table>
                    </td>
                    <td>10.000đ</td>
                    <td><i class="fa-solid fa-trash-can" onclick="deleteProduct(this)"></i></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="d">
    <button type="submit" class=""><a href="../home.html">Tiếp tục mua hàng</a> </button>
    <button type="submit" class="ContinueButton" id="ContinueButton">Thanh toán ngay</button>
</div>
</body>
</html>
