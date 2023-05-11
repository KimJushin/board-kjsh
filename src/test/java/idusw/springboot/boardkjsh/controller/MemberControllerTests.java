package idusw.springboot.boardkjsh.controller;

import idusw.springboot.boardkjsh.domain.Member;
import idusw.springboot.boardkjsh.domain.PageRequestDTO;
import idusw.springboot.boardkjsh.domain.PageResultDTO;
import idusw.springboot.boardkjsh.entity.MemberEntity;
import idusw.springboot.boardkjsh.repository.MemberRepository;
import idusw.springboot.boardkjsh.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberControllerTests {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
        // could not initialize proxy - no Session : Lazy fetch 로 인한 오류
    void readMember() { // seq를 사용해야 함
        Member member = new Member();
        member.setSeq(51L);
        Member result = null;
        if((result = memberService.read(member)) != null )
            System.out.println("조회 성공! " + result.getEmail() + ":::" + result.getName());
        else
            System.out.println("조회 실패!");
    }

    @Test
    void readMemberList() {
        List<Member> resultList = null;
        if((resultList = memberService.readList()) != null) {
            for(Member m : resultList)
                System.out.format("%-10s | %-10s | %10s\n", m.getName(), m.getEmail(), m.getRegDate());
        }
        else
            System.out.println("목록 없음");
    }

    @Test
    void initializeMember() {
        // Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        IntStream.rangeClosed(1, 101).forEach(i -> {
            MemberEntity member = MemberEntity.builder()
                    .seq(Long.valueOf(i))
                    .email("e" + i + "@induk.ac.kr") // 201812012  -> 16038
                    .pw("pw" + i)
                    .name("name" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    void createMember() {
        Member member = Member.builder()
                .email("13@13.com")
                .name("13")
                .pw("13")
                .build();
        if(memberService.create(member) > 0 ) // 정상적으로 레코드의 변화가 발생하는 경우 영향받는 레코드 수를 반환
            System.out.println("등록 성공");
        else
            System.out.println("등록 실패");
    }

    @Test
    public void testPageList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(5).size(10).build();
        PageResultDTO<Member, MemberEntity> resultDTO = memberService.getList(pageRequestDTO);
        for(Member member : resultDTO.getDtoList())
            System.out.println(member);

        System.out.println("Prev : " + resultDTO.isPrev()); // PerPagination = 4인경우 , 1 - 4, 5 - 8, 9 - 12
        System.out.println("Next : " + resultDTO.isNext());
        System.out.println("Total Page : " + resultDTO.getTotalPage());
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
