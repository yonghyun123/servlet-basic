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



**주로 다음 3가지 방법을 사용한다**

- GET - 쿼리 파라미터
  - /url?username=Kim&age=20
  - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
  - 예)검색,필터,페이징등에서 많이 사용
- POST - HTML form
  - Content-type:application/x-www-form-urlencoded
  - 메시지에 바디에 쿼리 파라미터 형식으로 전달
  - 예)회원가입, 상품 주문, HTML Form을 사용
- HTTP message body에 데이터를 직접 담아서 요청
  - HTTP API에 주로 사용, JSON,XML,TEXT
  - POST, PUT, PATCH



> 참고
>
> Content-type은 HTTP 메시지 바디의 데이터 형식을 지정한다.
>
> *GET URL 쿼리 파라미터 형식*으로 데이터를 전달하면 HTTP 메시지 바디를 사용하지 않기 때문에 content-type이 없다.
>
> "POST HTML Form 형식"으로 데이터를 전달하면 HTTP 메시지 바디에 해당하는 데이터를 포함해서 보내기 때문에 바디에 포함된 데이터가 어떤 형식인지 반드시 content-type을 지정해야 한다.



### HTTP 요청 데이터 - API 메시지 바디 - JSON

HTTP API에서 주로 사용하는 JSON형식으로 데이터를 전달해보자.



**"JSON 형식 전송"**

- POST 
- Content-type: application/json
- Message body: {"username": "hello", "age":20}
- 결과: messageBody = {"username": "hello", "age":20}



### HttpServletResponse - 기본 사용법

- Content-type
- 쿠키, redirect

### HTTP 응답 데이터 - 단순 텍스, HTML

- 단순 텍스트 응답
- HTML 응답



