package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.EmailDAO;
import email.EmailVO;
import msg.MsgDAO;
import msg.MsgVO;

public class LeaveMsgAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
	//	forward.setPath("/LeaveCheck");
	//	forward.setPath("/blog.do");
		forward.setPath("/leavemsg.jsp");
		forward.setRedirect(false);
		MsgVO msgvo = new MsgVO();
		MsgDAO msgdao = new MsgDAO();

		msgvo.setMsgEmail(request.getParameter("email"));
		msgvo.setMsgContent(request.getParameter("content"));
		msgvo.setMsgTitle(request.getParameter("title"));

		msgdao.insert(msgvo);  //문의내역추가
		
		//email API--------------------------------------------------------- 
		System.out.println("로그: 이메일API 실행됨");
				
		EmailVO evo = new EmailVO();
		EmailDAO edao = new EmailDAO();
				
		evo.setEmailAddress(request.getParameter("email"));   //보낼 메일 주소
		System.out.println("이메일: "+evo.getEmailAddress());
				
		evo.setEmailTitle(request.getParameter("title"));     //메일 제목
		System.out.println("제목: "+evo.getEmailTitle());

		
		String content ="<문의내역>"+request.getParameter("content")
				          + "문의가 접수되셨습니다." 
				          + "문의 순서대로 답장 드리오니 기다려주세요 :D" ;
		evo.setEmailContent(content); //메일 내용
		System.out.println("내용: "+evo.getEmailContent());
				
		int result = edao.email(evo);
		System.out.println("result: "+result);
		request.setAttribute("result",result);
		
		
		return forward;
	}

}
