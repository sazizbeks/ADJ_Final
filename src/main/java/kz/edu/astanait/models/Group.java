package kz.edu.astanait.models;

import java.util.Date;

public class Group {
    private String major_id;
    private Integer group_number;

    public Group() {
    }

    public Group(Integer group_number) {
        this.group_number = group_number;
    }


    public Group(String major_id, Integer group_number) {
        setMajor_id(major_id);
        this.group_number = group_number;
    }

    public Group(Builder builder) {
        this.major_id=builder.major_id;
        this.group_number= builder.group_number;
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
    public static class Builder{
        private String major_id;
        private Integer group_number;

        public Group.Builder setMajor_id(String major_id) {
            this.major_id = major_id;
            return this;
        }

        public Group.Builder setGroup_number(Integer group_number) {
            this.group_number = group_number;
            return this;
        }

        public Group build(){
            return new Group(this);
        }
    }

}
