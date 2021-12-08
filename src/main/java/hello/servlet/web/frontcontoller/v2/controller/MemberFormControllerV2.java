package hello.servlet.web.frontcontoller.v2.controller;

import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView
    process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
