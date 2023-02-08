package sms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

@WebServlet("/Sms")
public class Sms extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
    public Sms() {
        super();
    }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.getWriter().append("Served at: ").append(request.getContextPath());
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("로그3: Sms서블릿 들어옴");
      SmsVO svo = new SmsVO();
      SmsDAO sdao = new SmsDAO();
      MemberVO mvo = new MemberVO();
      MemberDAO mdao = new MemberDAO();
      
      String check = request.getParameter("check");
      System.out.println("로그 check : "+check);
      svo.setMemberPhone(request.getParameter("userPhoneNum"));   //사용자 전화번호 가져오기
      mvo.setMemberPhone(request.getParameter("userPhoneNum"));
      System.out.println("로그 이메일:"+ svo.getMemberPhone()+"/멤버:"+mvo.getMemberPhone());

      MemberVO member = mdao.selectOne(mvo);
      System.out.println("로그 member : "+member);
      
      int randNum = 0; 
      //받아온 randNum값을 다시 ajax으로 보낸다 ->  success: function(randNum)  
      response.setContentType("application/x-json; charset=UTF-8");  
      
      if(check.equals("1")) {   //아이디 찾기 할 때, 비밀번호 찾기 할 때
         System.out.println("로그 아이디(비번) 찾기 할 것");
         if(member != null) {  //전화번호가 있어. 
            randNum = sdao.sns(svo);  //sns메서드 통해 randNum값 리턴받아옴.
            System.out.println(randNum);
         }
         else if(member == null) {  //전화번호가 없어.
            randNum = 1 ; 
         }

      }
      else if(check.equals("2")) {  //회원가입할때
         System.out.println("로그 회원가입 할 것");
         if(member == null) {  //전화번호가 없어. 
            randNum = sdao.sns(svo);  //sns메서드 통해 randNum값 리턴받아옴.
            System.out.println(randNum);
         }
         else if(member != null) {
            randNum = 1 ; 
            //   PrintWriter out = response.getWriter();
            //   out.println("<script>alert('이미 사용중인 전화번호입니다. 다른 전화번호를 사용해주세요!');history.go(-1);</script>");
            //   out.flush();
         }
      }
      else if(check.equals("3")) {  //changeinfo할때
         System.out.println("로그 changeinfo할 것");
         randNum = sdao.sns(svo);  //sns메서드 통해 randNum값 리턴받아옴.
         System.out.println(randNum);
      
      
      }
      response.getWriter().write(randNum+"");
   }

}