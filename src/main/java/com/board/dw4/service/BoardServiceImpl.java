package com.board.dw4.service;

import com.board.dw4.dao.BoardDao;
import com.board.dw4.domain.BoardDto;
import com.board.dw4.domain.PageHandler;
import com.board.dw4.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;

//    개수 확인
    @Override
    public int getCount(){
        return boardDao.count();
    }

//    게시글 삭제
    @Override
    public int remove(Integer bno, String writer){
        return boardDao.delete(bno, writer);
    }

//    게시글 등록
    @Override
    public int write(BoardDto dto){
        return boardDao.insert(dto);
    }

//    전체 게시글 조회
    @Override
    public List<BoardDto> selectAll(){
        return boardDao.selectAll();
    }

//    게시글 내용 조회
    @Override
    public BoardDto select(Integer bno){
        return boardDao.select(bno);
    }

//    페이징
    @Override
    public List<BoardDto> getPage(Map map){
        return boardDao.page(map);
    }

//    게시글 수정
    @Override
    public int modify(BoardDto dto){
        return boardDao.update(dto);
    }

//    조회수 증가
    @Override
    public BoardDto viewCnt(Integer bno){
        BoardDto dto = boardDao.select(bno);
        boardDao.viewCnt(bno);
        return dto;
    }

    // 검색 게시글 조회
    @Override
    public List<BoardDto> getSearchPage(SearchCondition sc){
        return boardDao.searchPage(sc);
    }

    // 검색게시글 개수
    @Override
    public int getSearchCount(SearchCondition sc){
        return boardDao.searchCount(sc);
    }

}