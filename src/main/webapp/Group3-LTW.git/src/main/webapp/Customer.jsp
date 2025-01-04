<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/6/2024
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
   <style>
       html, body {
           padding: 0;
           margin: 0;
           font-size: 16px;
           color: black;
           font-family: sans-serif, Tahoma;
       }

       .Container {
           width: 1200px;
           margin-left: auto;
           margin-right: auto;
       }

       .Header .Container {
           display: flex;
       }

       #logo {
           margin-left: 20px;
       }

       #search {
           width: 300px;
           height: 30px;
           border: solid 1px gray;
           border-radius: 35px;
           padding-left: 15px;
           margin: 20px 50px 0 130px;
       }

       .fas {
           margin-right: 5px;
           margin-top: 30px;
           color: #7cc652;
       }

       .headerphone {
           margin: 30px 50px 0 0;
           color: #7cc652;
       }

       .headercontendangnhap {
           margin: 30px 5px;
           color: gray;
       }

       .headercontendangki {
           margin: 30px 30px 0 5px;
           color: gray;
       }

       .line {
           border: solid 1px gray;
           height: 15px;
           margin-top: 30px;
       }

       .shopping_cart .fa-solid {
           margin-top: 30px;
           color: #7cc652;
           margin-right: 5px;
       }

       .shoppingtext {
           margin-top: 30px;
           color: #7cc652;
       }
       .shoppingtext a{
           text-decoration: none;
           color: #7cc652;
       }
       .pathline {
           width: 100%;
           border: solid 1px gray;
       }

       .CustomerPageConttentHeader {
           margin: 20px 0 30px 0;
       }

       .CustomerPageConttentHeader .text {
           color: #7cc652;
       }

       .NavigationbarTitle {
           font-size: 20px;
           margin-bottom: 10px;
       }

       .NavigationbarHello span {
           color: #7cc652;
       }

       .NavigationbarHello {
           font-weight: 600;
       }

       .NavigationbarHello {
           margin-bottom: 15px;
       }

       .NavigationbarSelect {
           margin-bottom: 15px;
           cursor: pointer;
       }

       .NavigationbarSelect.active {
           color: #7cc652; /* Màu khi được chọn */
       }

       .NavigationbarSelect:hover {
           color: #7cc652;
       }

       .CustomerPageConttenNavigationbar {
           width: 30%;
           float: left;
       }

       .CustomerPageConttenNavigationbarContent {
           width: 70%;
           float: right;
           position: relative;

       }

       /*Thong tin tai khoan*/

       .AccountInformationTitle {
           font-size: 20px;
           margin-bottom: 10px;
       }

       .AccountInformationContent {
           margin-bottom: 15px;
           font-weight: 600;
       }

       .AccountInformationContent span {
           font-weight: normal;
       }

       /*Don hang cua ban*/
       .YourOrderTitle {
           font-size: 20px;
           margin-bottom: 20px;
       }

       .CustomerPageConttenNavigationbarContent .YourOrder table {
           width: 100%;
           border-collapse: collapse;
       }

       th, td {
           padding: 10px;
           border: 1px solid gainsboro;
       }

       th {
           background-color: #7cc652;
       }

       th:nth-child(1), td:nth-child(1), th:nth-child(3), td:nth-child(3), th:nth-child(4), td:nth-child(4), th:nth-child(5), td:nth-child(5) {
           width: 120px;
           text-align: center;
           font-size: 14px;
       }

       th:nth-child(2), td:nth-child(2) {
           width: 250px;
           text-align: center;
           font-size: 14px;
       }

       th:nth-child(6), td:nth-child(6) {
           width: 90px;
           text-align: center;
           font-size: 14px;
       }

       td .OrderAddress {
           display: inline-block;
           width: 250px;
           white-space: nowrap;
           overflow: hidden;
           text-overflow: ellipsis;
           cursor: pointer;

       }

       .Detail:hover i,
       .Detail:hover span {
           cursor: pointer;
           color: #7cc652;
       }

       td .Detail .fa-solid {
           margin: 0;
       }

       /*Doi mat khau*/
       .ChangePasswordTitle {
           font-size: 20px;
           margin-bottom: 20px;
       }

       .ChangePasswordReminder {
           margin-bottom: 15px;
           width: 500px;
       }

       .ChangePasswordForm input {
           margin: 10px 0 20px 0;
           width: 350px;
           height: 35px;
           border: solid 1px gainsboro;
           border-radius: 5px;
           padding-left: 10px;
       }

       input:focus {
           border-color: #7cc652;
           outline: none;
           box-shadow: 0 0 1px #7cc652;
       }

       .ChangePassword button {
           padding: 5px 10px;
           height: 35px;
           margin-left: 5px;
           background-color: #7cc652;
           border: none;
           border-radius: 5px;
           color: white;
       }

       .ChangePassword i {
           width: 18px;
           height: 17px;
           padding: 4px 3px 3px 3px;
           margin-left: 10px;
           color: #7cc652;
           border-radius: 50%;
       }

       /*phan so don hang*/
       .AddressBook {
           width: 100%;
           height: 422px;
       }

       .AddressBookHeader {
           border-bottom: solid 1px gainsboro;
           padding-bottom: 30px;
       }

       .AddressBookTitle {
           font-size: 20px;
           margin-bottom: 20px;
       }

       .AddressBook .button1 {
           padding: 15px 25px;
           margin-left: 10px;
           color: white;
           background-color: #7cc652;
           border: none;
           border-radius: 5px;
           cursor: pointer;
       }

       .AddressBookDetail {
           height: 100px;
           padding: 15px 0 15px 10px;
           border-bottom: solid 1px gainsboro;
       }

       .AddressBookDetailAphal {
           width: calc(75% - 1px);
           float: left;
       }

       .AddressBookDetailOmega {
           width: 25%;
           float: right;
       }

       .AddressBookDetailAphal {
           font-weight: 600;
           border-right: solid 1px gainsboro;
       }

       .AddressBookDetailAphal span {
           font-weight: normal;
       }
       .ptext{
           color: #7cc652;
           font-size: 14px;
       }
       .fa-circle-check {
           color: #7cc652;
           margin-left: 10px;
           margin-right: 5px;
       }

       .AddressBookDetailAphalContent {
           margin-bottom: 5px;
       }

       .AddressBookDetailOmega {
           margin-top: 45px;
       }

       .AddressBookDetailOmega .Edit {
           margin: 0 15px;
           color: #7cc652;
       }

       .AddressBookDetailOmega .Edit:hover {
           color: #00845c;
           cursor: pointer;
       }

       .AddressBookDetailOmega .Delete {
           color: red;
       }

       .AddressBookDetailOmega .Delete:hover {
           color: #00845c;
           cursor: pointer;
       }

       /*Phan cua so nhap lieu de them dia chi*/
       .Addnewaddress {
           position: fixed; /* Đặt ở giữa màn hình */
           top: 50%;
           left: 50%;
           transform: translate(-50%, -50%);
           width: 700px;
           height: 550px;
           background-color: white;
           border-radius: 5px;
           display: none;
           z-index: 1001; /* Nằm trên overlay */
       }

       .overlay {
           position: fixed;
           top: 0;
           left: 0;
           width: 100%;
           height: 100%;
           background-color: rgba(0, 0, 0, 0.5); /* Làm tối nền */
           z-index: 1000; /* Đảm bảo overlay nằm trên cùng */
       }

       .AddnewaddressTitle {
           padding: 20px 0 20px 20px;
           border-bottom: 1px solid #888888;
           font-weight: 600;
       }

       .radiobutton {
           margin-top: 10px;
           margin-left: 10px;
           display: inline-block;
           width: 14px;
           height: 16px;
           padding: 0 0 0 2px;
           border: solid 1px #888888;
           background-color: white;
           border-radius: 5px;
       }

       .radiobuttontext {
           cursor: pointer;
       }

       .fa-check {
           color: white;
           font-size: 12px;
           cursor: pointer;
       }

       .AddnewaddressContent {
           padding: 20px 0 20px 20px;
           color: #888888;
       }

       .AddnewaddressContent .form {
           margin: 5px 0;
           border: solid 1px gainsboro;
           padding-left: 10px;
           width: 95%;
           height: 40px;
           font-size: 14px;
           color: #888888;
           border-radius: 5px;
       }

       .AddnewaddressContent select {
           margin: 5px 3px 5px 0;
           border: solid 1px gainsboro;
           padding-left: 8px;
           height: 40px;
           font-size: 14px;
           color: #888888;
           border-radius: 5px;
       }

       .AddnewaddressContent .ButtonSubmit {
           float: right;
           margin: 30px 20px 0 0;
       }

       .AddnewaddressContent button {
           height: 40px;
           margin-left: 5px;
           border: none;
           border-radius: 5px;
           background-color: #7cc652;
           cursor: pointer;
       }

       .AddnewaddressContent .b1:hover {
           background-color: brown;
       }

       .AddnewaddressContent .b2:hover {
           background-color: #00845c;
       }
       .fa-truck-fast{
           color: #7cc652;
       }
       /*OderWindow */
       .OderWindow{
           position: fixed; /* Đặt ở giữa màn hình */
           top: 50%;
           left: 50%;
           transform: translate(-50%, -50%);
           width: 380px;
           height: 400px;
           border: 1px solid gainsboro;
           margin: 30px 50px;
           padding: 10px 20px;
           background-color: white;
           z-index: 1001;
           display: none;
       }
       .Product_List_item .Product_item {
           display: flex;
           padding: 5px 0;
       }
       .iconClose{
           float: right;
           color: #00845c;
       }
       .iconClose:hover{
           cursor: pointer;
           color: #7cc652;
       }

       .OderWindowTitle{
           font-size: 20px;
           font-weight: 600;
           text-align: center;
           margin-top: 10px;
       }
       .Product_List_item {
           padding: 15px 0 0;
           list-style: none;
           max-height: 220px; /* Chiều cao tối đa hiển thị, tùy chỉnh theo ý muốn */
           overflow-y: auto;
           border-bottom: 1px solid gainsboro;
       }

       .Product_item_imgnotice {
           position: relative;
       }

       .Product_item_notice {
           position: absolute;
           top: -10px;
           left: 45px;
           padding: 2px 5px;
           color: white;
           font-size: 0.6rem;
           background-color: #00845c;
           border-radius: 8px;
           border: 1px solid green;
       }

       .OderWindow .Product_img_item {
           margin: 2px 5px 0 5px;
           width: 50px;
           border: 1px solid gainsboro;
           cursor: pointer;
           border-radius: 5px;
       }

       .Product_item_info {
           padding: 10px 0 0 15px;
       }

       .Product_item_name {
           display: inline-block;
           width: 170px;
           margin-right: 5px;
           overflow-wrap: break-word;
       }

       .Product_item_price {
           display: inline-block;
       }
       .TotalAmount{
           margin-top: 20px;
           margin-left: 5px;
       }
       .text {
           display: inline-block;
           width: 220px;
           margin-bottom: 15px;
           font-size: 20px;
       }

       .total {
           color: #7cc652;
           font-size: 20px;
       }
       /*footer*/

   </style>
