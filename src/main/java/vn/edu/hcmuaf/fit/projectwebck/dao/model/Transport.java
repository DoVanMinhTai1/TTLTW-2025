package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class Transport {
    int id;
    String name;
    double value;

    public Transport(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Transport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
