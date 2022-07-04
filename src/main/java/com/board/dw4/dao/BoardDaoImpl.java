package com.board.dw4.dao;

import com.board.dw4.domain.BoardDto;
import com.board.dw4.domain.PageHandler;
import com.board.dw4.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    SqlSession session;

    private String namespace = "com.board.dw4.dao.BoardMapper.";

//    개수확인
    @Override
    public int count(){
        return session.selectOne(namespace + "count");
    }

//    전체삭제
    @Override
    public int deleteAll(){
        return session.delete(namespace + "deleteAll");
    }

//    해당게시글 삭제
    @Override
    public int delete(Integer bno, String writer){
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(namespace + "delete", map);
    }

//    게시글 등록
    @Override
    public int insert(BoardDto dto){
        return session.insert(namespace + "insert", dto);
    }

//    전체 게시글 조회
    @Override
    public List<BoardDto> selectAll(){
        return session.selectList(namespace + "selectAll");
    }

//    게시글 내용 조회
    @Override
    public BoardDto select(Integer bno){
        return session.selectOne(namespace + "select", bno);
    }

//    페이징
    @Override
    public List<BoardDto> page(Map map){
        return session.selectList(namespace + "selectPage", map);
    }

//    게시글 수정
    @Override
    public int update(BoardDto dto){
        return session.update(namespace + "update", dto);
    }

//    조회수 증가
    @Override
    public int viewCnt(Integer bno){
        return session.update(namespace + "viewCnt", bno);
    }

//    검색 게시글 조회
    @Override
    public List<BoardDto> searchPage(SearchCondition sc){
        return session.selectList(namespace + "searchPage", sc);
    }

//    검색게시글 개수
    @Override
    public int searchCount(SearchCondition sc){
        return session.selectOne(namespace + "searchCount", sc);
    }

    @Override
    public int commentCnt(Integer bno, Integer cnt){
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("cnt", cnt);
        return session.update(namespace + "commentCnt", map);
    }
}
