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
    <div class="container d-flex">
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
                    <%--                    <ul class="img-describe">--%>
                    <%--                        <li>--%>
                    <%--                            <img src="${p.image}">--%>
                    <%--                        </li>--%>
                    <%--                        <li>--%>
                    <%--                            <img src="${p.image}">--%>
                    <%--                        </li>--%>
                    <%--                        <li>--%>
                    <%--                            <img src="${p.image}">--%>
                    <%--                        </li>--%>
                    <%--                    </ul>--%>
                    <ul class="img-describe">
                        <c:forEach var="img" items="${p.productImages}">
                            <li>
                                <img src="${img.url}" alt="Ảnh sản phẩm">
                            </li>
                        </c:forEach>
                    </ul>


                </div>
            </div>
        </div>
        <%--        <div class="detail-right">--%>
        <%--            <div class="detail-headline">--%>
        <%--                <h3>${p.name}</h3>--%>
        <%--            </div>--%>
        <%--            <div class="detail-price">--%>
        <%--                <h4 id="product-price"><f:formatNumber value="${p.price}"/>đ</h4>--%>
        <%--            </div>--%>
        <%--            <div class="summary-detail-describeContent">--%>
        <%--                <c:if test="${not empty sentences}">--%>
        <%--                    <c:forEach var="sentence" items="${sentences}">--%>
        <%--                        <h6>--%>
        <%--                            <i class="fa-solid fa-clover"></i>--%>
        <%--                            <c:out value="${sentence}"/>--%>
        <%--                        </h6>--%>
        <%--                    </c:forEach>--%>
        <%--                </c:if>--%>
        <%--                <c:if test="${empty sentences}">--%>
        <%--                    <h6>--%>
        <%--                        <i class="fa-solid fa-clover"></i>Không có mô tả nào.--%>
        <%--                    </h6>--%>
        <%--                </c:if>--%>
        <%--                &lt;%&ndash;                <h6>&ndash;%&gt;--%>
        <%--                &lt;%&ndash;                    <i class="fa-solid fa-clover"></i>&ndash;%&gt;--%>
        <%--                &lt;%&ndash;                    Góp phần tăng cường hệ miễn dịch nhờ việc cung cấp hơn 19% nhu cầu vitamin C hàng ngày cho cơ thể trong một khẩu phần ăn</h6>&ndash;%&gt;--%>
        <%--                &lt;%&ndash;                <h6>&ndash;%&gt;--%>
        <%--                &lt;%&ndash;                    <i class="fa-solid fa-clover"></i>&ndash;%&gt;--%>
        <%--                &lt;%&ndash;                    Nguồn vitamin C lớn từ quả bí đao thúc đẩy sự phát triển của xương nên cũng góp phần kích thích tăng chiều cao cho bạn</h6>&ndash;%&gt;--%>
        <%--            </div>--%>
        <%--            <div class="main-bottom">--%>
        <%--                <ul class="img-describe">--%>
        <%--                    <c:forEach var="size" items="${p.productVariants}">--%>
        <%--                        <button class="size-btn"--%>
        <%--                                data-price="${size.price}"--%>
        <%--                                onclick="updatePrice(this)"--%>
        <%--                        >--%>
        <%--                                ${size.massUnits}--%>
        <%--                                ${size.massValue}--%>

        <%--                        </button>--%>
        <%--                    </c:forEach>--%>
        <%--                </ul>--%>
        <%--                <div class="btn-bounder">--%>
        <%--                    <button class="add-later" id="add-later"><a href="add-cart?pid=${p.id}">Thêm vào giỏ hàng</a>--%>
        <%--                    </button>--%>
        <%--                    <button class="buy"><a href="showPay?uId=${sessionScope.user.id}&productId=${p.id}">Mua ngay</a></button>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <div class="detail-right p-3">
            <div class="detail-headline">

                <h3>${p.name}</h3>
            </div>

            <%--            <div class="summary-detail-describeContent">--%>
            <%--                <c:if test="${not empty sentences}">--%>
            <%--                    <c:forEach var="sentence" items="${sentences}">--%>
            <%--                        <h6>--%>
            <%--                            <i class="fa-solid fa-clover"></i>--%>
            <%--                            <c:out value="${sentence}"/>--%>
            <%--                        </h6>--%>
            <%--                    </c:forEach>--%>
            <%--                </c:if>--%>
            <%--                <c:if test="${empty sentences}">--%>
            <%--                    <h6>--%>
            <%--                        <i class="fa-solid fa-clover"></i>Không có mô tả nào.--%>
            <%--                    </h6>--%>
            <%--                </c:if>--%>
            <%--                &lt;%&ndash;                <h6>&ndash;%&gt;--%>
            <%--                &lt;%&ndash;                    <i class="fa-solid fa-clover"></i>&ndash;%&gt;--%>
            <%--                &lt;%&ndash;                    Góp phần tăng cường hệ miễn dịch nhờ việc cung cấp hơn 19% nhu cầu vitamin C hàng ngày cho cơ thể trong một khẩu phần ăn</h6>&ndash;%&gt;--%>
            <%--                &lt;%&ndash;                <h6>&ndash;%&gt;--%>
            <%--                &lt;%&ndash;                    <i class="fa-solid fa-clover"></i>&ndash;%&gt;--%>
            <%--                &lt;%&ndash;                    Nguồn vitamin C lớn từ quả bí đao thúc đẩy sự phát triển của xương nên cũng góp phần kích thích tăng chiều cao cho bạn</h6>&ndash;%&gt;--%>
            <%--            </div>--%>
            <div class="product-info-extra">
                <p><strong>Loại rau củ</strong><c:choose>
                    <c:when test="${p.category == 1}">Rau</c:when>
                    <c:when test="${p.category == 2}">Củ</c:when>
                    <c:when test="${p.category == 3}">Qủa</c:when>

                </c:choose></p>
                <p><strong>Xuất xứ:</strong> Đà lạt</p>
                <p><strong>Ngày thu hoạch:</strong> 15/4/2025</p>
                <p><strong>Hạn sử dụng:</strong> 20/4/2025</p>
                <p><strong>Bảo quản:</strong> Nhiệt độ lạnh từ 3 độ - 5 độ</p>
                <p><strong>Đặc tính dinh dưỡng:</strong> Giàu vitamin C, hỗ trợ tiêu hóa</p>
                <p><strong>Trạng thái:</strong> Còn hàng</p>
            </div>

            <div class="">
                <p><strong>Kích thước:</strong>
                <div class="img-describe">
                    <c:forEach var="size" items="${p.productVariants}">
                        <button
                                style="
                   border-radius: 50px;
                   border: 2px solid #4CAF50;
                   background-color: white;
                   color: #4CAF50;
                   font-weight: bold;
                   cursor: pointer;
                   transition: all 0.3s ease;
                   box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);"
                                data-price="${size.price}"
                                onclick="updatePrice(this)">
                                ${size.massUnits} ${size.massValue}
                        </button>
                    </c:forEach>
                </div>

                </p>
            </div>
            <div class="d-flex gap-3" style="color: #7cc652;">
                <p><strong>Giá</strong>
                <h4 id="product-price" style="color: #7cc652;"><f:formatNumber value="${p.price}"/>đ</h4>


                </p>
            </div>
            <div class="" style="margin: 10px auto 10px 10px;
    display: flex
