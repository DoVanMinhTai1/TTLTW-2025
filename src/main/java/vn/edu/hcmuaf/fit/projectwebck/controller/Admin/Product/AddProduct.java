package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)

@WebServlet(name = "addProduct", value = "/addProduct")
public class AddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String massStr = request.getParameter("mass");
        String description = request.getParameter("describe");
        String category = request.getParameter("category");
        System.out.println(category);
        Part productImagePart = request.getPart("image");
        // Kiểm tra dữ liệu đầu vào
        if (name == null || name.trim().isEmpty() ||
                priceStr == null || priceStr.trim().isEmpty() ||
                massStr == null || massStr.trim().isEmpty() ||
                category == null || category.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        if (productImagePart == null || productImagePart.getSize() == 0) {
            request.setAttribute("error", "Image file is required.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            // Chuyển đổi giá trị đầu vào
            double price = Double.parseDouble(priceStr);
            double mass = Double.parseDouble(massStr);
            int categoryId = Integer.parseInt(category);

            // Lưu ảnh vào thư mục Img
            String imagePath = saveProductImage(request, productImagePart);
            System.out.println("Image: " + imagePath);

            // Tạo đối tượng Product
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setMass(mass);
            product.setDescription(description);
            product.setCategory(categoryId);
            product.setImage(imagePath);

            // Gọi service để lưu sản phẩm vào cơ sở dữ liệu
            ProductServices service = new ProductServices();
            service.insert(product);
            List<Product> products = service.getAll();
            request.setAttribute("listproduct", products);
            request.setAttribute("message", "Thêm sản phẩm thành công");
            request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Thêm sản phẩm không thành công");
            // Trả về kết quả và hiển thị lỗi bằng cách chuyển hướng
            request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request, response);
        }
    }

    private String saveProductImage(HttpServletRequest request, Part productImagePart) throws IOException {
        // Lấy tên file
        String fileName = System.currentTimeMillis() + "_" + productImagePart.getSubmittedFileName();

        // Đường dẫn tuyệt đối tới thư mục Img trong webapp
        String uploadPath = getServletContext().getRealPath("/Img");

        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }


        // Lưu file ảnh vào thư mục
        String filePath = uploadPath + File.separator + fileName;
        System.out.println("Image saved at: " + filePath);
        productImagePart.write(filePath);

        // Trả về đường dẫn tương đối để lưu vào database
        return "Img/" + fileName;
    }
}