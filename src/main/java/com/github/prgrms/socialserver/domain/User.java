package com.github.prgrms.socialserver.domain;

import java.time.LocalDateTime;

public class User {
    private Long seq; // 불변
    private String email; // 입력
    private String passwd; // 입력
    private Integer login_count; // 가변
    private LocalDateTime last_login_at; // 가변
    private LocalDateTime create_at; // 불변

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }

    public void setLast_login_at(LocalDateTime last_login_at) {
        this.last_login_at = last_login_at;
    }

    public Long getSeq() {
        return seq;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public int getLogin_count() {
        return login_count;
    }

    public LocalDateTime getLast_login_at() {
        return last_login_at;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public User(Long seq, String email, String passwd, int login_count,
                LocalDateTime last_login_at, LocalDateTime create_at) {
        this.seq = seq;
        this.email = email;
        this.passwd = passwd;
        this.login_count = login_count;
        this.last_login_at = LocalDateTime.now();
        this.create_at = LocalDateTime.now();
    }

    public User(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
        this.last_login_at = LocalDateTime.now();
    }
}
