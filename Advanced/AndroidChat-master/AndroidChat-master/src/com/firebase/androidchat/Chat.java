package com.firebase.androidchat;

/**
 * User: greg
 * Date: 6/21/13
 * Time: 2:38 PM
 */
public class Chat {

    private String message;
    private String author;

    // Required default constructor for Firebase object mapping
    private Chat() { }

    Chat(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
