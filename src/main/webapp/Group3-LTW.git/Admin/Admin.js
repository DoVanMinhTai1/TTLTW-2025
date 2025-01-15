// Js cho button them san pham
function addProduct() {
    const newProduct = document.getElementById("ProductWindow");
    // Thêm một lớp overlay để làm tối nền
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    // Hiển thị form thêm địa chỉ
    newProduct.style.display = "block";
}

// js cho button huy
function closeProduct() {
    const overlay = document.getElementById("overlay");
    const newProduct = document.getElementById("ProductWindow");

    // Ẩn form và xóa overlay
    newProduct.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
    // Xóa dữ liệu trong form
    const inputs = newProduct.querySelectorAll("input");
    const selectElements = newProduct.querySelectorAll("select");
    selectElements.forEach(select => {
        select.value = ""; // Hoặc giá trị mặc định nếu cần
    });
    inputs.forEach(input => {
        input.value = ""; // Reset giá trị của mỗi input
    });
}

// Js cho button luu san pham
function confirmProduct() {
    const overlay = document.getElementById("overlay");
    const newProduct = document.getElementById("ProductWindow");

    // Ẩn form và xóa overlay
    newProduct.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
    const id = Math.floor(10000000 + Math.random() * 90000000);
    const image = document.getElementById("productImage").value;
    const name = document.getElementById("productName").value;
    const price = document.getElementById("productPrice").value;
    const quantity = document.getElementById("productQuantity").value;
    // Kiểm tra nếu các trường bắt buộc không được nhập
    if (!name || !image || !price || !quantity) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }
    const productList = document.getElementById("ProductItem");
// Tạo một `li` mới
    productList.innerHTML += `
    <li>
        <div>#${id}</div>
        <div><img src="${image}" alt="${name}" style="width: 50px"></div>
        <div>${name}</div>
        <div>${price}</div>
        <div>${quantity}</div>
        <div class="menu">
            <i class="fa-solid fa-ellipsis-vertical"></i>
            <div class="ellipsis">
                <div>Sửa</div>
                <div onclick="Delete(this)">Xóa</div>
            </div>
        </div>
    </li>
`;
    const inputs = newProduct.querySelectorAll("input");
    const selectElements = newProduct.querySelectorAll("select");
    selectElements.forEach(select => {
        select.value = ""; // Hoặc giá trị mặc định nếu cần
    });
    inputs.forEach(input => {
        input.value = ""; // Reset giá trị của mỗi input
    });
}

// js cho button sua san pham
function UpdateProduct(element) {
    const productItem = element.closest('li'); // Tìm mục sản phẩm gần nhất
    const name = productItem.querySelector('div:nth-child(3)').textContent.trim();
    const price = productItem.querySelector('div:nth-child(4)').textContent.trim();
    const quantity = productItem.querySelector('div:nth-child(5)').textContent.trim();

    addProduct();

    document.getElementById('productName').value = name;
    document.getElementById('productPrice').value = price;
    document.getElementById('productQuantity').value = quantity;

    const saveButton = document.querySelector(".ButtonProduct1");
    saveButton.textContent = "Cập nhật";

    saveButton.onclick = function () {
        confirmProductUpdate(productItem);
    };
}

// js cho button sua san pham
function confirmProductUpdate(productItem) {
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;
    const quantity = document.getElementById('productQuantity').value;

    // Cập nhật thông tin trực tiếp vào danh sách sản phẩm
    productItem.querySelector('div:nth-child(3)').textContent = name;
    productItem.querySelector('div:nth-child(4)').textContent = price;
    productItem.querySelector('div:nth-child(5)').textContent = quantity;
    closeUpdate();
}

// js cho button sua san pham
function closeUpdate() {
    const saveButtonProduct = document.querySelector(".ButtonProduct1");
    saveButtonProduct.textContent = "Lưu";
    saveButtonProduct.onclick = function () {
        confirmProduct();
    };
    const overlay = document.getElementById("overlay");
    const newUser = document.getElementById("ProductWindow");

    // Ẩn form và xóa overlay
    newUser.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
}

// Js ho button xoa
function Delete(element) {
    element.closest('li').remove();
}

// js cho button them tai khoan
function addUser() {
    const newUser = document.getElementById("UserWindow");
    // Thêm một lớp overlay để làm tối nền
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    // Hiển thị form thêm địa chỉ
    newUser.style.display = "block";
}

// js cho button huy
function closeUser() {
    const overlay = document.getElementById("overlay");
    const newUser = document.getElementById("UserWindow");

    // Ẩn form và xóa overlay
    newUser.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
    // Xóa dữ liệu trong form
    const inputs = newUser.querySelectorAll("input");
    const selectElements = newUser.querySelectorAll("select");
    selectElements.forEach(select => {
        select.value = ""; // Hoặc giá trị mặc định nếu cần
    });
    inputs.forEach(input => {
        input.value = ""; // Reset giá trị của mỗi input
    });
}

// Js cho button luu tai khoan
function confirmUser() {
    const overlay = document.getElementById("overlay");
    const newUser = document.getElementById("UserWindow");

    // Ẩn form và xóa overlay
    newUser.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
    const id = Math.floor(10000000 + Math.random() * 90000000);
    const name = document.getElementById("FullName").value;
    const phone = document.getElementById("Phone").value;
    const role = document.getElementById("Decentralization").value;

    if (!id || !name || !phone || !role) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    const UserList = document.getElementById("UserItem");
// Tạo một `li` mới
    UserList.innerHTML += `
    <li>
        <div>#${id}</div>
        <div class="name">${name}</div>
        <div>${phone}</div>
        <div>${role}</div>
        <div class="menu">
            <i class="fa-solid fa-ellipsis-vertical"></i>
            <div class="ellipsis">
                <div>Sửa</div>
                <div onclick="Delete(this)">Xóa</div>
            </div>
        </div>
    </li>
`;
    const inputs = newUser.querySelectorAll("input");
    const selectElements = newUser.querySelectorAll("select");
    selectElements.forEach(select => {
        select.value = ""; // Hoặc giá trị mặc định nếu cần
    });
    inputs.forEach(input => {
        input.value = ""; // Reset giá trị của mỗi input
    });
}

