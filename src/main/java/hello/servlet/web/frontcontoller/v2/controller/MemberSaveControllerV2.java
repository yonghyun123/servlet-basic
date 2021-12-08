package hello.servlet.web.frontcontoller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //모델에 데이터를 보관한다.
        request.setAttribute("member", member);

        System.out.println("==========IN SERVICE===========");

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
