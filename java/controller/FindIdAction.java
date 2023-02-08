package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class FindIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		System.out.println("FindIdAction들어옴");
		forward.setPath("findIdResult.jsp");
		forward.setRedirect(false);
		System.out.println("sb 생성 전");
		StringBuffer sb = new StringBuffer();

		sb.append(request.getParameter("memberPhone"));
		System.out.println("sb 생성 후" + sb);
		
		/*
		 * sb.insert(3, "-"); 
		 * sb.insert(8, "-");
		 */
		
		//String p = sb.toString();

		//System.out.println(p);
		
		MemberVO mvo = new MemberVO();
		mvo.setMemberPhone(sb.toString());
		MemberDAO mdao = new MemberDAO();

		MemberVO member = mdao.selectOne(mvo); // id, 이름이 담긴 멤버
		if(member == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('No member!!!!');history.go(-1);</script>");
			out.flush();
		}

		request.setAttribute("memberName", member.getMemberName());
		request.setAttribute("memberId", member.getMemberId());
		return forward;
	}

}
