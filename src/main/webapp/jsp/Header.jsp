<%--
  Created by IntelliJ IDEA.
  User: dotai
  Date: 4/4/2025
  Time: 8:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<header class="container" id="">
    <div class="row">
        <div class="col-lg-2">

            <a href="showHome"><img id="logo" src="Img/snapedit_1730861562696.png" alt="Shopping Cart Image"
                                         style="width: 150px"></a>
        </div>
        <div class="col-lg-8 d-flex ">
            <div class="col-5 flex-grow-1" style="justify-content: center; align-items: center; height: 100%;">

                <form action="${pageContext.request.contextPath}/search" method="get" id="search-box10"
                      style="display: flex; justify-content: center; align-items: center; height: 100%">
                    <input type="text" name="search" id="search" placeholder="Bạn cần tìm gì ?" style="width: 80%; ">
                </form>
                <div id="suggestions" class="suggestions" style="display: none; position: absolute; background: white; border: 1px solid #ccc; z-index: 1000;"></div>
            </div>
            <div class="d-flex flex-grow-1" style="align-items: center; justify-content: center;">
                <i class="fas fa-phone"></i>
                <div class="headerphone">HOTLINE: 0327237467</div>
            </div>
            <div class="d-flex flex-grow-1" style="align-items: center; justify-content: center;">

                <c:set var="currentUser" value="${sessionScope.user}"/> <!-- Lấy user từ session -->
                <c:choose>
                    <c:when test="${not empty currentUser}">
                        <a href="showCustomerPage?uId=${sessionScope.user.id}" style="text-decoration: none">
                            <div class="headercontendangnhap">
                                    ${not empty currentUser.username ? currentUser.username : currentUser.given_name}
                            </div>
                        </a>
                        <div class="line d-flex align-items-center justify-content-center"
                             style="border: solid 1px gray;  height: 16px; margin-left: 3px; margin-right: 3px"></div>
                        <a href="logout" style="text-decoration: none">
                            <div class="headercontendangki">Đăng Xuất</div>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="showLogin" style="text-decoration: none">
                            <div class="headercontendangnhap">
                                Đăng Nhập
                            </div>
                        </a>
                        <div class="line d-flex align-items-center justify-content-center"
                             style="border: solid 1px gray;  height: 16px; margin-left: 3px; margin-right: 3px"></div>
                        <a href="showLogin" style="text-decoration: none">
                            <div class="headercontendangki">Đăng Kí</div>
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <!--        gio hang-->
        <div class="col-lg-2 d-flex align-items-center justify-content-center">

            <a href="/web/cartItem">
                <div class="d-flex align-items-center justify-content-center gap-3">
                    <div class="shopping_cart">
                        <div class="shopping_cart_swap">
                            <i class="fa-solid fa-basket-shopping"></i>
                            <span
                                    class="shopping_notice">${sessionScope.cart!=null?sessionScope.cart.totalQuantity:0}</span>
                        </div>
                    </div>
                    <div class="shoppingtext">Giỏ hàng</div>
                </div>
            </a>
        </div>
    </div>
</header>

</body>
<script>
    document.getElementById("search").addEventListener("input", function() {
        const query = this.value;
        const suggestionsBox = document.getElementById("suggestions");

        if (query.length > 0) {
            fetch(`/search?search=${query}`)
                .then(response => response.json())
                .then(data => {
                    suggestionsBox.innerHTML = ""; // Xóa gợi ý cũ
                    if (data.length > 0) {
                        suggestionsBox.style.display = "block"; // Hiển thị gợi ý
                        data.forEach(item => {
                            const suggestionItem = document.createElement("div");
                            suggestionItem.textContent = item.name; // Giả sử `name` là trường bạn muốn hiển thị
                            suggestionItem.classList.add("suggestion-item");
                            suggestionItem.addEventListener("click", function() {
                                document.getElementById("search").value = item.name; // Cập nhật ô tìm kiếm
                                suggestionsBox.innerHTML = ""; // Xóa gợi ý
                                suggestionsBox.style.display = "none"; // Ẩn gợi ý
                            });
                            suggestionsBox.appendChild(suggestionItem);
                        });
                    } else {
                        suggestionsBox.style.display = "none"; // Ẩn gợi ý nếu không có kết quả
                    }
                })
                .catch(error => console.error('Error fetching suggestions:', error));
        } else {
            suggestionsBox.style.display = "none"; // Ẩn gợi ý nếu ô tìm kiếm trống
        }
    });
</script>
</html>
