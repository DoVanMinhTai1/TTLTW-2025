<%@ page import="vn.edu.hcmuaf.fit.projectwebck.dao.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession();
    String runScript = request.getParameter("runScript");
    User user =(User) session.getAttribute("user");

    if(user == null){
        response.sendRedirect("showLogin");
        return;
    }
    if(user.getRole() !=1 ){
        response.sendRedirect("showHome");
        return;
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/Admin.js"></script>
</body>
</html>
