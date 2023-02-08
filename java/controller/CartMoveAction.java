package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.ProductVO;

public class CartMoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("shopping-cart.jsp");
		forward.setRedirect(false);
		HttpSession session = request.getSession(false);
		ArrayList<ProductVO> cart = (ArrayList<ProductVO>) session.getAttribute("cart");
		if(cart==null) {
			cart=new ArrayList<ProductVO>();
		}
		int total = 0;
		for (int i = 0; i < cart.size(); i++) {
			// 총 금액 계산
			// DB 저장할 필요 없을듯
			total += cart.get(i).getProductPrice() * cart.get(i).getSellCnt();
		}
		// session에 계속 가지고 다닐 필요 없기 때문에 request
		request.setAttribute("total", total);
		return forward;
	}

}
