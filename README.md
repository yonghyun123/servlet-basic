# Servlet



> 서블릿은 톰캣 같은 웹 어플리케이션 서버를 직접 설치하고, 그 위에 서블릿 코드를 클래스 파일로 빌드해서 올린 다음, 톰캣 서버를 실행하면 된다. 하지만 이 과정은 번거롭다.
>
> 스프링 부트는 톰캣 서버를 내장하고 있으므로, 설치 첪이 편리하게 서블릿 코드를 실행할 수 있다.

 



```java
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello" + username);
    }
}

```

- @WebServlet 서블릿 애노테이션
  - name:서블릿 이름
  - urlPatterns: URL 매핑
  - 
