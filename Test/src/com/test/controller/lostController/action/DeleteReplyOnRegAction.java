package com.test.controller.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.replyDto.ReplyDto;

public class DeleteReplyOnRegAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LostDaoImpl dao = LostDaoImpl.getInstance();
		ReplyDto    dto = new ReplyDto();
		
		int boardId = Integer.valueOf(request.getParameter("boardId"));
		int num	    = Integer.valueOf(request.getParameter("num"));
		int ordIdx = Integer.valueOf(request.getParameter("ordIdx"));
		int order  = dao.getReplyOrderValue(boardId, num, ordIdx);
		
		//ReplyDto stan = dao.selectReply(order);
		
		dto.setBoardId(boardId);
		dto.setReplyOrder(order);
		dto.setNum(num);
		
		int delRes = dao.deleteReply(dto);
		String url = "lost.do?command=lostMain&page=1";
		String msg = "";
		
		if(delRes > 0) {
			msg = "댓글 삭제 성공";
		}else {
			msg = "댓글 삭제 실패";
		}
		
		alertByJavascript(msg,url,response);
	}
	//캡슐화
	private void alertByJavascript(String msg, String url , HttpServletResponse response) throws IOException {
			
		PrintWriter out = response.getWriter();
			
		String    alert = "<script>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
			
		out.print(alert);
	}
}
