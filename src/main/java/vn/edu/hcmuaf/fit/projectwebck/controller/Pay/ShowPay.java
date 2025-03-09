package vn.edu.hcmuaf.fit.projectwebck.controller.Pay;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Address;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Transport;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;
import vn.edu.hcmuaf.fit.projectwebck.services.TransportServices;
import vn.edu.hcmuaf.fit.projectwebck.services.UserServices;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowPay", value = "/showPay")
public class ShowPay extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TransportServices transportServices = new TransportServices();
        List<Transport> list= transportServices.getAll();
        request.setAttribute("listTransport", list);
        int uId =Integer.parseInt(request.getParameter("uId")) ;
        UserServices us = new UserServices();
        String email = us.getUserById(uId).getEmail();
        AddressServices addressServices = new AddressServices();
        Address address = addressServices.getByIdOrigin(uId);
        request.setAttribute("address", address);

        String[] parts = address.getAddress().split(",");

        String number = parts.length > 0 ? parts[0].trim() : "";
        String ward = parts.length > 1 ? parts[1].trim() : "";
        String district = parts.length > 2 ? parts[2].trim() : "";
        String province = parts.length > 3 ? parts[3].trim() : "";

        request.setAttribute("number", number);
        request.setAttribute("ward", ward);
        request.setAttribute("district", district);
        request.setAttribute("province", province);
        request.setAttribute("email", email);
        request.getRequestDispatcher("Pay.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}