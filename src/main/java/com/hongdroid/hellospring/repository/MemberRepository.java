package com.hongdroid.hellospring.repository;

import com.hongdroid.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // optional은 java 8에서 지원되는 기능인데 값이 없을 때 null 반환 말고 한번 감싸서 반환할때 활용하기 좋음
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
