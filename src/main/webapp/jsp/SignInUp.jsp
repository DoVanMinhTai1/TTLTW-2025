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
<%
  String message = (String) session.getAttribute("message");
  if (message != null) {
%>
<div style="color: green;">
  <%= message %>
</div>
<% session.removeAttribute("message"); } %>
<h2>Chào Mừng Đến Với Nông Lâm Food</h2>
<div class="container" id="container">
  <div class="form-container sign-up-container">
    <form action="register" method = "post">
      <h1>Tạo Tài Khoản</h1>
      <div class="social-container">
        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/OAuth2CallbackServlet&response_type=code&client_id=314134351747-96fqghf4l57catc97as2q2ibv14l7u0u.apps.googleusercontent.com&approval_prompt=force" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>hoặc sử dụng số điện thoại của bạn để đăng ký</span>
      <input type="text" placeholder="Tên người dùng" name="username" required/>
      <input type="text" placeholder="Email" name="email" required/>
      <input type="text" placeholder="Số điện thoại" name="phone" required/>
      <input type="password" placeholder="Mật khẩu" name="password" required/>
      <button type="submit" >Đăng Kí</button>
    </form>
  </div>
  <div class="form-container sign-in-container">
    <form action="login" method ="post">
      <h1>Đăng Nhập</h1>
      <div class="social-container">
        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>

        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/OAuth2CallbackServlet&response_type=code&client_id=314134351747-96fqghf4l57catc97as2q2ibv14l7u0u.apps.googleusercontent.com&approval_prompt=force">

          <i class="fab fa-google-plus-g"></i>

        </a>

        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>hoặc sử dụng tài khoản của bạn</span>
      <input type="text" placeholder="Tên người dùng" name = "username" required/>
      <input type="password" placeholder="Mật khẩu" name ="password" required/>
      <input type="text" placeholder="Captcha" name ="captcha" required/>
      <img src="CaptchaServlet" alt="CAPTCHA Image">
      <a href="passwordBack">Quên mật khẩu?</a>
      <button type="submit" >Đăng nhập</button>
      <% if (request.getAttribute("errorMessage") != null) { %>
      <script>
        alert("<%= request.getAttribute("errorMessage") %>");
      </script>
      <% } %>
    </form>
  </div>
  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h1>Chào Mừng</h1>
        <p>
          Để luôn giữ kết nối với chúng tôi, vui lòng đăng kí tài khoản với các thông tin cá nhân cần thiết!
        </p>
        <button class="ghost" id="signIn">Đăng Nhập</button>
      </div>
      <div class="overlay-panel overlay-right">
        <h1>Chào Mừng Đến Với Nông Lâm Food!</h1>
        <p>Đăng nhập và cùng nhau trải nghiệm cảm giác mua sắm trực tuyến</p>
        <button class="ghost" id="signUp">Đăng Kí</button>
      </div>
    </div>
  </div>
</div>
<script>
  const signUpButton = document.getElementById('signUp');
  const signInButton = document.getElementById('signIn');
  const container = document.getElementById('container');
  const signUp2_Btn=  document.getElementById("signUp2")
  const signIn2 = document.getElementById("signIn2");

  signUpButton.addEventListener('click', () => {
    container.classList.add('right-panel-active');
  });

  signInButton.addEventListener('click', () => {
    container.classList.remove('right-panel-active');
  });

  signUp2_Btn.addEventListener('click',()=>{
    container.classList.remove('right-panel-active')
  });

  signIn2.addEventListener('click', () => {
    window.location.href = '../home.html';
  });
</script>
</body>
</html>

