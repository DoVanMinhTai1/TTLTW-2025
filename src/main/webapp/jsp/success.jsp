<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/5/2025
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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

    <div class="d-flex" style=" justify-content: center; gap: 5px;">
        <button type="submit"><a
                href="showHome"
                class="Continue" target="myTab">
            VỀ TRANG CHỦ
        </a></button>
        <button type="button" onclick="exportPdf()">In hóa đơn</button>
    </div>

</div>

</body>
</html>
