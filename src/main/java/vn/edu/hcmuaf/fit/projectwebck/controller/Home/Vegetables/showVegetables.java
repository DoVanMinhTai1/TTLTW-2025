package vn.edu.hcmuaf.fit.projectwebck.controller.Home.Vegetables;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "showVegetables", value = "/showVegetables")
public class showVegetables extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductServices productService = new ProductServices();
        String indexPage = request.getParameter("index");

        if (indexPage == null) {
            indexPage = "1"; // Mặc định chỉ số trang là 1
        }
        int index = Integer.parseInt(indexPage);


        List<Product> products;
        if (request.getAttribute("listPaging") != null) {
            products = (List<Product>) request.getAttribute("listPaging");
        } else {
            products = productService.getAllVegetables(); // Nếu không có, lấy tất cả rau
        }

        int count = productService.getTotalVegetables();
        int productsPerPage = 50; // Số sản phẩm trên mỗi trang
        int endPage = (int) Math.ceil((double) count / productsPerPage);

        int fromIndex = (index - 1) * productsPerPage;
        int toIndex = Math.min(fromIndex + productsPerPage, products.size());
        List<Product> list = products.subList(fromIndex, toIndex);

        // Lưu giá trị sortProduct về request để hiển thị trên giao diện
        String sortProduct = request.getParameter("sortProduct");
        request.setAttribute("sortProduct", sortProduct);

        request.setAttribute("listPaging", list);
        request.setAttribute("endPage", endPage);
//        request.setAttribute("listProduct", products);
        request.getRequestDispatcher("jsp/Vegetables.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
