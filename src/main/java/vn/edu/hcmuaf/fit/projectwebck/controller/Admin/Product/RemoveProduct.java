package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.LogsServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveProduct", value = "/removeProduct")
public class RemoveProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("pid");
        int pid = Integer.parseInt(id);
        ProductServices productService = new ProductServices();
        Product productRm=productService.getById(pid);
        productService.removeProduct(pid);
        List<Product> products = productService.getAll();
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserServices userService = new UserServices();
            User user = (User) session.getAttribute("user"); ;
            if (user != null) {
                // Gọi LogService để ghi log
                LogsServices logService = new LogsServices();
                logService.danger(user.getUsername()+" đã xóa một sản phẩm",user.getId(),"Xóa sản phẩm",productRm.toString(),"");
            }
        }
        request.setAttribute("listproduct", products);
        request.setAttribute("message", "Xóa sản phẩm thành công");
        request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}