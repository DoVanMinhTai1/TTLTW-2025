<%@ page import="vn.edu.hcmuaf.fit.projectwebck.dao.model.User" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="vn.edu.hcmuaf.fit.projectwebck.dao.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession();
//    String runScript = request.getParameter("runScript");
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("showLogin");
        return;
    }
    User user = (User) session.getAttribute("user");
    int roleId = user.getRole();
    Set<Integer> allowedRoles = new HashSet<>();
    allowedRoles.add(1); // ADMIN
    allowedRoles.add(2); // MOD_VEGETABLES
    allowedRoles.add(3); // MOD_USERS
    allowedRoles.add(4); // MOD_ORDERS
    allowedRoles.add(5); // MOD_PROMOTIONS
    allowedRoles.add(6); // MOD_PRODUCT_PROMOTION
    allowedRoles.add(7); //MOD_STOCK
    allowedRoles.add(8); //MOD_RETURN

    if (!allowedRoles.contains(roleId)) {
        response.sendRedirect("showHome");
        return;
    }

    Role role = Role.fromId(roleId);
    request.setAttribute("role", role);

    // Lấy runScript từ request.getAttribute thay vì getParameter
    String runScript = (String) request.getAttribute("runScript");
    if (runScript == null || runScript.trim().isEmpty()) {
        runScript = "option1"; // Giá trị mặc định
    }
    request.setAttribute("runScript", runScript);
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
        <c:if test="${role.hasPermission('VIEW_DASHBOARD')}">

            <%--            <a href="showOption?option=option1">--%>
            <li class="NavigationbarSelect" id="option1" onclick="navigationbarClick('option1')"><img
                    src="Img/bar-admin.png" alt="" style="width: 40px; height:40px;"/>&nbsp;<span>Điều khiển</span>
            </li>
            <%--            </a>--%>
        </c:if>
        <c:if test="${role.hasPermission('MANAGE_VEGETABLES')}">
            <%--            <a href="showOption?option=option2">--%>
            <li class="NavigationbarSelect" id="option2" onclick="navigationbarClick('option2')"><img
                    src="Img/snapedit_ves-admin.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Rau củ</span></li>
            <%--            </a>--%>
        </c:if>
        <c:if test="${role.hasPermission('MANAGE_USERS')}">
            <%--            <a href="showOption?option=option3">--%>
            <li class="NavigationbarSelect" id="option3" onclick="navigationbarClick('option3')"><img
                    src="Img/snapedit_user-admin2.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Người dùng</span>
            </li>
            <%--            </a>--%>
        </c:if>
        <c:if test="${role.hasPermission('MANAGE_ORDERS')}">
            <%--            <a href="showOption?option=option4">--%>
            <li class="NavigationbarSelect" id="option4" onclick="navigationbarClick('option4')"><img
                    src="Img/order-admin.png" alt="" style="width: 40px; height:40px"/>&nbsp;<span>Đơn hàng</span>
            </li>
            <%--            </a>--%>
        </c:if>
        <c:if test="${role.hasPermission('MANAGE_PROMOTIONS')}">
            <%--            <a href="showOption?option=option5">--%>
            <li class="NavigationbarSelect" id="option5" onclick="navigationbarClick('option5')"><img
                    src="Img/discount-admin.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Khuyến mãi</span>
            </li>
            <%--            </a>--%>
        </c:if>
        <c:if test="${role.hasPermission('MANAGE_PRODUCT_PROMOTION')}">
            <%--            <a href="showOption?option=option6">--%>
            <li class="NavigationbarSelect" id="option6" onclick="navigationbarClick('option6')"><img
                    src="Img/discount-admin.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Sản phẩm giảm giá</span>
            </li>
            <%--            </a>--%>
        </c:if>
        <c:if test="${role.hasPermission('MANAGE_STOCK')}">
            <%--            <a href="showOption?option=option7">--%>
            <li class="NavigationbarSelect" id="option7" onclick="navigationbarClick('option7')"><img
                    src="Img/discount-admin.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Kho hàng</span>
            </li>
            <%--            </a>--%>
        </c:if>

        <c:if test="${role.hasPermission('MANAGE_RETURN')}">

            <li class="NavigationbarSelect" id="option8" onclick="navigationbarClick('option8')"><img
                    src="Img/discount-admin.png" alt=""
                    style="width: 40px; height:40px"/>&nbsp;<span>Đổi Trả</span>
            </li>
        </c:if>

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
                            <a href="logout">
                                <img src="Img/power.png" alt="" class="admin-avatar">
                                <%--                            <div class="dropdown-menu">--%>
                                <%--                                <button class="logout-btn" onclick="logout()">Đăng Xuất</button>--%>
                                <%--                            </div>--%>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="NotificationBell">
                    <i class="fa-solid fa-bell"></i>
                    <div class="Notification" id="Notification">
                        <div class="NotificationTitle">Thông Báo</div>
                        <ul id="NotificationList" class="Notification_List_item">
                            <c:forEach var="log" items="${listlog}">
                                <li class="Notification_item">
                                    <div class="Notification_Eye">
                                        <i class="fa-solid fa-exclamation"></i>
                                    </div>
                                    <div class="Notification_item_info">
                                        <div class="Notification_Describe">${log.label}
                                        </div>
                                    </div>
                                    <i class="fa-solid fa-eye" onclick="viewNotification(${log.logId})"></i>
                                    <c:if test="${sessionScope.user.role == 2}">
                                        <a href="DeleteLog?logId=${log.logId}"><i class="fa-solid fa-trash"></i></a>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="NotificationWindow" id="NotificationWindow">
        <h2>Chi tiết Log</h2>
        <i class="fa-solid fa-xmark" onclick="closeNotification()"></i>
        <div><label>ID:</label> 1</div>
        <div><label>Label:</label> Cập nhật người dùng</div>
        <div><label>User ID:</label> 123</div>
        <div><label>Time:</label> 2025-04-07 10:30:00</div>
        <div><label>Location:</label> /user/update</div>
        <div><label>Dữ liệu trước:</label><br>
            <pre></pre>
        </div>
        <div><label>Dữ liệu sau:</label><br>
            <pre></pre>
        </div>
    </div>
    <div class="content">
        <%--        DashBoar--%>
        <c:if test="${role.hasPermission('VIEW_DASHBOARD')}">
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
        </c:if>

        <%--        Rau cu--%>
        <c:if test="${role.hasPermission('MANAGE_VEGETABLES')}">
            <div class="AdminListProduct select">
                <div class="AdminListProductHeader">
                    <div>Sản phẩm(<span>${listproduct.size()}</span>)</div>
                    <input type="text" name="name" id="searchProduct" placeholder="Nhập tên sản phẩm?">
                    <button type="submit" data-bs-toggle="modal" data-bs-target="#ProductWindow" onclick="addProduct()">
                        Thêm
                        sản phẩm
                    </button>
                </div>
                <ul class="Product_Item" id="list-searchProduct">
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
                <!-- Modal -->
                <div class="modal fade" id="ProductWindow" tabindex="-1" aria-labelledby="ProductWindowLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="ProductWindowLabel">Nhập thông tin sản phẩm</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Đóng"></button>
                            </div>
                            <div class="modal-body">
                                <form action="addProduct" method="post" enctype="multipart/form-data">
                                    <input type="hidden" id="idp" name="idp">

                                    <div class="mb-3">
                                        <label for="productImage" class="form-label">Ảnh Thumbnail:</label>
                                        <input type="file" class="form-control" id="productImage" name="images"
                                               multiple>

                                        <img src="" alt="" id="image" style="width: 50px; display: none" class="mt-2">
                                    </div>
                                    <div class="mb-3">
                                        <label for="productImage" class="form-label">Ảnh Chi tiết sản phẩm:</label>
                                        <input type="file" class="form-control" id="productImageList"
                                               name="imagesList132[]"

                                               multiple>
                                        <div id="showImageList">

                                            <img src="" alt="" style="width: 50px; display: none" class="mt-2">

                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="productName" class="form-label">Tên:</label>
                                        <input type="text" class="form-control" id="productName" name="name"
                                               placeholder="Vui lòng nhập tên ..." required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="productPrice" class="form-label">Giá:</label>
                                        <input type="text" class="form-control" id="productPrice" name="price"
                                               placeholder="Vui lòng nhập giá ..." required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="productDescribe" class="form-label">Mô tả:</label>
                                        <textarea class="form-control" id="productDescribe" name="describe"
                                                  placeholder="Vui lòng mô tả" required></textarea>
                                    </div>

                                    <div class="mb-3">
                                        <label for="productMass" class="form-label">Khối lượng:</label>
                                        <input type="text" class="form-control" id="productMass" name="mass"
                                               placeholder="Vui lòng nhập số lượng ..." required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="productCategory" class="form-label">Loại:</label>
                                        <select class="form-select" name="category" id="productCategory" required>
                                            <option value="1" selected>Rau</option>
                                            <option value="2">Củ</option>
                                            <option value="3">Quả</option>
                                        </select>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-success">Lưu</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"
                                                onclick="closeProduct()">Hủy
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </c:if>

        <%--        Nguoi dung--%>
        <c:if test="${role.hasPermission('MANAGE_USERS')}">
            <div class="AdminListUser select">
                <div class="AdminListUserHeader">
                    <div>Tài khoản(<span>${listuser.size()}</span>)</div>
                    <input type="text" name="searchUser" id="searchUser" placeholder="Nhập tên khách hàng?">
                    <button type="submit" data-bs-toggle="modal" data-bs-target="#UserWindow" onclick="addUser()">Thêm
                        tài

                        khoản
                    </button>
                </div>
                <ul class="User_Item" id="list-searchUser">
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
                <div class="modal fade" id="UserWindow" tabindex="-1" aria-labelledby="UserWindowLabel"
                     aria-hidden="true">

                    <div class="modal-dialog modal-dialog-centered modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="UserWindowLabel">Nhập thông tin tài khoản</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Đóng"></button>

                            </div>
                            <div class="modal-body">
                                <form action="addUser" method="post">
                                    <input type="hidden" id="uid" name="uid">

                                    <div class="mb-3">
                                        <label for="UserName" class="form-label">Tên đăng nhập:</label>
                                        <input type="text" id="UserName" name="UserName" class="form-control"
                                               placeholder="Vui lòng nhập tên đăng nhập..." required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="UserPassword" class="form-label">Mật khẩu:</label>
                                        <input type="password" id="UserPassword" name="Password" class="form-control"
                                               placeholder="Vui lòng nhập mật khẩu..." required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="Role" class="form-label">Phân quyền:</label>
                                        <select name="Role" id="Role" class="form-select" required>
                                            <option value="Quản trị viên">Quản trị viên</option>
                                            <option value="Người dùng">Người dùng</option>
                                        </select>
                                    </div>

                                    <div class="mb-3">
                                        <label for="FullName" class="form-label">Họ tên:</label>
                                        <input type="text" id="FullName" name="FullName" class="form-control"
                                               placeholder="Vui lòng nhập họ tên..." required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="Phone" class="form-label">Số điện thoại:</label>
                                        <input type="text" id="Phone" name="Phone" class="form-control"
                                               placeholder="Vui lòng nhập số điện thoại..." required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="Birthday" class="form-label">Ngày sinh:</label>
                                        <input type="date" id="Birthday" name="Birthday" class="form-control" required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="Email" class="form-label">Email:</label>
                                        <input type="email" id="Email" name="Email" class="form-control"
                                               placeholder="Vui lòng nhập email..." required>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-success ButtonUser1">Lưu</button>
                                        <button type="button" class="btn btn-secondary ButtonUser2"
                                                data-bs-dismiss="modal"

                                                onclick="closeUser()">Hủy
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </c:if>


        <%--Don hang--%>
        <c:if test="${role.hasPermission('MANAGE_ORDERS')}">
            <div class="AdminListOrder select">
                <div class="AdminListOrderHeader">
                    <div>Đơn Hàng(<span>${listuser.size()}</span>)</div>
                    <input type="text" name="searchOrder" id="searchOrder" placeholder="Nhập mã đơn hàng?">
                </div>
                <ul class="Order_Item" id="list-searchOrder">
                    <li class="title_Item">
                        <div class="orderItemId">Mã đơn</div>
                        <div class="orderItemName">Khách hàng</div>
                        <div class="orderItemDate">Ngày đặt</div>
                        <div class="orderItemTotal">Thành tiền</div>
                        <div class="orderItemStatus">Trạng thái</div>
                        <div class="act">Hành động</div>
                    </li>
                    <c:forEach var="order" items="${listorder}">
                        <li>
                            <div class="orderItemId">${order.id}</div>
                            <div class="orderItemName">${order.fullName}</div>
                            <div class="orderItemDate">${order.dateOfBooking}</div>
                            <div class="orderItemTotal"><f:formatNumber value="${order.money}" type="number"
                                                                        pattern="#,##0VND"/></div>
                            <div class="orderItemStatus "><span class="orderItemS status-${order.status}"
                                                                id="orderItemS-${order.id}"> <c:choose>
                                <c:when test="${order.status == 0}">Chờ xác nhận</c:when>
                                <c:when test="${order.status == 1}">Đã xác nhận</c:when>
                                <c:when test="${order.status == 2}">Đang đóng gói</c:when>
                                <c:when test="${order.status == 3}">Đang vận chuyển</c:when>
                                <c:when test="${order.status == 4}">Hoàn tất</c:when>
                                <c:when test="${order.status == 5}">Đã hủy</c:when>
                                <c:otherwise>Không xác định</c:otherwise>
                            </c:choose></span>

                            </div>
                            <div class="act">
                                <select class="status-update">
                                    <option value="0" ${order.status == 0 ? 'selected' : ''}>Chờ xác nhận</option>
                                    <option value="1" ${order.status == 1 ? 'selected' : ''}>Đã xác nhận</option>
                                    <option value="2" ${order.status == 2 ? 'selected' : ''}>Đang đóng gói</option>
                                    <option value="3" ${order.status == 3 ? 'selected' : ''}>Đang vận chuyển</option>
                                    <option value="4" ${order.status == 4 ? 'selected' : ''}>Hoàn tất</option>
                                    <option value="5" ${order.status == 5 ? 'selected' : ''}>Hủy đơn</option>
                                </select>
                            </div>
                            <div class="menu">
                                <i class="fa-solid fa-ellipsis-vertical"></i>
                                <div class="ellipsis">
                                    <div onclick="viewOrder('${order.id}','${order.address}','${order.dateOfBooking}','${order.fullName}','${order.phone}')">
                                        Chi tiết đơn hàng
                                    </div>
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
                    <div class="DeliveryAddress">
                        <span class="text">Địa chỉ nhận hàng:</span>
                        <span class="delivery"></span>
                    </div>
                    <div class="UserBuy">
                        <span class="text">Tên khách hàng & SĐT:</span>
                        <span class="userInf"></span>
                    </div>
                    <div class="DeliveryAddress">
                        <span class="text">Ngày đặt:<span class="deliveryDate"> </span></span>
                    </div>
                </div>
            </div>
        </c:if>

        <%--Khuyến mãi--%>
        <c:if test="${role.hasPermission('MANAGE_PROMOTIONS')}">
            <div class="AdminListPromotion select">
                <div class="AdminListPromotionHeader">
                    <div>Khuyến mãi (<span>${listpromotion.size()}</span>)</div>
                    <input type="text" name="searchPromotion" id="searchPromotion" placeholder="Nhập mã khuyến mãi?">
                    <button type="button" onclick="generatePromoCode()">
                        Phát mã khuyến mãi
                    </button>
                    <button type="submit" data-bs-toggle="modal" data-bs-target="#PromotionWindow"
                            onclick="addPromotion()">

                        Thêm khuyến mãi
                    </button>
                </div>
                <ul class="Promotion_Item" id="list-searchPromotion">
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
                <div class="GeneratePromoCode" id="GeneratePromoCode">
                    <h2>Phát mã khuyến mãi</h2>
                    <form action="GeneratePromoCode" method="POST">
                        <label for="maKM">Chọn mã khuyến mãi:</label>
                        <select id="maKM" name="maKM" required>
                            <!-- Dữ liệu từ backend -->
                            <c:forEach var="po" items="${listpromotion}">
                                <option value="${po.id}">${po.id} - ${po.description}</option>
                            </c:forEach>
                        </select>

                        <label for="listAccount">Chọn tài khoản được nhận mã:</label>
                        <select id="listAccount" name="listAccount" multiple required>
                            <c:forEach var="account" items="${listAccount}">
                                <option value="${account.id}">
                                        ${account.username} - Tổng: ${account.tong}đ

                                </option>
                            </c:forEach>
                        </select>
                        <br>
                        <button type="submit">Phát mã</button>
                        <button onclick="closeGeneratePromoCode()">Đóng</button>
                    </form>
                </div>
                <div class="modal fade" id="PromotionWindow" tabindex="-1" aria-labelledby="PromotionWindowLabel"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="PromotionWindowLabel">Nhập thông tin khuyến mãi</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Đóng"></button>

                            </div>
                            <div class="modal-body">
                                <form action="addPromotion" method="post">
                                    <input type="hidden" id="poid" name="poid">

                                    <div class="mb-3">
                                        <label for="PromotionName" class="form-label">Tên khuyến mãi:</label>
                                        <input type="text" id="PromotionName" name="PromotionName" class="form-control"
                                               placeholder="Vui lòng nhập tên khuyến mãi..." required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="StartDate" class="form-label">Ngày bắt đầu:</label>
                                        <input type="date" id="StartDate" name="StartDate" class="form-control"
                                               required>

                                    </div>

                                    <div class="mb-3">
                                        <label for="EndDate" class="form-label">Ngày kết thúc:</label>
                                        <input type="date" id="EndDate" name="EndDate" class="form-control" required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="Value" class="form-label">Giá trị (%):</label>
                                        <input type="number" id="Value" name="Value" class="form-control"
                                               placeholder="Nhập giá trị khuyến mãi (%)..." required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="Quantity" class="form-label">Số lượng:</label>
                                        <input type="number" id="Quantity" name="Quantity" class="form-control"
                                               placeholder="Nhập Số lượng..." required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="Describe" class="form-label">Mô tả:</label>
                                        <input type="text" id="Describe" name="Describe" class="form-control"
                                               placeholder="Nhập Mô tả ..." required>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-success ButtonPromotion1">Lưu</button>
                                        <button type="button" class="btn btn-secondary ButtonPromotion2"
                                                data-bs-dismiss="modal" onclick="closePromotion()">Hủy
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${role.hasPermission('MANAGE_PRODUCT_PROMOTION')}">
            <div class="AdminListProductDiscount select mt-4">
                <div class="d-flex justify-content-between align-items-center">
                    <h2>Quản lý sản phẩm giảm giá</h2>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductDiscount">Thêm sản
                        phẩm giảm giá
                    </button>
                </div>
                <table id="productTable" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Tên sản phẩm</th>
                        <th>Loại giảm giá</th>
                        <th>Phần trăm giảm giá</th>
                        <th>Giảm giá theo tiền tệ</th>
                        <th>Gía sau khi giảm</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="productDiscount" items="${productWithDiscount}">
                        <tr>
                            <td>${productDiscount.id}</td>
                            <td>${productDiscount.nameProduct}</td>
                            <td>${productDiscount.discoutType}</td>
                            <td>${productDiscount.discountPercentage}</td>
                            <td>${productDiscount.price}</td>
                            <td>abc</td>
                            <td>${productDiscount.startDate}</td>
                            <td>${productDiscount.endDate}</td>
                            <td>
                                <button  class="btn btn-primary" onclick="deleteProductDiscount(${productDiscount.id})">Xóa sản phẩm giảm giá
                                </button>
                                <button class="btn btn-primary" onclick="getProductById(${productDiscount.prouctId})">
                                    Cập
                                    nhật sản phẩm giảm giá
                                </button>
                                    <%--                            <button onclick="updateProductDiscount(${productDiscount.id})"></button>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </c:if>

        <c:if test="${role.hasPermission('MANAGE_STOCK')}">
            <div class="AdminListStock select mt-4">
                <div class="d-flex justify-content-between align-items-center">
                    <h2>Quản lý kho hàng</h2>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addStock">Thêm sản
                        phẩm vào kho hàng
                    </button>

                </div>
                <table id="stockTable" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Tên Kho hàng</th>
                        <th>Địa chỉ</th>
                        <th>Quận</th>
                        <th></th>
                    </tr>

                    </thead>

                    <tbody>
                    <c:forEach var="stock" items="${stocks}">
                        <tr>
                            <td>${stock.id}</td>
                            <td>${stock.productId}</td>
                            <td>${stock.quantity}</td>
                            <td>${stock.name}</td>
                            <td>${stock.addressLine }</td>
                            <td>${stock.district}</td>
                            <td>
                                <button onclick="deleteStock(${stock.id})">Xóa sản phẩm kho hàng
                                </button>
                                <button class="btn btn-primary" onclick="getStockById(${stock.id})">Cật
                                    nhật kho hàng

                                </button>
                                    <%--                            <button onclick="updateProductDiscount(${productDiscount.id})"></button>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${role.hasPermission('MANAGE_RETURN')}">
            <div class="AdminReturnList select mt-4">
                <div class="d-flex justify-content-between align-items-center">
                    <h2>Quản lý đơn đổi trả</h2>
                </div>

                <table id="returnTable" class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mã đơn hàng</th>
                        <th>Người dùng</th>
                        <th>Lý do đổi trả</th>
                        <th>Ảnh</th>
                        <th>Trạng Thái</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="returnRequest" items="${returnRequests}">
                        <tr>
                            <td>${returnRequest.id}</td>
                            <td>${returnRequest.orderId}</td>
                            <td>${returnRequest.userId}</td>
                            <td>${returnRequest.reason}</td>
                            <td>
                                <c:forEach var="img" items="${returnRequest.imagePaths}">
                                    <img src="${img}" style="max-height: 80px; margin-right: 5px;"/>
                                </c:forEach>
                            </td>
                            <td>
                                    ${returnRequest.status}
                            <td>
                            <td>
                                <button class="btn btn-success"
                                        onclick="showProcessModal(${returnRequest.id}, '${returnRequest.reason}', '${returnRequest.orderId}')">
                                    Xử lý
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </c:if>

    </div>
