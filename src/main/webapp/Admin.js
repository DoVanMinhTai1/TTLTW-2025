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


// Js cho button them san pham
function addProduct() {
    windowProduct()

    document.getElementById('idp').value = "";
    document.getElementById('image').src = "";
    document.getElementById('productName').value = "";
    document.getElementById('productPrice').value = "";
    document.getElementById('productDescribe').value = "";
    document.getElementById('productMass').value = "";
    document.getElementById('productCategory').value = "Rau";

    const saveButton = document.querySelector(".ButtonProduct1");
    saveButton.textContent = "Lưu";

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
    windowProduct(); // Hiển thị modal
    // Điền thông tin sản phẩm vào form
    document.getElementById('idp').value = id;
    document.getElementById('image').src = image;
    document.getElementById('productName').value = name;
    document.getElementById('productPrice').value = price;
    document.getElementById('productDescribe').value = description;
    document.getElementById('productMass').value = mass;

    document.getElementById('productCategory').value = category;
    const saveButton = document.querySelector(".ButtonProduct1");
    saveButton.textContent = "Cập nhật";
    const action = document.querySelector(".ProductWindow form");
    action.action = "updateProduct";

}

// Phan User
function addUser() {
    windowUser();

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
    const action = document.querySelector(".UserWindow form");
    action.action = "addUser";
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
    windowUser(); // Display the modal
    // Populate the form with user details
    document.getElementById('uid').value = id;
    document.getElementById('UserName').value = username;
    document.getElementById('UserPassword').value = password;
    if (role === "1") {
        role = "Quản trị viên";
    } else {
        role = "Người dùng";
    }
    document.getElementById('Role').value = role;
    document.getElementById('FullName').value = fullName;
    document.getElementById('Email').value = email;
    document.getElementById('Birthday').value = dateOfBirth;
    document.getElementById('Phone').value = phone;

    // Set button text to "Cập nhật"
    const saveButton = document.querySelector('.ButtonUser1');
    saveButton.textContent = "Cập nhật";

    // Update form action to "updateUser"
    const action = document.querySelector(".UserWindow form");
    action.action = "updateUser";
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
    windowPromotion();

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
    action.action = "addPromotion";
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
    windowPromotion(); // Display the modal
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
    action.action = "updatePromotion";
}