// js cho button sua tai khoan
function UpdateUser(element) {
    const userItem = element.closest('li'); // Tìm phần tử `<li>` gần nhất
    const fullName = userItem.querySelector('div:nth-child(2)').textContent.trim();
    const phone = userItem.querySelector('div:nth-child(3)').textContent.trim();
    const role = userItem.querySelector('div:nth-child(4)').textContent.trim();

    // Hiển thị cửa sổ chỉnh sửa tài khoản
    addUser()

    // Gán giá trị vào các trường nhập liệu
    document.getElementById('FullName').value = fullName;
    document.getElementById('Phone').value = phone;
    document.getElementById('Decentralization').value = role;

    // Chỉnh nút lưu thành "Cập nhật"
    const saveButton = document.querySelector('.ButtonUser1');
    saveButton.textContent = "Cập nhật";

    saveButton.onclick = function () {
        confirmUserUpdate(userItem);
    };
}

// js cho button sua san pham
function confirmUserUpdate(userItem) {
    const fullName = document.getElementById('FullName').value;
    const phone = document.getElementById('Phone').value;
    const role = document.getElementById('Decentralization').value;

    // Cập nhật thông tin trong danh sách người dùng
    userItem.querySelector('div:nth-child(2)').textContent = fullName;
    userItem.querySelector('div:nth-child(3)').textContent = phone;
    userItem.querySelector('div:nth-child(4)').textContent = role;
    closeUpdateUser();
}

