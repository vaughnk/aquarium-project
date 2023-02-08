package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;

public class BlogDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/blogList.do"); 
		forward.setRedirect(false);
		BoardVO bvo=new BoardVO();
		BoardDAO bdao=new BoardDAO();
		
		bvo.setBoardNum(Integer.parseInt(request.getParameter("bNum")));
		bdao.delete(bvo);
		System.out.println(bdao.delete(bvo));
		
		return forward;
	}
}
