<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 1/6/2025
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>allProduct</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
       <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>


<%--<header class="header" id="header">--%>
<%--    <div class="container1">--%>
<%--        <a href="/web/showHome"><img id="logo" src="Img/snapedit_1730861562696.png" alt="Shopping Cart Image"--%>
<%--                                     style="width: 150px"></a>--%>
<%--        <form action="${pageContext.request.contextPath}/search" method="get" id="search-box10">--%>
<%--            <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">--%>
<%--        </form>--%>
<%--        <i class="fas fa-phone"></i>--%>
<%--        <div class="headerphone">HOTLINE: 0327237467</div>--%>
<%--        <c:set var="currentUser" value="${sessionScope.user}" /> <!-- Lấy user từ session -->--%>
<%--        <c:choose>--%>
<%--            <c:when test="${not empty currentUser}">--%>
<%--                <a href="showCustomerPage?uId=${sessionScope.user.id}" style="text-decoration: none">--%>
<%--                    <div class="headercontendangnhap">--%>
<%--                            ${not empty currentUser.username ? currentUser.username : currentUser.given_name}--%>
<%--                    </div>--%>
<%--                </a>--%>
<%--                <div class="line"></div>--%>
<%--                <a href="logout" style="text-decoration: none">--%>
<%--                    <div class="headercontendangki">Đăng Xuất</div>--%>
<%--                </a>--%>
<%--            </c:when>--%>
<%--            <c:otherwise>--%>
<%--                <a href="showLogin" style="text-decoration: none">--%>
<%--                    <div class="headercontendangnhap">--%>
<%--                        Đăng Nhập--%>
<%--                    </div>--%>
<%--                </a>--%>
<%--                <div class="line"></div>--%>
<%--                <a href="showLogin" style="text-decoration: none">--%>
<%--                    <div class="headercontendangki">Đăng Kí</div>--%>
<%--                </a>--%>
<%--            </c:otherwise>--%>
<%--        </c:choose>--%>

<%--        <!--        gio hang-->--%>
<%--        <div class="shopping_cart">--%>
<%--            <div class="shopping_cart_swap">--%>
<%--                <i class="fa-solid fa-basket-shopping"></i>--%>
<%--                <span class="shopping_notice">${sessionScope.cart!=null?sessionScope.cart.totalQuantity:0}</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="shoppingtext"><a href="ShowCart">Giỏ hàng</a></div>--%>
<%--    </div>--%>
<%--</header>--%>


<jsp:include page="Header.jsp" />

<div class="container-fluid " style="padding-top: 20px; padding-bottom: 20px; background-color: #e0e5dc;">
    <div class="row" style="">
        <div class="col-lg-3 d-flex align-items-center  " style=" gap: 10px; justify-content: center">
            <a href="showAll"><i class="fa-brands fa-product-hunt" id="all" style="font-size: 25px;"></i>
                <span style="text-decoration: none; color: black">TẤT CẢ SẢN PHẨM</span>
            </a>
        </div>

        <div class="col-lg-3 d-flex align-items-center  " style="justify-content: center; gap: 10px">
            <a href="showVegetables"><i class="fa-solid fa-leaf" id="ves" style="font-size: 25px;"></i>
                <span style="text-decoration: none; color: black">RAU</span></a>
        </div>
        <div class="col-lg-3 d-flex align-items-center  " style="justify-content: center; gap:10px;">
            <a href="showTubers"><i class="fa-solid fa-carrot" id="root" style="font-size: 25px; "></i>
                <span style="text-decoration: none; color: black">CỦ</span>
            </a>
        </div>
        <div class="col-lg-3 d-flex align-items-center  " style="justify-content: center; gap: 10px">
            <a href="showFruits"><i class="fa-solid fa-apple-whole" id="fruit" style="font-size: 25px;"></i>
                <span style="text-decoration: none; color: black">QUẢ</span></a>
        </div>
    </div>
</div>


<div class="container" >
    <div class="row">
        <div class="col-12">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <c:forEach var="item" items="${listMainBanner}" varStatus="status">
                        <li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}"></li>
                    </c:forEach>
                </ol>
                <div class="carousel-inner">
                    <c:forEach var="item" items="${listMainBanner}" varStatus="status">

                        <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                            <img src="${item}" class="d-block w-100" alt="Banner ${status.index + 1}">
                        </div>
                    </c:forEach>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>

