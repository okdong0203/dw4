package com.board.dw4.dao;

import com.board.dw4.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSession session;

    private String namespace="com.board.dw4.dao.UserMapper.";

    // 회원가입
    @Override
    public int insert(UserDto dto){return session.insert(namespace+"insert", dto);}

    // id중복체크
    @Override
    public int idCheck(String id){
        return session.selectOne(namespace+"idCheck", id);
    }

    // 로그인
    @Override
    public UserDto select(String id){
        return session.selectOne(namespace+"login", id);
    }

    // 아이디 찾기
    @Override
    public String idFind(UserDto dto){
        return session.selectOne(namespace+"idFind", dto);
    }

    // 비밀번호 찾기
    @Override
    public String pwdFind(UserDto dto){
        return session.selectOne(namespace+"pwdFind", dto);
    }

    // 회원정보 수정
    @Override
    public int update(UserDto dto){return session.update(namespace+"update", dto);}

    // 회원탈퇴
    @Override
    public int delete(UserDto dto) {return session.delete(namespace+"delete", dto);}

    @Override
    public int deleteAll() {return session.delete(namespace+"deleteAll");}

}
