package com.szs.domain.member.entity;

import com.szs.domain.authentication.dto.MemberDetails;
import com.szs.domain.member.dto.response.UpdateTaxRequestDto;
import com.szs.domain.member.entity.value.Tax;
import com.szs.exception.InvalidInformationException;
import com.szs.exception.RequiredInformationBlankException;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.DecimalFormat;


@Entity
public class Member {
    @Id
    @Comment(value = "사용자 ID")
    private String userId;

    @Comment(value = "비밀번호")
    private String password;

    @Comment(value = "이름")
    private String name;

    @Comment(value = "주민등록번호")
    private String regNo;

    @Embedded
    private Tax tax;


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

    public UserDetails toMemberDetails() {
        return new MemberDetails(userId, password);

    }

    public Member(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
    }

    public Member withEncodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
        regNo = passwordEncoder.encode(regNo);
        return this;
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


    public void updateTax(UpdateTaxRequestDto updateTaxRequestDto) {
        this.tax = updateTaxRequestDto.toValue();
    }

    public String getRefund() {
        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(tax != null ? tax.getRefund() : null);
    }
}

