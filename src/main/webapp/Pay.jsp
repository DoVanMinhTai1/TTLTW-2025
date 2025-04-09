<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/6/2024
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Pay</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Pay.css">
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

                <input type="text" name="Email" id="Email" placeholder="Email" class="form" value="${email}"><br>
                <input type="text" name="Fullname" id="Fullname" placeholder="Họ và tên" class="form"
                       value="${address.name}"><br>
                <input type="text" name="Phonenumber" id="Phonenumber" placeholder="Số điện thoại" class="form"
                       value="${address.phone}"><br>
                <input type="text" name="" id="Address" placeholder="Address" class="form" value="${number}"><br>
                <select name="Conscious" id="Conscious" style="width: 93%;height:40px;">
                    <option value="" selected>${province}</option>
                    <option value="Hồ Chí Minh"> Hồ Chí Minh</option>
                    <option value="Thái Bình">Thái Bình</option>
                    <option value="Hưng Yên">Hưng Yên</option>
                </select>
                <select name="District" id="District" style="width: 93%;height:40px;">
                    <option value="" selected>${district}</option>
                    <option value="Thủ Đức">Thủ Đức</option>
                    <option value="Quận 1">Quận 1</option>
                    <option value="Quận 2">Quận 2</option>
                </select>
                <select name="Commune" id="Commune" style="width: 93%;height:40px;">
                    <option value="" selected>${ward}</option>
                    <option value="Linh Trung">Linh Trung</option>
                    <option value="Linh Tây">Linh Tây</option>
                    <option value="Linh Xuân">Linh Xuân</option>
                </select>
                <textarea name="Note" id="Note" placeholder="Ghi chú"></textarea>
            </div>

            <!--phan van chuyen va thanh toan-->
            <div class="PayLeftContentOmega">
                <div class="PayLeftContentOmegaText">Vận chuyển</div>
                <form id="transportForm" action="SelectTransport" method="post">
                    <c:forEach var="tr" items="${listTransport}">
                        <div class="PayLeftContentOmegaTextSelect">
                            <input type="radio" id="${tr.id}" name="option" value="${tr.value}" onchange="submitForm()">
                            <label for="${tr.id}">${tr.name}</label>
                            <span class="PayLeftContentOmegaTextPrice"> <fmt:formatNumber value="${tr.value}"
                                                                                          type="number"
                                                                                          maxFractionDigits="0"/></span>
                        </div>
                    </c:forEach>
                </form>
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
            <div class="PayRightContentTitle">Đơn hàng (${sessionScope.cart!=null?sessionScope.cart.totalQuantity:0} sản
                phẩm)
            </div>
            <div class="PayRightContentTitleProductList">
                <ul id="cartItems" class="PayRightContent_List_item">
                    <c:choose>
                        <c:when test="${not empty cartList}">
                            <c:forEach items="${cartList}" var="p">
                                <div class="PayRightContent_item" data-id="${p.id}" data-quantity="${p.quantity}"
                                     data-price="${p.price}">
                                    <div class="PayRightContent_item_imgnotice">
                                        <img src="${p.img}" alt="Product Image" class="PayRightContent_img_item">
                                        <span class="PayRightContent_item_notice">${p.quantity}</span>
                                    </div>
                                    <div class="PayRightContent_item_info">
                                        <div class="PayRightContent_item_header">
                                            <div class="PayRightContent_item_name">${p.name}</div>
                                            <div class="PayRightContent_item_price"><fmt:formatNumber value="${p.price}"
                                                                                                      type="number"
                                                                                                      maxFractionDigits="0"/>đ
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${not empty product}">
                                <div class="PayRightContent_item" data-id="${product.id}" data-quantity="1"
                                     data-price="${product.price}">
                                    <div class="PayRightContent_item_imgnotice">
                                        <img src="${product.image}" alt="Product Image" class="PayRightContent_img_item">
                                        <span class="PayRightContent_item_notice">1</span>
                                    </div>
                                    <div class="PayRightContent_item_info">
                                        <div class="PayRightContent_item_header">
                                            <div class="PayRightContent_item_name">${product.name}</div>
                                            <div class="PayRightContent_item_price"><fmt:formatNumber
                                                    value="${product.price}"
                                                    type="number"
                                                    maxFractionDigits="0"/>đ
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                </ul>
                <div class="PayRightContentTitleProductListDiscountCode">
                    <input type="text" name="DiscountCode" id="DiscountCode" placeholder="Mã giảm giá" class="form">
                    <button type="button" onclick="discount(${sessionScope.total},${sessionScope.user.id})">Áp dụng
                    </button>

                </div>
                <div class="PayRightContentTitleProductListSum">
                    <div class="text1">
                        <span class="t1">Tạm tính</span>
                        <span id="provisional">
                            <c:choose>
                                <c:when test="${not empty cartList}">
                                    <fmt:formatNumber value="${sessionScope.total}" type="number" maxFractionDigits="0"/>đ
                                </c:when>
                                <c:when test="${not empty product}">
                                    ${product.price}đ
                                </c:when>
                                <c:otherwise>
                                    0đ
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                    <div class="text2">
                        <span class="t1">Phí vận chuyển</span>
                        <span id="transportValue"></span>
                    </div>
                </div>
                <div class="PayRightContentTitleProductListTotal">
                    <div>
                        <span class="text">Tổng cộng</span>
                        <span class="total" id="total"><fmt:formatNumber value="${sessionScope.total}" type="number"
                                                                         maxFractionDigits="0"/>đ</span>
                    </div>
                    <span class="WayBack"><a
                            href="ShowCart"
                            class="Back" target="myTab">
                    < Quay về giỏ hàng</a></span>
                    <span class="total"><button type="submit" id="ConfirmAddressButton"
                                                onclick="order(${sessionScope.user.id}, '${address.id}', '${sessionScope.total}')">Đặt Hàng</button></span>
                    <%--                    1 la userId cua tai khoan dang dn--%>
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
        <span>Mã đơn hàng</span><span class="Code" id="code">#FOOD1998</span>
    </div>
    <button type="submit"><a
            href="showHome"
            class="Continue" target="myTab">
        VỀ TRANG CHỦ
    </a></button>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/PayMoney.js"></script>
<script type="text/javascript">
    var userId = "${sessionScope.user.id}";
    console.log("User ID type: " + typeof userId);
</script>


</body>
</html>
