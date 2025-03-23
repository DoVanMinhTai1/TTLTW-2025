// js phan lua chon Navigationbar
function navigationbarClick(select) {
    const options = document.querySelectorAll(".NavigationbarSelect");
    options.forEach(option => option.classList.remove("active"));
    // Thêm màu nổi bật vào phần tử được nhấp
    const selectedOption = document.getElementById(select);
    selectedOption.classList.add("active");

    const optionContent = document.querySelectorAll(".select");
    optionContent.forEach(option => option.classList.remove("active1"));

    const div = document.createElement('div');
    switch (select) {
        case "option1":
            const AccountInformation = document.querySelector(".AccountInformation");
            if (AccountInformation) {
                AccountInformation.classList.add("active1");
            }
            break;
        case "option2":
            const YourOrder = document.querySelector(".YourOrder");
            if (YourOrder) {
                YourOrder.classList.add("active1");
            }
            break;
        case "option3":
            const ChangePassword = document.querySelector(".ChangePassword");
            if (ChangePassword) {
                ChangePassword.classList.add("active1");
            }
            break;
        case "option4":
            const AddressBook = document.querySelector(".AddressBook");
            if (AddressBook) {
                AddressBook.classList.add("active1");
            }
            break
        case "option5":
            window.location.href = "../signIn&Up/index.html"
            break;
        default:
            div.innerHTML = "<p>Vui lòng chọn một mục.</p>";
            break
    }
}

async function  viewOrder(orderId,address,dateOfBooking) {
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
    viewOrder.querySelector(".total").innerText = totalAmount+'VND';
    viewOrder.querySelector(".delivery").innerText = address;
    viewOrder.querySelector(".deliveryDate").innerText = dateOfBooking;
    //
    viewOrder.style.display = "block";

}

function viewOrderClose() {
    const overlay = document.getElementById("overlay");
    const viewOrder = document.getElementById("OderWindow");
    viewOrder.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
}

// phan dia chi
function addAddress() {
    windowAddress()
    // Reset các trường nhập liệu
    document.getElementById("Name").value = "";
    document.getElementById("Phone").value = "";
    document.getElementById("Company").value = "";
    document.getElementById("Address").value = "";
    document.getElementById("Nation").value = "";
    document.getElementById("Province").value = "";
    document.getElementById("District").value = "";
    document.getElementById("Wardandcommune").value = "";

    // Set button text to "Lưu"
    const saveButton = document.querySelector(".b2");
    saveButton.textContent = "Thêm địa chỉ";

    // Update form action to "addPromotion"
    const action = document.querySelector(".Addnewaddress form");
    action.action = "addAddress";

}

function UpdateAddress(id,name,address,phone,origin,company) {
    windowAddress()

    const parts = address.split(',');

    // Gán các giá trị
    const addresss = parts[0]?.trim() || "";         // Địa chỉ
    const ward = parts[1]?.trim() || "";            // Phường xã
    const district = parts[2]?.trim() || "";        // Quận huyện
    const province = parts[3]?.trim() || "";        // Tỉnh thành
    const nation = parts[4]?.trim() || "";          // Quốc gia
    // Điền thông tin vào form
    document.getElementById('addressId').value = id;
    document.getElementById("Name").value = name;
    document.getElementById("Phone").value = phone;
    document.getElementById("Company").value = company;
    document.getElementById("Address").value = addresss;
    document.getElementById("Nation").value = nation;
    document.getElementById("Province").value = province;
    document.getElementById("District").value = district;
    document.getElementById("Wardandcommune").value = ward;


    const saveButton = document.querySelector('.b2');
    saveButton.textContent = "Cập nhật";

    // Update form action to "updatePromotion"
    const action = document.querySelector(".Addnewaddress form");
    action.action = "updateAddress";


}

function closeAddress() {
    const overlay = document.getElementById("overlay");
    const newAddress = document.getElementById("Addnewaddress");

    // Ẩn form và xóa overlay
    newAddress.style.display = "none";
    if (overlay) {
        overlay.remove();
    }
}

function windowAddress() {
    const newAddress = document.getElementById("Addnewaddress");
    const overlay = document.createElement('div');
    overlay.className = "overlay";
    overlay.id = "overlay";
    document.body.appendChild(overlay);
    newAddress.style.display = "block";
}
function toggleRadioButton() {
    const checkI = document.getElementById("fa-check");
    const isDefaultInput = document.getElementById("isDefault");

    if (checkI.style.color === "blue") {
        checkI.style.color = "white";
        isDefaultInput.value = "0"; // Không được chọn
    } else {
        checkI.style.color = "blue";
        isDefaultInput.value = "1"; // Được chọn
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

// Hàm hiển thị thông báo
function displayMessage(message, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.textContent = message;
    messageDiv.className = type === 'error' ? 'alert alert-danger' : 'alert alert-success';
}

// Gọi các hàm kiểm tra khi tài liệu đã sẵn sàng
document.addEventListener('DOMContentLoaded', function() {
    addPasswordValidation('newpassword');
    addConfirmPasswordValidation('newpassword', 'confirm-password');
});