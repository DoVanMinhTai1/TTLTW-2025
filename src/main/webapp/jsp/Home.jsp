<%@ page import="vn.edu.hcmuaf.fit.projectwebck.dao.model.User" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 1/6/2025
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>home</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
<header class="header" id="header">
  <div class="container1">
    <a href="/web/showHome"><img id="logo" src="Img/snapedit_1730861562696.png" alt="Shopping Cart Image"
                             style="width: 150px"></a>
    <form action="" id="search-box10">
      <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">
    </form>
    <i class="fas fa-phone"></i>
    <div class="headerphone">HOTLINE: 0327237467</div>
    <c:set var="currentUser" value="${sessionScope.user}" /> <!-- Lấy user từ session -->
      <c:choose>
        <c:when test="${not empty currentUser}">
          <a href="CustomerPage/CustomerPage.html" style="text-decoration: none">
            <div class="headercontendangnhap">
            ${currentUser.username}
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
    <div class="shoppingtext"><a href="ShowCart">Giỏ hàng</a></div>
  </div>
</header>
<div class="menu">
  <ul>
    <li>
      <a href="/web/showAll"><i class="fa-brands fa-product-hunt" id="all"></i></a>
      <div class="all">
        <a href="/web/showAll"><span>TẤT CẢ SẢN PHẨM</span></a>
      </div>
    </li>

    <li>
      <a href="/web/showVegetables"><i class="fa-solid fa-leaf" id="ves"></i></a>
      <div class="vegetables">
        <a href="/web/showVegetables"><span>RAU</span></a>
      </div>
    </li>
    <li>
      <a href="/web/showTubers"><i class="fa-solid fa-carrot" id="root"></i></a>
      <div class="tubers">
        <a href="/web/showTubers"><span>CỦ</span></a>
      </div>
    </li>
    <li>
      <a href="/web/showFruits"><i class="fa-solid fa-apple-whole" id="fruit"></i></a>
      <div class="vegetable_fruits">
        <a href="/web/showFruits"><span>QUẢ</span></a>
      </div>
    </li>
  </ul>
</div>
<main class="main" id="main">
  <div class="banner">
    <img src="Img/banner.png" alt="" class="image_banner">
  </div>
  <ul class="intro">
    <li>
      <div class="delivery">
        <i class="fa-solid fa-truck" id="truck"></i>
        <h5>FREE SHIP</h5>
        <p>Miễn phí vận chuyển</p>
      </div>
    </li>
    <li>
      <div class="return">
        <i class="fa-solid fa-right-left" id="back"></i>
        <h5>HOÀN TRẢ</h5>
        <p>Miễn phí vận chuyển</p>
      </div>
    </li>
    <li>
      <div class="payment">
        <i class="fa-regular fa-credit-card" id="pay"></i>
        <h5>THANH TOÁN</h5>
        <p>Miễn phí vận chuyển</p>
      </div>
    </li>
    <li>
      <div class="help">
        <i class="fa-solid fa-headset" id="listen"></i>
        <h5>HỖ TRỢ</h5>
        <p>Miễn phí vận chuyển</p>
      </div>
    </li>
  </ul>
  <div id="bestSeller">
    <div id="bestSellerProduct">
      <div class="headline">
        <h3>SẢN PHẨM BÁN CHẠY</h3>
      </div>
      <ul class="products">
        <c:forEach var="p" items="${listProductBestSeller}">
          <li>
            <div class="product-item">
              <div class="product-top">
                <a href="" class="product-thumb">
                  <img src="${p.image}" alt=""/>
                </a>
                <!--xem ngay-->
                <a href="detailProduct?id=${p.id}" class="buy-now">Xem ngay</a>
              </div>
              <div class="product-info">
                <a href="" class="product-cat">${p.category}</a>
                <a href="" class="product-name">${p.name}</a>
                <div class="product-price">${p.price}đ</div>
              </div>
            </div>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <div class="decor">
    <img src="Img/decor1.webp" alt="">
    <img src="Img/decor2.webp" alt="">
  </div>
  <div id="listProduct">
    <div class="headline-listProduct">
      <h3>DANH SÁCH SẢN PHẨM</h3>
    </div>
    <ul class="products">
      <c:forEach var="p" items="${allProduct}">
        <li>
          <div class="product-item">
            <div class="product-top">
              <a href="showDetail?id=${p.id}" class="product-thumb">
                <img src="${p.image}" alt=""/>
              </a>
              <!--xem ngay-->
              <a href="showDetail?id=${p.id}" class="buy-now">Xem ngay</a>
            </div>
            <div class="product-info">
              <a href="" class="product-cat">${p.category}</a>
              <a href="showDetail?id=${p.id}" class="product-name">${p.name}</a>
              <div class="product-price">${p.price}đ</div>
            </div>
          </div>
        </li>
      </c:forEach>
    </ul>
    <button id="more">
      <p>Xem Thêm</p>
    </button>
  </div>
