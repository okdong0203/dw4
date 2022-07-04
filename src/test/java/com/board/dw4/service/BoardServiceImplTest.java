package com.board.dw4.service;

import com.board.dw4.dao.BoardDao;
import com.board.dw4.domain.BoardDto;
import com.board.dw4.domain.SearchCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // 테스트 하기위한 애노테이션
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardServiceImplTest {
    @Autowired
    BoardService service;
    @Autowired
    BoardDao boardDao;

    @Test
    public void insertTest(){
        boardDao.deleteAll();

        for(int i=1; i<=150; i++){
            BoardDto boardDto = new BoardDto("title"+i, "content"+i, "asdf");
            service.write(boardDto);
        }
    }
    @Test
    public void getCount() {
        boardDao.deleteAll();
        assertTrue(service.getCount() == 0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");

        assertTrue(service.write(boardDto) == 1);
        assertTrue(service.getCount() == 1);
    }

    @Test
    public void remove() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(service.write(boardDto) == 1);

        int bno = service.selectAll().get(0).getBno();
        assertTrue(service.remove(bno, boardDto.getWriter()) == 1);
    }

    @Test
    public void insert() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(service.write(boardDto) == 1);
    }

    @Test
    public void selectAll() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(service.write(boardDto) == 1);

        List<BoardDto> list = service.selectAll();
        assertTrue(list.size() == 1);
        System.out.println("list = " + list);
    }

    @Test
    public void select() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(service.write(boardDto) == 1);

        int bno = service.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);
        BoardDto dto = service.select(bno);
        System.out.println("dto = " + dto);
    }

    @Test
    public void getPage() {
        boardDao.deleteAll();

        for(int i=1; i<=5; i++){
            BoardDto boardDto = new BoardDto(""+i, "content"+i, "asdf");
            service.write(boardDto);
        }
        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<BoardDto> list = service.getPage(map);
        assertTrue(list.get(0).getTitle().equals("5"));
        assertTrue(list.get(1).getTitle().equals("4"));
        assertTrue(list.get(2).getTitle().equals("3"));
    }

    @Test
    public void modify() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(service.write(boardDto) == 1);

        int bno = service.selectAll().get(0).getBno();
        boardDto.setBno(bno);
        boardDto.setTitle("updateTitle");
        assertTrue(service.modify(boardDto) == 1);
        System.out.println("boardDto = " + boardDto);
    }

    @Test
    public void getSearchPage(){
        boardDao.deleteAll();

        for(int i=1; i<=20; i++){
            BoardDto boardDto = new BoardDto("title"+i, "content", "asdf"+i);
            service.write(boardDto);
        }
        SearchCondition sc = new SearchCondition(1, 10, "title2", "T");
        List<BoardDto> list = service.getSearchPage(sc);
        System.out.println("list = " + list);
        assertTrue(list.size()==2);

        sc = new SearchCondition(1,10,"asdf2","W");
        list = service.getSearchPage(sc);
        System.out.println("list = " + list);
        assertTrue(list.size() == 2);
    }

    @Test
    public void getSearchCount(){
        boardDao.deleteAll();

        for(int i=1; i<=20; i++){
            BoardDto boardDto = new BoardDto("title"+i, "content", "asdf"+i);
            service.write(boardDto);
        }
        SearchCondition sc = new SearchCondition(1,10,"title2","T");
        int rowCnt = service.getSearchCount(sc);
        System.out.println("rowCnt = " + rowCnt);
        assertTrue(rowCnt==2);

        sc = new SearchCondition(1,10,"asdf2","W");
        rowCnt = service.getSearchCount(sc);
        System.out.println("rowCnt = " + rowCnt);
        assertTrue(rowCnt==2);
    }
}