</div>
<!-- Modal xử lý đổi trả -->
<div class="modal fade" id="processReturnModal" tabindex="-1" aria-labelledby="processModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form id="returnProcessForm" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Xử lý đơn đổi trả</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="returnId" id="modal-returnId">
                    <div class="mb-3">
                        <label for="modal-userReason" class="form-label">Lý do từ khách hàng:</label>
                        <textarea class="form-control" id="modal-userReason" readonly></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="adminResponse" class="form-label">Phản hồi từ Admin:</label>
                        <textarea class="form-control" name="adminResponse" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Trạng thái xử lý:</label><br>
                        <input type="radio" name="status" value="accepted" required> Chấp nhận
                        <input type="radio" name="status" value="rejected" required style="margin-left: 20px;"> Từ chối
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="orderId" id="modal-orderId">
                    <button type="submit" class="btn btn-primary">Gửi phản hồi</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                </div>
            </div>
        </form>
    </div>
</div>


<div class="modal fade" id="addStock" tabindex="-1" aria-labelledby="addStockModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addStockModalLabel">Thêm kho hàng</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <div id="uploadSection" class="d-flex align-items-center gap-2 mt-3" style="display: none;">
                    <input type="file" id="input-excel" class="form-control"/>
                    <div>

                        <button class="btn btn-secondary" onclick="addStock()">Thêm</button>
                    </div>
                </div>
            </div>
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


