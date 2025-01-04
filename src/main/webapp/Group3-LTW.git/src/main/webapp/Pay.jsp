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
    <title>Pay</title>
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

        .PayLeftContent {
            width: 70%;
            float: left;
            padding-top: 30px;
        }

        .PayLeftContentAlpha {
            width: 50%;
            float: left;
        }

        .PayLeftContentOmega {
            width: 50%;
            float: right;
        }

        .PayLeftContentTitle {
            font-size: 22px;
            color: #7cc652;
            margin-bottom: 20px;
        }

        .PayLeftContentAlphaHeader {
            margin-bottom: 10px;
        }

        input:focus {
            border-color: #7cc652;
            outline: none;
            box-shadow: 0 0 1px #7cc652;
        }

        textarea:focus {
            border-color: #7cc652;
            outline: none;
            box-shadow: 0 0 1px #7cc652;
        }

        select:focus {
            border-color: #7cc652;
            outline: none;
            box-shadow: 0 0 1px #7cc652;
        }

        .PayLeftContentAlphaText {
            margin-right: 130px;
            font-weight: 600;
        }

        .PayLeftContentAlphaLogin {
            font-size: 14px;
            color: #7cc652;
        }

        .PayLeftContentAlphaLogin i {
            border-radius: 50%;
            border: solid 1px #7cc652;
            color: #7cc652;
            width: 14px;
            height: 14px;
            padding: 2px 2px 2px 3px;
            margin-right: 5px;
        }

        .PayLeftContentAlpha .form {
            margin: 5px 0;
            border: solid 1px gainsboro;
            padding-left: 10px;
            width: 90%;
            height: 40px;
            font-size: 14px;
            color: #888888;
            border-radius: 5px;
        }

        .PayLeftContentAlpha select {
            margin: 5px 0;
            border: solid 1px gainsboro;
            padding-left: 8px;
            width: 90%;
            height: 40px;
            font-size: 14px;
            color: #888888;
            border-radius: 5px;
        }

        .PayLeftContentAlpha textarea {
            margin: 5px 0;
            border: solid 1px gainsboro;
            padding-left: 12px;
            width: 90%;
            height: 60px;
            font-size: 14px;
            color: #888888;
            border-radius: 5px;
        }

        .PayLeftContentOmegaText {
            font-weight: 600;
            margin-bottom: 15px;
        }

        .PayLeftContentOmegaTextSelect {
            margin: 10px 0;
            border: solid 1px gainsboro;
            padding: 10px 0 10px 10px;
            width: 90%;
            height: 22px;
            font-size: 14px;
            color: #888888;
            border-radius: 5px;
        }

        .PayLeftContentOmega label {
            display: inline-block;
            width: 260px;
        }

        .PayRightContent {
            width: calc(30% - 1px);
            float: right;
            border-left: 1px solid gainsboro;
            padding-top: 20px;
        }

        .PayRightContentTitle {
            padding: 0 0 20px 20px;
            border-bottom: 1px solid gainsboro;
            font-weight: 600;
        }

        .PayRightContent_item {
            display: flex;
            padding: 5px 0;
        }

        .PayRightContent_List_item {
            margin: 0 0 0 20px;
            padding: 15px 0 0;
            list-style: none;
            max-height: 300px; /* Chiều cao tối đa hiển thị, tùy chỉnh theo ý muốn */
            overflow-y: auto;
            border-bottom: 1px solid gainsboro;
        }

        .PayRightContent_item_imgnotice {
            position: relative;
        }

        .PayRightContent_item_notice {
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

        .PayRightContent_img_item {
            margin: 2px 5px 0 5px;
            width: 50px;
            border: 1px solid gainsboro;
            cursor: pointer;
            border-radius: 5px;
        }

        .PayRightContent_item_info {
            padding: 10px 0 0 15px;
        }

        .PayRightContent_item_name {
            display: inline-block;
            width: 170px;
            margin-right: 5px;
            overflow-wrap: break-word;
        }

        .PayRightContent_item_price {
            display: inline-block;
        }

        .PayRightContentTitleProductListDiscountCode {
            margin: 15px 0 0 20px;
            padding-bottom: 10px;
            border-bottom: solid 1px gainsboro;
        }

        .PayRightContentTitleProductListDiscountCode .form {
            height: 40px;
            width: 200px;
            padding-left: 10px;
            font-size: 14px;
            color: #888888;
            border-radius: 5px;
            border: solid 1px gainsboro;
        }

        .PayRightContentTitleProductListDiscountCode button {
            height: 44px;
            border-radius: 5px;
            margin-left: 10px;
            padding: 0 25px;
            background-color: #7cc652;
            border: none;
            color: white;
            cursor: pointer;
        }

        .PayRightContentTitleProductListSum {
            margin: 20px 0 0 20px;
            border-bottom: solid 1px gainsboro;
        }

        .PayRightContentTitleProductListSum .t1 {
            display: inline-block;
            width: 220px;
            margin-bottom: 15px;
        }

        .PayRightContentTitleProductListTotal {
            margin: 20px 0 0 20px;
        }

        .PayRightContentTitleProductListTotal .text {
            display: inline-block;
            width: 220px;
            margin-bottom: 15px;
            font-size: 20px;
        }

        .PayRightContentTitleProductListTotal .total {
            color: #7cc652;
            font-size: 20px;
        }

        .PayRightContentTitleProductListTotal .Back {
            text-decoration: none;
            color: #7cc652;
        }

        .PayRightContentTitleProductListTotal .WayBack {
            display: inline-block;
            width: 220px;
            margin-bottom: 15px;
            font-size: 16px;
        }

        .PayRightContentTitleProductListTotal button {
            height: 44px;
            border-radius: 5px;
            margin-left: 10px;
            padding: 0 22px;
            background-color: #7cc652;
            border: none;
            color: white;
            cursor: pointer;
        }

        .OrderSuccessful {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 460px;
            height: 360px;
            padding: 20px;
            border-radius: 5px;
            background-color: #ebebeb;
            display: none;
            z-index: 1001;
        }

        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1000;
        }

        .OrderSuccessful p {
            text-align: center;
            margin-bottom: 20px;
        }

        .OrderSuccessful i {
            font-size: 80px;
            margin-left: 185px;
            margin-bottom: 20px;
        }

        .OrderSuccessfulTitle {
            font-size: 26px;
            text-align: center;
            font-weight: 600;

        }

        .OrderSuccessfulFotter {
            text-align: center;
            margin-bottom: 20px;
        }

        .OrderSuccessfulFotter .Code {
            background-color: #7cc652;
            padding: 5px;
            border-radius: 5px;
            margin-left: 10px;
        }

        .OrderSuccessful button {
            background-color: #7cc652;
            font-weight: bold;
            border: none;
            padding: 15px;
            border-radius: 5px;
            margin-left: 165px;
        }
        .Continue {
            color: black;
            text-decoration: none;
        }

    </style>
