package vn.edu.hcmuaf.fit.projectwebck.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Role;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String uri = httpRequest.getRequestURI();
        // Cho phép truy cập các trang công khai (login, showLogin, SignInUp.jsp, CaptchaServlet)
        if (uri.endsWith("login") || uri.endsWith("showLogin") || uri.endsWith("SignInUp.jsp") || uri.endsWith("CaptchaServlet")) {
            chain.doFilter(request, response);
            return;
        }
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect("showLogin");
            return;
        }

        User user = (User) session.getAttribute("user");
        Role role = Role.fromId(user.getRole());
        // Kiểm tra quyềnpie quyền cho showOption
        if (uri.contains("showOption") && httpRequest.getParameter("option") != null) {
            String option = httpRequest.getParameter("option");
            boolean hasAccess = checkPermission(role, option);
            if (!hasAccess) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        }
        // Kiểm tra quyền cho các hành động liên quan đến sản phẩm
        else if (uri.contains("addProduct") || uri.contains("removeProduct") || uri.contains("updateProduct")) {
            if (!role.hasPermission("MANAGE_VEGETABLES")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        }
        // Kiểm tra quyền cho các hành động liên quan đến người dùng
        else if (uri.contains("addUser") || uri.contains("removeUser") || uri.contains("updateUser")) {
            if (!role.hasPermission("MANAGE_USERS")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        }
        // Kiểm tra quyền cho các hành động liên quan đến khuyến mãi
        else if (uri.contains("addPromotion") || uri.contains("removePromotion") || uri.contains("updatePromotion")) {
            if (!role.hasPermission("MANAGE_PROMOTIONS")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        }
        // Kiểm tra quyền cho các hành động liên quan đến sản phẩm giảm giá
        else if (uri.contains("AddProductDiscount") || uri.contains("deleteProductDiscount") || uri.contains("updateProductDiscount")) {
            if (!role.hasPermission("MANAGE_PRODUCT_PROMOTION")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        }
        // Kiểm tra quyền cho các hành động liên quan đến đơn hàng
        else if (uri.contains("removeOder") || uri.contains("detailOrder")) {
            if (!role.hasPermission("MANAGE_ORDERS")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean checkPermission(Role role, String option) {
        switch (option) {
            case "option1": return role.hasPermission("VIEW_DASHBOARD");
            case "option2": return role.hasPermission("MANAGE_VEGETABLES");
            case "option3": return role.hasPermission("MANAGE_USERS");
            case "option4": return role.hasPermission("MANAGE_ORDERS");
            case "option5": return role.hasPermission("MANAGE_PROMOTIONS");
            case "option6": return role.hasPermission("MANAGE_PRODUCT_PROMOTION");
            default: return false;
        }
    }
}