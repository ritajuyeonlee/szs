package com.szs.domain;

import com.szs.dto.response.MemberDetails;
import com.szs.exception.InvalidInformationException;
import com.szs.exception.RequiredInformationBlankException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class Member {
    @Id
    private String userId;
    private String password;
    private String name;
    private String regNo;

    public Member() {
    }

    public static Member of(
            String userId,
            String password,
            String name,
            String regNo
    ) {
        if (userId.isBlank() || password.isBlank() || name.isBlank() || regNo.isBlank()) {
            throw new RequiredInformationBlankException();
        } else if ((name.equals("동탁") && regNo.equals("921108-1582816"))
                || (name.equals("관우") && regNo.equals("681108-1582816"))
                || (name.equals("손권") && regNo.equals("890601-2455116"))
                || (name.equals("유비") && regNo.equals("7904111656116"))
                || (name.equals("조조") && regNo.equals("8103262715702"))
        ) {
            return new Member(userId, password, name, regNo);
        } else {
          throw new InvalidInformationException();
        }

    }

    public UserDetails toMemberDetails(){
        return new MemberDetails(userId,password, "USER","email",true,true,true,true);

    }


    public Member(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.password = password;
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name;
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

