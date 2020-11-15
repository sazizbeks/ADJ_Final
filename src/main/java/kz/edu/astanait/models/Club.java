package kz.edu.astanait.models;

public class Club {
    private Integer club_id;
    private String club_name;

    public Club(){

    }

    public Club(Builder builder){
        club_id = builder.club_id;
        club_name = builder.club_name;
    }

    public Integer getClub_id() {
        return club_id;
    }

    public void setClub_id(Integer club_id) {
        this.club_id = club_id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    @Override
    public String toString() {
        return "News{" +
                "club_id=" + club_id +
                ", club_name=" + club_name +
                '}';
    }

    public static class Builder{
        private Integer club_id;
        private String club_name;

        public Builder setClub_id(Integer club_id) {
            this.club_id = club_id;
            return this;
        }

        public Builder setClub_name(String club_name) {
            this.club_name = club_name;
            return this;
        }

        public Club build(){
            return new Club(this);
        }
    }
}
