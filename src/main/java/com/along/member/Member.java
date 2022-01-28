package com.along.member;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
public class Member {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_PW")
    private String userPw;

    @Column(name = "USER_NM")
    private String userNm;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "REG_DATE")
    private String regDate;

    public Member() { }

    public Member(String userId, String userPw, String userNm, String phone, String email, String regDate) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.phone = phone;
        this.email = email;
        this.regDate = regDate;
    }

}
