<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 1/6/2025
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>signInUp</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signInUp.css">
</head>
<body>
<h2>Chào Mừng Đến Với Nông Lâm Food</h2>
<%-- Hiển thị thông báo nếu có --%>
<%
  String message = (String) request.getAttribute("message");
  if (message != null) {
%>
<div class="alert">
  <p><%= message %></p>
</div>
<%
  }
%>
<div class="container" id="container">
  <div class="form-container resetPassword-container">
    <form action="${pageContext.request.contextPath}/passwordBack" method="post" id="resetPasswordForm">
      <h1>Quên Mật Khẩu?</h1>
      <div class="social-container">
        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <input type="text" placeholder="Email" name ="email" required/>
      <input type="text" placeholder="Username" name ="username" required/>
      <a href="showLogin">Hủy</a>
      <button type="submit">Gửi</button>
    </form>
  </div>
  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-right">
        <h1>Chào Mừng Đến Với Nông Lâm Food!</h1>
        <p>Chúng tôi sẽ gửi cho bạn một email để kích hoạt việc đặt lại mật khẩu</p>
      </div>
    </div>
  </div>
</div>
</body>

</html>