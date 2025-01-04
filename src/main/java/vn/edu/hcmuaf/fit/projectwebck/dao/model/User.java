package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class User {
    private int id;
    private String username;
    private String password;
    private int role;
    private String fullName;
    private String email;
    private String dateOfBirth;
    private String phone;

    public User(int id, String username, String password, int role, String fullName, String email, String dateOfBirth, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
    }
    public User(){};
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + ";" + username + ";" + password + ";" + role + ";" +
                fullName + ";" + email + ";" + dateOfBirth + ";" + phone;
    }

}
