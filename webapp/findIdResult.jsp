<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="lee" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="icon" href="img/ms-icon-70x70.png">
<meta charset="UTF-8">
<title>Aquarium | 아이디찾기</title>

<link rel="stylesheet" href="css/modal.css" type="text/css">
<link rel="stylesheet" href="css/join.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style type="text/css">
body {
   background-color: #ffffff;
}
</style>
</head>
<body>
   <%-- 클릭하면 테두리 바뀌는 거 나중에 하겠습니다. (로그인 css 참고) --%>

   <%--<form method="post" name="idfindscreen">--%>
      <div class="member">
         <!-- 로고 -->
         <a href="logout.do"><img class="logo" src="img/logo.png"></a>

         <div class="field tel-number">
            <h3 style="text-align: center;">${memberName}님이
               가입하신 아이디는 <a href="loginMove.do"><strong
                  style="color: skyblue;">${memberId}</a></strong> 입니다.
            </h3>
            <br> <br>
         
         </div>
         <%-- 
         <a href="login.jsp">
             <button style="width: 100%; background-color: skyblue; color: white; border-radius: 3px; margin-bottom: 10px; border-style: none;">로그인</button></a>
        --%>
         <!-- 푸터 -->
         <center><lee:modal /><center>
      </div>
   <%--</form>--%>
   
   <script src="js/regex.js"></script>
   <script src="js/modal.js"></script>

</body>
</html>