package com.board.dw4.dao;

import com.board.dw4.domain.BoardDto;
import com.board.dw4.domain.PageHandler;
import com.board.dw4.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    //    개수확인
    int count();

    //    전체삭제
    int deleteAll();

    //    해당게시글 삭제
    int delete(Integer bno, String writer);

    //    게시글 등록
    int insert(BoardDto dto);

    //    전체 게시글 조회
    List<BoardDto> selectAll();

    //    게시글 내용 조회
    BoardDto select(Integer bno);

    // 페이징처리
    public List<BoardDto> page(Map map);

    //    게시글 수정
    int update(BoardDto dto);

    //    조회수 증가
    int viewCnt(Integer bno);

    // 검색 게시글 조회
    List<BoardDto> searchPage(SearchCondition sc);

    // 검색게시글 개수
    int searchCount(SearchCondition sc);

    // 댓글 개수
    public int commentCnt(Integer bno, Integer cnt);
}
