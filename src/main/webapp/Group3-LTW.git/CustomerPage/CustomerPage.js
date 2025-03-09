// js cho phan kiem tra mat khau
function addPasswordValidation(inputId) {
    const password = document.getElementById(inputId);
    if (password) {
        password.addEventListener('blur', function () {
            if (password.value.length < 8 || !containsUpperAndLower(password.value)) {
                alert('Vui lòng nhập mật khẩu trên 8 ký tự, bao gồm cả chữ hoa, chữ thường và ký tự đặc biệt!');
            }
        });
    } else {
        console.error(`Element with id "${inputId}" not found.`);
    }
}

function containsUpperAndLower(str) {
    const upperRegex = /[A-Z]/;
    const lowerRegex = /[a-z]/;
    const specialCharRegex = /[^a-zA-Z0-9]/;
    return upperRegex.test(str) && lowerRegex.test(str) && specialCharRegex.test(str);
}

function addConfirmPasswordValidation(passwordId, confirmPasswordId) {
    const password = document.getElementById(passwordId);
    const confirmPassword = document.getElementById(confirmPasswordId);
    if (confirmPassword) {
        confirmPassword.addEventListener('blur', function () {
            if (password && password.value !== "" && password.value !== confirmPassword.value) {
                alert("Mật khẩu không trùng khớp với mật khẩu mới!");
            }
        });
    } else {
        console.error(`Element with id "${confirmPasswordId}" not found.`);
    }
}

// js cho icon con mat
function togglePassword(inputId, iconId) {
    const input = document.getElementById(inputId);
    const icon = document.getElementById(iconId);
    if (input.type === "password") {
            input.type = "text";
        icon.style.backgroundColor = "#cde2c0";
    } else {
        input.type = "password";
        icon.style.backgroundColor = "white";
    }
}
function viewOrder() {
    const viewOrder = document.getElementById("OderWindow");
    // Thêm một lớp overlay để làm tối nền
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    // Hiển thị form
    viewOrder.style.display = "block";
}
// dong form chi tiet don hang
function viewOrderClose() {
    const overlay = document.getElementById("overlay");
    const viewOrder = document.getElementById("OderWindow");
    viewOrder.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
}
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
                <div class="Product_item">
                    <div class="Product_item_imgnotice">
                        <img src="../images/bi_ngoi.webp" alt="Product Image" class="Product_img_item">
                        <span class="Product_item_notice">2</span>
                    </div>
                    <div class="Product_item_info">
                        <div class="Product_item_header">
                            <div class="Product_item_name">Bí ngòi</div>
                            <div class="Product_item_price">20.000đ</div>
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

            // Lấy thông tin từ localStorage (dữ liệu đơn hàng)
            const information = JSON.parse(localStorage.getItem('information'));
            if (information && Object.keys(information).length > 0) {
                const orderTableBody = div.querySelector('#OrderTableBody');

                // Tạo dòng mới cho đơn hàng
                const listItem = document.createElement('tr');
                listItem.className = 'Oder_item';
                listItem.innerHTML = `
            <td><span class="Order_Id">${information.Code}</span></td>
            <td><span class="OrderAddress">${information.Address}</span></td>
            <td><span class="Order_DateBooked">${information.DateBooked}</span></td>
            <td><span class="Order_DeliveryDate"> <i class="fa-solid fa-truck-fast"></i></span></td>
            <td><span class="Order_Money">${information.Total}đ</span></td>
            <td>
                <div class="Detail" onclick="viewOrder()"><i class="fa-regular fa-eye"></i><span>Xem</span></div>
            </td>
        `;
                // Thêm dòng mới vào bảng
                orderTableBody.appendChild(listItem);
            } else {
                // Nếu không có thông tin đơn hàng
                div.innerHTML = "<p>Không có đơn hàng nào.</p>";
            }

            // Thêm div vào nội dung trang
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

// js cho button them dia chi moi trong trang so dia chi

function addAddress() {
    const newAddress = document.getElementById("Addnewaddress");
    // Thêm một lớp overlay để làm tối nền
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    // Hiển thị form thêm địa chỉ
    newAddress.style.display = "block";

}

// js cho phan dau tich chon dat dia chi lam mac dinh

function toggleRadioButton() {
    const checkI = document.getElementById("fa-check");
    if (checkI.style.color === "blue") {
        checkI.style.color = "white";
    } else {
        checkI.style.color = "blue";
    }
}

// js cho button dong cua so nhap lieu
function closeAddress() {
    const overlay = document.getElementById("overlay");
    const newAddress = document.getElementById("Addnewaddress");

    // Ẩn form và xóa overlay
    newAddress.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
    // Xóa dữ liệu trong form
    const inputs = newAddress.querySelectorAll("input");
    inputs.forEach(input => {
        input.value = ""; // Reset giá trị của mỗi input
    });
    const selects = newAddress.querySelectorAll("select");
    selects.forEach(select => {
        select.value = ""; // Reset giá trị của mỗi select
    });
}

// js cho button them dia chi trong cua so nhap lieu

