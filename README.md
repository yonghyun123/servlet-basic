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

### HTTP 응답 데이터 - 단순 텍스트, HTML

- 단순 텍스트 응답
- HTML 응답



> **템플릿 엔진으로**
>
> 지금까지 서블릿과 자바 코드만으로 html을 만들었다. 서블릿 덕분에 동적으로 원하는 html을 만들 수 있었다. 정적인 html문서라면 화면이 달라지는 회원의 저장 결과라던가, 회원 목록 같은 동적인 html을 만드는 일은 불가능하다.
>
> 그런데, 코드에서 보듯이 매우 복잡하다 자바코드로 html을 만들어내는 것 보다 차라리 html 문서에 동적으로 변경해야 하는 부분만 자바 코드를 넣을 수 있다면 편리할 것이다.
>
> 이것이 템플릿 엔진이 나온 이유이다. 템플릿 엔진을 사용하면 html문서에서 필요한 곳만 코드를 적용해서 동적으로 변경할 수 있다.
>
> 템플릿 엔진에는 jsp, Thymeleaf, Freemarker, Velocity등이 있다.



### MVC 패턴 - 개요

**너무 많은 역할**

하나의 서블릿이나 JSP만으로 비즈니스 로직과 뷰 렌더링까지 모두 처리하게 되면, 너무 많은 역할을 하게 되어 유지보수가 어려워진다. 비즈니스 로직을 호출하는 부분에 변경이 발생해도 해당 코드를 손대야 하고, UI를 변경해도 비즈니스 로직이 함께 있는 해당 파일을 수정해야 한다.



**변경의 라이프 사이클**

중요한 문제는 둘 사이에 변경의 라이프 사이클이 다르다는 점이다. 예를 들어서 UI를 일부 수정하는 일과 비즈니스 로직을 수정하는 일은 각각 다르게 발생할 가능성이 높고 대부분 서로에게 영향을 주지 않는다. 이렇게 변경의 라이프 사이클이 다른 부분을 하나의 코드르 관리하는 것은 유지보수하기 좋지 않다.



**기능 특화 ** 

특히 JSP같은 뷰 템플릿은 화면을 렌더링 하는데 최적화 되어 있기 때문에 이 부분의 업무만 담당하는 것이 가장 효과적이다.



**Model View Controller**

MVC 패턴은 지금까지 학습한 것 처럼 하나의 서블릿이나, JSP로 처리하던 것을 컨트롤러와 뷰라는 영역으로 서로 역할을 나눈 것을 말한다. 웹 어플리케이션은 보통 이 MVC를 사용한다.



**WEB-INF**

> 이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 우리가 기대하는 것은 항상 컨트롤러를 통해서 JSP를 호출한는 것이다.



**redirect vs forward**

> 리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가 클라이언트가 redirect 경로로 다시 요청한다. 따라서 클라이언트가 인지 할 수 있고, URL경로도 실제로 변경된다. 반면에 forward는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다.









