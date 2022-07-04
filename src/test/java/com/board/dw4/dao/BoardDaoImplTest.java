package com.board.dw4.dao;

import com.board.dw4.domain.BoardDto;
import com.board.dw4.domain.PageHandler;
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
public class BoardDaoImplTest {
    @Autowired
    private BoardDao boardDao;

    @Test
    public void count() {
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");

        int rowCnt = boardDao.insert(boardDto);
        assertTrue(rowCnt == 1);
    }

    @Test
    public void delete() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        int rowCnt = boardDao.insert(boardDto);
        assertTrue(rowCnt == 1);

        int bno = boardDao.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);
        int rowCnt2 = boardDao.delete(bno, boardDto.getWriter());
        assertTrue(rowCnt2 == 1);
    }

    @Test
    public void insert() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto) == 1);
    }

    @Test
    public void selectAll() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto) == 1);

        List<BoardDto> list = boardDao.selectAll();
        assertTrue(list.size()==1);
        System.out.println("list = " + list);
    }

    @Test
    public void select() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto) == 1);

        int bno = boardDao.selectAll().get(0).getBno();
        BoardDto dto = boardDao.select(bno);
        System.out.println("dto = " + dto);
    }

    @Test
    public void page() {
        boardDao.deleteAll();

        for(int i=1; i<=5; i++){
            BoardDto boardDto = new BoardDto(""+i, "content"+i, "asdf");
            boardDao.insert(boardDto);
        }
        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);


        List<BoardDto> list = boardDao.page(map);
        assertTrue(list.get(0).getTitle().equals("5"));
        assertTrue(list.get(1).getTitle().equals("4"));
        assertTrue(list.get(2).getTitle().equals("3"));
    }

    @Test
    public void update() {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto) == 1);
        System.out.println("boardDto = " + boardDto);

        int bno = boardDao.selectAll().get(0).getBno();
        boardDto.setBno(bno);
        System.out.println("bno = " + bno);
        boardDto.setTitle("update title");
        assertTrue(boardDao.update(boardDto) == 1);
        System.out.println("boardDto = " + boardDto);
    }

    @Test
    public void searchPage() {
        boardDao.deleteAll();

        for (int i = 1; i <= 20; i++) {
            BoardDto boardDto = new BoardDto("title" + i, "content", "asdf" + i);
            boardDao.insert(boardDto);
        }
        SearchCondition sc = new SearchCondition(1, 10, "title2", "T");
        List<BoardDto> list = boardDao.searchPage(sc);
        System.out.println("list = " + list);
        assertTrue(list.size() == 2);

        sc = new SearchCondition(1,10,"asdf2", "W");
        list = boardDao.searchPage(sc);
        System.out.println("list = " + list);
        assertTrue(list.size() == 2);
    }

    @Test
    public void searchCount(){
        boardDao.deleteAll();

        for (int i = 1; i <= 20; i++) {
            BoardDto boardDto = new BoardDto("title" + i, "content", "asdf" + i);
            boardDao.insert(boardDto);
        }
        SearchCondition sc = new SearchCondition(1, 10, "title2", "T");
        int rowCnt = boardDao.searchCount(sc);
        System.out.println("rowCnt = " + rowCnt);
        assertTrue(rowCnt == 2);

        sc = new SearchCondition(1, 10, "asdf2", "W");
        rowCnt = boardDao.searchCount(sc);
        System.out.println("rowCnt = " + rowCnt);
        assertTrue(rowCnt == 2);
    }
}