package com.test.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.lostDao.LostDaoImpl;
import com.test.replyDto.ReplyDto;

public class RegisterReplyAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		int parentSeq = Integer.valueOf(request.getParameter("seq"));
//		int parentTab = Integer.valueOf(request.getParameter("tab"));
//		int parentLev = Integer.valueOf(request.getParameter("lev"));
//		String parentDepth = request.getParameter("depth");
		
		//int order = Integer.valueOf(request.getParameter("order"));
		
		//ReplyDto reParent = dao.selectSpecificReply(boardId, parentNum, parentLev, parentSeq);
//		System.out.println("reParent: "+reParent);
		
		//먼저 레벨 업데이트
		LostDaoImpl dao = LostDaoImpl.getInstance();
		
		int    parentNum = Integer.valueOf(request.getParameter("num"));

		//부모노드의 ReplyDto 객체 가져오기
		int boardId = Integer.valueOf(request.getParameter("boardId"));
		String tmp  =request.getParameter("reparent-order");
		
		int toFind = 0;
		
		if(tmp.equals("")) {
			toFind = 0;	
		}else {
			toFind = Integer.valueOf(tmp);
		}
		
		
		ReplyDto reParent= dao.getReparentReplyNode(boardId, parentNum, toFind);
		
		
		String id = request.getParameter("id");
		String content= request.getParameter("reply");
		
		System.out.println("reParent: "+reParent);
		
		//대댓글을 달려고 할때 처리
//		if(id=="") {
//			id = request.getParameter("clonedName");
//		}
//		
//		if(content=="") {
//			content=request.getParameter("clonedReply");
//		}
		
		
		ReplyDto target = new ReplyDto();
		target.setBoardId(boardId);
		target.setNum(parentNum);
		target.setLev(reParent.getLev());
		target.setLevSeq(reParent.getLevSeq());
		target.setReplyTab(reParent.getReplyTab());
		target.setId(id);
		target.setContent(content);
//		
	//	System.out.println("now, target: "+target);
		
		int regRes = dao.registerReplyToArticle(target);
		
		//레벨 먼저 조정
		dao.updateLevelForReply(boardId, parentNum, reParent.getLev(), reParent.getReplyTab());
		
		String msg ="";
		
		
		if(regRes > 0) {
			msg = "댓글 등록에 성공하였습니다";
		}else {
			msg ="댓글 등록에 실패하였습니다";
		}
		
		alertByJavascript(msg,"lost.do?command=lostMain&page=1",response);
	}
	
	private void alertByJavascript(String msg, String url , HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String    alert = "<script>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
		
		out.print(alert);
	}

}