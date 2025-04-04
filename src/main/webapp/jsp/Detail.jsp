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
    <title>Tuber</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
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


<main id="main-detail">
    <div class="headline-detail">
        <a href="showHome" class="navigationBarHome">Trang Chủ</a>
        <span class="navigationBar/">/</span>
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
        <span class="navigationBar/">/</span>
        <span class="navigationBarType">${p.name}</span>
    </div>
    <div class="detail-product">
        <div class="detail-left">
            <div class="image-product">
                <div class="main-image">
                    <ul>
                        <li>
                            <img src="${p.image}">
                        </li>
                    </ul>
                </div>
                <div class="bounder">
                    <ul class="img-describe">
                        <li>
                            <img src="${p.image}">
                        </li>
                        <li>
                            <img src="${p.image}">
                        </li>
                        <li>
                            <img src="${p.image}">
                        </li>
                    </ul>
                    <ul class="img-describe">
                        <c:forEach var="img" items="${p.productImages}">
                            <li>
                                <img src="${img.url}" alt="Ảnh sản phẩm">
                            </li>
                        </c:forEach>
                    </ul>
                    <ul class="img-describe">
                        <c:forEach var="size" items="${p.productVariants}">
                            <button class="size-btn"
                                    data-price="${size.price}"
                                    onclick="updatePrice(this)"
                            >
                                    ${size.massUnits}
                                    ${size.massValue}

                            </button>
                        </c:forEach>
                    </ul>


                </div>
            </div>
        </div>
        <div class="detail-right">
            <div class="detail-headline">
                <h3>${p.name}</h3>
            </div>
            <div class="detail-price">
                <h4 id="product-price"><f:formatNumber value="${p.price}"/>đ</h4>
            </div>
            <div class="summary-detail-describeContent">
                <c:if test="${not empty sentences}">
                    <c:forEach var="sentence" items="${sentences}">
                        <h6>
                            <i class="fa-solid fa-clover"></i>
                            <c:out value="${sentence}"/>
                        </h6>
                    </c:forEach>
                </c:if>
                <c:if test="${empty sentences}">
                    <h6>
                        <i class="fa-solid fa-clover"></i>Không có mô tả nào.
                    </h6>
                </c:if>
                <%--                <h6>--%>
                <%--                    <i class="fa-solid fa-clover"></i>--%>
                <%--                    Góp phần tăng cường hệ miễn dịch nhờ việc cung cấp hơn 19% nhu cầu vitamin C hàng ngày cho cơ thể trong một khẩu phần ăn</h6>--%>
                <%--                <h6>--%>
                <%--                    <i class="fa-solid fa-clover"></i>--%>
                <%--                    Nguồn vitamin C lớn từ quả bí đao thúc đẩy sự phát triển của xương nên cũng góp phần kích thích tăng chiều cao cho bạn</h6>--%>
            </div>
            <div class="main-bottom">
                <div class="size-bounder">
                    <div class="option">
                        <span>Khối Lượng</span>
                        <div class="btn-choose">
                            <%--                            <button>500g</button>--%>
                            <button>1 kg</button>
                        </div>
                    </div>
                </div>
                <div class="btn-bounder">
                    <button class="add-later" id="add-later"><a href="add-cart?pid=${p.id}">Thêm vào giỏ hàng</a>
                    </button>
                    <button class="buy"><a href="">Mua ngay</a></button>
                </div>
            </div>
        </div>
    </div>
    <!--  <div class="detail-bottom">-->
    <!--    <div class="btn-describe">-->
    <!--      <button id = "describe">Mô tả</button>-->
    <!--      <button>Đánh giá</button>-->
    <!--    </div>-->
    <!--    <div class="describe-text">-->
    <!--      <h5>I. Nguồn gốc, xuất xứ:</h5>-->
    <!--      <p>Bí đao là loài bản địa ở vùng Đông Nam Á nhưng hiện nay, nó được trồng phổ biến khắp từ Nam Á sang Đông Á. Ở nước ta, bí đao cũng được trồng ở khắp nơi làm nguyên liệu thực phẩm, với kỹ thuật trồng và kiểm tra đạt tiêu chuẩn Vietgap trước khi đưa ra thị trường. Bí đao hay còn được gọi là bí xanh, bí phấn, thuộc họ Bầu bí.</p>-->
    <!--      <h5>II. Đặc điểm, hình dáng, hương vị:</h5>-->
    <!--      <p>Bí đao có dạng hình trụ, cuống có mủ, bên dưới có tua lông màu đen. Trên vỏ bí có nhiều lông, màu xanh đậm. Bên trong ruột có màu trắng xanh, ăn giòn và ngọt, thơm. Còn bộ phận không sử dụng được là hạt, vỏ, cùi chúng ta khi sơ chế nên bỏ đi.</p>-->
    <!--      <h5>III. Công dụng:</h5>-->
    <!--      <p>Với tính ngọt và thanh mát, các chất dinh dưỡng thì bí đao mang lại công dụng lớn cho sức khỏe: giảm cân, thanh nhiệt giải độc, tốt cho bệnh nhân đái tháo đường, mát gan, tăng cường hệ miễn dịch, làm làn da đẹp, căng sáng,....</p>-->
    <!--      <h5>IV. Các món ăn với bí đao:</h5>-->
    <!--      <p>Bí đao được dùng làm nguyên liệu cho món ăn hằng ngày đã trở nên quen thuộc với các mẹ nội trợ</p>-->
    <!--      <p>Bí đao nhồi thịt kho nước tương</p>-->
    <!--      <p>Canh bí đao hầm xương heo, nấu với thịt gà, nấu với tôm</p>-->
    <!--      <p>Bí đao xào trứng</p>-->
    <!--      <p>Làm nước bí đao, trà bí đao hạt chia,....</p>-->
    <!--      <h5>V. Sơ chế và bảo quản:</h5>-->
    <!--      <p>Bí đao mua về cắt bỏ phần cuống và phần thân dưới có các tua.</p>-->
    <!--      <p>Rửa sơ với nước để loại tạp chất trên vỏ trước khi gọt, sau đó gọt sạch vỏ rồi rửa lại với nước 1 lần nữa</p>-->
    <!--      <p>Cắt bí đao thành từng khoanh, từng khúc tùy vào cách chế biến</p>-->
    <!--      <p>Bí đao thích hợp bảo quản ở nhiệt độ 10-13 độ C, mát hơn nhiệt độ phòng mà không lạnh như tủ lạnh.</p>-->
    <!--    </div>-->
    <!--  </div>-->
    <div class="ralated-product">
        <div class="headline-related">
            <h3>Sản Phẩm Liên Quan</h3>
        </div>
        <ul class="products">
            <c:forEach var="pr" items="${relatedProducts}">
                <li>
                    <div class="product-item">
                        <div class="product-top">
                            <a href="showDetail?id=${pr.id}" class="product-thumb">
                                <img src="${pageContext.request.contextPath}/${pr.image}">

                            </a>
                            <!--xem ngay-->
                            <a href="addRelativePr?id=${p.id}&pid=${pr.id}" class = "add-to-cart">Them</a>
                            <a href="showDetail?id=${pr.id}" class="buy-now">Xem ngay</a>
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
                            <a href="showDetail?id=${pr.id}" class="product-name">${pr.name}</a>
                            <div class="product-price">${pr.price}đ</div>
                        </div>
                    </div>
                </li>
            </c:forEach>

        </ul>
    </div>
</main>
<jsp:include page="Footer.jsp" />
<div id="backtop">
    <i class="fa-solid fa-arrow-up"></i>
</div>
</body>
<%--<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        $(window).scroll(function () {--%>
<%--            if ($(this).scrollTop()) {--%>
<%--                $('#backtop').fadeIn();--%>
<%--            } else {--%>
<%--                $('#backtop').fadeOut();--%>
<%--            }--%>
<%--        })--%>
<%--        $('#backtop').click(function () {--%>
<%--            $('html, body').animate({scrollTop: 0}, 300);--%>
<%--        });--%>
<%--    })--%>
<%--    $('#more').click(function () {--%>
<%--        window.location.href = "allProduct.html";--%>
<%--    });--%>


<%--</script>--%>
<script>
    function updatePrice(element) {
        console.log('click123')
        let selectedPrice = element.getAttribute("data-price");
        document.getElementById("product-price").innerText = selectedPrice + " VND";
        console.log('click456')
        console.log(selectedPrice)
        document.querySelectorAll('.size-btn').forEach(btn => btn.classList.remove('active'));
        element.classList.add('active');
    }
</script>

</html>