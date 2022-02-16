package com.along.vo;

import com.along.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Setter
@Getter
public class MemberVO {
    @NotEmpty @Size(min=1,max=20)
    private String userId;
    //@NotEmpty @Size(min=1,max=20)
    private String userPw;
    //@NotEmpty @Size(min=1,max=20)
    private String userNm;
    //@NotNull @Pattern(regexp="^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phone;
    //@NotNull @Email
    private String email;
    private String regDate;
    private String accessToken;

    public MemberVO(String userId, String userPw, String userNm, String phone, String email) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.phone = phone;
        this.email = email;
        this.regDate = LocalDateTime.now().toString();
    }

    public MemberVO(Member member){
        this.userId = member.getUserId();
        this.userPw = member.getUserPw();
        this.userNm = member.getUserNm();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.regDate = member.getRegDate();
    }
}
