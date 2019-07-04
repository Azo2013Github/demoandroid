package com.pgrsoft.resthelloworld.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private Integer id;
    private int userId;
    private String name;
    private String email;

    @SerializedName("body")
    private String text;

    public Comment(Integer id, int userId, String name, String email, String text) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
