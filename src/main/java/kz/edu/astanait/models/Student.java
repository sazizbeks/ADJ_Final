package kz.edu.astanait.models;

public class Student {
    private Integer id;
    private String fname;
    private String lname;
    private Integer year;
    private String username;
    private String password;
    private String major_id;
    private Integer group_number;

    public Student() {
    }

    public Student(Builder builder) {
        id = builder.id;
        fname = builder.fname;
        lname = builder.lname;
        year = builder.year;
        username = builder.username;
        password = builder.password;
        major_id = builder.major_id;
        group_number = builder.group_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public Integer getGroup_number() {
        return group_number;
    }

    public void setGroup_number(Integer group_number) {
        this.group_number = group_number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", year=" + year +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", major_id='" + major_id + '\'' +
                ", group_number=" + group_number +
                '}';
    }

    public static class Builder {
        private Integer id;
        private String fname;
        private String lname;
        private Integer year;
        private String username;
        private String password;
        private String major_id;
        private Integer group_number;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setFname(String fname) {
            this.fname = fname;
            return this;
        }

        public Builder setLname(String lname) {
            this.lname = lname;
            return this;
        }

        public Builder setYear(Integer year) {
            this.year = year;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setMajor_id(String major_id) {
            this.major_id = major_id;
            return this;
        }

        public Builder setGroup_number(Integer group_number) {
            this.group_number = group_number;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
