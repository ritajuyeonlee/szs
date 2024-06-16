package com.szs.domain.tax.dto.request;

public class ScrapRequestDto {

    private String userId;

    private String password;

    private String name;

    private String regNo;


    public ScrapRequestDto(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
    }

    public String getUserId() {
        return userId;
    }


    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;
    }


    public String getRegNo() {
        return regNo;
    }


}
