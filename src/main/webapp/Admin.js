function navigationbarClick(select) {
    // Loại bỏ class 'active' khỏi tất cả các phần tử navigation
    const options = document.querySelectorAll(".NavigationbarSelect");
    options.forEach(option => option.classList.remove("active"));

    // Thêm class 'active' vào phần tử được chọn
    const selectedOption = document.getElementById(select);
    if (selectedOption) {
        selectedOption.classList.add("active");
    }

    // Loại bỏ class 'active1' khỏi tất cả các phần tử nội dung
    const optionContent = document.querySelectorAll(".select");
    optionContent.forEach(option => option.classList.remove("active1"));

    // Hiển thị nội dung dựa trên phần tử được chọn
    switch (select) {
        case "option1":
            const dashboardContent = document.querySelector(".DashBoar");
            if (dashboardContent) {
                dashboardContent.classList.add("active1");
            }
            break;
        case "option2":
            const productContent = document.querySelector(".AdminListProduct");
            if (productContent) {
                productContent.classList.add("active1");
            }
            break;
        case "option3":
            const userContent = document.querySelector(".AdminListUser");
            if (userContent) {
                userContent.classList.add("active1");
            }
            break;
        case "option4":
            const orderContent = document.querySelector(".AdminListOrder");
            if (orderContent) {
                orderContent.classList.add("active1");
            }
            break;
        case "option5":
            const promotionContent = document.querySelector(".AdminListPromotion");
            if (promotionContent) {
                promotionContent.classList.add("active1");
            }
            break;
        case "option6":
            const productDiscount = document.querySelector(".AdminListProductDiscount");
            if (productDiscount) {
                productDiscount.classList.add("active1");
            }
            break;


        default:
            console.warn(`No content found for select: ${select}`);
            break;
    }
}

async function viewNotification(logId) {
    const response = await fetch(`/web/GetLogByID?logId=${logId}`);
    const logDetails = await response.json();
    const viewNotification = document.getElementById("NotificationWindow");
    // Thêm một lớp overlay để làm tối nền
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    viewNotification.innerHTML = ""; // Xóa dữ liệu cũ
    viewNotification.innerHTML = `
         <h2>Chi tiết Log</h2>
        <i class="fa-solid fa-xmark" onclick="closeNotification()"></i>
        <div><label>ID:</label> ${logDetails.logId}</div>
        <div><label>Label:</label> ${logDetails.label}</div>
        <div><label>User ID:</label> ${logDetails.userId}</div>
        <div><label>Time:</label> ${logDetails.time}</div>
        <div><label>Location:</label> ${logDetails.location}</div>
        <div><label>Dữ liệu trước:</label><br><pre>${logDetails.beforeData}</pre></div>
        <div><label>Dữ liệu sau:</label><br><pre>${logDetails.afterData}</pre></div>`;
    // Hiển thị form
    viewNotification.style.display = "block";
}

function closeNotification() {
    const overlay = document.getElementById("overlay");
    const viewNotification = document.getElementById("NotificationWindow");

    viewNotification.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
}

// Js cho button them san pham
function addProduct() {
    // windowProduct()

    document.getElementById('idp').value = "";
    document.getElementById('image').src = "";
    document.getElementById('productName').value = "";
    document.getElementById('productPrice').value = "";
    document.getElementById('productDescribe').value = "";
    document.getElementById('productMass').value = "";
    document.getElementById('productCategory').value = "Rau";
    document.getElementById('productImageList').value="";

    const previewContainer = document.getElementById('showImageList');
    previewContainer.innerHTML = "";
    // const saveButton = document.querySelector(".ButtonProduct1");
    // saveButton.textContent = "Lưu";

    const action = document.querySelector(".ProductWindow form");
    action.action = "addProduct";

}

