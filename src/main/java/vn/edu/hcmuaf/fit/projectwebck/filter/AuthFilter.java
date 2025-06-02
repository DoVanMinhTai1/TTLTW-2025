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
        if (uri.endsWith("login") || uri.endsWith("showLogin") || uri.endsWith("SignInUp.jsp") || uri.endsWith("CaptchaServlet") || uri.endsWith("register")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect("showLogin");
            return;
        }

        User user = (User) session.getAttribute("user");
        Role role = Role.fromId(user.getRole());

        if (uri.contains("showOption") && httpRequest.getParameter("option") != null) {
            String option = httpRequest.getParameter("option");
            boolean hasAccess = checkPermission(role, option);
            if (!hasAccess) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        } else if (uri.contains("addProduct") || uri.contains("removeProduct") || uri.contains("updateProduct") || uri.contains("searchProduct") || uri.contains("GetProductImageByProductId") || uri.contains("deleteProductImageById")) {
            if (!role.hasPermission("MANAGE_VEGETABLES")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        } else if (uri.contains("addUser") || uri.contains("removeUser") || uri.contains("searchUser") || uri.contains("updateUser") || uri.contains("UpdateUserCustomer")) {
            if (!role.hasPermission("MANAGE_USERS")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        } else if (uri.contains("cancelOrder") || uri.contains("confirmOrder") || uri.contains("detailOrder") || uri.contains("removeOder") || uri.contains("searchOrder") || uri.contains("UpdateStatus")) {
            if (!role.hasPermission("MANAGE_ORDERS")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        } else if (uri.contains("updatePromotion") || uri.contains("addPromotion") || uri.contains("GeneratePromoCode") || uri.contains("removePromotion") || uri.contains("searchPromotion")) {
            if (!role.hasPermission("MANAGE_PROMOTIONS")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        } else if (uri.contains("updateProductDiscount") || uri.contains("AddProductDiscount") || uri.contains("deleteProductDiscount") || uri.contains("getProductById") || uri.contains("productDiscount")) {
            if (!role.hasPermission("MANAGE_PRODUCT_PROMOTION")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        } else if (uri.contains("updateStock") || uri.contains("UploadStock") || uri.contains("deleteStock") || uri.contains("getAllStock")) {
            if (!role.hasPermission("MANAGE_STOCK")) {
                httpResponse.sendRedirect("error.html");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean checkPermission(Role role, String option) {
        switch (option) {
            case "option1":
                return role.hasPermission("VIEW_DASHBOARD");
            case "option2":
                return role.hasPermission("MANAGE_VEGETABLES");
            case "option3":
                return role.hasPermission("MANAGE_USERS");
            case "option4":
                return role.hasPermission("MANAGE_ORDERS");
            case "option5":
                return role.hasPermission("MANAGE_PROMOTIONS");
            case "option6":
                return role.hasPermission("MANAGE_PRODUCT_PROMOTION");
            case "option7":
                return role.hasPermission("MANAGE_STOCK");
            default:
                return false;
        }
    }
}