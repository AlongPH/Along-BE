package com.along.member;

import com.along.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public MemberVO createMember(MemberVO memberVO){
        boolean isExist=memberRepository.existsByUserId(memberVO.getUserId());
        if(!isExist){ //새로운 회원
            Member joiner=new Member(memberVO.getUserId(),
                    memberVO.getUserPw(),
                    memberVO.getUserNm(),
                    memberVO.getPhone(),
                    memberVO.getEmail(),
                    memberVO.getRegDate());
            memberRepository.save(joiner);
            return memberVO;
        }

        return null;
    }
}