<div id="allProduct">
    <div class="filter">
        <div class="left">
            <h6>Bạn đang xem: </h6>
            <div class="pro-cat"><h6>Sản Phẩm</h6></div>
        </div>
        <div class="right">
            <button class="newOption" id="optionNewOption">
                <h6>
                    <span id="selected-option">Mặc định</span>
                    <i class="fa-solid fa-chevron-down"></i>
                </h6>
            </button>
            <div class="newOption drop-menu">
                <ul class="newOption dropdown-menu123">
                    <li data-value="Mặc định" data-link="allProduct.html">Mặc định</li>
                    <li data-value="Giá giảm dần" data-link="allProduct-desc.html">Giá giảm dần</li>
                    <li data-value="Giá tăng dần">Giá tăng dần</li>
                </ul>
            </div>
            <form id="sortForm" action="showAll" method="GET" style="display:none;">
                <input type="hidden" name="sortProduct" value="Mặc định" id="sortProductInput">
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
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <button class="add-to-cart"
                                        onclick="handleAddToCart(${sessionScope.user.id},${p.id})">Thêm
                                </button>

                            </c:when>
                            <c:otherwise>
                                <button class="add-to-cart"
                                        onclick="handleAddToCart(null,${p.id})">Thêm
                                </button>
                            </c:otherwise>

                        </c:choose>

                        <a href="showDetail?id=${p.id}" class="buy-now">Xem</a>

                        <fmt:parseDate value="${p.extraDay}" pattern="yyyy-MM-dd" var="dateAdded" />
                        <c:set var="now" value="<%= new java.util.Date() %>" />
                        <c:set var="diff" value="${now.time - dateAdded.time}" />
                        <c:set var="days" value="${diff / (1000 * 60 * 60 * 24)}" />

                        <c:choose>
                            <c:when test="${days > 3}">
                                <img src="Img/new.png"  class="newProduct" style="display: none;"/>
                            </c:when>
                            <c:otherwise>
                                <img src="Img/new.png"  class="newProduct"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="product-info">
                        <c:set var="categoryHref">
                            <c:choose>
                                <c:when test="${p.category == 1}">showVegetables</c:when>
                                <c:when test="${p.category == 2}">showTubers</c:when>
                                <c:when test="${p.category == 3}">showFruits</c:when>
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
            <c:forEach begin="1" end="${endPage}" var="i">
                <li>
                    <button class="btn-1" type="button" onclick="location.href='showAll?index=${i}&sortProduct=${sortProduct}'">${i}</button>
                </li>
            </c:forEach>

        </ul>
    </div>
</div>
<jsp:include page="Footer.jsp" />

<div id="backtop">
    <i class="fa-solid fa-arrow-up"></i>
</div>
<div class="modal fade" id="addToCartModalAlert" tabindex="-1" aria-labelledby="loginAlertModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginAlertModalLabel">Thông báo</h5>
            </div>
            <div class="modal-body">
                Bạn cần đăng nhập để thêm vào giỏ hàng!
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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

    function handleAddToCart(Id, productId) {
        if (Id === null) {
            const loginModal = new bootstrap.Modal(document.getElementById('addToCartModalAlert'));
            loginModal.show();

            setTimeout(() => {
                loginModal.hide();
            }, 3000);
        } else {
            $.ajax({
                url: "/web/add-cart",
                method: 'POST',
                data: JSON.stringify(
                    {
                        userId: Id,
                        productId: productId
                    }
                ),
                success: function (reponse) {
                    document.querySelector("#addToCartModalAlert .modal-body").textContent = "Thêm vào giỏ hàng thành công"
                    const loginModal = new bootstrap.Modal(document.getElementById('addToCartModalAlert'));

                    loginModal.show();

                    setTimeout(() => {
                        loginModal.hide();
                    }, 3000);
                    $.ajax({
                        url: "/web/TotalQuantity",
                        type: "GET",
                        contentType: "application/json",
                        success: function (data) {
                            let totalQuantity = document.getElementById("totalQuantityCartItem");
                            totalQuantity.textContent = data;
                        }
                    });
                }
            })
        }
    }

    document.querySelectorAll(".newOption.dropdown-menu123 li").forEach(item => {
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
    const menu = document.querySelector(".drop-menu.newOption");
    const option = document.getElementById("optionNewOption");
    document.getElementById("optionNewOption").addEventListener("click", function (e) {
        e.stopPropagation(); // Ngăn chặn sự kiện nổi lên
        document.querySelector(".drop-menu.newOption").classList.toggle("show");
    });

    // Đóng menu nếu người dùng nhấp ra ngoài
    document.addEventListener("click", function  (event) {
        if (!option.contains(event.target) && !menu.contains(event.target)) {
            menu.classList.remove("show");
        }
    });
    document.addEventListener("DOMContentLoaded", function () {
        const selectedValue = "${param.sortProduct != null ? param.sortProduct : 'Mặc định'}";
        document.getElementById("selected-option").textContent = selectedValue;
        document.getElementById("sortProductInput").value = selectedValue; // Cập nhật giá trị input
    });

    console.log('check',${item})
    menu.querySelectorAll("li").forEach(item => {
        item.addEventListener("click", function () {
            console.log("Clicked:", this.getAttribute("data-value")); // Log giá trị được chọn
            // Các dòng còn lại...
        });
    });
</script>
</html>