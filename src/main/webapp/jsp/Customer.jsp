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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Customer.css">
</head>
<body>
<div class="CustomerPage">
  <div class="Header">
    <div class="Container">
      <a href="../home.html"><img id="logo" src="Img/snapedit_1730861562696.png" alt="Shopping Cart Image"
                                  style="width: 150px"></a>
      <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?">
      <i class="fas fa-phone"></i>
      <div class="headerphone">HOTLINE: 0327237467</div>
      <div class="headercontendangnhap">Đăng nhập</div>
      <div class="line"></div>
      <div class="headercontendangki">Đăng kí</div>
      <div class="shopping_cart">
        <div class="shopping_cart_swap">
          <i class="fa-solid fa-basket-shopping"></i>
        </div>
      </div>
      <div class="shoppingtext"><a href="../ShoppingCart/ShoppingCart.html">Giỏ hàng</a></div>
    </div>
  </div>
  <div class="pathline"></div>
  <div class="CustomerPageConttent">
    <div class="Container">
      <div class="CustomerPageConttentHeader">
        <span>Trang chủ</span>
        <span>/</span>
        <span class="text">Trang khách hàng</span>
      </div>
      <div class="CustomerPageConttenNavigationbar">
        <div class="NavigationbarTitle">TRANG TÀI KHOẢN</div>
        <div class="NavigationbarHello">Xin chào, <span>Nguyễn Vỹ</span> !</div>
        <a href="showCustomer?option=option1">
          <div class="NavigationbarSelect" id="option1" onclick="navigationbarClick('option1')">Thông tin tài
            khoản
          </div>
        </a>
        <a href="showCustomer?option=option2">
          <div class="NavigationbarSelect" id="option2" onclick="navigationbarClick('option2')">Đơn hàng của
            bạn
          </div>
        </a>
        <a href="showCustomer?option=option3">
          <div class="NavigationbarSelect" id="option3" onclick="navigationbarClick('option3')">Đổi mật khẩu
          </div>
        </a>
        <a href="showCustomer?option=option4">
          <div class="NavigationbarSelect" id="option4" onclick="navigationbarClick('option4')">Sổ địa chỉ
          </div>
        </a>
        <a href="showCustomer?option=option5">
          <div class="NavigationbarSelect" id="option5" onclick="navigationbarClick('option5')">Đăng xuất
          </div>
        </a>
      </div>
      <div class="CustomerPageConttenNavigationbarContent" id="CustomerPageConttenNavigationbarContent">
        <div class="AccountInformation select">
          <div class="AccountInformationTitle">THÔNG TIN TÀI KHOẢN</div>
          <div class="AccountInformationContent">Họ tên: <span>${user.fullName}</span></div>
          <div class="AccountInformationContent">Email: <span>${user.email}</span></div>
          <div class="AccountInformationContent">Điện thoại: <span>${user.phone}</span></div>
        </div>
        <div class="YourOrder select">
          <div class="YourOrderTitle">ĐƠN HÀNG CỦA BẠN</div>
          <table>
            <thead>
            <tr>
              <th>Mã Vận Đơn</th>
              <th>Địa Chỉ</th>
              <th>Ngày Đặt</th>
              <th>Trạng thái</th>
              <th>Tổng Thanh Toán</th>
              <th>Chi Tiết</th>
            </tr>
            </thead>
            <tbody id="OrderTableBody">
            <c:forEach var="order" items="${orders}">
              <tr class="Order_item">
                <td><span class="Order_Id">${order.id}</span></td>
                <td><span class="OrderAddress">${order.address}</span></td>
                <td><span class="Order_DateBooked">${order.dateOfBooking}</span></td>
                <td><span class="Order_DeliveryDate"> <c:choose>
                  <c:when test="${order.status == 1}">
                    <i class="fa-solid fa-circle-check"></i>
                  </c:when>
                  <c:otherwise>
                    <i class="fa-solid fa-truck-fast"></i>
                  </c:otherwise>
                </c:choose></span></td>
                <td><span class="Order_Money">${order.money}đ</span></td>
                <td>
                  <div class="Detail" onclick="viewOrder(${order.id})">
                    <i class="fa-regular fa-eye"></i><span>Xem</span>
                  </div>
                </td>
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
            <div class="AddressBookDetail">
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
                                <span class="Edit"
                                      onclick="UpdateAddress('${address.id}','${address.name}','${address.address}','${address.phone}','${address.origin}','${address.company}')">Chỉnh sửa địa chỉ</span>
                <c:if test="${address.origin == 0}">
                  <a href="removeAddress?addressId=${address.id}">
                    <span class="Delete">Xóa</span>
                  </a>
                </c:if>
              </div>
            </div>
          </c:forEach>
          <div class="Addnewaddress" id="Addnewaddress">
            <div class="AddnewaddressTitle">THÊM ĐỊA CHỈ MỚI</div>
            <div class="AddnewaddressContent">
              <form action="addAddress" method="post">
                <input type="hidden" id="addressId" name="addressId">
                <input type="text" name="Name" id="Name" placeholder="Họ và tên" class="form"><br>
                <input type="text" name="Phone" id="Phone" placeholder="Số điện thoại" class="form"><br>
                <input type="text" name="Company" id="Company" placeholder="Công ty" class="form"><br>
                <input type="text" name="Address" id="Address" placeholder="Địa chỉ" class="form"><br>
                <select name="Nation" id="Nation" style="width: 97%;height:44px;">
                  <option value="" selected>Quốc gia</option>
                  <option value="Việt Nam"> Việt Nam</option>
                  <option value="Thái Lan">Thái Lan</option>
                  <option value="Campuchia">Campuchia</option>
                </select>
                <select name="Province" id="Province" style="width: 31.5%;height:44px;">
                  <option value="" disabled selected>Tỉnh thành</option>
                  <option value="KonTum"> Kon Tum</option>
                  <option value="Thái Bình">Thái Bình</option>
                  <option value="Hưng Yên">Hưng Yên</option>
                </select>
                <select name="District" id="District" style="width: 31.5%;height:44px;">
                  <option value="" disabled selected>Quận huyện</option>
                  <option value="KonPlong"> KonPlong</option>
                  <option value="Quận 1">Quận 1</option>
                  <option value="KrongPak">KrongPak</option>
                </select>
                <select name="Wardandcommune" id="Wardandcommune" style="width: 31.5%;height:44px;">
                  <option value="" disabled selected>Phường xã</option>
                  <option value="Hiệp Bình">Hiệp Bình</option>
                  <option value="An Long">An Long</option>
                  <option value="Hoài Nhơn">Hoài Nhơn</option>
                </select>
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
<script type="text/javascript">
  window.onload = function () {
    // Kiểm tra xem runScript có khác null không
    <% if (runScript != null) { %>
    // Gọi hàm navigationbarClick và truyền giá trị runScript vào
    navigationbarClick('<%= runScript %>');
    <% } %>
  };
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Customer.js"></script>
</body>
</html>
