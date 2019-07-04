package com.pgrsoft.resthelloworld.model;

public class Post {

    private int id;
    private int userId;
    private String name;
    private String text;

    public Post(int id, int userId, String name, String text) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
