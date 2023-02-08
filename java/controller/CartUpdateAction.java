package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.ProductDAO;
import product.ProductVO;

public class CartUpdateAction implements Action { // 장바구니 업데이트 액션

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      forward.setPath("cartMove.do"); // 장바구니 업데이트 후 ??로 이동
      System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
      System.out.println("gggggggggggggggggggggggggggg"+request.getParameter("pNum"));
      System.out.println("ffffffffffffffff"+request.getParameter("sellCnt"));
      forward.setRedirect(true);
      ProductVO pvo = new ProductVO();
      ProductDAO pdao = new ProductDAO();
      // ArrayList<ProductVO> cart = (ArrayList)request.getAttribute("cart");
      HttpSession session = request.getSession(false);
      ArrayList<ProductVO> cart = (ArrayList<ProductVO>) session.getAttribute("cart");
      if (cart == null) {
         cart = new ArrayList<ProductVO>();
      }
      int sellCnt = 0;
      int pNum = 0;
      if (request.getParameter("pNum") != null) { // 상품번호
         pNum = Integer.parseInt(request.getParameter("pNum"));
      }
      pvo.setProductNum(pNum);
      
      System.out.println(pvo);
      // System.out.println("sellCnt 1 =" + request.getParameter("sellCnt")); // 로그

      if (request.getParameter("sellCnt") != null) { // 선택 수량
            sellCnt = Integer.parseInt(request.getParameter("sellCnt"));
      } else {
         sellCnt = 1;
      }
      pvo.setSellCnt(sellCnt);
        ProductVO pCheck = new ProductVO();
         pCheck = pdao.selectOne(pvo);
         if (request.getParameter("sellCnt") != null) { // 선택 수량
            sellCnt = Integer.parseInt(request.getParameter("sellCnt"));
            System.out.println("로그 sellCnt:" + sellCnt);
            if (sellCnt >= 1 && sellCnt <= pCheck.getProductCnt() - 1) {
               System.out.println("로그 조건문 제대로 들어옴 ");
               pvo.setSellCnt(sellCnt);
               System.out.println("로그 수량확인:" + pvo.getSellCnt());
            } else {
               System.out.println("로그 수량잘못입력");
               forward.setPath("SellCheck");
               return forward;
            }
         }
      boolean flag = true;
      if (cart.size() != 0) { // 장바구니에 상품이 있을때
         // int pCnt=Integer.parseInt(request.getParameter("pCnt"));
         // pvo.setProductCnt(pCnt-sellCnt); 물건을 구매한만큼 product의 Cnt 감소
         // 카트 중복체크
         for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getProductNum() == pNum) { //|| cart.get(i).getSellCnt()!=sellCnt) {
               flag = false;
               // 같은게있어요
               int totalCnt = cart.get(i).getSellCnt()+pvo.getSellCnt();
               System.out.println(totalCnt);
               pvo.setSellCnt(totalCnt);
               cart.remove(i);
               cart.add(pdao.selectOne(pvo));
               System.out.println(cart.get(i));
               break;
            }
         }
         if(flag) { // 같은게없으면
            cart.add(pdao.selectOne(pvo));
         }
         System.out.println("sellCnt 1 =" + pdao.selectOne(pvo).getSellCnt()); // 로그
         System.out.println("카트 로그 :" + cart); // 로그
      } else { // 없을때
         cart.add(pdao.selectOne(pvo));
         System.out.println("카트 로그 =" + cart); // 로그
      }
      session.setAttribute("cart", cart);
      return forward;
   }
}