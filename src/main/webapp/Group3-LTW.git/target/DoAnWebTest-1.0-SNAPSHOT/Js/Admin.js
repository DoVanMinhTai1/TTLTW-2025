
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
                    <img src="../Img/snapedit_seed.png" alt="" style="width: 40px; height: 40px">
                </div>
            </div>
            <div class="card">
                <div class="box">
                    <h1>200</h1>
                    <h3>Người dùng</h3>
                </div>
                <div class="icon-case">
                    <img src="../Img/snapedit_user-line.png" alt="" style="width: 40px; height: 40px">
                </div>
            </div>
            <div class="card">
                <div class="box">
                    <h1>320</h1>
                    <h3>Đơn hàng</h3>
                </div>
                <div class="icon-case">
                    <img src="../Img/snapedit_cart-line.png" alt="" style="width: 40px; height: 40px">
                </div>
            </div>
            <div class="card">
                        <div class="box">
                            <h1>3.200.000đ</h1>
                            <h3>Doanh thu</h3>
                        </div>
                        <div class="icon-case">
                            <img src="../Img/doanhthu-admin.png" alt="" style="width: 40px; height: 40px">
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
                            <td><img src="../Img/user.png" alt="" style="width: 40px; height: 40px"></td>
                            <td>thungan584</td>
                            <td><img src="../Img/infor-admin.png" alt="" style="width: 40px; height: 40px"></td>
                        </tr>
                        <tr>
                            <td><img src="../Img/user.png" alt="" style="width: 40px; height: 40px"></td>
                            <td>thungan584</td>
                            <td><img src="../Img/infor-admin.png" alt="" style="width: 40px; height: 40px"></td>
                        </tr>
                        <tr>
                            <td><img src="../Img/user.png" alt="" style="width: 40px; height: 40px"></td>
                            <td>thungan584</td>
                            <td><img src="../Img/infor-admin.png" alt="" style="width: 40px; height: 40px"></td>
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
                    <div>#22130333</div>
                    <div ><img src="../Img/bi_ngoi.webp" alt="" style="width: 50px"></div>
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
                        <img src="../Img/bi_ngoi.webp" alt="Product Image" class="Product_img_item">
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
                        <img src="../Img/bi_ngoi.webp" alt="Product Image" class="Product_img_item">
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
                        <img src="../Img/bi_ngoi.webp" alt="Product Image" class="Product_img_item">
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
        default:
            div.innerHTML = "<p>Vui lòng chọn một mục.</p>";
            break
    }
    NavigationbarContent.appendChild(div);
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