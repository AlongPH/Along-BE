package com.along.jwt;

import com.along.vo.MemberVO;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService{
    private String secretKey="alongKey";//서명에 사용할 secretKey
    private long exp=1000L * 60 * 60; //토큰 사용가능 시간=1시간

    /**
     * Member 정보를 담은 JWT 토큰 생성
     * @param memberVO
     * @return String JWT
     */
    @Override
    public String createToken(MemberVO memberVO) {
        Map<String,Object> header=new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");

        Map<String,Object> claim=new HashMap<>();
        claim.put("member",memberVO.getUserId());

        return Jwts.builder()
                .setHeader(header) //header 설정
                .setSubject("memberToken") //토큰 제목
                .setExpiration(new Date(System.currentTimeMillis()+exp)) //토큰 만료 시간 설정
                .setClaims(claim) //claim 설정
                .signWith(SignatureAlgorithm.HS256,secretKey.getBytes())
                .compact(); //직렬화, 문자열로 변경

    }

    /**
     * Token 검증
     * @param token
     * @return boolean 토큰 유효 여부
     */
    @Override
    public boolean verifyToken(String token){
        boolean verify=true;
        try{
            Jws<Claims> claims=Jwts.parser()
                                .setSigningKey(secretKey.getBytes())
                                .parseClaimsJws(token); //파싱 및 검증, 실패 시 에러
        }catch(ExpiredJwtException e){ //토큰이 만료되었을 경우
            verify=false;
            log.error(e.getMessage());
        }catch(Exception e){
            verify=false;
            log.error(e.getMessage());
        }
        return verify;
    }

    /**
     * Token에서 Claim 정보 가져오는 함수
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getInfo(String token) {
        Claims claims=null;
        try{
            claims=Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return claims;
    }


}
