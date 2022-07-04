package com.board.dw4.dao;

import com.board.dw4.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // 테스트 하기위한 애노테이션
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;

    @Test
    public void insert() {
        userDao.deleteAll();
        UserDto dto = new UserDto();
        dto.setId("asdf");

        UserDto userDto = new UserDto("asdf", "1234", "dong", "aaa@aaa.com", "951010");

        assertTrue(userDao.insert(userDto)==1);
    }

    @Test
    public void idCheck() {
        userDao.deleteAll();

        UserDto userDto = new UserDto("asdf", "1234", "dong", "aaa@aaa.com", "951010");
        userDao.insert(userDto);
        String id = "asdf";
        System.out.println("id = " + id);
        System.out.println("userDto.getId() = " + userDto.getId());
//        assertTrue(!id.equals(userDao.idCheck(userDto)));
    }

    @Test
    public void login() {
        userDao.deleteAll();
        UserDto userDto = new UserDto("asdf", "1234", "dong", "aaa@aaa.com", "951010");
        userDao.insert(userDto);
        String id = "asdf";

        System.out.println("userDto = " + userDto);
        System.out.println("id = " + id);

    }

    @Test
    public void idFind() {
        UserDto dto = new UserDto();
        dto.setName("dong");
        dto.setEmail("aaa@aaa.com");
        String id = userDao.idFind(dto);

        System.out.println("id = " + id);
    }

    @Test
    public void pwdFind() {
        UserDto dto = new UserDto();
        dto.setId("asdf");
        dto.setEmail("aaa@aaa.com");
        String pwd = userDao.pwdFind(dto);

        System.out.println("pwd = " + pwd);
    }

    @Test
    public void update(){
        userDao.deleteAll();
        UserDto userDto = new UserDto("asdf", "1234", "dong", "aaa@aaa.com", "951010");
        assertTrue(userDao.insert(userDto) == 1);


        if(userDto.getId().equals("asdf")){
            userDto.setPwd("4321");
            userDto.setName("길동");
        }
        int rowCnt = userDao.update(userDto);

        assertTrue(rowCnt == 1);
        System.out.println("userDto = " + userDto);
    }

    @Test
    public void delete() {
        userDao.deleteAll();
        UserDto userDto = new UserDto("asdf", "1234", "dong", "aaa@aaa.com", "951010");
        assertTrue(userDao.insert(userDto) == 1);

        int rowCnt = 0;
        rowCnt = userDao.delete(userDto);

        assertTrue(rowCnt == 1);
        System.out.println("userDto = " + userDto);


        UserDto dto = userDao.select("asdf");
        System.out.println("dto = " + dto);
    }
}