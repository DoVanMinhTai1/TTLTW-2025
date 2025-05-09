package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class Order {
    private int id;
    private int userId;
    private String dateOfBooking;
    private int status;
    private double money;
    private int addressId;
    private String fullName;
    private String phone;
    private String address;
    private String thirty_party_id;

    public Order(int id, int userId, String dateOfBooking, double money, int status, int addressId, String fullName, String phone, String address, String thirty_party_id) {
        this.id = id;
        this.userId = userId;
        this.dateOfBooking = dateOfBooking;
        this.money = money;
        this.status = status;
        this.addressId = addressId;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.thirty_party_id = thirty_party_id;
    }

    public Order() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getThirty_party_id() {
        return thirty_party_id;
    }

    public void setThirty_party_id(String thirty_party_id) {
        this.thirty_party_id = thirty_party_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateOfBooking='" + dateOfBooking + '\'' +
                ", status=" + status +
                ", money=" + money +
                ", addressId=" + addressId +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", thirty_party_id='" + thirty_party_id + '\'' +
                '}';
    }
}
