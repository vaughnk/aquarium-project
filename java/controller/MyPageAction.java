package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/mypage.jsp"); //마이페이지
		forward.setRedirect(false);
	
	/*	MemberVO mvo=new MemberVO();
		MemberDAO mdao=new MemberDAO();
		mvo.setMemberId(request.getParameter("userId"));
		mvo.setMemberPw(request.getParameter("password"));
		
		MemberVO member = mdao.selectOne(mvo);
		request.getSession().setAttribute("member", member);
		request.getSession().setAttribute("memberName", member.getMemberId());
*/		
		return forward;
	}

}
