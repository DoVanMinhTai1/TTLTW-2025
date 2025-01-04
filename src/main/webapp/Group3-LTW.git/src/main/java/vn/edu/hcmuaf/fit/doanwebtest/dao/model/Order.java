package vn.edu.hcmuaf.fit.doanwebtest.dao.model;

public class Order {
    private int id;
    private int userId;
    private String dateOfBooking;
    private int status;
    private int money;
    private int addressId;
    private String fullName;

    public Order(int id, int userId, String dateOfBooking, int status, int money, int addressId, String fullName) {
        this.id = id;
        this.userId = userId;
        this.dateOfBooking = dateOfBooking;
        this.status = status;
        this.money = money;
        this.addressId = addressId;
        this.fullName = fullName;
    }

    public Order() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return id + ";" + userId + ";" + dateOfBooking + ";" + status + ";" + money + ";" + addressId+ ";" + fullName;
    }

}
