package kz.edu.astanait.models;

public class Admin {
    private Integer admin_id;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(Builder builder) {
        admin_id = builder.admin_id;
        username = builder.username;
        password = builder.password;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
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

    @Override
    public String toString() {
        return "Admins{" +
                "admin_id=" + admin_id +
                ", username=" + username +
                ", password=" + password +
                '}';
    }

    public static class Builder{
        private Integer admin_id;
        private String username;
        private String password;

        public Builder setAdmin_id(Integer admin_id) {
            this.admin_id = admin_id;
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

        public Admin build(){
            return new Admin(this);
        }
    }
}
