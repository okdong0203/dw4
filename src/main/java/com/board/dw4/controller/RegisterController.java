package com.board.dw4.controller;

import com.board.dw4.domain.UserDto;
import com.board.dw4.service.UserService;
import com.board.dw4.validation.UserValidator;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService service;

    // 회원가입 화면
    @GetMapping("/add")
    public String registerAdd(){
        return "registerForm";
    }

    //회원가입
    @PostMapping("/add")
    public String registerSave(UserDto userDto, BindingResult result, Model m, RedirectAttributes rattr){

        UserValidator validator = new UserValidator();
        validator.validate(userDto, result);


        System.out.println("result = " + result);
        try {
            if(result.hasErrors()){
                return "registerForm";
            }else{
                service.insert(userDto);
                rattr.addFlashAttribute("msg", "ADD_OK");
               return  "redirect:/";
            }
        } catch (DuplicateKeyException e) {
            m.addAttribute("msg", "ADD_ERR");
            return "registerForm";
        }
    }

//     아이디 중복확인
    @PostMapping("/idcheck")
    @ResponseBody
    public String idCheck(@RequestBody String id) {
        String check;
        int count = service.idCheck(id);
        if(count == 0){
            check = "T"; // 사용가능한 아이디
        }else check = "F"; // 중복된 아이디
        return check;
    }

    // 아이디 찾기 뷰
    @GetMapping("/idfind")
    public String idFindView(Model m){
        m.addAttribute("mode", "idFind");
        return "find";
    }

    // 아이디 찾기
    @PostMapping("/idfind")
    public String idFind(UserDto userDto, Model m){
        m.addAttribute("mode", "idFind");

        String id = service.idFind(userDto);

            if(id==null || id.equals("")){
                m.addAttribute("check", "no");
            }else {
                m.addAttribute("check", "yes");
                m.addAttribute("id", id);
            } 
        return "findResult";
    }

    // 아이디찾기 예외처리
    @ExceptionHandler(MyBatisSystemException.class)
    public String myBatisSystemException(Model m){
        m.addAttribute("FIND_ERR", "FIND_ERR");
        return "findResult";
    }

    // 비밀번호 찾기 뷰
    @GetMapping("/pwdfind")
    public String pwdFindView(){
        return "find";
    }

    // 비밀번호 찾기
    @PostMapping("/pwdfind")
    public String pwdFind(UserDto userDto, Model m){

        String pwd = service.pwdFind(userDto);

            if(pwd==null || pwd.equals("")){
                m.addAttribute("check", "null");
            }else{
                m.addAttribute("check", "nnull");
                m.addAttribute("pwd", pwd);
            }
        return "findResult";
    }

    // 비밀번호 변경
    @GetMapping("modify")
    public String updateView(HttpSession session, HttpServletRequest request, Model m){
        if(session.getAttribute("id") == null) return "redirect:/login/login?toURL="+request.getRequestURL();
        m.addAttribute("mode", "mod");
        return "remo";
    }
    // 비밀번호 변경
    @PostMapping("/modify")
    public String update(UserDto userDto, RedirectAttributes rattr){

        int rowCnt = 0;
        rowCnt = service.modify(userDto);

        if(rowCnt == 1){
            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/";
        }
        return "remo";
    }

    @GetMapping("/remove")
    public String deleteView(HttpSession session, HttpServletRequest request){
        if(session.getAttribute("id") == null) return "redirect:/login/login?toURL="+request.getRequestURL();
        return "remo";
    }

    // 회원 삭제
    @PostMapping("/remove")
    public String delete(UserDto userDto, Model m, RedirectAttributes rattr, HttpSession session){
        int rowCnt = service.remove(userDto);

        if(rowCnt == 1){
            rattr.addFlashAttribute("msg", "DEL_OK");
            session.invalidate();
            return "redirect:/";
        }else{
            m.addAttribute("msg", "DEL_ERR");
            return "remo";
        }
    }
}
