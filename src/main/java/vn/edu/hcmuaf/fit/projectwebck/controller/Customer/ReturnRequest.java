package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.User;
import vn.edu.hcmuaf.fit.projectwebck.dao.ReturnRequestDAO;

@WebServlet(name = "returnRequest", value = "/ReturnRequest")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=20*1024*1024)
public class ReturnRequest extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    private List<String> saveProductImageList(Collection<Part> listPart) {
        String uploadPath = getServletContext().getRealPath("/Img");
        List<String> listPathRes = new ArrayList<>();
        File file = new File(uploadPath);
        if (!file.exists()) file.mkdirs();

        for (Part part : listPart) {
            if (part.getName().equals("images") && part.getSize() > 0) {
                String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
                String filePath = uploadPath + File.separator + fileName;

                try {
                    part.write(filePath);
                    listPathRes.add("Img/" + fileName); // Relative path
                } catch (IOException e) {
                    throw new RuntimeException("Lỗi khi lưu ảnh: " + fileName, e);
                }
            }
        }
        return listPathRes;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");

        String orderIdStr = req.getParameter("orderId");
        String reason = req.getParameter("reason");

        if (orderIdStr == null || reason == null || reason.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thông tin không hợp lệ");
            return;
        }

        int orderId = Integer.parseInt(orderIdStr);
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();

        List<String> imagePaths = saveProductImageList(req.getParts());

        // Gọi DAO để lưu vào CSDL
        ReturnRequestDAO.insertRequest(orderId, userId, reason, imagePaths);

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}