package com.hongdroid.hellospring.service;

import com.hongdroid.hellospring.domain.Member;
import com.hongdroid.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 하나의 테스트들이 끝날때마다 마무리 시점에 처리해주고 싶은 것들이 있을 때 활용하는 어노테이션
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // TIP. 테스트 코드는 과감하게 한글로 적어도 상관없다 (영어권 사람들과 일하지 않는 이상 한글로 쓰는 경우도 많다. 직관적이기 때문에)
        // given (상황이 주어짐)
        Member member = new Member();
        member.setName("hello");
        // when (실행 했을 때)
        Long saveId = memberService.join(member);

        // then (결과가 이렇게 나와야 함..!) -> 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
//        }

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOnd() {
    }
}