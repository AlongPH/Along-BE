package com.along.jwt;

import com.along.vo.MemberVO;

import java.util.Map;

public interface JwtService {
    String createToken(MemberVO memberVO);
    boolean verifyToken(String token);
    Map<String,Object> getInfo(String token);
}
