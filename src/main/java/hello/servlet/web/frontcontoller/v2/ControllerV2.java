package hello.servlet.web.frontcontoller.v2;

import hello.servlet.web.frontcontoller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    MyView process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
