package com.library.domain;

public class UserToken {
    private String username;
    private String token;

    public UserToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
