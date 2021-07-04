package com.test.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.lostDao.LostDaoImpl;
import com.test.lostDto.LostDto;

public class RegisterLostArticleAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LostDaoImpl dao = LostDaoImpl.getInstance();
		LostDto     dto = new LostDto();
		
		int maxLimit    = 1 * 1024 * 1024;//10mb
		
		
		ServletContext context = request.getServletContext();
		String dir      = context .getRealPath("lostAnimal");
		System.out.println(dir);
		String encType  = "UTF-8";
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					dir,
					maxLimit,
					encType,
					new DefaultFileRenamePolicy()
			);
			
			String    title = multi.getParameter("title");
			String   writer = multi.getParameter("writer");
			String   tel    = multi.getParameter("tel");
			
			String   lostDate= multi.getParameter("lostDate");
			
			String province = multi.getParameter("province");
			String reserv= multi.getParameter("reserveAddr");
			String lostPlace= new StringBuilder(province+" "+reserv).toString();
			
			String lostPic = multi.getFilesystemName("lostPic");
			String detail  = multi.getParameter("detail");
			
			String species = multi.getParameter("species");
			String cate    = multi.getParameter("cate");
			
			String etc     = multi.getParameter("etc");
			int   regRes   = 0;
			String msg     = "";
			
			if(detail==null) {
				detail="-";
			}
			
			if(etc==null) {
				etc = "-";
			}
			
			
			
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setTel(tel);
			dto.setLostDate(lostDate);
			dto.setProvince(province);
			dto.setReserv(reserv);
			dto.setLostPlace(lostPlace);
			dto.setLostPic(lostPic);
			dto.setDetail(detail);
			dto.setSpecies(species);
			dto.setCate(cate);
			dto.setEtc(etc);
			
			System.out.println(dto);
			regRes= dao.registerLost(dto);
			
			if(regRes > 0) {
				msg = new StringBuilder(writer+"님,실종신고 등록이 완료되었습니다!").toString();
			}else {
				msg = new StringBuilder(writer+"님,실종신고 등록을 다시 시도해주세요").toString();
				
			}
			
			alertMsg(msg,"lost.do?command=lostMain&page=1",response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	private void alertMsg(String msg, String url, HttpServletResponse response) {
		
		PrintWriter out = null;
		String   literal="<script>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
		try {
			out  = response.getWriter();
			out.print(literal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
