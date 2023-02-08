package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;
import common.Paging;

public class BlogListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/blog-list.jsp"); // 관리자 공지사항 목록
		forward.setRedirect(false);			
		BoardVO bvo = new BoardVO();
		BoardDAO bdao = new BoardDAO();
		
	//	if(!request.getParameter("search").equals(null)) {
	//		bvo.setSearchContent(request.getParameter("search"));
	//	}
		
		//---페이징 처리 
		Paging paging = new Paging();

		paging.setDisplayRow(10); //회원목록 출력은 row 수 = 10으로 세팅
		bvo.setDisplayRow(10);

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
		bvo.setStartNum(page);    
		System.out.println("이때 startNum : "+paging.getStartNum()+"/"+bvo.getStartNum());
		paging.setEndNum(page);   //끝글목록번호
		bvo.setEndNum(page);
		System.out.println("이때 endNum : "+paging.getEndNum()+"/"+bvo.getEndNum());
		int count = bdao.getAllCount();  //총 목록 개수
		paging.setTotalCount(count);
		System.out.println("총목록수 : "+count);

		request.setAttribute("paging", paging); //페이징처리
		//-----------


		
		
		
		
	/*	String pc=request.getParameter("pageCnt");
		int pageCnt=10;
		if(pc!=null){
			pageCnt=Integer.parseInt(pc);
		}
		request.setAttribute("pageCnt", pageCnt);
	*/	
		request.setAttribute("bList", bdao.selectAll(bvo));

		return forward;
	}

}
