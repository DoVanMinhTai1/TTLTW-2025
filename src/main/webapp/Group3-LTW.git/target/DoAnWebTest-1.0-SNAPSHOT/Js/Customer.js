
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
