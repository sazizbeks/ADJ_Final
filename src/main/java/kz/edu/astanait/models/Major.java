package kz.edu.astanait.models;

public class Major {
    private String id;
    private String full_name;

    public Major(String id) {
        setId(id);
    }

    public Major(String id, String full_name) {
        setId(id);
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.toUpperCase();
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id='" + id + '\'' +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
