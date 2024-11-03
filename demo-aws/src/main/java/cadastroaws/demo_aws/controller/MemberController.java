package cadastroaws.demo_aws.controller;

import cadastroaws.demo_aws.domain.Member;
import cadastroaws.demo_aws.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public String createMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return "Member saved successfully!";
    }
}