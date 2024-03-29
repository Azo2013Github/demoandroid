package com.pgrsoft.resthelloworld.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private int postId;
    private int id;
    private String name;
    private String email;

    @SerializedName("body")
    private String text;

    public Comment(){

    }

    public Comment(int postId, String name, String email, String text) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public int getPostId() {

        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public String getEmail() {

        return email;
    }

    public String getText() {

        return text;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
