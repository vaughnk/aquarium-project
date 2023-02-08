package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 판매수량 범위지정
 */
@WebServlet("/SellCheck")
public class SellCheck extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    
    public SellCheck() {
        super();
       
    }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      writer.println("<script>alert('주문수량 오류'); history.go(-1);</script>"); 
      writer.close();
   }

}