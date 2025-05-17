package vn.edu.hcmuaf.fit.projectwebck.services;


import vn.edu.hcmuaf.fit.projectwebck.dao.model.Address;
import vn.edu.hcmuaf.fit.projectwebck.dao.AddressDao;

import java.util.List;

public class AddressServices {
    AddressDao addressDao = new AddressDao();

    public List<Address> getAllById(int userId) {
        return addressDao.getAllById(userId);
    }

    public Address getByIdOrigin(int userId) {
        return addressDao.getByIdOrigin(userId);
    }

    public void insertAddress(Address address) {
        addressDao.insertAddress(address);
    }

    public void removeAddress(int addressId) {
        addressDao.removeAddress(addressId);
    }

    public void updateAddress(Address address) {
        addressDao.updateAddress(address);
    }

    public Address getByThirtyPartyId(String uIdParam) {
        return addressDao.getByThirtyPartyId(uIdParam);
    }

    public void insertAddressByThirtyPartyId(Address newAddress) {
        addressDao.insertAddressByThirtyParTyId(newAddress);
    }
    public Address getByIdThirtyOrigin(String userId) {
        return addressDao.getByThirtyPartyId(userId);
    }

    public boolean updateAddressOrigin(int id, int userId) {
        return addressDao.updateAddressOrigin(id,userId);
    }
}
