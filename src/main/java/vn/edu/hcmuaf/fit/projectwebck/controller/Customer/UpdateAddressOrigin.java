package vn.edu.hcmuaf.fit.projectwebck.controller.Customer;

import java.io.*;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.services.AddressServices;

@WebServlet(name = "updateAddressOrigin", value = "/updateAddressOrigin")
public class UpdateAddressOrigin extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader br = req.getReader();
        String line;
        StringBuilder responseBuilder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            responseBuilder.append(line);
        }

        JsonObject jsonObject = new com.google.gson.JsonParser().parseString(responseBuilder.toString()).getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();
        int userId = jsonObject.get("userId").getAsInt();

        AddressServices addressServices = new AddressServices();
        boolean success = addressServices.updateAddressOrigin(id,userId);


    }

    public void destroy() {
    }
}