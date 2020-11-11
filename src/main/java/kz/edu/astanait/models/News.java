package kz.edu.astanait.models;

public class News {
    private Integer id;
    private String title;
    private String description;
    private Integer moderator_id;

    public News() {
    }

    public News(Builder builder) {
        id = builder.id;
        title = builder.title;
        description = builder.description;
        moderator_id = builder.moderator_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getModerator_id() {
        return moderator_id;
    }

    public void setModerator_id(Integer moderator_id) {
        this.moderator_id = moderator_id;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", moderator_id=" + moderator_id +
                '}';
    }

    public static class Builder {
        private Integer id;
        private String title;
        private String description;
        private Integer moderator_id;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setModerator_id(Integer moderator_id) {
            this.moderator_id = moderator_id;
            return this;
        }

        public News build() {
            return new News(this);
        }
    }
}
