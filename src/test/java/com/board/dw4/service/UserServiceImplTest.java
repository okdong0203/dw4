package com.board.dw4.service;

import com.board.dw4.dao.UserDao;
import com.board.dw4.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // 테스트 하기위한 애노테이션
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserServiceImplTest {
    @Autowired
    UserDao userDao;
    @Autowired
    UserService service;

    @Test
    public void register() {
        userDao.deleteAll();

        UserDto userDto = new UserDto("asdf", "1234", "dong", "aaa@aaa.com", "951010");

        int rowCnt = service.insert(userDto);
        assertTrue(rowCnt == 1);
    }

    @Test
    public void getIdCheck() {
        userDao.deleteAll();


        UserDto userDto = new UserDto("asdf", "1234", "dong", "aaa@aaa.com", "951010");
        service.insert(userDto);

        String id = "asdf";
        System.out.println("id = " + id);
        System.out.println("userDto.getId() = " + userDto.getId());
//        assertTrue(!id.equals(service.getIdCheck(userDto)));
    }

    @Test
    public void getLogin() {
        userDao.deleteAll();
        String id = "asdf";
        String pwd = "1234";

        UserDto userDto = new UserDto("asdf","1234","dong","aaa@aaa.com", "951010");
        service.insert(userDto);

        UserDto dto = service.login(id);
        System.out.println("userDto = " + userDto);
        System.out.println("dto = " + dto);
        assertTrue(dto!=null && dto.getPwd().equals(pwd));
    }

    @Test
    public void getIdFind() {
        userDao.deleteAll();

        UserDto userDto = new UserDto("asdf","1234","dong","aaa@aaa.com", "951010");
        service.insert(userDto);
        userDto.setName("dong");
        userDto.setEmail("aaa@aaa.com");

        String id = service.idFind(userDto);
        System.out.println("id = " + id);
        assertTrue(id!=userDto.getId());
    }

    @Test
    public void getPwdFind() {
        userDao.deleteAll();

        UserDto userDto = new UserDto("asdf","1234","dong","aaa@aaa.com", "951010");
        service.insert(userDto);
        userDto.setId("asdf");
        userDto.setEmail("aaa@aaa.com");

        String pwd = service.pwdFind(userDto);
        System.out.println("pwd = " + pwd);
        assertTrue(pwd.equals(userDto.getPwd()));
    }
}