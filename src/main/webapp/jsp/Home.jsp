<%@ page import="vn.edu.hcmuaf.fit.projectwebck.dao.model.User" %><%--
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
    <title>home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">

</head>
<body>

<jsp:include page="Header.jsp"/>


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
                                <a href="showDetail?id=${p.id}" class="product-thumb">
                                    <img src="${p.image}" alt=""/>
                                </a>
                                <!--xem ngay-->
                                <a href="addItemHome?pid=${p.id}" class="add-to-cart">Them</a>
                                <a href="detailProduct?id=${p.id}" class="buy-now">Xem ngay</a>
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

                                <%--              <a href="addItemHome?pid=${p.id}" class = "add-to-cart">Thêm</a>--%>
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

                            <fmt:parseDate value="${p.extraDay}" pattern="yyyy-MM-dd" var="dateAdded"/>
                            <c:set var="now" value="<%= new java.util.Date() %>"/>
                            <c:set var="diff" value="${now.time - dateAdded.time}"/>
                            <c:set var="days" value="${diff / (1000 * 60 * 60 * 24)}"/>

                            <c:choose>
                                <c:when test="${days > 3}">
                                    <img src="Img/new.png" class="newProduct" style="display: none;"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="Img/new.png" class="newProduct"/>
                                </c:otherwise>
                            </c:choose>
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
        <button id="more">
            <p>Xem Thêm</p>
        </button>
    </div>
</main>
<!-- Modal -->
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


<jsp:include page="Footer.jsp"/>

<div id="backtop">
    <i class="fa-solid fa-arrow-up"></i>
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
    $('#more').click(function () {
            window.location.href = "showAll";
    });

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




</script>
</body>
</html>
