package kz.edu.astanait.models;

import java.sql.Timestamp;
import java.util.Date;

public class Event {
    private Integer id;
    private String name;
    private Date start_date;
    private Date end_date;
    private Integer creator_id;

    public Event() {
    }

    public Event(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.start_date = builder.start_date;
        this.end_date = builder.end_date;
        this.creator_id = builder.creator_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", creator_id=" + creator_id +
                '}';
    }

    public static class Builder{
        private Integer id;
        private String name;
        private Date start_date;
        private Date end_date;
        private Integer creator_id;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setStart_date(Date start_date) {
            this.start_date = start_date;
            return this;
        }

        public Builder setEnd_date(Date end_date) {
            this.end_date = end_date;
            return this;
        }

        public Builder setCreator_id(Integer creator_id) {
            this.creator_id = creator_id;
            return this;
        }

        public Event build(){
            return new Event(this);
        }
    }
}
