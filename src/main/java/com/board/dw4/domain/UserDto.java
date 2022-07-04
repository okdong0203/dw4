package com.board.dw4.domain;

import javax.validation.constraints.*;

public class UserDto {
    private String id; // 아이디
    private String pwd; // 비밀번호
    private String checkPwd; // 비밀번호 확인
    private String name; // 이름
    private String email; // 이메일
    private String birth; // 생년월일

    public UserDto(){}

    public UserDto(String id, String pwd, String name, String email, String birth) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }


    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCheckPwd() { return checkPwd; }

    public void setCheckPwd(String checkPwd) { this.checkPwd = checkPwd; }

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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", checkPwd='" + checkPwd + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}
