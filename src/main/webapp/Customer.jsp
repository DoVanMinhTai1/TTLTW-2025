<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/6/2024
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String runScript = request.getParameter("runScript");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script type="text/javascript" src="${pageContext.request.contextPath}/Customer.js" defer></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Customer.css">
</head>
<body>
<div class="CustomerPage">
    <div class="Header">
        <div class="Container">
            <a href="showHome"><img id="logo" src="Img/snapedit_1730861562696.png" alt="Shopping Cart Image"
                                    style="width: 150px"></a>
            <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">
            <i class="fas fa-phone"></i>
            <div class="headerphone">HOTLINE: 0327237467</div>
            <c:set var="currentUser" value="${sessionScope.user}"/> <!-- Lấy user từ session -->
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
            <div class="shopping_cart">
                <div class="shopping_cart_swap">
                    <i class="fa-solid fa-basket-shopping"></i>
                    <span class="shopping_notice"></span>
                </div>
            </div>
            <div class="shoppingtext"><a href="ShowCart">Giỏ hàng</a></div>
        </div>
    </div>
    <div class="pathline"></div>
    <c:set var="currentUser" value="${sessionScope.user}"/> <!-- Lấy user từ session -->
    <div class="CustomerPageConttent">
        <div class="Container">
            <div class="CustomerPageConttentHeader">
                <span>Trang chủ</span>
                <span>/</span>
                <span class="text">Trang khách hàng</span>
            </div>
            <div class="CustomerPageConttenNavigationbar">
                <div class="NavigationbarTitle">TRANG TÀI KHOẢN</div>
                <div class="NavigationbarHello">Xin chào, <span>${currentUser.username}</span> !</div>
                <a href="showCustomer?option=option1&uId=${sessionScope.user.id}">
                    <div class="NavigationbarSelect" id="option1" onclick="navigationbarClick('option1')">Thông tin tài
                        khoản
                    </div>
                </a>
                <a href="showCustomer?option=option2&uId=${sessionScope.user.id}">
                    <div class="NavigationbarSelect" id="option2" onclick="navigationbarClick('option2')">Đơn hàng của
                        bạn
                    </div>
                </a>
                <a href="showCustomer?option=option3&uId=${sessionScope.user.id}">
                    <div class="NavigationbarSelect" id="option3" onclick="navigationbarClick('option3')">Đổi mật khẩu
                    </div>
                </a>
                <a href="showCustomer?option=option4&uId=${sessionScope.user.id}">
                    <div class="NavigationbarSelect" id="option4" onclick="navigationbarClick('option4')">Sổ địa chỉ
                    </div>
                </a>
                <a href="showCustomer?option=option5&uId=${sessionScope.user.id}">
                    <div class="NavigationbarSelect" id="option5" onclick="navigationbarClick('option5')">Đăng xuất
                    </div>
                </a>
            </div>
            <div class="CustomerPageConttenNavigationbarContent" id="CustomerPageConttenNavigationbarContent">
                <div class="AccountInformation select">
                    <div class="AccountInformationTitle">THÔNG TIN TÀI KHOẢN</div>
                    <div class="AccountInformationContent">Họ tên: <span id="nameAccount">${currentUser.fullName}</span>
                    </div>
                    <div class="AccountInformationContent">Email: <span id="emailAccount">${currentUser.email}</span>
                    </div>
                    <div class="AccountInformationContent">Điện thoại: <span
                            id="phoneAccount">${currentUser.phone}</span></div>
                    <button id="editButton" onclick="editAccountInf(${currentUser.id})">Chỉnh sửa</button>
                </div>
                <div class="AccountListPromotion select">
                    <div class="ListPromotionTitle">MÃ GIẢM GIÁ CỦA BẠN</div>
                    <table class="TablePromotion">
                        <thead>
                        <tr>
                            <th>Mã Khuyễn Mãi</th>
                            <th>Tên Khuyễn Mãi</th>
                            <th>Ngày Bắt Đầu</th>
                            <th>Ngày Kết Thúc</th>
                            <th>Mô tả</th>
                        </tr>
                        </thead>
                        <tbody id="ListPromotionTableBody">
                        <!-- Các đơn hàng sẽ được chèn vào đây -->
                        <c:forEach var="promotion" items="${listpromotion}">
                            <tr class="Promotion_item">
                                <td><span class="ListPromotion_Id">${promotion.id}</span></td>
                                <td><span class="ListPromotionName">${promotion.name}</span></td>
                                <td><span class="ListPromotion_StartDate">${promotion.startDate}</span></td>
                                <td><span class="ListPromotion_EndDate">${promotion.endDate}</span></td>
                                <td><span class="ListPromotionDescribe">${promotion.description}</span></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="YourOrder select">
                    <div class="d-flex" style="display: flex; justify-content: space-between">
                        <div class="YourOrderTitle" style="width: 100%; text-align: center;">ĐƠN HÀNG CỦA BẠN</div>

                    </div>
                    <div class="filter-section" style="justify-content: end;  margin-bottom: 20px;
    font-size: 16px;">

                        <label for="orderStatusFilter">Lọc theo trạng thái:</label>
                        <select id="orderStatusFilter" onchange="filterOrdersByStatus()" style="padding: 6px 10px;
    font-size: 15px;
    border-radius: 6px;">
                            <option value="all">Tất cả</option>
                            <option value="0">Chờ xác nhận</option>
                            <option value="1">Đã xác nhận</option>
                            <option value="2">Đang đóng gói</option>
                            <option value="3">Đang vận chuyển</option>
                            <option value="4">Hoàn tất</option>
                            <option value="5">Đã hủy</option>
                            <option value="6">Đổi trả</option>
                        </select>
                    </div>

                    <table>
                        <thead>
                        <tr>
                            <th>Mã Vận Đơn</th>
                            <th>Địa Chỉ</th>
                            <th>Ngày Đặt</th>
                            <th>Trạng thái</th>
                            <th>Tổng Thanh Toán</th>
                            <th>Chi Tiết</th>
                            <th>Xuất pdf</th>
                            <th>Đổi Trả</th>
                        </tr>
                        </thead>
                        <tbody id="OrderTableBody">
                        <c:forEach var="order" items="${orders}">
                            <tr class="Order_item">
                                <td><span class="Order_Id">${order.id}</span></td>
                                <td><span class="OrderAddress">${order.address}</span></td>
                                <td><span class="Order_DateBooked">${order.dateOfBooking}</span></td>
                                <td><span class="Order_DeliveryDate status-${order.status}">  <c:choose>
                                    <c:when test="${order.status == 0}">Chờ xác nhận</c:when>
                                    <c:when test="${order.status == 1}">Đã xác nhận</c:when>
                                    <c:when test="${order.status == 2}">Đang đóng gói</c:when>
                                    <c:when test="${order.status == 3}">Đang vận chuyển</c:when>
                                    <c:when test="${order.status == 4}">Hoàn tất</c:when>
                                    <c:when test="${order.status == 5}">Đã hủy</c:when>
                                    <c:when test="${order.status == 6}">Đang chờ xử lý từ đổi trả</c:when>
                                    <c:when test="${order.status == 7}">Chấp thuận đổi trả</c:when>
                                    <c:when test="${order.status == 8}">Từ chối đổi trả</c:when>
                                    <c:otherwise>Không xác định</c:otherwise>
                                </c:choose></span></td>
                                <td><span class="Order_Money">${order.money}đ</span></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.status == 6}">

                                            <button onclick="showWordReturn(${order.status})">
                                                Xem
                                            </button>
                                        </c:when>
                                        <c:when test="${order.status == 7}">

                                            <button onclick="showWordReturn(${order.status})">
                                                Xem
                                            </button>
                                        </c:when>
                                        <c:when test="${order.status == 8}">

                                            <button onclick="showWordReturn(${order.status})">
                                                Xem
                                            </button>
                                        </c:when>
                                        <c:otherwise>

                                            <div class="Detail"
                                                 onclick="viewOrder('${order.id}','${order.address}','${order.dateOfBooking}','${order.status}','${sessionScope.user.id}')">
                                                <i class="fa-regular fa-eye"></i><span>Xem</span>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                </td>
                                <td>
                                    <button class="ExportBtn" onclick="exportPdf(${order.id})">
                                        <i class="fa-solid fa-file-pdf"></i> PDF
                                    </button>
                                </td>
                                <c:if test="${order.status == 4}">
                                    <td>
                                        <button class="ReturnBtn" onclick="openReturnModal(${order.id})">
                                            <i class="fa-solid fa-rotate-left"></i> Đổi/Trả
                                        </button>
                                    </td>
                                </c:if>


                            </tr>
                        </c:forEach>
                        </tbody>
                        <div class="OderWindow" id="OderWindow">
                            <div class="iconClose" onclick="viewOrderClose()"><i class="fa-solid fa-xmark"></i></div>
                            <div class="OderWindowTitle">Danh sách sản phẩm</div>
                            <div id="ProductItems" class="Product_List_item">

                            </div>
                            <div class="TotalAmount">
                                <span class="text">Tổng cộng</span>
                                <span class="total" id="total"></span>
                            </div>
                            <div class="DeliveryAddress">
                                <span class="text">Địa chỉ nhận hàng:</span>
                                <span class="delivery"></span>
                            </div>
                            <div class="DeliveryAddress">
                                <span class="text">Ngày đặt:<span class="deliveryDate"> </span></span>
                            </div>
                            <button class="confirm"></button>
                            <button class="cancelOrder"></button>
                        </div>
                    </table>
                </div>
                <form action="updatePassword" method="post">
                    <div class="ChangePassword select">
                        <div class="ChangePasswordTitle">ĐỔI MẬT KHẨU</div>
                        <div class="ChangePasswordReminder">Để đảm bảo tính bảo mật bạn vui lòng nhập mật khẩu trên 8 ký
                            tự,
                            bao gồm cả chữ hoa, chữ thường và ký tự đặc biệt!
                        </div>
                        <div class="ChangePasswordForm">
                            <label>Mật khẩu cũ *</label><br>
                            <input type="password" name="oldpassword" id="oldpassword"> <i class="fa-regular fa-eye"
                                                                                           id="icon"
                                                                                           onclick="togglePassword('oldpassword','icon')"></i><br>
                            <label>Mật khẩu mới *</label><br>
                            <input type="password" name="newpassword" id="newpassword"> <i class="fa-regular fa-eye"
                                                                                           id="icon1"
                                                                                           onclick="togglePassword('newpassword','icon1')"></i><br>
                            <label>Xác nhận lại mật khẩu *</label><br>
                            <input type="password" name="confirm-password" id="confirm-password"> <i
                                class="fa-regular fa-eye" id="icon2"
                                onclick="togglePassword('confirm-password','icon2')"></i><br>
                            <button type="submit">Đặt lại mật khẩu</button>
                        </div>
                        <c:if test="${not empty error}">
                            <script type="text/javascript">
                                alert("${error}");
                            </script>
                        </c:if>
                        <c:if test="${not empty success}">
                            <script type="text/javascript">
                                alert("${success}");
                            </script>
                        </c:if>
                    </div>
                </form>
                <div class="AddressBook select">
                    <div class="AddressBookHeader">
                        <div class="AddressBookTitle">ĐỊA CHỈ CỦA BẠN</div>
                        <button type="submit" class="button1" onclick="addAddress()">Thêm địa chỉ</button>
                    </div>
                    <c:forEach var="address" items="${listAddress}">
                        <div class="AddressBookDetail" style="display: flex;">
                            <div class="AddressBookDetailAphal">
                                <div class="AddressBookDetailAphalContent">Họ tên: <span>${address.name}</span></div>
                                <div class="AddressBookDetailAphalContent">Địa chỉ: <span>${address.address}</span>
                                </div>
                                <div class="AddressBookDetailAphalContent">Số điện thoại: <span>${address.phone}</span>
                                </div>
                                <div class="AddressBookDetailAphalContent">Công ty: <span>${address.company}</span>
                                </div>
                            </div>
                            <div class="AddressBookDetailOmega">
                                <span class="Edit" style="text-align: center;"
                                      onclick="UpdateAddress('${address.id}','${address.name}','${address.address}','${address.phone}','${address.origin}','${address.company}')">Chỉnh sửa địa chỉ</span>
                                <c:if test="${address.origin == 0}">
                                    <a  href="removeAddress?addressId=${address.id}" style="text-align: center;">
                                        <span  class="Delete">Xóa</span>
                                    </a>
                                    <button style="text-align: center; border: none; background-color: white" onclick="updateAddressOrigin(${address.id}, ${currentUser.id})">Đặt làm địa
                                        chỉ mặc định
                                    </button>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="Addnewaddress" id="Addnewaddress">
                        <div class="AddnewaddressTitle">THÊM ĐỊA CHỈ MỚI</div>
                        <div class="AddnewaddressContent">
                            <form action="addAddress" method="post">
                                <input type="hidden" id="addressId" name="addressId">
                                <input type="hidden" id="userId" name="userId" value="${sessionScope.user.id}">
                                <input type="text" name="Name" id="Name" placeholder="Họ và tên" class="form"><br>
                                <input type="text" name="Phone" id="Phone" placeholder="Số điện thoại" class="form"><br>
                                <input type="text" name="Company" id="Company" placeholder="Công ty" class="form"><br>
                                <input type="text" name="Address" id="Address" placeholder="Địa chỉ" class="form"><br>
                                <select name="Nation" id="Nation" style="width: 97%;height:44px;">
                                    <option value="" disabled selected>Quốc gia</option>
                                    <option value="Việt Nam">Việt Nam</option>
                                    <option value="Thái Lan">Thái Lan</option>
                                </select>
                                <select name="Province" id="Province" style="width: 31.5%;height:44px;"></select>
                                <select name="District" id="District" style="width: 31.5%;height:44px;"></select>
                                <select name="Wardandcommune" id="Wardandcommune"
                                        style="width: 31.5%;height:44px;"></select>
                                <div class="radiobutton" onclick="toggleRadioButton()" id="radiobutton"><i
                                        class="fa-solid fa-check" id="fa-check"></i></div>
                                <span onclick="toggleRadioButton()"
                                      class="radiobuttontext">Đặt địa chỉ làm mặc định?</span>
                                <input type="hidden" name="isDefault" id="isDefault" value="0">
                                <div class="ButtonSubmit">
                                    <button type="button" class="b1" onclick="closeAddress()">Hủy</button>
                                    <button type="submit" class="b2">Thêm địa chỉ</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal đổi trả hàng -->
