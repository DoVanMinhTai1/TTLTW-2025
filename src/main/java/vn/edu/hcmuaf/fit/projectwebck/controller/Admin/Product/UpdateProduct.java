package vn.edu.hcmuaf.fit.projectwebck.controller.Admin.Product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.projectwebck.controller.Admin.productdiscounts.LocalDateTimeAdapter;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Product;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.services.LogsServices;
import vn.edu.hcmuaf.fit.projectwebck.services.ProductServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
@WebServlet(name = "Update", value = "/updateProduct")
public class UpdateProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        int id = Integer.parseInt(request.getParameter("idp"));
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String massStr = request.getParameter("mass");
        String description = request.getParameter("describe");
        String category = request.getParameter("category");
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
        ProductServices service = new ProductServices();
        Product product = service.getById(id);
        String imagePath;
        if (productImagePart == null || productImagePart.getSize() == 0) {
            imagePath = product.getImage();
        } else {
            imagePath = saveProductImage(request, productImagePart);
        }
        try {
            // Chuyển đổi giá trị đầu vào
            double price = Double.parseDouble(priceStr);
            double mass = Double.parseDouble(massStr);
            int categoryId = Integer.parseInt(category);


            // Tạo đối tượng Product
            Product productUpdate = new Product();
            productUpdate.setId(id);
            productUpdate.setName(name);
            productUpdate.setPrice(price);
            productUpdate.setMass(mass);
            productUpdate.setDescription(description);
            productUpdate.setCategory(categoryId);
            productUpdate.setImage(imagePath);

            // Gọi service để lưu sản phẩm vào cơ sở dữ liệu
            service.updateProduct(productUpdate);
            List<Product> products = service.getAll();
            HttpSession session = request.getSession(false);

            if (session != null) {
                UserServices userService = new UserServices();
                User user = (User) session.getAttribute("user"); ;
                if (user != null) {
                    // Gọi LogService để ghi log
                    LogsServices logService = new LogsServices();
                    String beforedataJson = gson.toJson(product);
                    String afterdataJson = gson.toJson(productUpdate);
                    logService.warning(user.getUsername()+" đã cập nhật 1 sản phẩm",user.getId(),"Cập nhật sản phẩm",beforedataJson,afterdataJson);
                }
            }
            request.setAttribute("listproduct", products);
            request.setAttribute("message", "Cập nhật sản phẩm thành công");
            request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Cập nhật sản phẩm không thành công");
            request.getRequestDispatcher("Admin.jsp?runScript=option2").forward(request, response);
        }
    }

    private String saveProductImage(HttpServletRequest request, Part productImagePart) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + productImagePart.getSubmittedFileName();

        // Đường dẫn tuyệt đối tới thư mục Img trong webapp
        String uploadPath = getServletContext().getRealPath("/Img");

        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Kiểm tra xem file đã tồn tại chưa
        String filePath = uploadPath + File.separator + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            // Nếu file đã tồn tại, trả về đường dẫn tương đối
            return "Img/" + fileName;
        }

        // Nếu file chưa tồn tại, lưu file ảnh vào thư mục
        productImagePart.write(filePath);


        // Trả về đường dẫn tương đối để lưu vào database
        return "Img/" + fileName;
    }
}