// js cho button sua san pham
function closeUpdateUser() {
    const saveButton = document.querySelector(".ButtonUser1");
    saveButton.textContent = "Lưu";
    saveButton.onclick = function () {
        confirmUser();
    };
    const overlay = document.getElementById("overlay");
    const newUser = document.getElementById("UserWindow");

    // Ẩn form và xóa overlay
    newUser.style.display = "none";
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
    selectedOption.classList.add("active");// Sử dụng đúng biến

    const optionContent = document.getElementById("content");
    optionContent.classList.remove("active1");
    const NavigationbarContent = document.getElementById("content");
    NavigationbarContent.innerHTML = "";
    const div = document.createElement('div');
    switch (select) {
        case "option1":
            div.className = "DashBoar";
            div.innerHTML = `
            <div class="cards">
            <div class="card">
                <div class="box">
                    <h1>160</h1>
                    <h3>Rau, củ, quả</h3>
                </div>
                <div class="icon-case">
                    <img src="../images/snapedit_seed.png" alt="" style="width: 40px; height: 40px">
                </div>
            </div>
            <div class="card">
                <div class="box">
                    <h1>200</h1>
                    <h3>Người dùng</h3>
                </div>
                <div class="icon-case">
                    <img src="../images/snapedit_user-line.png" alt="" style="width: 40px; height: 40px">
                </div>
            </div>
            <div class="card">
                <div class="box">
                    <h1>320</h1>
                    <h3>Đơn hàng</h3>
                </div>
                <div class="icon-case">
                    <img src="../images/snapedit_cart-line.png" alt="" style="width: 40px; height: 40px">
                </div>
            </div>
            <div class="card">
                        <div class="box">
                            <h1>3.200.000đ</h1>
                            <h3>Doanh thu</h3>
                        </div>
                        <div class="icon-case">
                            <img src="../images/doanhthu-admin.png" alt="" style="width: 40px; height: 40px">
                        </div>
            </div>
        </div>
            <div class="content-2">
                <div class="recent-payments">
                    <div class="title_Dashboar">
                        <h2>Đơn hàng gần đây</h2>
                        <a href="#" class="btn">Xem Tất Cả</a>
                    </div>
                    <table>
                        <tr>
                            <th>Người dùng</th>
                            <th>Rau Củ Quả</th>
                            <th>Thành Tiền</th>
                            <th>Lựa Chọn</th>
                        </tr>
                        <tr>
                            <td>thungan584</td>
                            <td>Quả Bí Đao</td>
                            <td>30.000đ</td>
                            <td><a href="#" class="btn">Xem</a></td>
                        </tr>
                        <tr>
                            <td>thungan584</td>
                            <td>Quả Bí Đao</td>
                            <td>30.000đ</td>
                            <td><a href="#" class="btn">Xem</a></td>
                        </tr>
                        <tr>
                            <td>thungan584</td>
                            <td>Quả Bí Đao</td>
                            <td>30.000đ</td>
                            <td><a href="#" class="btn">Xem</a></td>
                        </tr>
                        <tr>
                            <td>thungan584</td>
                            <td>Quả Bí Đao</td>
                            <td>30.000đ</td>
                            <td><a href="#" class="btn">Xem</a></td>
                        </tr>
                        <tr>
                            <td>thungan584</td>
                            <td>Quả Bí Đao</td>
                            <td>30.000đ</td>
                            <td><a href="#" class="btn">Xem</a></td>
                        </tr>
                    </table>
                </div>
                <div class="new-users">
                    <div class="title_Dashboar">
                        <h2>Người dùng</h2>
                        <a href="#" class="btn">Xem Tất Cả</a>
                    </div>
                    <table>
                        <tr>
                            <th>Tài khoản</th>
                            <th>Tên</th>
                            <th>Lựa chọn</th>
                        </tr>
                        <tr>
                            <td><img src="../images/user.png" alt="" style="width: 40px; height: 40px"></td>
                            <td>thungan584</td>
                            <td><img src="../images/infor-admin.png" alt="" style="width: 40px; height: 40px"></td>
                        </tr>
                        <tr>
                            <td><img src="../images/user.png" alt="" style="width: 40px; height: 40px"></td>
                            <td>thungan584</td>
                            <td><img src="../images/infor-admin.png" alt="" style="width: 40px; height: 40px"></td>
                        </tr>
                        <tr>
                            <td><img src="../images/user.png" alt="" style="width: 40px; height: 40px"></td>
                            <td>thungan584</td>
                            <td><img src="../images/infor-admin.png" alt="" style="width: 40px; height: 40px"></td>
                        </tr>
                    </table>
                </div>
            </div>                      
            `;
            break;
        case "option2":
            optionContent.classList.add("active1");
            div.className = "AdminListProduct";
            div.innerHTML = `
            <div class="AdminListProductHeader">
                <div>Sản phẩm(<span>7</span>)</div>
                <input type="text" name="searchProduct" id="searchProduct" placeholder="Tìm kiếm sản phẩm?">
                <button type="submit" onclick="addProduct()">Thêm sản phẩm</button>
            </div>
            <ul class="Product_Item" id="ProductItem">
                <li class="title_Item">
                    <div>ID</div>
                    <div>Ảnh</div>
                    <div>Tên</div>
                    <div>Giá</div>
                    <div>Số lượng</div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div ><img src="../images/bap_cai_tim.webp" alt="" style="width: 50px"></div>
                    <div>Bắp cải tím</div>
                    <div>18.000đ</div>
                    <div >20Kg</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdateProduct(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130333</div>
                    <div ><img src="../images/bi_ngoi.webp" alt="" style="width: 50px"></div>
                    <div>Bí ngòi</div>
                    <div>12.000đ</div>
                    <div >15Kg</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdateProduct(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130334</div>
                    <div ><img src="../images/ca_phao.jpg" alt="" style="width: 50px"></div>
                    <div>Cà pháo</div>
                    <div>15.000đ</div>
                    <div >25Kg</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdateProduct(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130335</div>
                    <div ><img src="../images/ca_chua.webp" alt="" style="width: 50px"></div>
                    <div>Cà chua</div>
                    <div>16.000đ</div>
                    <div >10Kg</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdateProduct(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="ProductWindow" id="ProductWindow">
                <div class="modal-content">
                    <span >Nhập thông tin sản phẩm</span><br>     
                            
                        <label for="productImage">Ảnh:</label><br>
                        <input type="file" id="productImage" name="productImage" "><br>
                       
                        <label for="productName">Tên:</label><br>
                        <input type="text" id="productName" name="productName" placeholder="Vui lòng nhập tên ..."><br>

                        <label for="productPrice">Giá:</label><br>
                        <input type="text" id="productPrice" name="productPrice" placeholder="Vui lòng nhập giá ..."><br>
                        <label for="productDescribe">Mô tả:</label><br>
                        <input type="text" id="productDescribe" name="productDescribe" placeholder="Vui lòng mô tả"><br>

                        <label for="productQuantity">Số lượng:</label><br>
                        <input type="text" id="productQuantity" name="productQuantity" placeholder="Vui lòng nhập số lượng ..."><br>
                        <button type="submit" class="ButtonProduct1" onclick="confirmProduct()">Lưu</button>
                        <button type="submit" class="ButtonProduct2" onclick="closeProduct()">Hủy</button>              
                </div>
            </div>
        </div>
            `;
            break
        case "option3":
            optionContent.classList.add("active1");
            div.className = "AdminListUser";
            div.innerHTML = `
                <div class="AdminListUserHeader">
                <div>Tài khoản(<span>7</span>)</div>
                <input type="text" name="searchUser" id="searchUser" placeholder="Tìm kiếm khách hàng?">
                <button type="submit" onclick="addUser()">Thêm tài khoản</button>
            </div>
            <ul class="User_Item" id="UserItem">
                <li class="title_Item">
                    <div>ID</div>
                    <div class="name">Tên</div>
                    <div>Số điện thoại</div>
                    <div>Phân quyền</div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div class="name">Nguyễn Ngọc Vỹ</div>
                    <div>0326443274</div>
                    <div>Quản trị viên</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                              <div onclick="UpdateUser(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130180</div>
                    <div class="name">Trần Thị Thu Ngân</div>
                    <div>0988337294</div>
                    <div>Người dùng</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                        <div onclick="UpdateUser(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div class="name">Lâm Hoàng Huy</div>
                    <div>0911769241</div>
                    <div>Người dùng</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdateUser(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130180</div>
                    <div class="name">Lê Thị Thùy Linh</div>
                    <div>0387354279</div>
                    <div>Người dùng</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdateUser(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
            </ul>
        <div class="UserWindow" id="UserWindow">
            <div class="modal-content">
                <span>Nhập thông tin tài khoản</span><br>
                <label for="UserName">Tên đăng nhập:</label><br>
                <input type="text" id="UserName" name="UserName" ><br>
                <label for="UsePassword">Mật khẩu đăng nhập:</label><br>
                <input type="text" id="UsePassword" name="UsePassword"><br>
                <label for="Decentralization">Phân quyền:</label><br>
                <select name="Decentralization" id="Decentralization" style="width:250px;height:30px;margin: 5px 0;">
                    <option value="" disabled selected>Phân quyền</option>
                    <option value="Quản trị viên"> Quản trị viên</option>
                    <option value="Người dùng"> Người dùng</option>
                </select><br>
                <label for="FullName">Họ tên:</label><br>
                <input type="text" id="FullName" name="FullName" ><br>

                <label for="Phone">Số điện thoại:</label><br>
                <input type="text" id="Phone" name="Phone" ><br>
                <button type="submit" class="ButtonUser1" onclick="confirmUser()">Lưu</button>
                <button type="submit" class="ButtonUser2" onclick="closeUser()">Hủy</button>
            </div>
        </div>
            `;
            break
        case "option4":
            optionContent.classList.add("active1");
            div.className = "AdminListOrder";
            div.innerHTML = `
            <div class="AdminListOrderHeader">
                <div>Đơn Hàng(<span>7</span>)</div>
                <input type="text" name="searchOrder" id="searchOrder" placeholder="Tìm kiếm đơn hàng?">
            </div>
            <ul class="Order_Item">
                <li class="title_Item">
                    <div>Mã vận đơn</div>
                    <div>Khách hàng</div>
                    <div>Ngày đặt</div>
                    <div>Thành tiền</div>
                    <div>Trạng thái</div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div class="name">Nguyễn Ngọc Vỹ</div>
                    <div>31/12/2024</div>
                    <div>318.000đ</div>
                    <div class="statusT">Đã thanh toán</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="viewOrder()">Chi tiết đơn hàng</div>
                            <div onclick="Delete(this)">Xóa đơn hàng</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130180</div>
                    <div class="name">Trần Thị Thu Ngân</div>
                    <div>31/08/2004</div>
                    <div>220.000đ</div>
                    <div class="statusF">Chờ thanh toán</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="viewOrder()">Chi tiết đơn hàng</div>
                            <div onclick="Delete(this)">Xóa đơn hàng</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div class="name">Lâm Hàng Huy</div>
                    <div>31/12/2024</div>
                    <div>318.000đ</div>
                    <div class="statusT">Đã thanh toán</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="viewOrder()">Chi tiết đơn hàng</div>
                            <div onclick="Delete(this)">Xóa đơn hàng</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130180</div>
                    <div class="name">Lê Thị Thùy Linh</div>
                    <div>31/08/2004</div>
                    <div>220.000đ</div>
                    <div class="statusF">Chờ thanh toán</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="viewOrder()">Chi tiết đơn hàng</div>
                            <div onclick="Delete(this)">Xóa đơn hàng</div>
                        </div>
                    </div>
                </li>
            </ul>  
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
            `;

            break;

        case "option5":
            optionContent.classList.add("active1");
            div.className = "AdminListPromotion";
            div.innerHTML = `
            <div class="AdminListPromotionHeader">
                <div>Khuyến mãi(<span>7</span>)</div>
                <input type="text" name="searchPromotion" id="searchPromotion" placeholder="Tìm kiếm khuyến mãi?">
                <button type="submit" onclick="addPromotion()">Thêm khuyến mãi</button>
            </div>
            <ul class="Promotion_Item" id="PromotionItem">
                <li class="title_Item">
                    <div>Mã khuyến mãi</div>
                    <div >Ngày bắt đầu</div>
                    <div>Ngày kết thúc</div>
                    <div>Giá trị khuyến mãi</div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div >22/11/2024</div>
                    <div>23/12/2024</div>
                    <div>Giảm 50%</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdatePromotion(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="PromotionWindow" id="PromotionWindow">
                <div class="modal-content">
                    <span>Nhập thông tin khuyến mãi</span><br>
                    <label for="CodePromotion">Mã khuyến mãi:</label><br>
                    <input type="text" id="CodePromotion" name="CodePromotion"><br>
                    <label for="StartDate">Ngày bắt đầu:</label><br>
                    <input type="date" id="StartDate" name="StartDate"><br>
                    <label for="EndDate">Ngày kết thúc:</label><br>
                    <input type="date" id="EndDate" name="EndDate"><br>

                    <label for="ValuePromotion" >Giá trị khuyến mãi:</label><br>
                    <input type="text" id="ValuePromotion" name="ValuePromotion"><br>
                    <button type="submit" class="ButtonPromotion1" onclick="confirmPromotion()">Lưu</button>
                    <button type="submit" class="ButtonPromotion2" onclick="closePromotion()">Hủy</button>
                </div>
            </div>
            `;
            break;
        case "option6":
            optionContent.classList.add("active1");
            div.className = "AdminListHelp";
            div.innerHTML = `
            <div class="content-3">
        <ul class="help-admin">
            <li>
                <span class="toggle">Hướng Dẫn Sử Dụng</span>
                <ul class="submenu">
                    <li>
                        <span class="toggle">Tổng Quan</span>
                        <ul class="submenu">
                            <li>
                                <p>Giúp người quản trị quản lý hiệu quả, tối ưu hóa quy trình vận hành từ sản pẩm đến khách hàng.</p>
                                <p>Tiết kiệm thời gian, quản lý dữ liệu tập trung, theo dõi hiệu quả kinh doanh.</p>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Chức Năng Chính</span>
                        <ul class="submenu">
                            <li>
                                <span class="toggle">Quản lí sản phẩm</span>
                                <ul class="submenu">
                                    <li>
                                        <h6 class="toggle">Thêm sản phẩm mới</h6>
                                        <ul class="submenu">
                                            <li>
                                                <p>1. Vào mục Quản lý sản phẩm.</p>
                                                <p>2. Nhấn Thêm sản phẩm.</p>
                                                <p>3. Điền các thông tin: Tên sản phẩm, mô tả, giá, hình ảnh, số lượng.</p>
                                                <p>4. Nhấn Lưu để hoàn tất.</p>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <h6 class="toggle">Chỉnh sửa sản phẩm</h6>
                                        <ul class="submenu">
                                            <li>
                                                <p>1. Vào mục Quản lý sản phẩm.</p>
                                                <p>2. Chọn sản phẩm cần chỉnh sửa.</p>
                                                <p>3. Thực hiện thay đổi và nhấn Cập Nhật.</p>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <h6 class ="toggle">Xóa sản phẩm</h6>
                                        <ul class="submenu">
                                            <li>
                                                <p>1. Vào mục Quản lý sản phẩm.</p>
                                                <p>2. Chọn sản phẩm cần xóa.</p>
                                                <p>3. Nhấn nút Xóa.</p>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <span class="toggle">Quản lí người dùng</span>
                                <ul class="submenu">
                                    <li>
                                        <h6 class="toggle">Thêm người dùng</h6>
                                        <ul class="submenu">
                                            <li>
                                                <p>1. Truy cập vào mục Quản lý người dùng.</p>
                                                <p>2. Nhấn nút Thêm người dùng mới.</p>
                                                <p>3. Nhập thông tin người dùng cần thiết</p>
                                                <p>4. Nhấn Lưu để hoàn tất.</p>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <h6 class ="toggle">Sửa người dùng</h6>
                                        <ul class="submenu">
                                            <li>
                                                <p>1. Truy cập vào mục Quản lý người dùng.</p>
                                                <p>2. Tìm người dùng cần sửa trong danh sách.</p>
                                                <p>3. Nhấn vào Chỉnh Sửa.</p>
                                                <p>4. Cập nhật thông tin cần thay đổi.</p>
                                                <p>5. Nhấn Cập Nhật để thay đổi.</p>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <h6 class="toggle">Xóa người dùng</h6>
                                        <ul class="submenu">
                                            <li>
                                                <p>1. Truy cập vào mục Quản lý người dùng.</p>
                                                <p>2. Chọn người dùng cần xóa.</p>
                                                <p>3. Nhấn nút Xóa.</p>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <span class="toggle">Quản lí đơn hàng</span>
                                <ul class="submenu">
                                    <li>
                                        <h6 class="toggle">Xem chi tiết đơn hàng</h6>
                                        <ul class="submenu">
                                            <li>
                                                <p>1. Truy cập vào mục Quản lý đơn hàng.</p>
                                                <p>2. Tìm, chọn đơn hàng cần xem.</p>
                                                <p>3. Nhấn nút Xem.</p>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <h6 class="toggle">Xóa đơn hàng</h6>
                                        <ul class="submenu">
                                            <li>
                                                <p>1. Truy cập vào mục Quản lý đơn hàng.</p>
                                                <p>2. Tìm đơn hàng cần xóa.</p>
                                                <p>3. Nhấn vào nút xóa bên cạnh đơn hàng cần xóa.</p>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li>
                <span class="toggle">Cộng Đồng Hỗ Trợ</span>
                <ul class="submenu">
                    <li>
                        <span class ="toggle">Diễn Đàn Hoặc Nhóm</span>
                        <ul class="submenu">
                            <li>
                                <p>Kết nối admin với cộng đồng: <a href="#">tại đây.</a></p>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Tài Liệu Cập Nhật</span>
                        <ul class="submenu">
                            <li>
                                <p><a href="#">Tài liệu cập nhật.</a></p>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li>
                <span class="toggle">Thông Báo Lỗi Thường Gặp Và Cách Xử Lí</span>
                <ul class="submenu">
                    <li>
                        <span class="toggle">Lỗi Kết Nối Cơ Sở Dữ Liệu</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Mô Tả</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>Lỗi kết nối cơ sở dữ liệu thường xảy ra khi hệ thống không thể kết nối với cơ sở dữ liệu, khiến các thao tác trên hệ thống không thể thực hiện được (ví dụ: không thể lưu trữ dữ liệu, không thể truy xuất dữ liệu người dùng, sản phẩm, đơn hàng).</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Nguyên Nhân</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Cấu hình kết nối cơ sở dữ liệu không đúng (như tên máy chủ, tên người dùng, mật khẩu, tên cơ sở dữ liệu).</p>
                                        <p>2. Máy chủ cơ sở dữ liệu bị tắt hoặc không hoạt động.</p>
                                        <p>3. Lỗi mạng khiến hệ thống không thể kết nối với cơ sở dữ liệu từ xa.</p>
                                        <p>4. Cơ sở dữ liệu bị quá tải hoặc lỗi cấu hình.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Cách Khắc Phục</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>1. Kiểm tra cấu hình kết nối:</b> Đảm bảo các thông tin kết nối trong tệp cấu hình (host, user, password, database name) là chính xác.</p>
                                        <p><b>2. Khởi động lại cơ sở dữ liệu:</b> Kiểm tra trạng thái của máy chủ cơ sở dữ liệu và khởi động lại nếu cần.</p>
                                        <p><b>3. Kiểm tra kết nối mạng:</b> Đảm bảo mạng ổn định và không có vấn đề kết nối giữa ứng dụng và máy chủ cơ sở dữ liệu.</p>
                                        <p><b>4. Tăng tài nguyên cơ sở dữ liệu:</b> Nếu cơ sở dữ liệu bị quá tải, bạn cần xem xét tối ưu hóa và phân bổ thêm tài nguyên cho hệ thống.</p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Lỗi Không Tải Được Báo Cáo</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Mô Tả</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>Lỗi thường gặp khi quản trị viên cố gắng tải báo cáo (ví dụ: báo cáo doanh thu, báo cáo đơn hàng) nhưng hệ thống không thể xuất dữ liệu hoặc báo cáo không hiển thị.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Nguyên Nhân</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Lỗi trong quá trình truy vấn dữ liệu, dẫn đến báo cáo không thể được tạo ra.</p>
                                        <p>2. Cơ sở dữ liệu không chứa dữ liệu cần thiết cho báo cáo.</p>
                                        <p>3. Vấn đề với file tải xuống (file bị hỏng hoặc không tồn tại).</p>
                                        <p>4. Quyền truy cập hạn chế đối với người dùng quản trị viên hoặc nhóm người dùng.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Cách Khắc Phục</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>1. Kiểm tra truy vấn báo cáo:</b> Đảm bảo truy vấn SQL hoặc API tạo báo cáo không bị lỗi.</p>
                                        <p><b>2. Kiểm tra dữ liệu:</b> Xác minh rằng cơ sở dữ liệu chứa đầy đủ thông tin cần thiết cho báo cáo.</p>
                                        <p><b>3. Kiểm tra quyền truy cập::</b> Đảm bảo quản trị viên có quyền truy cập đầy đủ vào dữ liệu và các tính năng cần thiết để tạo báo cáo.</p>
                                        <p><b>4. Thử tải lại hoặc khôi phục file:</b> Nếu file báo cáo bị hỏng hoặc không tải được, hãy thử tải lại hoặc khôi phục từ bản sao lưu.</p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Lỗi Quyền Truy Cập</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Mô Tả</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>Lỗi này xuất hiện khi người quản trị không thể truy cập một số phần của hệ thống (chẳng hạn, không thể truy cập trang quản lý người dùng, sản phẩm, đơn hàng).</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Nguyên Nhân</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Người dùng không có quyền truy cập cần thiết.</p>
                                        <p>2. Cấu hình phân quyền không đúng hoặc bị thay đổi.</p>
                                        <p>3. Lỗi mạng khiến hệ thống không thể kết nối với cơ sở dữ liệu từ xa.</p>
                                        <p>4. Lỗi trong quản lý vai trò.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Cách Khắc Phục</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>1. Kiểm tra quyền người dùng:</b> Đảm bảo người quản trị có quyền truy cập vào các phần của hệ thống mà họ cần.</p>
                                        <p><b>2. Kiểm tra phân quyền vai trò:</b> Đảm bảo phân quyền cho các nhóm hoặc vai trò người dùng được thiết lập đúng.</p>
                                        <p><b>3. Đăng nhập lại::</b> Nếu có thay đổi về phân quyền, thử đăng nhập lại để làm mới quyền truy cập.</p></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Lỗi Tải Lên Hình Ảnh (File)</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Mô Tả</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>Khi quản trị viên cố gắng tải lên hình ảnh hoặc file (ví dụ: logo, sản phẩm, hình ảnh người dùng), hệ thống không thể tải hoặc hiển thị ảnh.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Nguyên Nhân</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Kích thước file quá lớn.</p>
                                        <p>2. Định dạng file không hợp lệ (chỉ hỗ trợ định dạng .jpg, .png,...).</p>
                                        <p>3. Lỗi về quyền truy cập thư mục tải lên.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Cách Khắc Phục</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>1. Kiểm tra giới hạn kích thước file:</b> Đảm bảo rằng file không vượt quá giới hạn kích thước được hệ thống cho phép (ví dụ: 5MB).</p>
                                        <p><b>2. Kiểm tra định dạng file:</b>  Đảm bảo file tải lên có định dạng hợp lệ (JPEG, PNG,...).</p>
                                        <p><b>3. Kiểm tra quyền thư mục:</b> Đảm bảo thư mục tải lên có quyền ghi cho người dùng hoặc hệ thống.</p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Lỗi Cập Nhật Dữ Liệu</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Mô Tả</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>Lỗi này xảy ra khi hệ thống không thể cập nhật hoặc lưu trữ dữ liệu người dùng, đơn hàng, sản phẩm,...</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Nguyên Nhân</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Dữ liệu nhập vào không hợp lệ (ví dụ: sai định dạng, thiếu thông tin bắt buộc).</p>
                                        <p>2. Lỗi trong quá trình lưu trữ dữ liệu (lỗi trong API, cấu hình cơ sở dữ liệu).</p>
                                        <p>3. Hết dung lượng lưu trữ trên server.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Cách Khắc Phục</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>1. Kiểm tra dữ liệu nhập:</b> Đảm bảo rằng tất cả các trường bắt buộc đã được điền và dữ liệu nhập vào đúng định dạng.</p>
                                        <p><b>2. Kiểm tra cấu hình lưu trữ:</b> Kiểm tra dung lượng còn lại trên server hoặc cơ sở dữ liệu.</p>
                                        <p><b>3. Kiểm tra log lỗi:</b> Xem nhật ký lỗi để xác định nguyên nhân cụ thể.</p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li>
                <span class ="toggle">FAQ (Câu Hỏi Thường Gặp)</span>
                <ul class="submenu">
                    <li>
                        <span class="toggle">Làm thế nào để reset mật khẩu?</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Phân Tích</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>Tầm quan trọng:</b> Mật khẩu là yếu tố quan trọng bảo vệ tài khoản người dùng và quản trị viên. Việc không thể truy cập vào tài khoản do quên mật khẩu có thể gây ra sự gián đoạn trong công việc.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Hướng Dẫn</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Truy cập vào trang đăng nhập.</p>
                                        <p>2. Chọn "Quên mật khẩu" trên nút đăng nhập.</p>
                                        <p>3. Nhập địa chỉ email đã đăng ký để nhận liên kết đặt lại mật khẩu.</p>
                                        <p>4. Nhấp vào liên kết gửi qua email và thiết lập mật khẩu mới.</p>
                                        <p>5. Đăng nhập với mật khẩu mới.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Lưu Ý</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Cần cung cấp rõ ràng thông tin về các yêu cầu của mật khẩu (ví dụ: độ dài, chữ hoa, chữ số).</p>
                                        <p>2. Cung cấp thông tin liên lạc trong trường hợp không thể nhận email đặt lại mật khẩu.</p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Cách phân quyền cho người dùng?</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Phân Tích</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>Tầm quan trọng:</b> Phân quyền cho người dùng giúp hệ thống trở nên linh hoạt và bảo mật hơn. Quản trị viên có thể phân quyền cho từng người dùng để kiểm soát quyền truy cập vào các phần của hệ thống, từ đó hạn chế rủi ro bảo mật và sự cố không mong muốn.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Hướng Dẫn</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Đăng nhập vào trang admin với quyền quản trị cao nhất.</p>
                                        <p>2. Đi tới phần "Quản lý người dùng".</p>
                                        <p>3. Chọn người dùng mà bạn muốn phân quyền.</p>
                                        <p>4. Chỉnh sửa quyền của người dùng đó (ví dụ: Quyền quản trị).</p>
                                        <p>5. Lưu lại thay đổi và kiểm tra lại để đảm bảo các quyền đã được cập nhật.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Lưu Ý</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Cần giải thích các loại quyền khác nhau và mức độ truy cập mà mỗi quyền đại diện.</p>
                                        <p>2. Cung cấp thông tin về việc phân quyền cho nhóm người dùng, ví dụ: Nhóm quản trị viên, nhân viên bán hàng, nhân viên hỗ trợ.</p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Làm sao để thêm người dùng mới vào hệ thống?</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Phân Tích</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>Tầm quan trọng:</b> Việc thêm người dùng mới vào hệ thống là một trong những công việc thường xuyên của quản trị viên, giúp mở rộng hệ thống và phân phối công việc cho các thành viên trong đội ngũ.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Hướng Dẫn</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Đăng nhập vào trang admin với quyền quản trị cao nhất.</p>
                                        <p>2. Đi tới phần "Quản lý người dùng".</p>
                                        <p>3. Chọn "Thêm người dùng mới".</p>
                                        <p>4. Điền thông tin cần thiết.</p>
                                        <p>5. Lưu lại và thông báo cho người dùng mới.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Lưu Ý</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Cung cấp thông tin về các loại quyền mà người dùng mới có thể được cấp.</p>
                                        <p>2. Cần xác minh email của người dùng mới trước khi hoàn tất quá trình thêm người dùng.</p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="toggle">Làm thế nào để xóa hoặc chỉnh sửa thông tin người dùng?</span>
                        <ul class="submenu">
                            <li>
                                <h6 class="toggle">Phân Tích</h6>
                                <ul class="submenu">
                                    <li>
                                        <p><b>Tầm quan trọng:</b> Quản trị viên cần có khả năng quản lý thông tin người dùng, bao gồm việc chỉnh sửa thông tin cá nhân hoặc xóa tài khoản không còn sử dụng. Điều này giúp đảm bảo dữ liệu trong hệ thống luôn chính xác và bảo mật.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Hướng Dẫn</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Đăng nhập vào trang admin.</p>
                                        <p>2. Đi tới phần "Quản lý người dùng".</p>
                                        <p>3. Chọn người dùng muốn chỉnh sửa hoặc xóa.</p>
                                        <p>4. Chỉnh sửa thông tin (hoặc xóa tài khoản).</p>
                                        <p>5. Lưu lại thay đổi và kiểm tra lại.</p>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <h6 class="toggle">Lưu Ý</h6>
                                <ul class="submenu">
                                    <li>
                                        <p>1. Giải thích về việc không thể phục hồi thông tin khi xóa tài khoản.</p>
                                        <p>2. Cung cấp thông tin về việc yêu cầu xác nhận xóa tài khoản.</p>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    
             `
            break;
        default:
            div.innerHTML = "<p>Vui lòng chọn một mục.</p>";
            break
    }
    NavigationbarContent.appendChild(div);
}

