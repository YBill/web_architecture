<%--
  Created by IntelliJ IDEA.
  User: taike
  Date: 2021/5/28
  Time: 5:58 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    String result = (String) request.getAttribute("result");
    Boolean show = (Boolean) request.getAttribute("show");
  %>
  <h1><%=result%></h1>

  <%if(show) {%>
    <a href="index.jsp" >去登陆</a>
  <% }%>


  </body>
</html>
