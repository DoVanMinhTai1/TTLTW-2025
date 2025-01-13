package vn.edu.hcmuaf.fit.projectwebck.services;
import vn.edu.hcmuaf.fit.projectwebck.dao.TransportDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Transport;

import java.util.List;

public class TransportServices {
    TransportDao dao = new TransportDao();

    public List<Transport> getAll() {
        return dao.getAll();
    }
    public Transport getById(int id) {
        return dao.getById(id);
    }
}
