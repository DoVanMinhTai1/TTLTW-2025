package vn.edu.hcmuaf.fit.projectwebck.dao.model;

public class Promotion {
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private int value;
    private String description;

    public Promotion(int id, String name, String startDate, String endDate, int value, String description) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
        this.description = description;
    }

    public Promotion(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + startDate + ";" + endDate + ";" + value+ ";" + description;
    }

}
