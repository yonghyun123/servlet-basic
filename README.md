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



### HttpServletRequest

HTTP 요청 메시지를 개발자가 직접 파싱해서 사용해도 되지만, 매우 불편할 것이다. 서블릿은 개발자가 HTTP요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱한다. 그 결롸를 HttpServletRequest 객체에 담아서 제공한다.



### HTTP 요청 메시지

```
POST /save HTTP/1.1
Host: localhost:8080
Content-type: application/x-www-form-urlencoded

username=kim&age=20
```



> 중요
>
> HttpServletRequest, HttpServletResponse를 사용할 때 가장 중요한 점은 이 객체들이 HTTP요청 메시지, HTTP 응답 메시지를 편리하게 사용하도록 도와주는 객체라는 점이다. 깊이 이해하려면 "**HTTP 스펙이 제공하는 요청, 응답 메시지 자체를 이해 해야한다."**







