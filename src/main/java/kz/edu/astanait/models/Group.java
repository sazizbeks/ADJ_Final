package kz.edu.astanait.models;

public class Group {
    private String major_id;
    private Integer group_number;

    public Group() {
    }

    public Group(String major_id, Integer group_number) {
        setMajor_id(major_id);
        this.group_number = group_number;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id.toUpperCase();
    }

    public Integer getGroup_number() {
        return group_number;
    }

    public void setGroup_number(Integer group_number) {
        this.group_number = group_number;
    }

    @Override
    public String toString() {
        return "Group{" +
                "major_id='" + major_id + '\'' +
                ", group_number=" + group_number +
                '}';
    }
}
