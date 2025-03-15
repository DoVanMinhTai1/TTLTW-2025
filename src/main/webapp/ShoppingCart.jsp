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
    <a href="showHome"><img id="logo" src="Img/snapedit_1730861562696.png" alt="Shopping Cart Image" style="width: 150px"> </a>
    <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">
    <i class="fas fa-phone"></i>
    <div class="headerphone">HOTLINE: 0327237467</div>
    <c:set var="currentUser" value="${sessionScope.user}" /> <!-- Lấy user từ session -->
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
          <th>Hình ảnh</th>
          <th>Tên sản phẩm</th>
          <th>Đơn Giá</th>
          <th>Số lượng</th>
          <th>Thành tiền</th>
          <th>Xóa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.cart.list}" var="p">
        <tr>
          <td><img src="${p.img}" alt="" class="Shopping_Cartlist_Content_ImgRauCuQua"></td>
          <td>${p.name}</td>
          <td><fmt:formatNumber value="${p.price}" type="number" maxFractionDigits="0" />đ</td>
          <td>
            <table>
              <tr>
                <td><a href="UpdateCart?pid=${p.id}&quantity=${p.quantity}&expression=plus" style="text-decoration: none; color: black;">+</a></td>
                <td><span id="quantity-${p.id}">${p.quantity}</span></td>
                <td><a href="UpdateCart?pid=${p.id}&quantity=${p.quantity}&expression=minus" style="text-decoration: none; color: black;">-</a></td>
              </tr>
            </table>
          </td>
          <td id="total-${p.id}"><fmt:formatNumber value="${p.price*p.quantity}" type="number" maxFractionDigits="0" />đ</td>
          <td>   <a href="del-cart?pid=${p.id}"><i class="fa-solid fa-trash-can" ></i> </a></td>
        </tr>
        </c:forEach>
        <tr>
          <th colspan="6">
            <div class="Totalpayment">
              <div class="Shopping_Cartlist_Content_Totalpayment">Tổng tiền thanh toán:</div>
              <div class="Shopping_Cartlist_Content_Value"><fmt:formatNumber value="${sessionScope.total}" type="number" maxFractionDigits="0" />đ</div>
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
  <button type="submit" class="ContinueButton" id="ContinueButton"><a href="showPay?uId=${sessionScope.user.id}">Thanh toán ngay</a></button>
</div>
</body>
</html>
