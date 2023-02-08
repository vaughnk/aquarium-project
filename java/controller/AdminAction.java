package controller;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;
import member.MemberDAO;
import member.MemberVO;
import msg.MsgDAO;
import msg.MsgVO;
import msgReply.MsgReplyDAO;
import msgReply.MsgReplyVO;
import orders.OrdersDAO;
import orders.OrdersVO;
import product.ProductDAO;
import product.ProductVO;

public class AdminAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/admin.jsp"); // admin 메인페이지
		forward.setRedirect(false);
		
		OrdersVO ovo = new OrdersVO(); 
		OrdersDAO odao = new OrdersDAO();
		
		//주문내역 
		ArrayList<OrdersVO> oList =  odao.selectAll(ovo) ;
		request.setAttribute("oList", oList);  //pnum 
		
		//총매출(totalSum)
		int totalSum=0;
		for(OrdersVO v :oList) {
			totalSum += (v.getOrdersCnt() * v.getProductPrice());
		}
		request.setAttribute("totalSum", totalSum);
		
		//총판매개수(totalCnt)
		int totalCnt=0;
		for(OrdersVO v:oList) {
			totalCnt +=v.getOrdersCnt();
		}
		request.setAttribute("totalCnt", totalCnt);
		
		//가장 많이 팔린 카테고리 
		int a =0;  //수조 총 판매 개수
		int b =0;  //사료 총 판매 개수
		int c =0;  //청소용품 총 판매 개수
		for(OrdersVO v:oList) {
			if( v.getProductCategory().equals("watertank")) {
				a +=v.getOrdersCnt();
			}	
			else if(v.getProductCategory().equals("feed")) {

				b+=v.getOrdersCnt();
			}
			else {
				c+=v.getOrdersCnt();
			}
		}//개수 구하기
		System.out.println("로그 개수 : a: "+a+",b: "+b+",c: "+c); //로그
		
		int sumFeed=0;
		int sumCleaning = 0; 
		//가장 많이 팔린 카테고리 
		String bestCate ="없음"; 
		int max1;
		int max ; 
		max1 = (b>a)? b : a ;
		if(max1 == a) {
			bestCate = (c>max1)? "청소용품" : "수조" ;
		}
		else {  //max1 == b  
			max = (c>max1)? c : max1 ; 	
			if(c == b) {  //가장 많이 팔린 개수가 b일때 -> 혹시모르니, 가격까지 비교
				for(OrdersVO v:oList) {
					if( v.getProductCategory().equals("feed")) {
						sumFeed +=v.getOrdersCnt()* v.getProductPrice();
					}
					else if(v.getProductCategory().equals("cleaning")) {
						sumCleaning +=v.getOrdersCnt() * v.getProductPrice();
					}
			
				} 
				// 먹이가 청소용품보다 많이 팔렸다면 사로 아니라면 청소용품 
				bestCate = (sumFeed>sumCleaning)? "사료": "청소용품";
			}
			else {
				if(max==c) {
				bestCate = "청소용품";
				} 
				else {
					bestCate = "사료";
				}
			}

		}
	
		System.out.println("로그 best : "+bestCate);
		request.setAttribute("bestCate", bestCate);
		
		//가장 적게 팔린 카테고리
		int min1;
		int min;
		String worstCate;
		
		min1 = (b<=a)? b : a ;
		if(min1 == a) {
			worstCate = (c<=min1)? "청소용품": "수조" ;
		}
		else { //min1 == b
			min = (c<=min1)? c: min1 ;    
			if(b == c) {
				for(OrdersVO v:oList) {
					if( v.getProductCategory().equals("feed")) {
						sumFeed +=v.getOrdersCnt()* v.getProductPrice();
					}
					else if(v.getProductCategory().equals("cleaning")) {
						sumCleaning +=v.getOrdersCnt() * v.getProductPrice();
					}
				}
				worstCate = (sumFeed>sumCleaning)? "청소용품": "사료";
			}//if(c)
			else {
				if(min == b) {
					worstCate = "사료";
				}
				else {
					worstCate = "청소용품";
				}
				
			}

		}
		
		request.setAttribute("worstCate", worstCate);
		

		
		
		return forward;
	}

}