// hien form chi tiet don hang
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

// Hiển thị form thêm khuyến mãi
function addPromotion() {
    const promotionWindow = document.getElementById("PromotionWindow");
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);

    promotionWindow.style.display = "block";
}

// Đóng form thêm khuyến mãi
function closePromotion() {
    const overlay = document.getElementById("overlay");
    const promotionWindow = document.getElementById("PromotionWindow");

    promotionWindow.style.display = "none";
    if (overlay) {
        overlay.remove();
    }

    // Xóa dữ liệu trong form
    const inputs = promotionWindow.querySelectorAll("input");
    inputs.forEach(input => {
        input.value = "";
    });
}

// Lưu khuyến mãi mới
function confirmPromotion() {
    const overlay = document.getElementById("overlay");
    const promotionWindow = document.getElementById("PromotionWindow");
    const code = document.getElementById("CodePromotion").value.trim();
    const startDate = document.getElementById("StartDate").value;
    const endDate = document.getElementById("EndDate").value;
    const value = document.getElementById("ValuePromotion").value.trim();

    // Kiểm tra dữ liệu đầu vào
    if (!code || !startDate || !endDate || !value) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    // Thêm khuyến mãi vào danh sách
    const promotionList = document.getElementById("PromotionItem");
    promotionList.innerHTML += `
        <li>
            <div>#${code}</div>
            <div>${startDate}</div>
            <div>${endDate}</div>
            <div>${value}</div>
            <div class="menu">
                <i class="fa-solid fa-ellipsis-vertical"></i>
                <div class="ellipsis">
                    <div onclick="UpdatePromotion(this)">Sửa</div>
                    <div onclick="Delete(this)">Xóa</div>
                </div>
            </div>
        </li>
    `;

    // Đóng form và xóa overlay
    promotionWindow.style.display = "none";
    if (overlay) {
        overlay.remove();
    }

    // Reset form
    const inputs = promotionWindow.querySelectorAll("input");
    inputs.forEach(input => {
        input.value = "";
    });
}

