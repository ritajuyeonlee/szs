package com.szs.unit.domain;

import com.szs.domain.Member;
import com.szs.exception.InvalidInformationException;
import com.szs.exception.RequiredInformationBlankException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("[단위테스트] member")
public class MemberTest {


    @Test
    @DisplayName("필수 값이 비었을 경우")
    void RequiredInformationBlankTest() {
        String userId = " ";
        String password = " ";
        String name = " ";
        String regNo = " ";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Member.of(userId, password, name, regNo);
        });
    }

    @Test
    @DisplayName("주어진 회원정보가 아닐 경우")
    void InvalidInformationTest() {
        String userId = "id";
        String password = "password";
        String name = "jake";
        String regNo = "1010-1010";

        Assertions.assertThrows(InvalidInformationException.class, () -> {
            Member.of(userId, password, name, regNo);
        });
    }
}
