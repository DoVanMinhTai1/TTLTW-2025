<%--
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
    <title>Vegetables</title>
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
        <a href="showHome"><img id="logo" src="Img/snapedit_1730861562696.png" alt="Shopping Cart Image"
                                     style="width: 150px"></a>
        <form action="${pageContext.request.contextPath}/search" method="get" id="search-box10">
            <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">
        </form>
        <i class="fas fa-phone"></i>
        <div class="headerphone">HOTLINE: 0327237467</div>
        <c:set var="currentUser" value="${sessionScope.user}" /> <!-- Lấy user từ session -->
        <c:choose>
            <c:when test="${not empty currentUser}">
                <a href="showCustomerPage?uId=${sessionScope.user.id}" style="text-decoration: none">
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
            <a href="showAll"><i class="fa-brands fa-product-hunt" id="all"></i></a>
            <div class="all">
                <a href="showAll"><span>TẤT CẢ SẢN PHẨM</span></a>
            </div>
        </li>

        <li>
            <a href="showVegetables"><i class="fa-solid fa-leaf" id="ves"></i></a>
            <div class="vegetables">
                <a href="showVegetables"><span>RAU</span></a>
            </div>
        </li>
        <li>
            <a href="showTubers"><i class="fa-solid fa-carrot" id="root"></i></a>
            <div class="tubers">
                <a href="showTubers"><span>CỦ</span></a>
            </div>
        </li>
        <li>
            <a href="showFruits"><i class="fa-solid fa-apple-whole" id="fruit"></i></a>
            <div class="vegetable_fruits">
                <a href="showFruits"><span>QUẢ</span></a>
            </div>
        </li>
    </ul>
</div>
<div id="allProduct">
    <div class="filter">
        <div class="left">
            <h6>Bạn đang xem: </h6>
            <div class="pro-cat"><h6>Rau</h6></div>
        </div>
        <div class="right">
            <button id="option">
                <h6>
                    <span  id="selected-option">Mặc định</span>
                    <i class="fa-solid fa-chevron-down"></i>
                </h6>
            </button>
            <div class="drop-menu">
                <ul class="dropdown-menu">
                    <li data-value="Mặc định" data-link="allProduct.html"  >Mặc định</li>
                    <li data-value="Giá giảm dần" data-link="allProduct-desc.html">Giá giảm dần</li>
                    <li data-value="Giá tăng dần" data-link="allProduct-asc.html">Giá tăng dần</li>
                </ul>
            </div>
            <form id="sortForm" action="sortV" method="GET" style="display:none;">
                <input type="hidden" name="sortProduct" value="" id="sortProductInput">
            </form>
        </div>
    </div>
    <div class="headline-listProduct">
        <h3>DANH SÁCH SẢN PHẨM</h3>
    </div>
    <ul class="products">
        <c:forEach var="p" items="${listPaging}">
            <li>
                <div class="product-item">
                    <div class="product-top">
                        <a href="showDetail?id=${p.id}" class="product-thumb">
                            <img src="${p.image}" alt=""/>
                        </a>
                        <!--xem ngay-->
                        <a href="addVegetable?pid=${p.id}" class = "add-to-cart">Them</a>
                        <a href="showDetail?id=${p.id}" class="buy-now">Xem ngay</a>
                    </div>
                    <div class="product-info">
                        <c:set var="categoryHref">
                            <c:choose>
                                <c:when test="${p.category == 1}">/web/showVegetables</c:when>
                                <c:when test="${p.category == 2}">/web/showTubers</c:when>
                                <c:when test="${p.category == 3}">/web/showFruits</c:when>
                                <c:otherwise>/khong-xac-dinh</c:otherwise>
                            </c:choose>
                        </c:set>
                        <a href="${categoryHref}" class="product-cat"> <c:choose>
                            <c:when test="${p.category == 1}">
                                Rau
                            </c:when>
                            <c:when test="${p.category == 2}">
                                Củ
                            </c:when>
                            <c:when test="${p.category == 3}">
                                Quả
                            </c:when>
                            <c:otherwise>
                                Không xác định
                            </c:otherwise>
                        </c:choose></a>
                        <a href="showDetail?id=${p.id}" class="product-name">${p.name}</a>
                        <div class="product-price">${p.price}đ</div>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
    <div class="button-more">
        <ul class="more-product">
            <c:forEach begin ="1" end = "${endPage}" var="i">
                <li>
                    <button class="btn-1" type="button" onclick="location.href='showVegetables?index=${i}'">${i}</button>
                </li>
            </c:forEach>

        </ul>
    </div>
</div>
<footer class="footer">
    <section class="head-footer" style="background-color: #E0E5DC; border-bottom: 1px solid #B1B1B1">
        <div class="head-text">
            <span>KẾT NỐI VỚI CHÚNG TÔI TRÊN MẠNG XÃ HỘI:</span>
        </div>
        <div class="icon">
            <a href="https://www.facebook.com/">
                <i class="fab fa-facebook-f"></i>
            </a>
            <a href="https://x.com/" >
                <i class="fab fa-twitter"></i>
            </a>
            <a href="https://accounts.google.com/InteractiveLogin/signinchooser?ifkv=AcMMx-eAnI_k5fnUiekh_ZIVLJbaydtwEkoPzxadnu-8S0hZHL_JYdaDtvE4CMit7jumqRy6ZsBxzQ&ddm=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin">
                <i class="fab fa-google"></i>
            </a>
            <a href="https://www.instagram.com/">
                <i class="fab fa-instagram"></i>
            </a>
            <a href="https://www.linkedin.com/login" >
                <i class="fab fa-linkedin"></i>
            </a>
            <a href="https://github.com/login">
                <i class="fab fa-github"></i>
            </a>
        </div>
    </section>
    <section class ="main-footer">
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
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        $(window).scroll(function (){
            if($(this).scrollTop()){
                $('#backtop').fadeIn();
            } else{
                $('#backtop').fadeOut();
            }
        })
        $('#backtop').click(function (){
            $('html, body').animate({scrollTop:0}, 300);
        });
    })

    document.querySelectorAll(".dropdown-menu li").forEach(item => {
        item.addEventListener("click", function () {
            // Lấy giá trị của mục được chọn
            const selectedValue = this.getAttribute("data-value");
            // Thay đổi nội dung trong nút
            document.getElementById("selected-option").textContent = selectedValue;

            // Gán giá trị vào trường ẩn
            document.getElementById("sortProductInput").value = selectedValue;

            // Gửi form tới servlet
            document.getElementById("sortForm").submit();
            // Ẩn menu thả xuống sau khi chọn
            document.querySelector(".drop-menu").classList.remove("show");
        });
    });
    const menu = document.querySelector(".drop-menu");
    const option = document.getElementById("option");
    document.getElementById("option").addEventListener("click", function (e) {
        e.stopPropagation(); // Ngăn chặn sự kiện nổi lên
        document.querySelector(".drop-menu").classList.toggle("show");
    });

    // Đóng menu nếu người dùng nhấp ra ngoài
    document.addEventListener("click", function (event) {
        if (!option.contains(event.target) && !menu.contains(event.target)) {
            menu.classList.remove("show");
        }
    });
    document.addEventListener("DOMContentLoaded", function() {
        const selectedValue = "${param.sortProduct != null ? param.sortProduct : 'Mặc định'}";
        document.getElementById("selected-option").textContent = selectedValue;
        document.getElementById("sortProductInput").value = selectedValue; // Cập nhật giá trị input
    });
</script>
</html>