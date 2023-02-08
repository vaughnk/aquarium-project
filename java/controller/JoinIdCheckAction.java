package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

// type: 'POST',
// url: 'check',
//  => check라는 URL을 향해 POST방식으로 요청
@WebServlet("/joinIdCheck")
public class JoinIdCheckAction extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   //기본 생성자
    public JoinIdCheckAction() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      response.getWriter().append("Served at: ").append(request.getContextPath());
   }

   // type: 'POST',
   // url: 'check',
   //  => check라는 URL을 향해 POST방식으로 요청
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("로그1");
      MemberDAO mdao=new MemberDAO();
      MemberVO mvo=new MemberVO();
      mvo.setMemberId(request.getParameter("userId")); // data: {id:id},
      System.out.println("로그 아이디체크:"+request.getParameter("userId"));
      MemberVO member=mdao.selectOne(mvo);
      System.out.println("로그 member:"+member);
      
      if(mvo.getMemberId() != "") {
         if(member==null) {
            System.out.println("로그1");
            // response.getWriter() == JSP 내장객체 out에 해당함
            response.getWriter().println("1"); // 중복아님. 가입가능
         }
         else {
            System.out.println("로그-1");
            response.getWriter().println("-1"); // 중복임. 가입불가능
         }
      }
      else {
         System.out.println("로그2");
         response.getWriter().println("2"); // 공백 불가능
      }
   }

}