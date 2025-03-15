package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class Address {
    int id;
    int userId;
    String name;
    String address;
    String phone;
    int origin;
    String company;
    String dateOfBooking;
    String thirty_party_id;
    public Address(int id, int userId, String name, String address, String phone,
                   int origin, String company, String dateOfBooking,String thirty_party_id) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.origin = origin;
        this.company = company;
        this.dateOfBooking = dateOfBooking;
        this.thirty_party_id = thirty_party_id;
    }

    public Address() {
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getThirty_party_id() {
        return thirty_party_id;
    }

    public void setThirty_party_id(String thirty_party_id) {
        this.thirty_party_id = thirty_party_id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", origin=" + origin +
                ", company='" + company + '\'' +
                ", dateOfBooking='" + dateOfBooking + '\'' +
                ", thirty_party_id='" + thirty_party_id + '\'' +
                '}';
    }
}
