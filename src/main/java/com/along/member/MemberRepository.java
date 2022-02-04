package com.along.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
    boolean existsByUserId(String userId);
    Member findByUserIdAndUserPw(String userId,String userPw);
}
