package com.board.dw4.service;

import com.board.dw4.dao.UserDao;
import com.board.dw4.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    // 회원가입
    @Override
    public int insert(UserDto dto){
        return userDao.insert(dto);
    }

    // 아이디 중복확인
    @Override
    public int idCheck(String id){
        return userDao.idCheck(id);
    }

    // 로그인
    @Override
    public UserDto login(String id){
        return userDao.select(id);
    }

    // 아이디 찾기
    @Override
    public String idFind(UserDto dto){
        return userDao.idFind(dto);
    }

    // 비밀번호 찾기
    @Override
    public String pwdFind(UserDto dto){
        return userDao.pwdFind(dto);
    }

    // 회원정보 수정
    @Override
    public int modify(UserDto dto){
        return userDao.update(dto);
    }

    // 회원 탈퇴
    @Override
    public int remove(UserDto dto){
        return userDao.delete(dto);
    }

}