function ConfirmAddress() {
    const overlay = document.getElementById("overlay");
    const newAddress = document.getElementById("Addnewaddress");

    // Ẩn form và xóa overlay
    newAddress.style.display = "none";
    if (overlay) {
        overlay.remove();
    }

    const name = document.getElementById("Name").value;
    const phone = document.getElementById("Phone").value;
    const company = document.getElementById("Company").value;
    const address = document.getElementById("Address").value;
    const nation = document.getElementById("Nation").value;
    const province = document.getElementById("Province").value;
    const district = document.getElementById("District").value;
    const wardAndCommune = document.getElementById("Wardandcommune").value;


    // Kiểm tra nếu các trường bắt buộc không được nhập
    if (!name || !phone || !address) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    const confirmAddress = document.getElementById("AddressBook");
    const checkI = document.getElementById("fa-check");
    if (checkI.style.color === "blue") {
        confirmAddress.innerHTML += `
        <div class="AddressBookDetail">
            <div class="AddressBookDetailAphal">
                <div class="AddressBookDetailAphalContent">Họ tên: <span>${name}</span> <i class="fa-regular fa-circle-check"></i> <span class="ptext">Địa chỉ mặc định</span></div>
                <div class="AddressBookDetailAphalContent">Địa chỉ: <span>${address}, ${wardAndCommune}, ${district}, ${province}, ${nation}</span></div>
                <div class="AddressBookDetailAphalContent">Số điện thoại: <span>${phone}</span></div>
                <div class="AddressBookDetailAphalContent">Công ty: <span>${company || "Không có"}</span></div>
            </div>
            <div class="AddressBookDetailOmega">
                <span class="Edit" onclick="UpdateAddress(this)">Chỉnh sửa địa chỉ</span>
<!--                <span class="Delete">Xóa</span>-->
            </div>
        </div>
    `;
    } else {
        confirmAddress.innerHTML += `
        <div class="AddressBookDetail">
            <div class="AddressBookDetailAphal">
                <div class="AddressBookDetailAphalContent">Họ tên: <span>${name}</span></div>
                <div class="AddressBookDetailAphalContent">Địa chỉ: <span>${address}, ${wardAndCommune}, ${district}, ${province}, ${nation}</span></div>
                <div class="AddressBookDetailAphalContent">Số điện thoại: <span>${phone}</span></div>
                <div class="AddressBookDetailAphalContent">Công ty: <span>${company || "Không có"}</span></div>
            </div>
            <div class="AddressBookDetailOmega">
                <span class="Edit" onclick="UpdateAddress(this)">Chỉnh sửa địa chỉ</span>
                <span class="Delete" onclick="DeleteAddress(this)">Xóa</span>
            </div>
        </div>
    `;
    }
    document.getElementById("fa-check").style.color = "white";
}

function UpdateAddress(element) {

    const addressDetail = element.closest('.AddressBookDetail');
    const name = addressDetail.querySelector('.AddressBookDetailAphalContent:nth-child(1) span').textContent;
    const address = addressDetail.querySelector('.AddressBookDetailAphalContent:nth-child(2) span').textContent;
    const phone = addressDetail.querySelector('.AddressBookDetailAphalContent:nth-child(3) span').textContent;
    const company = addressDetail.querySelector('.AddressBookDetailAphalContent:nth-child(4) span').textContent || "";

    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    const newAddressForm = document.getElementById("Addnewaddress");
    newAddressForm.style.display = "block";

    const fullAddress = address.split(', ');
    const remainingAddress = fullAddress.slice(0, -4).join(', ');
    document.getElementById("Name").value = name;
    document.getElementById("Address").value = remainingAddress;
    document.getElementById("Phone").value = phone;
    document.getElementById("Company").value = company;


    const [ward, district, province, nation] = address.split(', ').slice(-4);
    document.getElementById("Nation").value = nation;
    document.getElementById("Province").value = province;
    document.getElementById("District").value = district;
    document.getElementById("Wardandcommune").value = ward;

    const submitButton = document.querySelector(".b2");
    submitButton.textContent = "Cập nhật";

    submitButton.removeEventListener('click', submitButton.onclick); // Xóa sự kiện cũ
    submitButton.onclick = function () {
        ConfirmUpdateAddress(addressDetail, newAddressForm);
    };
}

function ConfirmUpdateAddress(addressDetail, newAddressForm) {
    // Lấy dữ liệu từ form
    const updatedName = document.getElementById("Name").value;
    const updatedPhone = document.getElementById("Phone").value;
    const updatedCompany = document.getElementById("Company").value;
    const updatedAddress = document.getElementById("Address").value;
    const updatedNation = document.getElementById("Nation").value;
    const updatedProvince = document.getElementById("Province").value;
    const updatedDistrict = document.getElementById("District").value;
    const updatedWard = document.getElementById("Wardandcommune").value;

    addressDetail.querySelector('.AddressBookDetailAphalContent:nth-child(1) span').textContent = updatedName;
    addressDetail.querySelector('.AddressBookDetailAphalContent:nth-child(2) span').textContent =
        `${updatedAddress}, ${updatedWard}, ${updatedDistrict}, ${updatedProvince}, ${updatedNation}`;
    addressDetail.querySelector('.AddressBookDetailAphalContent:nth-child(3) span').textContent = updatedPhone;
    addressDetail.querySelector('.AddressBookDetailAphalContent:nth-child(4) span').textContent = updatedCompany || "Không có";
    // Ẩn form và xóa overlay
    document.getElementById("Addnewaddress").style.display = "none";
    const overlay = document.getElementById("overlay");
    if (overlay) {
        overlay.remove();
    }
    const inputs = newAddressForm.querySelectorAll("input");
    inputs.forEach(input => {
        input.value = ""; // Reset giá trị của mỗi input
    });
    const selects = newAddressForm.querySelectorAll("select");
    selects.forEach(select => {
        select.value = ""; // Reset giá trị của mỗi select
    });
    const submitButton = document.querySelector(".b2");
    submitButton.textContent = "Thêm địa chỉ mới";
    // Gán sự kiện click vào nút Cập nhật
    submitButton.removeEventListener('click', submitButton.onclick); // Xóa sự kiện
    submitButton.onclick = function () {
        ConfirmAddress(); // Gọi hàm thêm địa chỉ mới
    };
}

function DeleteAddress(element) {
    element.closest('.AddressBookDetail').remove();
}


