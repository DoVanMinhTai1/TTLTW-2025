package vn.edu.hcmuaf.fit.projectwebck.services;


import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Address;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.AddressDao;

import java.util.List;

public class AddressServices {
    AddressDao addressDao = new AddressDao();

    public List<Address> getAllById(int userId) {
        return addressDao.getAllById(userId);
    }

    public Address getByIdOrigin(int userId) {
        return addressDao.getByIdOrigin(userId);
    }

}
