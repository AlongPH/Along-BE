package com.along.member;

import com.along.error.ErrorCode;
import com.along.error.ErrorResult;
import com.along.vo.MemberVO;
import com.along.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/start")
    public String start(){
        return "Hello Along";
    }

    @PostMapping("/member")
    public ResultVO createMember(@Valid @RequestBody MemberVO memberVO){
        ResultVO resultVO=new ResultVO();
        MemberVO joiner=memberService.createMember(memberVO);
        log.info("join member : {} {} {} {} {}",joiner.getUserId(),joiner.getUserPw(),joiner.getUserNm(),joiner.getPhone(),joiner.getEmail());
        if(joiner!=null){
            resultVO.setStatus(HttpStatus.OK);
            resultVO.setSuccess("회원가입에 성공했습니다.");
        }else{
            resultVO.setStatus(HttpStatus.BAD_REQUEST);
            resultVO.setError(new ErrorResult(ErrorCode.REQUEST_ERR));
        }

        log.info("USERID : {} JOIN SUCCESS",memberVO.getUserId());
        return resultVO;
    }

    @PostMapping("/member/login")
    public ResultVO loginMember(@RequestBody MemberVO memberVO){
        ResultVO resultVO=new ResultVO();
        MemberVO member=memberService.loginMember(memberVO);
        log.info("login member: {}",memberVO.getUserId());
        if(member!=null){
            resultVO.setStatus(HttpStatus.OK);
            resultVO.setSuccess("로그인 성공했습니다.");
        }else{
            resultVO.setStatus(HttpStatus.BAD_REQUEST);
            resultVO.setError(new ErrorResult(ErrorCode.REQUEST_ERR));
        }

        log.info("USERID : {} LOGIN SUCCESS",memberVO.getUserId());
        return resultVO;
    }

}
