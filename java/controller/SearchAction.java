package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import product.ProductDAO;
import product.ProductVO;

public class SearchAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      forward.setPath("/shop-grid.jsp");
      forward.setRedirect(false);
      ProductDAO pdao=new ProductDAO();
      ProductVO pvo=new ProductVO();
      // 검색한 이름에 포함된 애들 selectAll
   //   String pc = request.getParameter("pageCnt");
   //   int pageCnt = 9;
   //   if (pc != null) {
   //      pageCnt = Integer.parseInt(pc);
   //   }
   //   pvo.setPageCnt(pageCnt);
      
      //검색어 가져오기
      pvo.setProductName(request.getParameter("searchName")); 
      System.out.println("로그 :" + pvo.getProductName());
      
      //---페이징 처리 
      int page = 1;  //page 초기값=1 세팅
      if (request.getParameter("page") != null) {  //사용자가 보고싶은 페이지 번호 클릭했을경우
         page = Integer.parseInt(request.getParameter("page"));
         System.out.println("로그 클릭한 페이지 번호: "+page);
      }
      else {
         System.out.println("로그: 목록초기화면임");
         System.out.println("로그 클릭한 페이지 번호: "+page);
      }

      Paging paging = new Paging();

      paging.setDisplayRow(9); //회원목록 출력은 row 수 = 10으로 세팅
      pvo.setDisplayRow(9);

      paging.setCurpage(page); //현재페이지
      System.out.println("현재페이지 : "+paging.getCurpage());
      paging.setStartNum(page); //처음글목록번호
      pvo.setStartNum(page);    
      System.out.println("이때 startNum : "+paging.getStartNum()+"/"+pvo.getStartNum());
      paging.setEndNum(page);   //끝글목록번호
      pvo.setEndNum(page);
      System.out.println("이때 endNum : "+paging.getEndNum()+"/"+pvo.getEndNum());

      int count = pdao.getNameCount(pvo);  
      paging.setTotalCount(count);
      pvo.setTotalCount(count);
      System.out.println("총목록수 : "+count);
      System.out.println("next"+paging.isNext());
      System.out.println("prev"+paging.isPrev());
      request.setAttribute("paging", paging); //페이징처리

      //-----------
      
      request.setAttribute("pList", pdao.selectAll(pvo));
      
      return forward;
   }

}