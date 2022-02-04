package com.along.member;

import com.along.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberVO createMember(MemberVO memberVO){
        boolean isExist=memberRepository.existsByUserId(memberVO.getUserId());
        if(!isExist){ //새로운 회원
            Member joiner=new Member(memberVO.getUserId(),
                    memberVO.getUserPw(),
                    memberVO.getUserNm(),
                    memberVO.getPhone(),
                    memberVO.getEmail(),
                    memberVO.getRegDate());
            log.info("save member {}",memberVO.getUserId());
            memberRepository.save(joiner);
            return memberVO;
        }

        return null;
    }

    @Override
    public MemberVO loginMember(MemberVO memberVO) {
        try{
            Member member=memberRepository.findByUserIdAndUserPw(memberVO.getUserId(),memberVO.getUserPw());
            return new MemberVO(member);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