</head>
<body>
<div class="PayContent">
    <div class="Container">
        <div class="PayLeftContent">
            <div class="PayLeftContentTitle">Nông Lâm Food</div>
            <!--            Phan thong tin nhan hang-->
            <div class="PayLeftContentAlpha">
                <div class="PayLeftContentAlphaHeader">
                    <span class="PayLeftContentAlphaText">Thông tin nhận hàng</span>
                    <span class="PayLeftContentAlphaLogin"><i class="fa-solid fa-user"></i>Đăng nhập</span>
                </div>

                <input type="text" name="Email" id="Email" placeholder="Email" class="form"><br>
                <input type="text" name="Fullname" id="Fullname" placeholder="Họ và tên" class="form"><br>
                <input type="text" name="Phonenumber" id="Phonenumber" placeholder="Số điện thoại" class="form"><br>
                <input type="text" name="" id="Address" placeholder="Address" class="form"><br>
                <select name="Conscious" id="Conscious" style="width: 93%;height:40px;">
                    <option value="" selected>Tỉnh thành</option>
                    <option value="KonTum"> Kon Tum</option>
                    <option value="Thái Bình">Thái Bình</option>
                    <option value="Hưng Yên">Hưng Yên</option>
                </select>
                <select name="District" id="District" style="width: 93%;height:40px;">
                    <option value="" selected>Quận huyện</option>
                    <option value="Thủ Đức">Thủ Đức</option>
                    <option value="Quận 1">Quận 1</option>
                    <option value="Quận 2">Quận 2</option>
                </select>
                <select name="Commune" id="Commune" style="width: 93%;height:40px;">
                    <option value="" selected>Phường xã</option>
                    <option value="Linh Trung">Linh Trung</option>
                    <option value="Linh Tây">Linh Tây</option>
                    <option value="Linh Xuân">Linh Xuân</option>
                </select>
                <textarea name="Note" id="Note" placeholder="Ghi chú"></textarea>
            </div>

            <!--phan van chuyen va thanh toan-->
            <div class="PayLeftContentOmega">
                <div class="PayLeftContentOmegaText">Vận chuyển</div>
                <div class="PayLeftContentOmegaTextSelect">
                    <input type="radio" id="Superspeed" name="option" value="Superspeed">
                    <label for="Superspeed">Giao hàng siêu tốc</label>
                    <span class="PayLeftContentOmegaTextPrice">40.000đ</span>
                </div>
                <div class="PayLeftContentOmegaTextSelect">
                    <input type="radio" id="2h" name="option" value="2h">
                    <label for="2h"> Giao hàng 2 giờ</label>
                    <span class="PayLeftContentOmegaTextPrice">30.000đ</span>
                </div>
                <div class="PayLeftContentOmegaTextSelect">
                    <input type="radio" id="4h" name="option" value="4h">
                    <label for="4h"> Giao hàng 4 giờ</label>
                    <span class="PayLeftContentOmegaTexPrice">25.000đ</span>
                </div>
                <div class="PayLeftContentOmegaText">Thanh toán</div>
                <div class="PayLeftContentOmegaTextSelect">
                    <input type="radio" id="DirectPayment" name="Payment" value="DirectPayment">
                    <label for="DirectPayment"> Thanh toán khi giao hàng</label>
                    <i class="fa-sharp-duotone fa-solid fa-money-bill"></i>
                </div>
                <div class="PayLeftContentOmegaTextSelect">
                    <input type="radio" id="Paybycard" name="Payment" value="Paybycard">
                    <label for="Paybycard"> Trả qua thẻ</label>
                    <i class="fa-sharp-duotone fa-solid fa-money-bill"></i>
                </div>
            </div>
        </div>
        <!--        tong ket thanh toan-->
        <div class="PayRightContent">
            <div class="PayRightContentTitle">Đơn hàng (2 sản phẩm)</div>
            <div class="PayRightContentTitleProductList">
                <ul id="cartItems" class="PayRightContent_List_item">
                    <!--                    Noi nhan dc ds sp trong gio hang-->
                    <div class="PayRightContent_item">
                        <div class="PayRightContent_item_imgnotice">
                            <img src="../Img/RauMuong.webp" alt="Product Image" class="PayRightContent_img_item">
                            <span class="PayRightContent_item_notice">1</span>
                        </div>
                        <div class="PayRightContent_item_info">
                            <div class="PayRightContent_item_header">
                                <div class="PayRightContent_item_name">Rau muong</div>
                                <div class="PayRightContent_item_price">20.000</div>
                            </div>
                        </div>
                    </div>
                </ul>
                <div class="PayRightContentTitleProductListDiscountCode">
                    <input type="text" name="DiscountCode" id="DiscountCode" placeholder="Mã giảm giá" class="form">
                    <button type="submit">Áp dụng</button>
                </div>
                <div class="PayRightContentTitleProductListSum">
                    <div class="text1">
                        <span class="t1">Tạm tính</span>
                        <span>1.200.000đ</span>
                    </div>
                    <div class="text2">
                        <span class="t1">Phí vận chuyển</span>
                        <span>40.000đ</span>
                    </div>
                </div>
                <div class="PayRightContentTitleProductListTotal">
                    <div>
                        <span class="text">Tổng cộng</span>
                        <span class="total" id="total">1.240.000đ</span>
                    </div>
                    <span class="WayBack"><a
                            href="http://localhost:63342/Group3-LTW.git/ShoppingCart/ShoppingCart.html?_ijt=qmer6ibjrbtvsb4f7h1s5l230s&_ij_reload=RELOAD_ON_SAVE"
                            class="Back" target="myTab">
                    < Quay về giỏ hàng</a></span>
                    <span class="total"><button type="submit" id="ConfirmAddressButton">Đặt Hàng</button></span>
                </div>
            </div>
        </div>
    </div>
</div>
<!--cua so thong bao dat hang thanh cong-->
<div class="OrderSuccessful" id="OrderSuccessful">
    <i class="fa-regular fa-circle-check"></i>
    <div class="OrderSuccessfulTitle">Đặt Hàng Thành Công</div>
    <p class="OrderSuccessfulText">Nếu có bất kì câu hỏi nào, bạn có thể liên hệ với chúng tôi qua số <strong>+84 (0)
        327237467</strong>
        hoặc email <strong> nonglamfood@gmail.com</strong>, hoặc xem <strong>Trung tâm hỗ trợ khách hàng</strong>. Bạn
        cũng có thể theo dõi đơn hàng với mã
        đơn hàng dưới đây.</p>
    <div class="OrderSuccessfulFotter">
        <span>Mã đơn hàng</span><span class="Code">#FOOD1998</span>
    </div>
    <button type="submit"><a
            href="../home.html"
            class="Continue" target="myTab">
        VỀ TRANG CHỦ
    </a></button>
</div>
</body>
</html>