function formatDate(inputDate) {
    const [day, month, year] = inputDate.split('/');
    return `${year}-${month}-${day}`; // Trả về định dạng yyyy-MM-dd
}

// Sửa khuyến mãi
function UpdatePromotion(element) {
    const promotionItem = element.closest('li'); // Tìm phần tử `<li>` gần nhất
    const code = promotionItem.querySelector('div:nth-child(1)').textContent.replace('#', '').trim();
    const startDate = promotionItem.querySelector('div:nth-child(2)').textContent.trim();
    const endDate = promotionItem.querySelector('div:nth-child(3)').textContent.trim();
    const value = promotionItem.querySelector('div:nth-child(4)').textContent.trim();

    // Hiển thị form chỉnh sửa
    addPromotion();

    // Gán giá trị vào các trường nhập liệu
    document.getElementById("CodePromotion").value = code;
    document.getElementById("StartDate").value = formatDate(startDate);
    document.getElementById("EndDate").value = formatDate(endDate);
    document.getElementById("ValuePromotion").value = value;

    // Chỉnh nút lưu thành "Cập nhật"
    const saveButton = document.querySelector('.ButtonPromotion1');
    saveButton.textContent = "Cập nhật";

    saveButton.onclick = function () {
        confirmPromotionUpdate(promotionItem);
    };
}

