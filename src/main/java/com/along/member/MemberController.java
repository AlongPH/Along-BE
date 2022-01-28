package com.along.member;

import com.along.error.ErrorCode;
import com.along.error.ErrorResult;
import com.along.vo.MemberVO;
import com.along.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
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
        log.info("joiner : {}",joiner);
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


}
