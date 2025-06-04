package vn.edu.hcmuaf.fit.projectwebck.controller.Shipping;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ship.DistrictResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ship.ProvinceResponse;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ship.WardResponse;
import vn.edu.hcmuaf.fit.projectwebck.services.GHNApiClient;

import java.io.IOException;

@WebServlet(name = "AddressDataServlet", value = "/addressData")
public class AddressDataServlet extends HttpServlet {
    private static final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String type = request.getParameter("type");
        String parentId = request.getParameter("parentId");

        try {
            switch (type) {
                case "provinces":
                    ProvinceResponse provinceResponse = GHNApiClient.getProvinces();
                    response.getWriter().write(gson.toJson(provinceResponse.getData()));
                    break;
                case "districts":
                    DistrictResponse districtResponse = GHNApiClient.getDistricts(Integer.parseInt(parentId));
                    response.getWriter().write(gson.toJson(districtResponse.getData()));
                    break;
                case "wards":
                    WardResponse wardResponse = GHNApiClient.getWards(Integer.parseInt(parentId));
                    response.getWriter().write(gson.toJson(wardResponse.getData()));
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"Invalid type parameter\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}