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
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="../Css/Admin.css">
    <script src="../Js/Admin.js"></script>
</head>
<body>
<div class="side-menu">
    <div class="brand-name">
        <h1>NLU Food</h1>
    </div>
    <ul>
        <li class="NavigationbarSelect" id="option1" onclick="navigationbarClick('option1')"><img
                src="../Img/bar-admin.png" alt="" style="width: 40px; height:40px;"/>&nbsp;<span>Điều khiển</span>
        </li>
        <li class="NavigationbarSelect" id="option2" onclick="navigationbarClick('option2')"><img
                src="../Img/snapedit_ves-admin.png" alt=""
                style="width: 40px; height:40px"/>&nbsp;<span>Rau củ</span></li>
        <li class="NavigationbarSelect" id="option3" onclick="navigationbarClick('option3')"><img
                src="../Img/snapedit_user-admin2.png" alt=""
                style="width: 40px; height:40px"/>&nbsp;<span>Người dùng</span>
        </li>
        <li class="NavigationbarSelect" id="option4" onclick="navigationbarClick('option4')"><img
                src="../Img/order-admin.png" alt="" style="width: 40px; height:40px"/>&nbsp;<span>Đơn hàng</span>
        </li>
        <li class="NavigationbarSelect" id="option5" onclick="navigationbarClick('option5')"><img
                src="../Img/discount-admin.png" alt=""
                style="width: 40px; height:40px"/>&nbsp;<span>Khuyến mãi</span>
        </li>
    </ul>
</div>
<div class="container">
    <div class="header">
        <div class="nav">
            <div class="search">
                <input type="text" placeholder="Search here">
                <button type="submit"><img src="../Img/search-btn.png" alt=""/></button>
            </div>
            <div class="user">
                <div class="img-case">
                    <ul>
                        <li>
                            <img src="../Img/user.png" alt="" class="admin-avatar" onclick ="btnclick()">
                            <div class="dropdown-menu">
                                <button class="logout-btn" onclick="logout()">Đăng Xuất</button>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="content" id="content">
        <div class="cards">
            <div class="card">
                <div class="box">
                    <h1>160</h1>
                    <h3>Rau, củ, quả</h3>
                </div>
                <div class="icon-case">
                    <img src="../Img/snapedit_seed.png" alt="" style="width: 40px; height: 40px; ">
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
                    <button class="btn" id="btn1" onclick="btnClick('btn2')">Xem Tất Cả</button>
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
                    <button class="btn" id="btn2" onclick="btnClick('btn1')">Xem Tất Cả</button>
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
    </div>
</div>
</body>
</html>
