package vn.edu.hcmuaf.fit.projectwebck.controller.Home.All;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;


import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "sort", value = "/sort")
public class sort extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sortProduct");
        ProductServices productService = new ProductServices();
        List<Product> products = productService.getAll();

        if ("Giá giảm dần".equals(sort)) {
            // Sắp xếp sản phẩm theo giá giảm dần
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return Double.compare(p2.getPrice(), p1.getPrice());
                }
            });
        } else if ("Giá tăng dần".equals(sort)) {
            // Sắp xếp sản phẩm theo giá tăng dần
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return Double.compare(p1.getPrice(), p2.getPrice());
                }
            });
        } else {
            // Sắp xếp mặc định

        }
        request.setAttribute("listPaging", products);
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1"; // Mặc định là trang 1
        }
        request.getRequestDispatcher("showAll?index=" + indexPage + "&sortProduct=" + sort).forward(request, response);
//        request.getRequestDispatcher("showAll").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}