// Cập nhật khuyến mãi
function confirmPromotionUpdate(promotionItem) {
    const code = document.getElementById("CodePromotion").value.trim();
    const startDate = document.getElementById("StartDate").value;
    const endDate = document.getElementById("EndDate").value;
    const value = document.getElementById("ValuePromotion").value.trim();

    // Kiểm tra dữ liệu đầu vào
    if (!code || !startDate || !endDate || !value) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    // Cập nhật nội dung trong danh sách
    promotionItem.querySelector('div:nth-child(1)').textContent = `#${code}`;
    promotionItem.querySelector('div:nth-child(2)').textContent = startDate;
    promotionItem.querySelector('div:nth-child(3)').textContent = endDate;
    promotionItem.querySelector('div:nth-child(4)').textContent = value;

    // Đóng form và xóa overlay
    closePromotion();
}
//
function btnClick(select) {
    const options = document.querySelectorAll(".btn");
    options.forEach(option => option.classList.remove("active"));
    // Thêm màu nổi bật vào phần tử được nhấp
    const selectedOption = document.getElementById(select);
    selectedOption.classList.add("active");// Sử dụng đúng biến

    const optionContent = document.getElementById("content");
    optionContent.classList.remove("active1");
    const btnContent = document.getElementById("content");
    btnContent.innerHTML = "";
    const div = document.createElement('div');
    switch (select) {
        case "btn1":
    div.className = "AdminListUser";
    div.innerHTML = `
                <div class="AdminListUserHeader">
                <div>Tài khoản(<span>7</span>)</div>
                <input type="text" name="searchUser" id="searchUser" placeholder="Tìm kiếm khách hàng?">
                <button type="submit" onclick="addUser()">Thêm tài khoản</button>
            </div>
            <ul class="User_Item" id="UserItem">
                <li class="title_Item">
                    <div>ID</div>
                    <div class="name">Tên</div>
                    <div>Số điện thoại</div>
                    <div>Phân quyền</div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div class="name">Nguyễn Ngọc Vỹ</div>
                    <div>0326443274</div>
                    <div>Quản trị viên</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                              <div onclick="UpdateUser(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130180</div>
                    <div class="name">Trần Thị Thu Ngân</div>
                    <div>0988337294</div>
                    <div>Người dùng</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                        <div onclick="UpdateUser(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div class="name">Lâm Hoàng Huy</div>
                    <div>0911769241</div>
                    <div>Người dùng</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdateUser(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130180</div>
                    <div class="name">Lê Thị Thùy Linh</div>
                    <div>0387354279</div>
                    <div>Người dùng</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="UpdateUser(this)">Sửa</div>
                            <div onclick="Delete(this)">Xóa</div>
                        </div>
                    </div>
                </li>
            </ul>
        <div class="UserWindow" id="UserWindow">
            <div class="modal-content">
                <span>Nhập thông tin tài khoản</span><br>
                <label for="UserName">Tên đăng nhập:</label><br>
                <input type="text" id="UserName" name="UserName" ><br>
                <label for="UsePassword">Mật khẩu đăng nhập:</label><br>
                <input type="text" id="UsePassword" name="UsePassword"><br>
                <label for="Decentralization">Phân quyền:</label><br>
                <select name="Decentralization" id="Decentralization" style="width:250px;height:30px;margin: 5px 0;">
                    <option value="" disabled selected>Phân quyền</option>
                    <option value="Quản trị viên"> Quản trị viên</option>
                    <option value="Người dùng"> Người dùng</option>
                </select><br>
                <label for="FullName">Họ tên:</label><br>
                <input type="text" id="FullName" name="FullName" ><br>

                <label for="Phone">Số điện thoại:</label><br>
                <input type="text" id="Phone" name="Phone" ><br>
                <button type="submit" class="ButtonUser1" onclick="confirmUser()">Lưu</button>
                <button type="submit" class="ButtonUser2" onclick="closeUser()">Hủy</button>
            </div>
        </div>
            `;
            break;
        case "btn2":
            optionContent.classList.add("active1");
            div.className = "AdminListOrder";
            div.innerHTML = `
            <div class="AdminListOrderHeader">
                <div>Đơn Hàng(<span>7</span>)</div>
                <input type="text" name="searchOrder" id="searchOrder" placeholder="Tìm kiếm đơn hàng?">
            </div>
            <ul class="Order_Item">
                <li class="title_Item">
                    <div>Mã vận đơn</div>
                    <div>Khách hàng</div>
                    <div>Ngày đặt</div>
                    <div>Thành tiền</div>
                    <div>Trạng thái</div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div class="name">Nguyễn Ngọc Vỹ</div>
                    <div>31/12/2024</div>
                    <div>318.000đ</div>
                    <div class="statusT">Đã thanh toán</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="viewOrder()">Chi tiết đơn hàng</div>
                            <div onclick="Delete(this)">Xóa đơn hàng</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130180</div>
                    <div class="name">Trần Thị Thu Ngân</div>
                    <div>31/08/2004</div>
                    <div>220.000đ</div>
                    <div class="statusF">Chờ thanh toán</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="viewOrder()">Chi tiết đơn hàng</div>
                            <div onclick="Delete(this)">Xóa đơn hàng</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130332</div>
                    <div class="name">Lâm Hàng Huy</div>
                    <div>31/12/2024</div>
                    <div>318.000đ</div>
                    <div class="statusT">Đã thanh toán</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="viewOrder()">Chi tiết đơn hàng</div>
                            <div onclick="Delete(this)">Xóa đơn hàng</div>
                        </div>
                    </div>
                </li>
                <li>
                    <div>#22130180</div>
                    <div class="name">Lê Thị Thùy Linh</div>
                    <div>31/08/2004</div>
                    <div>220.000đ</div>
                    <div class="statusF">Chờ thanh toán</div>
                    <div class="menu">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                        <div class="ellipsis">
                            <div onclick="viewOrder()">Chi tiết đơn hàng</div>
                            <div onclick="Delete(this)">Xóa đơn hàng</div>
                        </div>
                    </div>
                </li>
            </ul>  
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
            `;
        break;
        default:
            div.innerHTML = "<p>Vui lòng chọn một mục.</p>";
            break
    }
    btnContent.appendChild(div);
}
//
function  btnclick(){
    // Lấy ảnh và menu tùy chọn
    const userAvatar = document.querySelector('.admin-avatar');
    const dropdownMenu = document.querySelector('.dropdown-menu');

    // Thêm sự kiện click để bật/tắt menu
    userAvatar.addEventListener('click', () => {
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });

    // Đóng menu nếu nhấn ra ngoài
    document.addEventListener('click', (event) => {
        if (!event.target.closest('.user')) {
            dropdownMenu.style.display = 'none';
        }
    });
}
// Hàm đăng xuất
function logout() {
    window.location.href = '/logout';
}
//xem chi tiet
function viewDetail(select){

}
//btn d
function handleToggleMenu() {
    const toggleElements = document.querySelectorAll(".help-admin .toggle");

    toggleElements.forEach((toggle) => {
        toggle.addEventListener("click", function () {
            const submenu = this.nextElementSibling; // Lấy ul con

            if (submenu) {
                submenu.classList.toggle("active"); // Toggle class "active"
            }
        });
    });
}
document.addEventListener("click", handleToggleMenu);


