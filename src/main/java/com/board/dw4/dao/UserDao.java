package com.board.dw4.dao;

import com.board.dw4.domain.UserDto;

public interface UserDao {
    // 회원가입
    int insert(UserDto dto);
    // 아이디 중복확인
    int idCheck(String id);
    // 로그인
    UserDto select(String id);
    // 아이디 찾기
    String idFind(UserDto dto);
    // 비밀번호 찾기
    String pwdFind(UserDto dto);
    // 회원정보 수정
    int update(UserDto dto);
    // 회원탈퇴
    int delete(UserDto dto);

    int deleteAll();

}
