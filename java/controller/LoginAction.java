package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class LoginAction implements Action { // 로그인 페이지

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      forward.setPath("/IdCheck"); // 로그인 후 메인페이지로 이동
      forward.setRedirect(false);
      MemberVO mvo = new MemberVO();
      MemberDAO mdao = new MemberDAO();
      MemberVO member = null;
      if(request.getParameter("kakao").equals("kakao")) {
         System.out.println("로그 카카오로그인");
       //  mvo.setMemberEmail(request.getParameter("Email"));
         mvo.setMemberName(request.getParameter("memberName"));
         mvo.setMemberId(request.getParameter("userId"));
       //  mvo.setMemberPw(request.getParameter("password"));
       //  mvo.setMemberPhone(request.getParameter("memberPhone"));
         forward.setPath("/main.do");
          System.out.println("로그인됨:" + mvo);
          request.getSession().setAttribute("member", mvo);
      }
      mvo.setMemberId(request.getParameter("userId"));
      mvo.setMemberPw(request.getParameter("password"));
      if (mdao.selectOne(mvo) != null) {
        System.out.println("로그 그냥 로그인");
         member = mdao.selectOne(mvo);
         forward.setPath("/main.do");
         System.out.println("로그인됨:" + member);
         request.getSession().setAttribute("member", member);
      } else {
         System.out.println("로그인 실패");
      }

      return forward;
   }

}