<div class="modal fade" id="returnModal" tabindex="-1" aria-labelledby="returnModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form id="returnForm" enctype="multipart/form-data">
                <div class="modal-header">
                    <h5 class="modal-title" id="returnModalLabel">Yêu cầu đổi/trả hàng</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    <input type="hidden" name="orderId" id="returnOrderId">
                    <div class="mb-3">
                        <label for="returnReason" class="form-label">Lý do đổi/trả</label>
                        <textarea name="reason" id="returnReason" class="form-control" required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="returnImages" class="form-label">Hình ảnh (có thể chọn nhiều):</label>
                        <input type="file" name="images" id="returnImages" class="form-control" multiple
                               accept="image/*">
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Gửi yêu cầu</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="showWordReturn" tabindex="-1" aria-labelledby="returnInfoLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="returnInfoLabel">Trạng thái đổi trả</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
            </div>
            <div class="modal-body" id="returnStatusContent">
                <!-- Nội dung sẽ được cập nhật bằng JS -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>



<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script type="text/javascript">

    function showWordReturn(status) {
        let content = "";

        switch (status) {
            case 6:
                content = "Đơn đổi trả của bạn đang chờ xử lý. Vui lòng đợi nhân viên kiểm tra.";
                break;
            case 7:
                content = "Yêu cầu đổi trả của bạn đã được chấp thuận. Đơn vị vận chuyển sẽ liên hệ để nhận hàng tại địa chỉ giao hàng.";
                break;
            case 8:
                content = "Yêu cầu đổi trả đã bị từ chối. Vui lòng kiểm tra lại thông tin hoặc liên hệ hỗ trợ.";
                break;
            default:
                content = "Không xác định trạng thái đơn hàng.";
        }

        document.getElementById('returnStatusContent').textContent = content;
        const modal = new bootstrap.Modal(document.getElementById('showWordReturn'));
        modal.show();
    }


    window.onload = function () {
        // Kiểm tra xem runScript có khác null không
        <% if (runScript != null) { %>
        // Gọi hàm navigationbarClick và truyền giá trị runScript vào
        navigationbarClick('<%= runScript %>');
        <% } %>
    };

    function updateAddressOrigin(id, userId) {
        $.ajax({
            url: "updateAddressOrigin",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                id: id,
                userId: userId
            }),
            success: function () {
                document.getElementById('setOriginAddressModalBody').textContent = 'Đã đặt địa chỉ mặc định thành công!';
                const modal = new bootstrap.Modal(document.getElementById('setOriginAddressModal'));
                modal.show();

                // Optionally reload page after a short delay
                setTimeout(() => {
                    modal.hide();
                    location.reload();
                }, 2500);

            },
            error: function () {
                // Show error message
                document.getElementById('setOriginAddressModalBody').textContent = 'Có lỗi xảy ra khi cập nhật địa chỉ mặc định!';
                const modal = new bootstrap.Modal(document.getElementById('setOriginAddressModal'));
                modal.show();
            }
        })
    }

    function exportPdf(orderId) {
        if (!orderId) {
            alert("Cannot export PDF without an order ID.");
            return;
        }

        fetch('/web/exportPdf', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({orderId: orderId})
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to generate PDF');
                }
                return response.blob();
            })
            .then(blob => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = 'order.pdf';
                document.body.appendChild(a);
                a.click();
                a.remove();
                window.URL.revokeObjectURL(url);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Export failed');
            });
    }


    function filterOrdersByStatus() {
        const selectedStatus = document.getElementById("orderStatusFilter").value;
        const rows = document.querySelectorAll("#OrderTableBody .Order_item");

        rows.forEach(row => {
            const statusSpan = row.querySelector(".Order_DeliveryDate");
            const classList = statusSpan.classList;
            const matched = [...classList].find(c => c.startsWith("status-"));
            const statusCode = matched ? matched.replace("status-", "") : "";

            if (selectedStatus === "all" || selectedStatus === statusCode) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
    }

    function requestReturn(orderId) {
        const confirmReturn = confirm("Bạn có chắc chắn muốn yêu cầu đổi/trả đơn hàng #" + orderId + "?");

        if (confirmReturn) {
            // Gửi yêu cầu lên server
            fetch(`/web/return-request?orderId=${orderId}`, {
                method: 'POST'
            })
                .then(res => {
                    if (res.ok) {
                        alert("Yêu cầu đổi/trả đã được gửi!");
                    } else {
                        alert("Không thể gửi yêu cầu đổi/trả. Vui lòng thử lại.");
                    }
                });
        }
    }

    function openReturnModal(orderId) {
        document.getElementById('returnOrderId').value = orderId;
        const modal = new bootstrap.Modal(document.getElementById('returnModal'));
        modal.show();
    }

    document.getElementById('returnForm').addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(this);

        fetch('/web/ReturnRequest', {
            method: 'POST',
            body: formData
        })
            .then(res => {
                if (res.ok) {
                    alert("Đã gửi yêu cầu đổi/trả!");
                    const modal = bootstrap.Modal.getInstance(document.getElementById('returnModal'));
                    modal.hide();
                } else {
                    alert("Lỗi khi gửi yêu cầu.");
                }
            });
    });

</script>

</body>
</html>
