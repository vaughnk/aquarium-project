package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.EmailDAO;
import email.EmailVO;
import msg.MsgDAO;
import msg.MsgVO;
import msgReply.MsgReplyDAO;
import msgReply.MsgReplyVO;

public class MsgSendAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/msg-send.jsp"); // 문의 답변 관리자영역
		forward.setRedirect(false);
		MsgVO msgvo=new MsgVO();
		MsgDAO msgdao=new MsgDAO();
		MsgReplyVO revo=new MsgReplyVO();
		MsgReplyDAO redao=new MsgReplyDAO();
		
		msgvo.setMsgNum(Integer.parseInt(request.getParameter("msgNum")));
		
		request.setAttribute("msg", msgdao.selectOne(msgvo));
				
		revo.setMsgNum(Integer.parseInt(request.getParameter("msgNum")));
		revo.setMemberId(request.getParameter("memberId"));
		revo.setMsgReplyTitle(request.getParameter("msgReplyTitle"));
		revo.setMsgReplyContent(request.getParameter("msgReplyContent"));
		
		request.setAttribute("msgReply", redao.insert(revo));
		
		//email API--------------------------------------------------------- 
		System.out.println("로그: 이메일API 실행됨");
		
		EmailVO evo = new EmailVO();
		EmailDAO edao = new EmailDAO();
		
		evo.setEmailAddress(request.getParameter("msgEmail"));   //보낼 메일 주소
		System.out.println("이메일: "+evo.getEmailAddress());
		
		evo.setEmailTitle(request.getParameter("msgReplyTitle"));     //메일 제목
		System.out.println("제목: "+evo.getEmailTitle());

		evo.setEmailContent(request.getParameter("msgReplyContent")); //메일 내용
		System.out.println("내용: "+evo.getEmailContent());
		
		int result = edao.email(evo);
		System.out.println("result: "+result);
		request.setAttribute("email",result);
				
		//이메일 성공여부 alert창 띄우기..안띄워짐...
		//response.setContentType("text/html; charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //if(result == 1){
        //	out.println("<script type='text/javascript'>alert('문의 답변 이메일 전송 성공');</script>");
        //}
		//else{
		//	 out.println("<script>alert('문의 답변 이메일 전송 실패');</script>");
		//}
        
		//------------------------------------------------------------------
		
        return forward;
		
	}

}