;
    width: 100%;
    flex-wrap: wrap;
    justify-content: end;">

                <div class="" style="    width: 50%;
    display: flex
;
    justify-content: center;
    align-items: center;
    border: none;">
<%--                    <button class="add-later" id="add-later" style="    margin: 5px;--%>
<%--    height: 35px;--%>
<%--    align-items: center;--%>
<%--    background: #7cc652;--%>
<%--    border-radius: 5px;--%>
<%--    font-size: 17px;--%>
<%--    color: white;--%>
<%--    border: none;"><a href="add-cart?pid=${p.id}" style="text-decoration: none;--%>
<%--    color: white;">Thêm vào giỏ hàng</a>--%>
<%--                    </button>--%>
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <button class="add-later" id="add-later" style="    margin: 5px;
    height: 35px;
    align-items: center;
    background: #7cc652;
    border-radius: 5px;
    font-size: 17px;
    color: white;
    border: none;"
                                    onclick="handleAddToCart(${sessionScope.user.id},${p.id})">Thêm Vào Giỏ Hàng
                            </button>

                        </c:when>
                        <c:otherwise>
                            <button class="add-later" id="add-later" style="    margin: 5px;
    height: 35px;
    align-items: center;
    background: #7cc652;
    border-radius: 5px;
    font-size: 17px;
    color: white;
    border: none;"
                                    onclick="handleAddToCart(null,${p.id})">Thêm Vào Giỏ Hàng
                            </button>
                        </c:otherwise>

                    </c:choose>
                    <button class="buy" style="    margin: 5px;
    height: 35px;
    align-items: center;
    background: #7cc652;
    border-radius: 5px;
    font-size: 17px;
    color: white;
    border: none;"><a href="showPay?uId=${sessionScope.user.id}&productId=${p.id}" style="text-decoration: none;
    color: white;">Mua ngay</a></button>
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
    <div class="Evaluate">
        <div class="comment-section">
            <h2>Bình luận sản phẩm</h2>

            <div class="comment-form">
                <form method="post" action="addComment">
                    <textarea name="content" placeholder="Viết bình luận của bạn..." required></textarea>
                    <input type="hidden" name="productId" value="${p.id}" />
                    <input type="hidden" name="userId" value="${sessionScope.user.id}" />
                    <input type="hidden" name="userName" value="${sessionScope.user.username}" />
                    <br>
                    <button type="submit" name="action" value="add">Gửi bình luận</button>
                </form>
            </div>

            <div class="comment-list">
                <c:forEach var="comment" items="${commentList}">
                    <div class="comment">
                        <img src="<%= request.getContextPath() %>/Img/avatarUser.jpg" alt="">
                        <div class="comment-body">
                            <div class="name">${comment.userName}</div>
                            <div class="date">${comment.createdAt}</div>
                            <div class="text">${comment.content}</div>
                            <c:if test="${sessionScope.user.id == comment.userId}">
                                <div class="correction"><i class="fa-solid fa-trash"></i><i
                                        class="fa-solid fa-pen-to-square"></i></div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

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
<jsp:include page="Footer.jsp"/>
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
    $('#more').click(function () {
        window.location.href = "allProduct.html";
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