<div class="modal fade" id="updateProductDiscount" tabindex="-1" aria-labelledby="addPromotionModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateProductDiscountModalLabel">Cật nhật Sản phẩm giảm giá</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="updateProductDiscountForm">
                    <input type="hidden" id="idProductWithDiscount"/>
                    <div class="mb-3">
                        <label for="productSelect" class="form-label">Chọn sản phẩm</label>
                        <select class="form-control" id="updateProductSelect"
                                required>
                            <option value="">-- Tìm sản phẩm --</option>
                            <!-- Các option sẽ được load bằng Ajax -->
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="discountType" class="form-label">Loại giảm giá</label>
                        <select class="form-control" id="updateDiscountType">
                            <option value="percentage">Phần trăm</option>
                            <option value="fixed">Giảm giá cố định</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="discountPercent" class="form-label">Phần trăm giảm giá (%)</label>
                        <input type="number" class="form-control" id="updateDiscountPercent" min="0" max="100" required>
                    </div>
                    <div class="mb-3">
                        <label for="productDiscountPrice" class="form-label">Gỉam giá cố định</label>
                        <input type="number" class="form-control" id="updateProductDiscountPrice" required>
                    </div>
                    <div class="mb-3">
                        <label for="startDate" class="form-label">Ngày bắt đầu</label>
                        <input type="datetime-local" class="form-control" id="updateStartDateDiscountPrice" required>
                    </div>
                    <div class="mb-3">
                        <label for="endDate" class="form-label">Ngày kết thúc</label>
                        <input type="datetime-local" class="form-control" id="updateEndDateDiscountPrice" required>
                    </div>
                    <button type="submit" class="btn btn-success">Cật nhật</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="uploadSection" class=" align-items-center gap-2 mt-3" style="display: none;">
    <input type="file" id="input-excel" class="form-control" style="max-width: 250px;"/>
    <button class="btn btn-secondary" onclick="addStock()">Thêm</button>
