package com.board.dw4.service;

import com.board.dw4.domain.BoardDto;
import com.board.dw4.domain.PageHandler;
import com.board.dw4.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardService {
    //    개수 확인
    int getCount();

    //    게시글 삭제
    int remove(Integer bno, String writer);

    //    게시글 등록
    int write(BoardDto dto);

    //    전체 게시글 조회
    List<BoardDto> selectAll();

    //    게시글 내용 조회
    BoardDto select(Integer bno);

    //    페이징
    List<BoardDto> getPage(Map map);

    //    게시글 수정
    int modify(BoardDto dto);

    //    조회수 증가
    BoardDto viewCnt(Integer bno);

    // 검색 게시글 조회
    List<BoardDto> getSearchPage(SearchCondition sc);

    // 검색게시글 개수
    int getSearchCount(SearchCondition sc);
}
