package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlogInsertMoveAction implements Action{

	@Override // blog-insert.jsp로 이동하는 페이지
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/blog-insert.jsp"); 
		forward.setRedirect(false);		
		
		return forward;
	}

}
