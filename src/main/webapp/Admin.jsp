<%@ page import="vn.edu.hcmuaf.fit.projectwebck.dao.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession();
    String runScript = request.getParameter("runScript");
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("/web/showLogin");
        return;
    }
    if (user.getRole() != 1) {
        response.sendRedirect("/web/showHome");
        return;
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0/dist/css/select2.min.css" rel="stylesheet"/>


    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Admin.css">

</head>
<body>
<c:if test="${not empty message}">
    <script type="text/javascript">
        alert("${message}");
    </script>
</c:if>
<div class="side-menu">
    <div class="brand-name">
        <h1>NLU Food</h1>
    </div>
    <ul>
        <a href="showOption?option=option1">
            <li class="NavigationbarSelect" id="option1" onclick="navigationbarClick('option1')"><img
                    src="Img/bar-admin.png" alt="" style="width: 40px; height:40px;"/>&nbsp;<span>Điều khiển</span>
            </li>
        </a>
        <a href="showOption?option=option2">
            <li class="NavigationbarSelect" id="option2" onclick="navigationbarClick('option2')"><img
                    src="Img/snapedit_ves-admin.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Rau củ</span></li>
        </a>
        <a href="showOption?option=option3">
            <li class="NavigationbarSelect" id="option3" onclick="navigationbarClick('option3')"><img
                    src="Img/snapedit_user-admin2.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Người dùng</span>
            </li>
        </a>
        <a href="showOption?option=option4">
            <li class="NavigationbarSelect" id="option4" onclick="navigationbarClick('option4')"><img
                    src="Img/order-admin.png" alt="" style="width: 40px; height:40px"/>&nbsp;<span>Đơn hàng</span>
            </li>
        </a>
        <a href="showOption?option=option5">
            <li class="NavigationbarSelect" id="option5" onclick="navigationbarClick('option5')"><img
                    src="Img/discount-admin.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Khuyến mãi</span>
            </li>
        </a>
        <a href="showOption?option=option6">
            <li class="NavigationbarSelect" id="option6" onclick="navigationbarClick('option6')"><img
                    src="Img/discount-admin.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Sản phẩm giảm giá test</span>
            </li>
        </a>
    </ul>
</div>
<div class="container">
    <div class="header">
        <div class="nav">
            <div class="search">
                <input type="text" placeholder="Search here">
                <button type="submit"><img src="Img/search-btn.png" alt=""/></button>
            </div>
            <div class="user">
                <div class="img-case">
                    <ul>
                        <li>
                            <a href="/web/logout">
                                <img src="Img/power.png" alt="" class="admin-avatar">
                                <%--                            <div class="dropdown-menu">--%>
                                <%--                                <button class="logout-btn" onclick="logout()">Đăng Xuất</button>--%>
                                <%--                            </div>--%>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <%--        DashBoar--%>
        <div class="DashBoar select">
            <div class="cards">
                <div class="card">
                    <div class="box">
                        <h1>${listproduct.size()}</h1>
                        <h3>Rau, củ, quả</h3>
                    </div>
                    <div class="icon-case">
                        <img src="Img/snapedit_seed.png" alt="" style="width: 40px; height: 40px; ">
                    </div>
                </div>
                <div class="card">
                    <div class="box">
                        <h1>${listuser.size()}</h1>
                        <h3>Người dùng</h3>
                    </div>
                    <div class="icon-case">
                        <img src="Img/snapedit_user-line.png" alt="" style="width: 40px; height: 40px">
                    </div>
                </div>
                <div class="card">
                    <div class="box">
                        <h1>${listorder.size()}</h1>
                        <h3>Đơn hàng</h3>
                    </div>
                    <div class="icon-case">
                        <img src="Img/snapedit_cart-line.png" alt="" style="width: 40px; height: 40px">
                    </div>
                </div>
                <div class="card">
                    <div class="box">
                        <h1><c:out value="${totalRevenue}"/>đ</h1>
                        <h3>Doanh thu</h3>
                    </div>
                    <div class="icon-case">
                        <img src="Img/doanhthu-admin.png" alt="" style="width: 40px; height: 40px">
                    </div>
                </div>
            </div>
            <div class="content-2">
                <div class="recent-payments">
                    <div class="title_Dashboar">
                        <h2>Đơn hàng gần đây</h2>
                    </div>
                    <table>
                        <tr>
                            <th>Người dùng</th>
                            <th>Rau Củ Quả</th>
                            <th>Thành Tiền</th>
                        </tr>
                        <%--                        <c:forEach var="o" items="${listlatestorders}">--%>
                        <%--                            <tr>--%>
                        <%--                                <td>${o.get}</td>--%>
                        <%--                                <td>Quả Bí Đao</td>--%>
                        <%--                                <td>30.000đ</td>--%>
                        <%--                                <td><a href="#" class="btn">Xem</a></td>--%>
                        <%--                            </tr>--%>
                        <%--                        </c:forEach>--%>

                        <c:choose>
                            <c:when test="${not empty listlatestorders}">
                                <c:forEach var="summary" items="${listlatestorders}">
                                    <tr>
                                        <td>${summary.username}</td>
                                        <td>${summary.name}</td>
                                        <td>${summary.totalamount}</td>
                                            <%--                                        <td><a href="#" class="btn">Xem</a></td>--%>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="3">Không tìm thấy đơn hàng nào.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </div>
                <div class="new-users">
                    <div class="title_Dashboar">
                        <h2>Người dùng mua nhiều</h2>
                    </div>
                    <table>
                        <tr>
                            <th>Tài khoản</th>
                            <th>Tên</th>
                        </tr>
                        <c:choose>
                            <c:when test="${not empty listCustomer}">
                                <c:forEach var="conclusion" items="${listCustomer}">
                                    <tr>
                                        <td><img src="Img/user.png" alt="" style="width: 40px; height: 40px"></td>
                                        <td>${conclusion.username}</td>
                                            <%--                                        <td><img src="Img/infor-admin.png" alt="" style="width: 40px; height: 40px"></td>--%>

                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="3">Không tìm thấy khách hàng nào.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </div>
            </div>
        </div>
        <%--        Rau cu--%>
        <div class="AdminListProduct select">
            <div class="AdminListProductHeader">
                <div>Sản phẩm(<span>${listproduct.size()}</span>)</div>
                <form action="searchProduct" method="get">
                    <input type="text" name="name" id="searchProduct" placeholder="Nhập tên sản phẩm?">
                </form>
                <button type="submit" onclick="addProduct()">Thêm sản phẩm</button>
            </div>
            <ul class="Product_Item" id="ProductItem">
                <li class="title_Item">
                    <div>ID</div>
                    <div>Ảnh</div>
                    <div>Tên</div>
                    <div>Giá</div>
                    <div>Khối lượng</div>
                </li>
                <c:forEach var="p" items="${listproduct}">
                    <li>
                        <div>${p.id}</div>
                        <div><img src="${p.image}" alt="" style="width: 50px"></div>
                        <div>${p.name}</div>
                        <div><f:formatNumber value="${p.price}" type="number" pattern="#,##0VND"/></div>
                        <div>${p.mass}Kg</div>
                        <div class="menu">
                            <i class="fa-solid fa-ellipsis-vertical"></i>
                            <div class="ellipsis">
                                <div onclick="UpdateProduct('${p.id}','${p.name}','${p.price}','${p.mass}','${p.description}','${p.image}','${p.category}')">
                                    Sửa
                                </div>
                                <a href="removeProduct?pid=${p.id}">
                                    <div>Xóa</div>
                                </a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="ProductWindow" id="ProductWindow">
                <div class="modal-content">
                    <span>Nhập thông tin sản phẩm</span><br>
                    <form action="addProduct" method="post" enctype="multipart/form-data">
                        <input type="hidden" id="idp" name="idp">
                        <label for="productImage">Ảnh:</label><br>
                        <input type="file" id="productImage" name="image" required><br>
                        <span><img src="" alt="" id="image" style="width: 50px; display: none"><br></span>
                        <label for="productName">Tên:</label><br>
                        <input type="text" id="productName" name="name" placeholder="Vui lòng nhập tên ..."
                               required><br>
                        <label for="productPrice">Giá:</label><br>
                        <input type="text" id="productPrice" name="price" placeholder="Vui lòng nhập giá ..."
                               required><br>
                        <label for="productDescribe">Mô tả:</label><br>
                        <textarea id="productDescribe" name="describe" placeholder="Vui lòng mô tả" required></textarea><br>
                        <label for="productMass">Khối lượng:</label><br>
                        <input type="text" id="productMass" name="mass"
                               placeholder="Vui lòng nhập số lượng ..." required><br>
                        <label for="productCategory">Loại:</label><br>
                        <select name="category" id="productCategory" required>
                            <option value="1" selected>Rau</option>
                            <option value="2">Củ</option>
                            <option value="3">Quả</option>
                        </select><br>
                        <button type="submit" class="ButtonProduct1">Lưu</button>
                        <button type="button" class="ButtonProduct2" onclick="closeProduct()">Hủy</button>
                    </form>
                </div>
            </div>
        </div>
        <%--        Nguoi dung--%>
        <div class="AdminListUser select">
            <div class="AdminListUserHeader">
                <div>Tài khoản(<span>${listuser.size()}</span>)</div>
                <form action="searchUser" method="get">
                    <input type="text" name="searchUser" id="searchUser" placeholder="Nhập tên khách hàng?">
                </form>
                <button type="submit" onclick="addUser()">Thêm tài khoản</button>
            </div>
            <ul class="User_Item" id="UserItem">
                <li class="title_Item">
                    <div>ID</div>
                    <div>Tên</div>
                    <div>Số điện thoại</div>
                    <div>Phân quyền</div>
                </li>
                <c:forEach var="u" items="${listuser}">
                    <li>
                        <div>${u.id}</div>
                        <div>${u.fullName}</div>
                        <div>${u.phone}</div>
                        <div>
                            <c:choose>
                                <c:when test="${u.role == '1'}">Quản trị viên</c:when>
                                <c:otherwise>Người dùng</c:otherwise>
                            </c:choose>
                        </div>
                        <div class="menu">
                            <i class="fa-solid fa-ellipsis-vertical"></i>
                            <div class="ellipsis">
                                <div onclick="UpdateUser('${u.id}', '${u.username}', '${u.password}', '${u.role}', '${u.fullName}', '${u.email}', '${u.dateOfBirth}', '${u.phone}')">
                                    Sửa
                                </div>
                                <a href="removeUser?uid=${u.id}">
                                    <div>Xóa</div>
                                </a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="UserWindow" id="UserWindow">
                <div class="modal-content">
                    <span>Nhập thông tin tài khoản</span><br>
                    <form action="addUser" method="post">
                        <input type="hidden" id="uid" name="uid">
                        <label for="UserName">Tên đăng nhập:</label><br>
                        <input type="text" id="UserName" name="UserName" placeholder="Vui lòng nhập tên đăng nhập..."
                               required><br>
                        <label for="UserPassword">Mật khẩu:</label><br>
                        <input type="password" id="UserPassword" name="Password" placeholder="Vui lòng nhập mật khẩu..."
                               required><br>
                        <label for="Role">Phân quyền:</label><br>
                        <select name="Role" id="Role" required>
                            <option value="Quản trị viên">Quản trị viên</option>
                            <option value="Người dùng">Người dùng</option>
                        </select><br>
                        <label for="FullName">Họ tên:</label><br>
                        <input type="text" id="FullName" name="FullName" placeholder="Vui lòng nhập họ tên..." required><br>
                        <label for="Phone">Số điện thoại:</label><br>
                        <input type="text" id="Phone" name="Phone" placeholder="Vui lòng nhập số điện thoại..."
                               required><br>
                        <label for="Birthday">Ngày sinh:</label><br>
                        <input type="date" id="Birthday" name="Birthday" required><br>
                        <label for="Email">Email:</label><br>
                        <input type="email" id="Email" name="Email" placeholder="Vui lòng nhập email..." required><br>
                        <button type="submit" class="ButtonUser1">Lưu</button>
                        <button type="button" class="ButtonUser2" onclick="closeUser()">Hủy</button>
                    </form>
                </div>
            </div>
        </div>

        <%--Don hang--%>
        <div class="AdminListOrder select">
            <div class="AdminListOrderHeader">
                <div>Đơn Hàng(<span>${listuser.size()}</span>)</div>
                <form action="searchOrder" method="get">
                    <input type="text" name="searchOrder" id="searchOrder" placeholder="Nhập mã đơn hàng?">
                </form>
            </div>
            <ul class="Order_Item">
                <li class="title_Item">
                    <div>Mã vận đơn</div>
                    <div>Khách hàng</div>
                    <div>Ngày đặt</div>
                    <div>Thành tiền</div>
                    <div>Trạng thái</div>
                </li>
                <c:forEach var="order" items="${listorder}">
                    <li>
                        <div>${order.id}</div>
                        <div class="name">${order.fullName}</div>
                        <div>${order.dateOfBooking}</div>
                        <div><f:formatNumber value="${order.money}" type="number" pattern="#,##0VND"/></div>
                        <div class="${order.status == '1' ? 'statusT' : 'statusF'}">
                                ${order.status == '1' ? 'Đã thanh toán' : 'Chờ thanh toán'}
                        </div>
                        <div class="menu">
                            <i class="fa-solid fa-ellipsis-vertical"></i>
                            <div class="ellipsis">
                                <div onclick="viewOrder(${order.id})">Chi tiết đơn hàng</div>
                                <a href="removeOder?oid=${order.id}">
                                    <div>Xóa</div>
                                </a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="OderWindow" id="OderWindow">
                <div class="iconClose" onclick="viewOrderClose()"><i class="fa-solid fa-xmark"></i></div>
                <div class="OderWindowTitle">Danh sách sản phẩm</div>
                <div id="ProductItems" class="Product_List_item">
                    <%--                    Phan chi tiet don hang--%>
                </div>
                <div class="TotalAmount">
                    <span class="text">Tổng cộng</span>
                    <span class="total" id="total">${totalAmount}đ</span>
                </div>
            </div>
        </div>
        <%--Khuyến mãi--%>
        <div class="AdminListPromotion select">
            <div class="AdminListPromotionHeader">
                <div>Khuyến mãi (<span>${listpromotion.size()}</span>)</div>
                <form action="searchPromotion" method="get">
                    <input type="text" name="searchPromotion" id="searchPromotion" placeholder="Nhập mã khuyến mãi?">
                </form>
                <button type="submit" onclick="addPromotion()">Thêm khuyến mãi</button>
            </div>
            <ul class="Promotion_Item" id="PromotionItem">
                <li class="title_Item">
                    <div>Mã khuyến mãi</div>
                    <div>Ngày bắt đầu</div>
                    <div>Ngày kết thúc</div>
                    <div>Giá trị</div>
                </li>
                <c:forEach var="po" items="${listpromotion}">
                    <li>
                        <div>${po.id}</div>
                        <div>${po.startDate}</div>
                        <div>${po.endDate}</div>
                        <div>${po.value}%</div>
                        <div class="menu">
                            <i class="fa-solid fa-ellipsis-vertical"></i>
                            <div class="ellipsis">
                                <div onclick="UpdatePromotion('${po.id}', '${po.name}', '${po.startDate}', '${po.endDate}', '${po.value}')">
                                    Sửa
                                </div>
                                <a href="removePromotion?poid=${po.id}">
                                    <div>Xóa</div>
                                </a>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <div class="PromotionWindow" id="PromotionWindow">
                <div class="modal-content">
                    <span>Nhập thông tin khuyến mãi</span><br>
                    <form action="addPromotion" method="post">
                        <input type="hidden" id="poid" name="poid">
                        <label for="PromotionName">Tên khuyến mãi:</label><br>
                        <input type="text" id="PromotionName" name="PromotionName"
                               placeholder="Vui lòng nhập tên khuyến mãi..." required><br>
                        <label for="StartDate">Ngày bắt đầu:</label><br>
                        <input type="date" id="StartDate" name="StartDate" required><br>
                        <label for="EndDate">Ngày kết thúc:</label><br>
                        <input type="date" id="EndDate" name="EndDate" required><br>
                        <label for="Value">Giá trị (%):</label><br>
                        <input type="number" id="Value" name="Value" placeholder="Nhập giá trị khuyến mãi (%)..."
                               required><br>
                        <button type="submit" class="ButtonPromotion1">Lưu</button>
                        <button type="button" class="ButtonPromotion2" onclick="closePromotion()">Hủy</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="AdminListProductDiscount select container mt-4">
            <div class="d-flex justify-content-between align-items-center">
                <h2>Quản lý sản phẩm giảm giá</h2>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductDiscount">Thêm sản
                    phẩm giảm giá
                </button>
            </div>
            <table id="productTable " class="table table-striped">
                <thead>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Loại giảm giá</th>
                    <th>Phần trăm giảm giá</th>
                    <th>Giảm giá theo tiền tệ</th>
                    <th>Gía sau khi giảm</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                </tr>

                </thead>

                <tbody>
                <c:forEach var="productDiscount" items="${productWithDiscount}">
                    <tr>
                        <td>${productDiscount.nameProduct}</td>
                        <td>${productDiscount.discoutType}</td>
                        <td>${productDiscount.discountPercentage}</td>
                        <td>${productDiscount.price}</td>
                        <td>abc</td>
                        <td>${productDiscount.startDate}</td>
                        <td>${productDiscount.endDate}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--modal add product discount  --%>
<div class="modal fade" id="addProductDiscount" tabindex="-1" aria-labelledby="addPromotionModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addPromotionModalLabel">Thêm Sản phẩm giảm giá</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="productDiscountForm">
                    <div class="mb-3">
                        <label for="productSelect" class="form-label">Chọn sản phẩm</label>
                        <select class="form-control" id="productSelect" required>
                            <option value="">-- Tìm sản phẩm --</option>
                            <!-- Các option sẽ được load bằng Ajax -->
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="discountType" class="form-label">Loại giảm giá</label>
                        <select class="form-control" id="discountType">
                            <option value="percentage">Phần trăm</option>
                            <option value="fixed">Giảm giá cố định</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="discountPercent" class="form-label">Phần trăm giảm giá (%)</label>
                        <input type="number" class="form-control" id="discountPercent" min="0" max="100" required>
                    </div>
                    <div class="mb-3">
                        <label for="productDiscountPrice" class="form-label">Gỉam giá cố định</label>
                        <input type="number" class="form-control" id="productDiscountPrice" required>
                    </div>
                    <div class="mb-3">
                        <label for="discountPrice" class="form-label">Giá sau giảm (VND)</label>
                        <input type="number" class="form-control" id="discountPrice" required>
                    </div>
                    <div class="mb-3">
                        <label for="startDate" class="form-label">Ngày bắt đầu</label>
                        <input type="datetime-local" class="form-control" id="startDateDiscountPrice" required>
                    </div>
                    <div class="mb-3">
                        <label for="endDate" class="form-label">Ngày kết thúc</label>
                        <input type="datetime-local" class="form-control" id="endDateDiscountPrice" required>
                    </div>
                    <button type="submit" class="btn btn-success">Thêm</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.onload = function () {
        // Kiểm tra xem runScript có khác null không
        <% if (runScript != null) { %>
        // Gọi hàm navigationbarClick và truyền giá trị runScript vào
        navigationbarClick('<%= runScript %>');
        <% } %>
    };
</script>

<%--them san pham khuyen mai--%>

<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0/dist/js/select2.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script>

    let isProductLoaded = false;

    $(document).ready(function () {
        loadProduct(); // Chỉ gọi 1 lần khi trang tải xong
    });


    function loadProduct() {
        let productSelect = document.getElementById("productSelect"); // Chuyển về DOM element
        if (isProductLoaded) {
            return; // Nếu đã load rồi thì không load lại nữa
        }
        isProductLoaded = true;
        $.ajax({
            url: "/web/productDiscount", // Gọi API lấy danh sách sản phẩm
            type: "GET",
            headers: {"discount": "discount"},
            dataType: "json",
            success: function (data) {
                productSelect.innerHTML = ""; // Xóa option cũ
                let defaultOption = document.createElement("option");
                defaultOption.value = "";
                defaultOption.textContent = "-- Tìm sản phẩm --";
                productSelect.appendChild(defaultOption); // Thêm option mặc định

                console.log("🚀 Data nhận được:", data);

                data.forEach(product => {
                    let option = document.createElement("option");
                    option.value = product.id;
                    option.textContent = product.name + " - " + product.price + " VND";
                    productSelect.append(option);
                });

                console.log("✅ HTML sau khi cập nhật:", productSelect.innerHTML);
            },
            error: function () {
                alert("Không thể tải danh sách sản phẩm!");
            }
        });
    }

    $(document).ready(function () {
        $('#discountType').change( function () {
            let discountType = $(this).val();

            if(discountType === 'percentage') {
                $('discountPrice').prop("disabled", true).val("");
                $('productDiscountPrice').prop("disabled", false);

            } else if (discountType === 'fixed') {
                $('productDiscountPrice').prop("disabled", true).val("");
                $('discountPrice').prop("disabled", false);
            }
        })
    })

    $(document).ready(function () {
        $("#productDiscountForm").submit(function (event) {
            event.preventDefault();
            let productId = document.getElementById("productSelect")?.value;
            let discountPercent = document.getElementById("discountPercent")?.value || 0;
            let discountPrice = document.getElementById("productDiscountPrice")?.value || 0;
            let startDate = new Date(document.getElementById("startDateDiscountPrice").value);
            let endDate = new Date(document.getElementById("endDateDiscountPrice").value);

            if (startDate >= endDate) {
                alert("Ngày bắt đầu phải trước ngày kết thúc.");
                return;
            }

            let timeDifference = endDate - startDate;
            let hoursDifference = timeDifference / (1000 * 60 * 60);
            let daysDifference = timeDifference / (1000 * 60 * 60 * 24);

            let DiscountType = daysDifference >=1 ? "HOURLY" : "DAILY";
            let formData = {
                productId: productId,
                discount_type: DiscountType,
                discount_percent: discountPercent,
                discount_price: discountPrice,
                startDateTime: startDate,
                endDateTime: endDate

            };

            fetch("/web/AddProductDiscount", {
                method: "POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(formData)
            })
                .then(response => reponse.text())
                .then(data =>  {
                    alert(data);
                    location.reload();
                })
                .catch(error => console.error(error))
        })
    })
</script>
</body>
</html>
