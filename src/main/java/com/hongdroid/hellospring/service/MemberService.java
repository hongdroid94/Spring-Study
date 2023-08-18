package com.hongdroid.hellospring.service;

import com.hongdroid.hellospring.domain.Member;
import com.hongdroid.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

/**
 * 회원 서비스
 * 서비스 클래스에서는 비즈니스 처리를 하는 부분이 많기에 네이밍 자체도 비즈니스 용어들을 섞으면 좋다.
 */
public class MemberService { // TIPS. test case code를 작성하고 싶을 때 클래스 명에 커서 두고 ctrl + shift + t 를 누르면 초기 테스트코드 템플릿을 생성해준다
    private final MemberRepository memberRepository;

    // 외부에서 생성된 인스턴스를 주입받는 이런 형태를 의존성 주입 -> DI (Dependency Injection) 이라고 함.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        // TIP. cmd + option + v 하면 리턴형 앞에 자동으로 붙일 수 있음..ㅋ

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
