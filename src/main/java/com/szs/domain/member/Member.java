package com.szs.domain.member;

import com.szs.domain.authentication.dto.MemberDetails;
import com.szs.domain.member.dto.response.UpdateTaxRequestDto;
import com.szs.exception.InvalidInformationException;
import com.szs.exception.RequiredInformationBlankException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.math.RoundingMode;


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

    @Comment(value = "과세표준")
    private BigDecimal taxBase;

    @Comment(value = "세액공제")
    private BigDecimal taxCredit;


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

    private BigDecimal tax(Double min, Double percent, Double additional) {
        return taxBase
                .subtract(BigDecimal.valueOf(min))
                .multiply(BigDecimal.valueOf(percent))
                .add(BigDecimal.valueOf(additional))
                .subtract(taxCredit)
                .setScale(0, RoundingMode.HALF_UP)
                ;

    }

    public BigDecimal getRefund() {
        if (taxBase.compareTo(BigDecimal.valueOf(140000)) <= 0) {
            return taxBase.multiply(BigDecimal.valueOf(0.06)).subtract(taxCredit);
        } else if (taxBase.compareTo(BigDecimal.valueOf(14000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(50000000)) <= 0) {
            return tax(14000000.0, 0.15, 840000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(50000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(88000000)) <= 0) {
            return tax(50000000.0, 0.24, 6240000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(88000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(150000000)) <= 0) {
            return tax(88000000.0, 0.35, 15360000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(150000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(300000000)) <= 0) {
            return tax(150000000.0, 0.38, 37060000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(300000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(500000000)) <= 0) {
            return tax(300000000.0, 0.4, 94060000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(500000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(1000000000)) <= 0) {
            return tax(500000000.0, 0.42, 174060000.0);

        } else {
            return tax(1000000000.0, 0.45, 384060000.0);
        }
    }


    public Member(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
    }

    public Member withEncodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
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
        this.taxBase = updateTaxRequestDto.taxBase();
        this.taxCredit = updateTaxRequestDto.taxCredit();
    }
}

