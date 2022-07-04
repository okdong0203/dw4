package com.board.dw4.controller;

import com.board.dw4.dao.BoardDao;
import com.board.dw4.domain.BoardDto;
import com.board.dw4.domain.PageHandler;
import com.board.dw4.domain.SearchCondition;
import com.board.dw4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardDao boardDao;
    @Autowired
    BoardService service;

    @PostMapping("/modify")
    public String modify(Integer page, Integer pageSize, BoardDto boardDto, Model m, RedirectAttributes rattr, HttpSession session){
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = service.modify(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            if(rowCnt != 1) throw new RuntimeException("failed");
            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/board/list";
        } catch (RuntimeException e) {
            e.printStackTrace();
            m.addAttribute("boardDto", boardDto);
            rattr.addFlashAttribute("msg", "MOD_ERR");
            return "board";
        }

    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, RedirectAttributes rattr, HttpSession session){
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = service.write(boardDto);

            if(rowCnt != 1)
                throw new RuntimeException("failed");
            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";
        }catch (RuntimeException e){
            e.printStackTrace();
            m.addAttribute("boardDto", boardDto);
            rattr.addFlashAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @GetMapping("/write")
    public String write(Integer page, Integer pageSize, Model m){
        m.addAttribute("page", page);
        m.addAttribute("pageSize", pageSize);
        m.addAttribute("mode", "write");
        return "board";
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr){

        String writer = (String)session.getAttribute("id");

        m.addAttribute("page", page);
        m.addAttribute("pageSize", pageSize);

        try {
            int rowCnt = service.remove(bno, writer);

            if(rowCnt != 1) throw new RuntimeException("failed");

            rattr.addFlashAttribute("msg", "DEL_OK");
            return "redirect:/board/list";
        } catch (RuntimeException e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
            return "board";
        }
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m){

        BoardDto boardDto = service.select(bno);
        service.viewCnt(bno);


        m.addAttribute("boardDto", boardDto);
        m.addAttribute("page", page);
        m.addAttribute("pageSize", pageSize);
        return "board";
    }
    // 게시글 목록
    @GetMapping("/list")
    public String board(SearchCondition sc, Model m, HttpSession session, HttpServletRequest request){
        if(session.getAttribute("id") == null) return "redirect:/login/login?toURL="+request.getRequestURL();


        int totalCnt = service.getSearchCount(sc);
        m.addAttribute("totalCnt", totalCnt);

        PageHandler ph = new PageHandler(totalCnt, sc);

        Instant today = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        m.addAttribute("today", today.toEpochMilli());

        List<BoardDto> list = service.getSearchPage(sc);
        m.addAttribute("list", list);
        m.addAttribute("ph", ph);
        return "boardList";
    }


}