</main>

<footer class="footer">
  <section class="head-footer" style="background-color: #E0E5DC; border-bottom: 1px solid #B1B1B1">
    <div class="head-text">
      <span>KẾT NỐI VỚI CHÚNG TÔI TRÊN MẠNG XÃ HỘI:</span>
    </div>
    <div class="icon">
      <a href="https://www.facebook.com/">
        <i class="fab fa-facebook-f"></i>
      </a>
      <a href="https://x.com/">
        <i class="fab fa-twitter"></i>
      </a>
      <a href="https://accounts.google.com/InteractiveLogin/signinchooser?ifkv=AcMMx-eAnI_k5fnUiekh_ZIVLJbaydtwEkoPzxadnu-8S0hZHL_JYdaDtvE4CMit7jumqRy6ZsBxzQ&ddm=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin">
        <i class="fab fa-google"></i>
      </a>
      <a href="https://www.instagram.com/">
        <i class="fab fa-instagram"></i>
      </a>
      <a href="https://www.linkedin.com/login">
        <i class="fab fa-linkedin"></i>
      </a>
      <a href="https://github.com/login">
        <i class="fab fa-github"></i>
      </a>
    </div>
  </section>
  <section class="main-footer">
    <!--    <div class="container text-center text-md-start mt-5">-->
    <!--      <div class="row mt-3">-->
    <div class="main-footer-column">
      <h6 class="footer-quotes">Nông Lâm Food</h6>
      <p>
        Luôn nỗ lực mang đến cho người tiêu dùng Việt Nam những sản phẩm sạch sẽ, đầy dinh dưỡng, được
        trồng theo phương pháp khoa học.
      </p>
    </div>
    <div class="footer-contact">
      <a href="contact.html"><h6 class="">LIÊN HỆ TẠI ĐÂY</h6></a>
    </div>
    <div class="footer-in4">
      <h6 class="">THÔNG TIN LIÊN HỆ</h6>
      <p><i class="fas fa-home mr-3"></i>Khu phố 6, phường Linh Trung, thành phố Thủ Đức, Thành
        phố Hồ Chí Minh, Việt Nam</p>
      <p><i class="fas fa-envelope mr-3"></i>22130322@st.hcmuaf.edu.vn</p>
      <p><i class="fas fa-phone mr-3"></i> + 84 327 237 467</p>
    </div>
    <!--      </div>-->
    <!--    </div>-->
  </section>
  <div class="text-bottom" style="background-color: #E0E5DC">
    © 2024 Copyright: Group03
  </div>
</footer>
<div id="backtop">
  <i class="fa-solid fa-arrow-up"></i>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script>
  $(document).ready(function () {
    $(window).scroll(function () {
      if ($(this).scrollTop()) {
        $('#backtop').fadeIn();
      } else {
        $('#backtop').fadeOut();
      }
    })
    $('#backtop').click(function () {
      $('html, body').animate({scrollTop: 0}, 300);
    });
  })
  $('#more').click(function () {
    window.location.href = "allProduct.html";
  });

</script>
</body>
</html>