function windowProduct() {
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

function UpdateProduct(id, name, price, mass, description, image, category) {
    // Gán dữ liệu vào form
    document.getElementById('idp').value = id;
    document.getElementById('productName').value = name;
    document.getElementById('productPrice').value = price;
    document.getElementById('productDescribe').value = description;
    document.getElementById('productMass').value = mass;
    document.getElementById('productCategory').value = category;

    // Hiển thị ảnh cũ
    const imageElement = document.getElementById('image');
    imageElement.src = image;
    imageElement.style.display = "block";

    if (id) {
        getListImageByProductId(id);
        console.log('test' + id)
    }

    // Thay đổi action của form thành cập nhật
    const form = document.querySelector("#ProductWindow form");
    form.action = "updateProduct";

    // Thay đổi nút lưu
    const saveButton = form.querySelector(".btn-success");
    saveButton.textContent = "Cập nhật";

    // Hiển thị modal bằng Bootstrap 5
    const modal = new bootstrap.Modal(document.getElementById('ProductWindow'));
    modal.show();
}

document.getElementById('productImageList').addEventListener('change', function (e) {
    const container = document.getElementById('showImageList');
    // container.innerHTML = ''; // clear trước


    const files = e.target.files;

    for (let i = 0; i < files.length; i++) {
        const file = files[i];

        const dt = new DataTransfer();
        dt.items.add(file);
        const reader = new FileReader();
        reader.onload = function (event) {
            const imageUrl = event.target.result;
            const newInput = document.createElement('input');
            newInput.type = 'file';
            newInput.name = 'imagesList[]';
            newInput.files = dt.files;
            newInput.style.display = 'none';

            // Create image element
            const img = document.createElement('img');
            img.src = imageUrl;
            img.style.width = "100px";
            img.style.marginRight = "10px";

            // Create delete button
            const button = document.createElement('button');
            button.textContent = "X";
            button.type = 'button';
            button.style.marginLeft = '10px';
            button.onclick = () => deleteProductImageNewAdd(button, img, newInput);

            // Append image and button to the container
            container.appendChild(newInput)
            container.appendChild(img);
            container.appendChild(button);

        };
        reader.readAsDataURL(files[i]);

    }

});

function deleteProductImageNewAdd(button, img,newInput) {
    button.remove();
    img.remove();
    newInput.remove();
}

function getListImageByProductId(id) {
    const listProductImage = document.getElementById('showImageList');
    $.ajax({
        url: '/web/GetProductImageByProductId',
        type: 'GET',
        data: {id: id},
        success: function (data) {
            listProductImage.innerHTML = '';

            data.forEach(imageUrl => {
                console.log('test object' + imageUrl)

                const img = document.createElement('img');
                img.src = imageUrl.url;
                img.alt = 'Image Product';
                img.style.width = '50px';
                img.style.marginTop = '2px'

                const button = document.createElement('button')
                button.style.marginLeft = '0px'
                button.textContent = "X";
                button.type = 'button';
                button.onclick = () => deleteProductImageById(imageUrl.productId, imageUrl.id, img, button);
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'imageId';
                input.value = imageUrl.id
                listProductImage.append((input))
                listProductImage.append((img));
                listProductImage.append((button));

            })
        }

    })
}

function deleteProductImageById(productId, id, imgElement, button) {
    $.ajax({
        url: '/web/deleteProductImageById',
        type: 'DELETE',
        data: JSON.stringify({productId: productId, id: id}),
        success: function (data) {
            imgElement.remove();
            button.remove();
            console.log("Xóa ảnh thành công");
        }
    })
}

//Tìm kiếm sản phẩm
window.addEventListener("DOMContentLoaded", function () {
    const searchInputs = [
        {id: "searchProduct", url: "/web/searchProduct", placeholder: "Nhập tên sản phẩm"},
        {id: "searchUser", url: "/web/searchUser", placeholder: "Nhập tên khách hàng"},
        {id: "searchOrder", url: "/web/searchOrder", placeholder: "Nhập mã đơn hàng"},
        {id: "searchPromotion", url: "/web/searchPromotion", placeholder: "Nhập mã khuyến mãi"}
    ];

    searchInputs.forEach(inputData => {
        const searchInput = document.getElementById(inputData.id);
        if (!searchInput) return; // Tránh lỗi nếu chưa có thẻ input

        searchInput.addEventListener("input", function () {
            const keyword = this.value.trim();

            if (keyword === "") {
                fetch(`${inputData.url}?keyword=`)
                    .then(response => response.json())
                    .then(data => renderData(inputData.id, data));
                return;
            }

            fetch(`${inputData.url}?keyword=${encodeURIComponent(keyword)}`)
                .then(response => response.json())
                .then(data => renderData(inputData.id, data))
                .catch(error => console.error(`Lỗi khi tìm kiếm ${inputData.placeholder}:`, error));
        });
    });

    function renderTitle(inputId) {
        switch (inputId) {
            case "searchProduct":
                return `
                <li class="title_Item">
                    <div>ID</div>
                    <div>Ảnh</div>
                    <div>Tên</div>
                    <div>Giá</div>
                    <div>Khối lượng</div>
                </li>`;
            case "searchUser":
                return `
                <li class="title_Item">
                    <div>ID</div>
                    <div>Tên</div>
                    <div>Số điện thoại</div>
                    <div>Phân quyền</div>
                </li>`;
            case "searchOrder":
                return `
                <li class="title_Item">
                    <div>Mã vận đơn</div>
                    <div>Khách hàng</div>
                    <div>Ngày đặt</div>
                    <div>Thành tiền</div>
                    <div>Trạng thái</div>
                </li>`;
            case "searchPromotion":
                return `
                <li class="title_Item">
                    <div>Mã khuyến mãi</div>
                    <div>Ngày bắt đầu</div>
                    <div>Ngày kết thúc</div>
                    <div>Giá trị</div>
                </li>`;
            default:
                return '';
        }
    }

    function renderData(inputId, data) {
        const listId = `list-${inputId}`;
        const list = document.getElementById(listId);
        if (!list) return;

        list.innerHTML = renderTitle(inputId);

        if (data.length === 0) {
            list.innerHTML += `<li><div style="padding: 10px">Không tìm thấy kết quả nào.</div></li>`;
            return;
        }

        data.forEach(item => {
            const li = document.createElement("li");
            li.innerHTML = renderItemHTML(inputId, item);
            list.appendChild(li);
        });
    }

    function renderItemHTML(inputId, item) {
        switch (inputId) {
            case "searchProduct":
                return `
                  <div>${item.id}</div>
                  <div><img src="${item.image}" alt="" style="width: 50px"></div>
                  <div>${item.name}</div>
                  <div>${formatCurrency(item.price)}</div>
                  <div>${item.mass}Kg</div>
                  <div class="menu">
                    <i class="fa-solid fa-ellipsis-vertical"></i>
                    <div class="ellipsis">
                       <div onclick="UpdateProduct('${item.id}','${escapeQuote(item.name)}','${item.price}','${item.mass}','${escapeQuote(item.description)}','${item.image}','${item.category}')">
                         Sửa
                       </div>
                       <a href="removeProduct?pid=${item.id}">
                        <div>Xóa</div>
                       </a>
                     </div>
                   </div>
                          `;
            case "searchUser":
                return `
                <div>${item.id}</div>
                <div>${item.fullName}</div>
                <div>${item.phone}</div>
                <div>${item.role == '1' ? 'Quản trị viên' : 'Người dùng'}</div>
                <div class="menu">
                    <i class="fa-solid fa-ellipsis-vertical"></i>
                    <div class="ellipsis">
                        <div onclick="UpdateUser('${item.id}', '${item.username}', '${item.password}', '${item.role}', '${escapeQuote(item.fullName)}', '${item.email}', '${item.dateOfBirth}', '${item.phone}')">
                            Sửa
                        </div>
                        <a href="removeUser?uid=${item.id}">
                            <div>Xóa</div>
                        </a>
                    </div>
                </div>
            `;

            case "searchOrder":
                return `
                <div>${item.id}</div>
                <div class="name">${item.fullName}</div>
                <div>${item.dateOfBooking}</div>
                <div>${formatCurrency(item.money)}</div>
                <div class="${item.status == '1' ? 'statusT' : 'statusF'}">
                    ${item.status == '1' ? 'Đã thanh toán' : 'Chờ thanh toán'}
                </div>
                <div class="menu">
                    <i class="fa-solid fa-ellipsis-vertical"></i>
                    <div class="ellipsis">
                        <div onclick="viewOrder(${item.id})">Chi tiết đơn hàng</div>
                        <a href="removeOder?oid=${item.id}">
                            <div>Xóa</div>
                        </a>
                    </div>
                </div>
            `;

            case "searchPromotion":
                return `
                <div>${item.id}</div>
                <div>${item.startDate}</div>
                <div>${item.endDate}</div>
                <div>${item.value}%</div>
                <div class="menu">
                    <i class="fa-solid fa-ellipsis-vertical"></i>
                    <div class="ellipsis">
                        <div onclick="UpdatePromotion('${item.id}', '${escapeQuote(item.name)}', '${item.startDate}', '${item.endDate}', '${item.value}')">
                            Sửa
                        </div>
                        <a href="removePromotion?poid=${item.id}">
                            <div>Xóa</div>
                        </a>
                    </div>
                </div>
            `;

            default:
                return '';
        }
    }


    function formatCurrency(number) {
        return Number(number).toLocaleString("vi-VN") + " VND";
    }

    function escapeQuote(str) {
        return typeof str === 'string' ? str.replace(/'/g, "\\'") : str;
    }

});


// Phan User
function addUser() {
    // windowUser();

    // Reset all fields in the form
    document.getElementById('uid').value = "";
    document.getElementById('UserName').value = "";
    document.getElementById('UserPassword').value = "";
    document.getElementById('Role').value = "";
    document.getElementById('FullName').value = "";
    document.getElementById('Phone').value = "";
    document.getElementById('Birthday').value = "";
    document.getElementById('Email').value = "";

    // Set button text to "Lưu"
    const saveButton = document.querySelector(".ButtonUser1");
    saveButton.textContent = "Lưu";

    // Update form action to "addUser"
    const userForm = document.querySelector("#UserWindow form");
    if (userForm) {
        userForm.action = "addUser";
    } else {
        console.error("Không tìm thấy form trong #UserWindow");
    }
}

function windowUser() {
    const newUser = document.getElementById("UserWindow");
    // Add an overlay to dim the background
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    // Display the user form
    newUser.style.display = "block";
}

function closeUser() {
    const overlay = document.getElementById("overlay");
    const newUser = document.getElementById("UserWindow");

    // Hide the form and remove the overlay
    newUser.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
    // Reset all fields in the form
    const inputs = newUser.querySelectorAll("input");
    const selectElements = newUser.querySelectorAll("select");
    selectElements.forEach(select => {
        select.value = ""; // Reset to default or blank
    });
    inputs.forEach(input => {
        input.value = ""; // Clear input values
    });
}

function UpdateUser(id, username, password, role, fullName, email, dateOfBirth, phone) {
    // Mở modal bằng Bootstrap 5
    const userModal = new bootstrap.Modal(document.getElementById('UserWindow'));
    userModal.show();

    // Gán dữ liệu vào form
    document.getElementById('uid').value = id;
    document.getElementById('UserName').value = username;
    document.getElementById('UserPassword').value = password;

    document.getElementById('Role').value = (role === "1") ? "Quản trị viên" : "Người dùng";
    document.getElementById('FullName').value = fullName;
    document.getElementById('Email').value = email;
    document.getElementById('Birthday').value = dateOfBirth;
    document.getElementById('Phone').value = phone;

    // Đổi nút lưu thành "Cập nhật"
    const saveButton = document.querySelector('.ButtonUser1');
    saveButton.textContent = "Cập nhật";

    // Đổi action form thành updateUser
    const form = document.querySelector("#UserWindow form");
    if (form) {
        form.action = "updateUser";
    }
}


// Phan Don hang
// hien form chi tiet don hang
async function viewOrder(orderId) {

    const response = await fetch(`/web/detailOrder?orderId=${orderId}`);
    const orderDetails = await response.json();
    const viewOrder = document.getElementById("OderWindow");
    // Thêm một lớp overlay để làm tối nền
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);

    const detailContainer = viewOrder.querySelector(".Product_List_item");
    detailContainer.innerHTML = ""; // Xóa dữ liệu cũ
    const detailTotalAmount = viewOrder.querySelector(".TotalAmount");
    detailTotalAmount.innerHTML = ""; // Xóa dữ liệu cũ
    let totalAmount = 0;
    orderDetails.forEach(detail => {
        totalAmount += detail.price * detail.quantity;
        const itemHTML = `
            <div class="Product_item">
                            <div class="Product_item_imgnotice">
                                <img src="${detail.image}" alt="Product Image" class="Product_img_item">
                                <span class="Product_item_notice">${detail.quantity}</span>
                            </div>
                            <div class="Product_item_info">
                                <div class="Product_item_header">
                                    <div class="Product_item_name">${detail.name}</div>
                                    <div class="Product_item_price">${detail.price * detail.quantity}VND</div>
                                </div>
                            </div>
                        </div>

        `;
        detailContainer.innerHTML += itemHTML;
    });
    const itemHTMLTotalAmount = `
        <span class="text">Tổng cộng</span>
        <span class="total" id="total">${totalAmount}VND</span>
    `;
    detailTotalAmount.innerHTML = itemHTMLTotalAmount;
    //
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

// Phan Khuyen Mai
//  Hiển thị form thêm khuyến mãi
function addPromotion() {
    // windowPromotion();   

    // Reset all fields in the form
    document.getElementById('poid').value = "";
    document.getElementById('PromotionName').value = "";
    document.getElementById('StartDate').value = "";
    document.getElementById('EndDate').value = "";
    document.getElementById('Value').value = "";

    // Set button text to "Lưu"
    const saveButton = document.querySelector(".ButtonPromotion1");
    saveButton.textContent = "Lưu";

    // Update form action to "addPromotion"
    const action = document.querySelector(".PromotionWindow form");
    if (action) {

        action.action = "addPromotion";
    }
}
function generatePromoCode() {
    const newPromotion = document.getElementById("GeneratePromoCode");
    // Add an overlay to dim the background
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    // Display the promotion form
    newPromotion.style.display = "block";
}
function closeGeneratePromoCode() {
    const overlay = document.getElementById("overlay");
    const newPromotion = document.getElementById("GeneratePromoCode");

    // Hide the form and remove the overlay
    newPromotion.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
    // Reset all fields in the form
    const inputs = newPromotion.querySelectorAll("input, select"); // Chọn cả input và select
    inputs.forEach(input => {
        input.value = ""; // Xóa giá trị các trường
        if (input.type === "select-multiple") {
            // Đặt lại các lựa chọn cho select multiple
            Array.from(input.selectedOptions).forEach(option => {
                option.selected = false;
            });
        }
    });
}
function windowPromotion() {
    const newPromotion = document.getElementById("PromotionWindow");
    // Add an overlay to dim the background
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    // Display the promotion form
    newPromotion.style.display = "block";
}

function closePromotion() {
    const overlay = document.getElementById("overlay");
    const newPromotion = document.getElementById("PromotionWindow");

    // Hide the form and remove the overlay
    newPromotion.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
    // Reset all fields in the form
    const inputs = newPromotion.querySelectorAll("input");
    inputs.forEach(input => {
        input.value = ""; // Clear input values
    });
}

function UpdatePromotion(id, name, startDate, endDate, value) {
    // Populate the form with promotion details
    document.getElementById('poid').value = id;
    document.getElementById('PromotionName').value = name;
    document.getElementById('StartDate').value = startDate;
    document.getElementById('EndDate').value = endDate;
    document.getElementById('Value').value = value;

    // Set button text to "Cập nhật"
    const saveButton = document.querySelector('.ButtonPromotion1');
    saveButton.textContent = "Cập nhật";

    // Update form action to "updatePromotion"
    const action = document.querySelector(".PromotionWindow form");
    if (action) {

        action.action = "updatePromotion";
    }

    // Show the modal using Bootstrap Modal API
    const promotionModal = new bootstrap.Modal(document.getElementById('PromotionWindow'));
    promotionModal.show();
}
