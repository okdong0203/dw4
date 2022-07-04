package com.board.dw4.validation;

import com.board.dw4.domain.UserDto;
import com.board.dw4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    static Pattern pattern = null;
    static Matcher matcher = null;
    static final String idReg = "^[0-9a-zA-Z]{4,12}$";
    static final String pwdReg = "^[0-9a-zA-Z]{4,12}$";
    static final String nameReg = "^[가-힣]{2,4}$";
    static final String emailReg = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
    static final String birthReg = "^(19\\d{2}|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";

    @Autowired
    UserService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    public static boolean idCheck(String id){
        pattern = Pattern.compile(idReg);
        matcher = pattern.matcher(id);
        return matcher.find();
    }
    public static boolean pwd(String pwd){
        pattern = Pattern.compile(pwdReg);
        matcher = pattern.matcher(pwd);
        return matcher.find();
    }
    public static boolean name(String name){
        pattern = Pattern.compile(nameReg);
        matcher = pattern.matcher(name);
        return matcher.find();
    }
    public static boolean email(String email){
        pattern = Pattern.compile(emailReg);
        matcher = pattern.matcher(email);
        return matcher.find();
    }
    public static boolean birth(String birth){
        pattern = Pattern.compile(birthReg);
        matcher = pattern.matcher(birth);
        return matcher.find();
    }
    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto)target;

        String id = userDto.getId();
        String pwd = userDto.getPwd();
        String checkPwd = userDto.getCheckPwd();
        String name = userDto.getName();
        String email = userDto.getEmail();
        String birth = userDto.getBirth();

        // 아이디
        if(id == null || id.trim().isEmpty()){
            errors.rejectValue("id", "required.userDto.id");
        }
        else if (!idCheck(id)) {
            errors.rejectValue("id", "misMatch.id");
        }

        // 비밀번호
        if(pwd == null || pwd.trim().isEmpty()){
            errors.rejectValue("pwd", "required.userDto.pwd");
        }else if (!pwd(pwd)){
            errors.rejectValue("pwd", "misMatch.pwd");
        }

        // 비밀번호 확인
        if(!checkPwd.equals(pwd)){
            errors.rejectValue("checkPwd", "misMatch.checkPwd");
        }

        // 이름
        if (name == null || name.trim().isEmpty()) {
            errors.rejectValue("name", "required.userDto.name");
        }else if (!name(name)){
            errors.rejectValue("name", "misMatch.name");
        }

        // 이메일
        if (email == null || email.trim().isEmpty()) {
            errors.rejectValue("email", "required.userDto.email");
        }else if (!email(email)){
            errors.rejectValue("email", "misMatch.email");
        }

        // 생년월일
        if (birth == null || birth.trim().isEmpty()) {
            errors.rejectValue("birth", "required.userDto.birth");
        }else if (!birth(birth)){
            errors.rejectValue("birth", "misMatch.birth");
        }
    }
}