</div>


<div class="modal fade" id="editStockModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <form id="editStockForm">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Cập nhật kho hàng</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="editStockId">
                    <input type="hidden" id="editProductId">

                    <div class="mb-3">
                        <label class="form-label">Tên kho:</label>
                        <input type="text" id="editName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Địa chỉ:</label>
                        <input type="text" id="editAddressLine" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Quận/Huyện:</label>
                        <input type="text" id="editDistrict" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tỉnh/Thành phố:</label>
                        <input type="text" id="editStateOrProvince" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Quốc gia:</label>
                        <input type="text" id="editCountry" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Số lượng:</label>
                        <input type="number" id="editQuantity" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
                    <button class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                </div>
            </div>
        </form>
    </div>
</div>


</div>
<script type="text/javascript">
    let userPermissions = []; // Khởi tạo trước để tránh lỗi undefined

    async function loadPermissions() {
        try {
            const response = await fetch('http://localhost:8080/web/getPermissions', {
                method: 'GET',
                credentials: 'include' // Đảm bảo gửi cookie/session
            });
            if (!response.ok) {
                throw new Error(`Lỗi tải quyền: ${response.status} - ${response.statusText}`);
            }
            const data = await response.json();
            userPermissions = Array.isArray(data) ? data : (data.permissions || []); // Gán giá trị
            console.log('Quyền đã tải:', userPermissions);
            return userPermissions;
        } catch (error) {
            console.error('Lỗi tải quyền:', error);
            userPermissions = ["VIEW_DASHBOARD"]; // Mặc định khi lỗi
            return userPermissions;
        }
    }

    window.onload = async function () {
        try {
            await loadPermissions(); // Đợi quyền được tải
            const runScript = '<%= (String) request.getAttribute("runScript") %>';
            console.log('runScript:', runScript);
            if (runScript && runScript !== 'null' && runScript !== '') {
                navigationbarClick(runScript);
            } else {
                const defaultOptions = [
                    {option: "option1", permission: "VIEW_DASHBOARD"},
                    {option: "option2", permission: "MANAGE_VEGETABLES"},
                    {option: "option3", permission: "MANAGE_USERS"},
                    {option: "option4", permission: "MANAGE_ORDERS"},
                    {option: "option5", permission: "MANAGE_PROMOTIONS"},
                    {option: "option6", permission: "MANAGE_PRODUCT_PROMOTION"},
                    {option: "option7", permission: "MANAGE_STOCK"}

                ];
                const validOption = defaultOptions.find(opt => userPermissions.includes(opt.permission));
                navigationbarClick(validOption ? validOption.option : "option1");
            }
        } catch (error) {
            console.error('Lỗi trong window.onload:', error);
        }
    };

    // Đảm bảo hàm navigationbarClick kiểm tra userPermissions
    function navigationbarClick(option) {
        if (!userPermissions || !Array.isArray(userPermissions)) {
            console.error('userPermissions không hợp lệ:', userPermissions);
            return;
        }
        const defaultOptions = [
            {option: "option1", permission: "VIEW_DASHBOARD"},
            {option: "option2", permission: "MANAGE_VEGETABLES"},
            {option: "option3", permission: "MANAGE_USERS"},
            {option: "option4", permission: "MANAGE_ORDERS"},
            {option: "option5", permission: "MANAGE_PROMOTIONS"},
            {option: "option6", permission: "MANAGE_PRODUCT_PROMOTION"},
            {option: "option7", permission: "MANAGE_STOCK"},
            {option: "option8", permission: "MANAGE_RETURN"}


        ];
        const selectedOption = defaultOptions.find(opt => opt.option === option);
        if (selectedOption && userPermissions.includes(selectedOption.permission)) {
            // Thực hiện logic hiển thị
            console.log(`Chọn option: ${option}`);
        } else {
            console.warn(`Thiếu quyền ${selectedOption ? selectedOption.permission : 'không xác định'} cho option ${option}`);
        }
    }
