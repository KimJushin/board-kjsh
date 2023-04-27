package idusw.springboot.boardkjsh.controller;

import idusw.springboot.boardkjsh.domain.Member;
import idusw.springboot.boardkjsh.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberControllerTests { // Unit Test : JUnit - Test Framework
    @Autowired
    MemberService memberService;

    @Test
    void contextLoads(){
        List<Member> result = memberService.readList();
        for (Member m : result)
            System.out.println(m.getSeq() + ":" + m.getEmail() + ":" + m.getName());
    }
}
