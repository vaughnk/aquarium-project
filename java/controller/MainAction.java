package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그: mainaction 들어옴");
		ActionForward forward=new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(false);
		
		return forward;
		// pageContext.forward("main.jsp");
		/*
		어디 페이지로 이동해야 하는지 == main.jsp
		어떤 방식으로 이동해야 하는지를 반환 해야함. == forward
		java의 메서드로는 2개 이상의 반환값을 전달 xxxx
		- > 객체를 생성 : ActionForward 클래스로 만든 객체
		
		*/
	}

}
