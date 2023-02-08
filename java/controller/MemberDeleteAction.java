package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class MemberDeleteAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward=new ActionForward();
      forward.setPath("/memberList.do");
      forward.setRedirect(false);
      MemberVO mvo=new MemberVO();
      MemberDAO mdao=new MemberDAO();
      
      if(request.getParameter("userId") != null) {
         mvo.setMemberId(request.getParameter("userId"));
         System.out.println("로그 userId:"+request.getParameter("userId"));
         System.out.println("로그 삭제 mvo: "+mvo.getMemberId());
         if(mdao.delete(mvo)) {
            System.out.println("회원삭제 완료"+mvo.getMemberId());
         } else {
            System.out.println("회원삭제 실패");
         }
      }
      else {
         
         mvo.setMemberId(request.getParameter("memberId"));
         if(mdao.delete(mvo)) {
            System.out.println("회원삭제 완료22"+mvo.getMemberId());
            forward.setPath("main.jsp");
            forward.setRedirect(true);
            request.getSession().invalidate();
            
         } else {
            System.out.println("회원삭제 실패22");

         }
      }
      
      return forward;
   }

}