package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Address;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveAddress", value = "/removeAddress")
public class RemoveAddress extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("addressId")) ;
        AddressServices addressServices = new AddressServices();
        addressServices.removeAddress(id);
        List<Address> listAddress = addressServices.getAllById(1);
        request.setAttribute("listAddress", listAddress);
        // Forward về trang quản lý địa chỉ
        request.getRequestDispatcher("Customer.jsp?runScript=option4").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}