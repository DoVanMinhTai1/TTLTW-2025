package vn.edu.hcmuaf.fit.projectwebck.controller.Home.All;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp.Oauth2.GoogleLogin;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.GoogleProfile;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showAll", value = "/showAll")
public class showAll extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        String indexPage = request.getParameter("index");

        if (indexPage == null) {
            indexPage = "1"; // Mặc định chỉ số trang là 1
        }
        int index = Integer.parseInt(indexPage);

        // Lấy tổng số sản phẩm
        int count = productService.getTotalProducts();
        int productsPerPage = 50; // Số sản phẩm trên mỗi trang
        int endPage = (int) Math.ceil((double) count / productsPerPage);

        // Lấy danh sách sản phẩm đã được sắp xếp từ servlet sort
        List<Product> products;
        if (request.getAttribute("listPaging") != null) {
            products = (List<Product>) request.getAttribute("listPaging");
        } else {
            products = productService.getAll(); // Nếu không có, lấy tất cả sản phẩm
        }

        // Phân trang
        int fromIndex = (index - 1) * productsPerPage;
        int toIndex = Math.min(fromIndex + productsPerPage, products.size());
        List<Product> list = products.subList(fromIndex, toIndex);

        // Lưu giá trị sortProduct về request để hiển thị trên giao diện
        String sortProduct = request.getParameter("sortProduct");
        request.setAttribute("sortProduct", sortProduct);

        request.setAttribute("listPaging", list);
        request.setAttribute("endPage", endPage);

//        String code = request.getParameter("code");
//        if (code != null) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("OAuth2CallbackServlet-servlet");
//            dispatcher.forward(request, response);
//            return; // Dừng xử lý tiếp
//        }

//        request.setAttribute("listProduct", products);
        System.out.println(response);
        request.getRequestDispatcher("jsp/All.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
