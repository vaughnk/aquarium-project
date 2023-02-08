package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import msg.MsgDAO;
import msg.MsgVO;

public class MsgListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/msg-list.jsp"); // 문의 내역 보기 (관리자영역)
		forward.setRedirect(false);

		MsgVO msgvo = new MsgVO();
		MsgDAO msgdao = new MsgDAO();
		
		//---페이징 처리 
		Paging paging = new Paging();

		paging.setDisplayRow(10); //회원목록 출력은 row 수 = 10으로 세팅
		msgvo.setDisplayRow(10);

		int page = 1;  //page 초기값=1 세팅
		if (request.getParameter("page") != null) {  //사용자가 보고싶은 페이지 번호 클릭했을경우
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println("로그 클릭한 페이지 번호: "+page);
		}
		else {
			System.out.println("로그: 목록초기화면임");
			System.out.println("로그 클릭한 페이지 번호: "+page);
		}

		paging.setCurpage(page); //현재페이지
		System.out.println("현재페이지 : "+paging.getCurpage());
		paging.setStartNum(page); //처음글목록번호
		msgvo.setStartNum(page);    
		System.out.println("이때 startNum : "+paging.getStartNum()+"/"+msgvo.getStartNum());
		paging.setEndNum(page);   //끝글목록번호
		msgvo.setEndNum(page);
		System.out.println("이때 endNum : "+paging.getEndNum()+"/"+msgvo.getEndNum());
		int count = msgdao.getAllCount();  //총 목록 개수
		paging.setTotalCount(count);
		System.out.println("총목록수 : "+count);

		request.setAttribute("paging", paging); //페이징처리
		//-----------






	/*	String pc=request.getParameter("pageCnt");
		int pageCnt=10;
		if(pc!=null){
			pageCnt=Integer.parseInt(pc);
		}

		msgvo.setPageCnt(pageCnt);
	*/	
		request.setAttribute("msgList", msgdao.selectAll(msgvo));

		return forward;
	}

}