</head>
<body>
<div class="CustomerPage">
    <div class="Header">
        <div class="Container">
            <a href="../home.html"><img id="logo" src="../Img/snapedit_1730861562696.png" alt="Shopping Cart Image" style="width: 150px"></a>
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
                <div class="NavigationbarSelect" id="option1" onclick="navigationbarClick('option1')">Thông tin tài
                    khoản
                </div>
                <div class="NavigationbarSelect" id="option2" onclick="navigationbarClick('option2')">Đơn hàng của bạn
                </div>
                <div class="NavigationbarSelect" id="option3" onclick="navigationbarClick('option3')">Đổi mật khẩu</div>
                <div class="NavigationbarSelect" id="option4" onclick="navigationbarClick('option4')">Sổ địa chỉ</div>
                <div class="NavigationbarSelect" id="option5" onclick="navigationbarClick('option5')">Đăng xuất</div>
            </div>
            <div class="CustomerPageConttenNavigationbarContent" id="CustomerPageConttenNavigationbarContent">
                <div class="AccountInformation">
                    <div class="AccountInformationTitle">THÔNG TIN TÀI KHOẢN</div>
                    <div class="AccountInformationContent">Họ tên: <span>Nguyễn Vỹ</span></div>
                    <div class="AccountInformationContent">Email: <span>nguyenvy310804@gmail.com</span></div>
                    <div class="AccountInformationContent">Điện thoại: <span>0327237467</span></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    // js phan lua chon Navigationbar
    function navigationbarClick(select) {
        const options = document.querySelectorAll(".NavigationbarSelect");
        options.forEach(option => option.classList.remove("active"));
        // Thêm màu nổi bật vào phần tử được nhấp
        const selectedOption = document.getElementById(select);
        selectedOption.classList.add("active");
        const NavigationbarContent = document.getElementById("CustomerPageConttenNavigationbarContent");
        NavigationbarContent.innerHTML = "";
        const div = document.createElement('div');
        switch (select) {
            case "option1":
                div.className = "AccountInformation";
                div.innerHTML = `
                                    <div class="AccountInformationTitle">THÔNG TIN TÀI KHOẢN</div>
                                    <div class="AccountInformationContent">Họ tên: <span>Nguyễn Vỹ</span></div>
                                    <div class="AccountInformationContent">Email: <span>nguyenvy310804@gmail.com</span></div>
                                    <div class="AccountInformationContent">Điện thoại: <span>0327237467</span></div>
            `;
                break;
            case "option2":
                div.className = "YourOrder";
                div.id = "YourOrder";
                div.innerHTML = `
        <div class="YourOrderTitle">ĐƠN HÀNG CỦA BẠN</div>
        <table>
            <thead>
                <tr>
                    <th>Mã Vận Đơn</th>
                    <th>Địa Chỉ</th>
                    <th>Ngày Đặt</th>
                    <th>Ngày Giao</th>
                    <th>Tổng Thanh Toán</th>
                    <th>Chi Tiết</th>
                </tr>
            </thead>
            <tbody id="OrderTableBody">
                <!-- Các đơn hàng sẽ được chèn vào đây -->
                <tr class="Oder_item">
                <td><span class="Order_Id">1</span></td>
            <td><span class="OrderAddress">Linh Xuan</span></td>
            <td><span class="Order_DateBooked">22/12/2024</span></td>
            <td><span class="Order_DeliveryDate"> <i class="fa-solid fa-truck-fast"></i></span></td>
            <td><span class="Order_Money">400.000đ</span></td>
            <td>
                <div class="Detail" onclick="viewOrder()"><i class="fa-regular fa-eye"></i><span>Xem</span></div>
            </td>
            </tr>
            </tbody>
            <div class="OderWindow" id="OderWindow">
            <div class="iconClose" onclick="viewOrderClose()"><i class="fa-solid fa-xmark"></i></div>
            <div class="OderWindowTitle">Danh sách sản phẩm</div>
            <div id="ProductItems" class="Product_List_item">
                <div class="Product_item">
                    <div class="Product_item_imgnotice">
                        <img src="../images/bi_ngoi.webp" alt="Product Image" class="Product_img_item">
                        <span class="Product_item_notice">2</span>
                    </div>
                    <div class="Product_item_info">
                        <div class="Product_item_header">
                            <div class="Product_item_name">Bí ngòi</div>
                            <div class="Product_item_price">20.000vnd</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="TotalAmount">
                <span class="text">Tổng cộng</span>
                <span class="total" id="total">1.240.000đ</span>
            </div>
        </div>
        </table>
    `;
                NavigationbarContent.appendChild(div);
                break;
            case "option3":
                div.className = "ChangePassword";
                div.innerHTML = `
                                    <div class="ChangePasswordTitle">ĐỔI MẬT KHẨU</div>
                                    <div class="ChangePasswordReminder">Để đảm bảo tính bảo mật bạn vui lòng nhập mật khẩu trên 8 ký tự, bao gồm cả chữ hoa, chữ thường và ký tự đặc biệt!</div>
                                    <div class="ChangePasswordForm">
                                        <label>Mật khẩu cũ *</label><br>
                                        <input type="password" name="oldpassword" id="oldpassword"> <i class="fa-regular fa-eye" id="icon" onclick="togglePassword('oldpassword','icon')"></i><br>
                                        <label>Mật khẩu mới *</label><br>
                                        <input type="password" name="newpassword" id="newpassword" > <i class="fa-regular fa-eye" id="icon1" onclick="togglePassword('newpassword','icon1')"></i><br>
                                        <label>Xác nhận lại mật khẩu *</label><br>
                                        <input type="password" name="confirm-password" id="confirm-password" > <i class="fa-regular fa-eye" id="icon2" onclick="togglePassword('confirm-password','icon2')"></i><br>
                                        <button type="submit">Đặt lại mật khẩu</button>
                                    </div>
            `;
                NavigationbarContent.appendChild(div);
                addPasswordValidation("newpassword");
                addConfirmPasswordValidation("newpassword", "confirm-password");
                break;
            case "option4":
                div.className = "AddressBook";
                div.id = "AddressBook";
                div.innerHTML = `
                    <div class="AddressBookHeader">
                        <div class="AddressBookTitle">ĐỊA CHỈ CỦA BẠN</div>
                        <button type="submit" class="button1" onclick="addAddress()">Thêm địa chỉ</button>
                    </div>
                    <div class="AddressBookDetail">
            <div class="AddressBookDetailAphal">
                <div class="AddressBookDetailAphalContent">Họ tên: <span>Nguyen Ngoc Vy</span></div>
               <div class="AddressBookDetailAphalContent">Địa chỉ: <span>27B,Duong So 8</span></div>
                <div class="AddressBookDetailAphalContent">Số điện thoại: <span>0327237467</span></div>
                <div class="AddressBookDetailAphalContent">Công ty: <span>NongLamFood</span></div>
            </div>
            <div class="AddressBookDetailOmega">
                <span class="Edit" onclick="UpdateAddress(this)">Chỉnh sửa địa chỉ</span>
                <span class="Delete" onclick="DeleteAddress(this)">Xóa</span>
            </div>
        </div>

                  <div class="Addnewaddress" id="Addnewaddress" >
                    <div class="AddnewaddressTitle">THÊM ĐỊA CHỈ MỚI</div>
                    <div class="AddnewaddressContent">
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
                        <input type="text" name="Zip" id="Zip" placeholder="Zip" class="form"><br>
                        <div class="radiobutton" onclick="toggleRadioButton()" id="radiobutton"><i class="fa-solid fa-check" id="fa-check"></i></div>
                        <span onclick="toggleRadioButton()" class="radiobuttontext">Đặt địa chỉ làm mặc định?</span>
                        <div class="ButtonSubmit">
                            <button type="submit" class="b1" onclick="closeAddress()">Hủy</button>
                            <button type="submit" class="b2" onclick="ConfirmAddress()">Thêm địa chỉ</button>
                        </div>
                    </div>
                </div>
            `;
                break
            case "option5":
                window.location.href = "../signIn&Up/index.html"
                break;
            default:
                div.innerHTML = "<p>Vui lòng chọn một mục.</p>";
                break
        }
        NavigationbarContent.appendChild(div);
    }

</script>
</body>
</html>
