package com.example.web4.dto;

public class UserDto {

    private Long id;
    private String login;
    private String password;

    // Конструкторы, геттеры и сеттеры
    public UserDto() {}

    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.login = username;
        this.password = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String email) {
        this.password = email;
    }
}