</script>

<%--them san pham khuyen mai--%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0/dist/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Admin.js"></script>
<script>
    function deleteStock(id) {
        if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này?")) {
            fetch('/web/deleteStock', {
                method: 'DELETE',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({id: id})
            }).then(res => res.json())
                .then(data => {
                    alert("Xóa thành công!");
                    location.reload();
                }).catch(err => console.error(err));
        }
    }

    function getStockById(id) {
        fetch(`/web/getStock?id=` + id)
            .then(res => res.json())
            .then(data => {
                document.getElementById('editStockId').value = data.id;
                document.getElementById('editProductId').value = data.productId;
                document.getElementById('editName').value = data.name;
                document.getElementById('editAddressLine').value = data.addressLine;
                document.getElementById('editDistrict').value = data.district;
                document.getElementById('editStateOrProvince').value = data.stateOrProvince;
                document.getElementById('editCountry').value = data.country;
                document.getElementById('editQuantity').value = data.quantity;

                let modal = new bootstrap.Modal(document.getElementById('editStockModal'));
                modal.show();
            });
    }

    document.getElementById('editStockForm').addEventListener('submit', function (e) {
        e.preventDefault();

        const payload = {
            id: parseInt(document.getElementById('editStockId').value),
            productId: parseInt(document.getElementById('editProductId').value),
            name: document.getElementById('editName').value,
            addressLine: document.getElementById('editAddressLine').value,
            district: document.getElementById('editDistrict').value,
            stateOrProvince: document.getElementById('editStateOrProvince').value,
            country: document.getElementById('editCountry').value,
            quantity: parseInt(document.getElementById('editQuantity').value)
        };

        fetch('/web/updateStock', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(payload)
        }).then(res => res.json())
            .then(data => {
                alert(data.message);
                bootstrap.Modal.getInstance(document.getElementById('editStockModal')).hide();
                location.reload();
            }).catch(err => {
            console.error(err);
            alert("Có lỗi xảy ra khi cập nhật kho.");
        });
    });


    function showProcessModal(returnId, reason, orderId) {
        document.getElementById('modal-returnId').value = returnId;
        document.getElementById('modal-userReason').value = reason;
        document.getElementById('modal-orderId').value = orderId;
        let modal = new bootstrap.Modal(document.getElementById('processReturnModal'));
        modal.show();
    }

    let extractData = []
    document.getElementById("input-excel").addEventListener("change", function (e) {
        const file = e.target.files[0];
        if (!file) return;

        const reader = new FileReader();
        reader.onload = function (env) {
            const data = env.target.result;
            const workbook = XLSX.read(data, {type: 'binary'});
            const firstSheetName = workbook.SheetNames[0];
            const worksheet = workbook.Sheets[firstSheetName];
            const extractedData = XLSX.utils.sheet_to_json(worksheet);
            extractData = extractedData;
            console.log("Extracted:", extractedData); // ✅ Should appear
        };

        reader.readAsBinaryString(file); // ✅ Required
    });

    function addStock() {
        $.ajax({
            url: "/web/UploadStock",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({stocks: extractData}),
            success: function (response) {
                alert(response)
            },
            error: function (error) {
                console.log(error);
            }
        })
    }


    document.getElementById("returnProcessForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Ngăn submit mặc định

        const returnId = document.getElementById("modal-returnId").value;
        const adminResponse = this.adminResponse.value;
        const status = this.status.value;
        const orderId = document.getElementById("modal-orderId").value;
        // Gọi API POST
        fetch("/web/ReturnProcessSubmit", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                returnId: returnId,
                adminResponse: adminResponse,
                status: status,
                orderId: orderId
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Lỗi khi gửi phản hồi");
                }
                return response.json();
            })
            .then(data => {
                alert("Xử lý thành công!");
                // Tùy chọn: reload lại trang hoặc đóng modal
                document.getElementById("returnProcessForm").reset();
                const modal = bootstrap.Modal.getInstance(document.getElementById("processReturnModal"));
                modal.hide();
                location.reload(); // hoặc cập nhật danh sách đổi trả
            })
            .catch(error => {
                alert("Đã xảy ra lỗi: " + error.message);
            });
    });

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


                data.products.forEach(product => {
                    let option = document.createElement("option");
                    option.value = product.id;
                    option.textContent = product.name + " - " + product.price + " VND";
                    productSelect.append(option);
                });

            },
            error: function () {
                alert("Không thể tải danh sách sản phẩm!");
            }
        });
    }

    $(document).ready(function () {
        $('#discountType').change(function () {
            let discountType = $(this).val();

            if (discountType === 'percentage') {
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

            let DiscountType = daysDifference < 24 ? "HOURLY" : "DAILY";
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
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(formData)
            })
                .then(alert("Thêm thành công"))
                .then(data => {
                    alert(data);
                    location.reload();
                    $('addProductDiscount').modal('hide')
                })
                .catch(error => console.error(error))
        })
    })

    function deleteProductDiscount(id) {
        console.log('test', id)
        const idnew = id;
        $.ajax({
            url: `/web/deleteProductDiscount`,
            type: 'POST',
            data: JSON.stringify({id: idnew}),
            contentType: 'application/json',
            success: function (response) {
                if (response.success) {
                    alert("Xóa sản phẩm thành công!");
                    location.reload();
                } else {
                    alert("Xóa sản phẩm thất bại.");
                }
            }

        })

    }

    let isUpdateProductLoaded = false;
    // $(document).ready(function () {
    //     loadUpdateProduct(); // Chỉ gọi 1 lần khi trang tải xong
    // });


    function loadUpdateProduct(callback) {
        let productSelect = document.getElementById("updateProductSelect"); // Chuyển về DOM element
        if (isUpdateProductLoaded) {
            return; // Nếu đã load rồi thì không load lại nữa
        }
        isUpdateProductLoaded = true;
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


                data.products.forEach(product => {
                    let option = document.createElement("option");
                    option.value = product.id;
                    option.textContent = product.name + " - " + product.price + " VND";
                    productSelect.append(option);
                });
                if (callback) {
                    callback(data);
                }


            },
            error: function () {
                alert("Không thể tải danh sách sản phẩm!");
            }
        });
    }


    //     update product discount
    function getProductById(productId) {

        $.ajax({
            url: `/web/getProductById?productId=` + productId, // 👈 Pass productId as a query param
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {
                let modal = new bootstrap.Modal(document.getElementById('updateProductDiscount'));
                modal.show();
                // let select = document.getElementById('updateProductSelect option');
                // select.value = data.id;
                document.getElementById('idProductWithDiscount').value = data.id
                loadUpdateProduct(function () {
                    let select = document.querySelector("#updateProductSelect");
                    let options = document.querySelectorAll("#updateProductSelect option");
                    options.forEach((item) => {
                        if (item.value.toString() === data.prouctId.toString()) {
                            item.selected = true;
                        }
                    })
                });

                if (data.discountPercentage > 0) {

                    document.getElementById('updateDiscountType').value = "percentage";
                } else {
                    document.getElementById('updateDiscountType').value = "fixed";

                }

                document.getElementById('updateDiscountPercent').value = data.discountPercentage;


                document.getElementById('updateProductDiscountPrice').value = data.price;
                document.getElementById('updateStartDateDiscountPrice').value = data.startDate;
                document.getElementById('updateEndDateDiscountPrice').value = data.endDate;
            },
            error: function (xhr, status, error) {
                console.error('Error fetching product by ID:', error);
            }
        });
    }


    document.getElementById('updateProductDiscountForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the form from submitting the traditional way


        let startDate = new Date(document.getElementById("updateStartDateDiscountPrice").value);
        let endDate = new Date(document.getElementById("updateEndDateDiscountPrice").value);
        let timeDifference = endDate - startDate;
        let hoursDifference = timeDifference / (1000 * 60 * 60);
        let daysDifference = timeDifference / (1000 * 60 * 60 * 24);

        let DiscountType = daysDifference < 24 ? "HOURLY" : "DAILY";
        let formData = {
            idProductWithDiscount: document.getElementById('idProductWithDiscount').value,
            productId: document.getElementById('updateProductSelect').value,
            discountType: DiscountType,
            discountPercent: document.getElementById('updateDiscountPercent').value,
            discountPrice: document.getElementById('updateProductDiscountPrice').value,
            startDate: document.getElementById('updateStartDateDiscountPrice').value,
            endDate: document.getElementById('updateEndDateDiscountPrice').value
        };

        // Send data via AJAX for updating
        $.ajax({
            url: '/web/updateProductDiscount', // Your API endpoint for updating product discount
            type: 'POST', // Use POST to submit form data
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                console.log(response)
                if (response.success) {
                    alert('Cập nhật sản phẩm giảm giá thành công');
                    location.reload(); // Reload page or update UI as needed
                } else {
                    alert('Lỗi khi cập nhật sản phẩm giảm giá');
                }
            },
            error: function (xhr, status, error) {
                console.error('Error updating product discount:', error);
                alert('Có lỗi xảy ra khi cập nhật');
            }
        });
    });


</script>
</body>
</html>
