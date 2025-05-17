package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Address;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@WebServlet(name = "AddAddress", value = "/addAddress")
public class AddAddress extends HttpServlet {
    private static final BigInteger MAX_LONG = new BigInteger(String.valueOf(Long.MAX_VALUE));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("Name");
        String phone = request.getParameter("Phone");
        String company = request.getParameter("Company");
        String address = request.getParameter("Address");
        String nation = request.getParameter("Nation");
        String province = request.getParameter("Province");
        String district = request.getParameter("District");
        String wardAndCommune = request.getParameter("Wardandcommune");
        int origin = Integer.parseInt(request.getParameter("isDefault"));
        String userIdString = request.getParameter("userId");
        BigInteger userIdbig = new BigInteger(userIdString);
        if (userIdbig.longValue() > 10000) {
            String addresss = address + "," + wardAndCommune + "," + district + "," + province + "," + nation;
            // Kiểm tra trạng thái của nút "Đặt địa chỉ làm mặc định"
            // Tạo đối tượng Address và gán giá trị
            Address newAddress = new Address();
            newAddress.setName(name);
            newAddress.setPhone(phone);
            newAddress.setCompany(company);
            newAddress.setAddress(addresss);
            newAddress.setOrigin(origin);
            newAddress.setThirty_party_id(userIdString);
            AddressServices addressServices = new AddressServices();
            addressServices.insertAddressByThirtyPartyId(newAddress);
            List<Address> listAddress = addressServices.getAllById(1);
            request.setAttribute("listAddress", listAddress);

            // Forward về trang quản lý địa chỉ
            request.getRequestDispatcher("Customer.jsp?runScript=option4").forward(request, response);

        } else {
           int userId = Integer.parseInt(request.getParameter("userId"));
            String addresss = address + "," + wardAndCommune + "," + district + "," + province + "," + nation;
            // Kiểm tra trạng thái của nút "Đặt địa chỉ làm mặc định"
            // Tạo đối tượng Address và gán giá trị
            Address newAddress = new Address();
            newAddress.setName(name);
            newAddress.setPhone(phone);
            newAddress.setCompany(company);
            newAddress.setAddress(addresss);
            newAddress.setOrigin(origin);
            newAddress.setUserId(userId);
            AddressServices addressServices = new AddressServices();
            addressServices.insertAddress(newAddress);
            List<Address> listAddress = addressServices.getAllById(userId);
            request.setAttribute("listAddress", listAddress);

            // Forward về trang quản lý địa chỉ
            request.getRequestDispatcher("Customer.jsp?runScript=option4").forward(request, response);

        }


        // Gọi service để thêm địa chỉ vào cơ sở dữ liệu

    }
}