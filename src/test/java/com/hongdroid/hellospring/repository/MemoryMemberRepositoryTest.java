package com.hongdroid.hellospring.repository;

import com.hongdroid.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// *주의사항 !! - 테스트 어노테이션이 붙은 각 메서드들은 클래스로 한번에 실행하게 될 시 순서보장을 할 수 없으며, 순서를 강제로 부여하여 의존적이게 메서드를 구현해서도 안된다.
public class MemoryMemberRepositoryTest {
    // TDD (테스트 주도 개발) - 테스트를 먼저 만들고 레포지토리 같은 구현체를 이후에 만드는 경우를 TDD (테스트 주도 개발) 이라고 부른다
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 하나의 테스트들이 끝날때마다 마무리 시점에 처리해주고 싶은 것들이 있을 때 활용하는 어노테이션
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
