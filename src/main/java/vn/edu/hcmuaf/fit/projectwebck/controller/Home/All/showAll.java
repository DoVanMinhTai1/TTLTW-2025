package vn.edu.hcmuaf.fit.projectwebck.controller.Home.All;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.controller.SingInUp.Oauth2.GoogleLogin;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.GoogleProfile;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "showAll", value = "/showAll")
public class showAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexPage = request.getParameter("index");

        if (indexPage == null) {
            indexPage = "1"; // Mặc định chỉ số trang là 1
        }
        int index = Integer.parseInt(indexPage);

        List<Product> products = (List<Product>) request.getAttribute("listPaging");
        if (products == null) {
            ProductServices productService = new ProductServices();
            products = productService.getAll(); // Nếu không có, lấy tất cả rau
        }
        String sortProduct = request.getParameter("sortProduct");
        List<Product> sortedProducts = new ArrayList<>(products);
        if ("Giá giảm dần".equals(sortProduct)) {
            // Sắp xếp sản phẩm theo giá giảm dần
            Collections.sort(sortedProducts, (p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        } else if ("Giá tăng dần".equals(sortProduct)) {
            // Sắp xếp sản phẩm theo giá tăng dần
            Collections.sort(sortedProducts, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        }
        int count = products.size();
        int productsPerPage = 50; // Số sản phẩm trên mỗi trang
        int endPage = (int) Math.ceil((double) count / productsPerPage);

        // Phân trang
        int fromIndex = (index - 1) * productsPerPage;
        int toIndex = Math.min(fromIndex + productsPerPage, products.size());
        List<Product> list = sortedProducts.subList(fromIndex, toIndex);

        // Lưu giá trị sortProduct về request để hiển thị trên giao diện
        request.setAttribute("sortProduct", sortProduct);
        request.setAttribute("listPaging", list);
        request.setAttribute("endPage", endPage);

//        request.setAttribute("listProduct", products);

        String[] listMainBanner = {
                "Img/banner.png",
                "Img/banner.png",
                "Img/banner.png"
        };

        // Gửi dữ liệu vào JSP
        request.setAttribute("listMainBanner", listMainBanner);


        System.out.println(response);
        request.getRequestDispatcher("jsp/All.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
