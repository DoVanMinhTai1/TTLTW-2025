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
    <link rel="stylesheet" href="../Css/ShoppingCart.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
