package com.szs.unit.domain;

import com.szs.domain.member.entity.Member;
import com.szs.exception.InvalidInformationException;
import com.szs.exception.RequiredInformationBlankException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("[단위테스트] Member")
public class MemberTest {


    @Test
    @DisplayName("userId 값이 Blank일 경우")
    void given_BlankUserId_when_CreateValue_then_ThrowException() {
        String userId = " ";
        String password = "password";
        String name = "동탁";
        String regNo = "303030-303030";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Member.of(userId, password, name, regNo);
        });
    }

    @Test
    @DisplayName("passworkd 값이 Blank일 경우")
    void given_BlankPassword_when_CreateValue_then_ThrowException() {
        String userId = "dh34";
        String password = " ";
        String name = "동탁";
        String regNo = "303030-303030";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Member.of(userId, password, name, regNo);
        });
    }

    @Test
    @DisplayName("name 값이 Blank일 경우")
    void given_BlankName_when_CreateValue_then_ThrowException() {
        String userId = "dh34";
         String password = "123456";
         String name = " ";
         String regNo = "303030-303030";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Member.of(userId, password, name, regNo);
        });
    }

    @Test
    @DisplayName("regNo 값이 Blank일 경우")
    void given_BlankRegNo_when_CreateValue_then_ThrowException() {
        String userId = "dh34";
         String password = "123456";
         String name = "동탁";
         String regNo = " ";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Member.of(userId, password, name, regNo);
        });
    }

    @Test
    @DisplayName("주어진 회원정보가 아닐 경우")
    void given_InvalidInformation_when_CreateValue_then_ThrowException() {
        String userId = "id";
        String password = "password";
        String name = "jake";
        String regNo = "1010-1010";

        Assertions.assertThrows(InvalidInformationException.class, () -> {
            Member.of(userId, password, name, regNo);
        });
    }

}
