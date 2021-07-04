package com.test.lostDao;

import static util.common.JDBCTemplate.close;
import static util.common.JDBCTemplate.commit;
import static util.common.JDBCTemplate.getConnection;
import static util.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.test.lostDto.LostDto;
import com.test.replyDto.ReplyDto;

public class LostDaoImpl implements LostDao{
	private static LostDaoImpl dao = new LostDaoImpl();
	Connection conn 		   = null;
	Statement  stat		       = null;
	PreparedStatement ps 	   = null;
	ResultSet  rs 			   = null;
	SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");//transfer date to string(.format)
	
	private LostDaoImpl() {
		
	}
	
	public static LostDaoImpl getInstance() {
		return dao;
	}
	
	
	//모든 게시글 조회
	
	@Override
	public List<LostDto> selectAllLost(int page) {
		// TODO Auto-generated method stub
		//"SELECT * FROM (SELECT ROWNUM CNT, NO, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, RESERV, LOSTPLACE,LOSTPIC, DETAIL, SPECIES, CATE, ETC, WATCH FROM (SELECT * FROM REGLOST ORDER BY NO DESC)) WHERE CNT BETWEEN ? AND ?"
		List<LostDto> list = new ArrayList<>();
		
		conn = getConnection();

		//9개씩 출력 할 것
		//final int items = 9;
		int     startIdx= (page-1)*9+1;//1 10 19
		int     finIdx  = startIdx +8;//9 18 27
		try {
			ps  = conn.prepareStatement(selectAllQuery);
			
			ps.setInt(1,startIdx);
			ps.setInt(2, finIdx);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LostDto        dto = new LostDto();
				
				dto.setNum(rs.getInt(2));
				dto.setTitle(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setTel(rs.getString(5));
				dto.setLostDate(rs.getString(6));
				dto.setProvince(rs.getString(7));
				dto.setReserv(rs.getString(8));
				dto.setLostPlace(rs.getString(9));
				dto.setLostPic(rs.getString(10));
				dto.setDetail(rs.getString(11));
				dto.setSpecies(rs.getString(12));
				dto.setCate(rs.getString(13));
				dto.setEtc(rs.getString(14));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]SELECT LOST ALL ARTICLE FAILED");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		return list;
	}
	//게시글 수 조회
	@Override
	public int countAllLostArticle() {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			
			conn = getConnection();
			stat = conn.createStatement();
			
			rs   = stat.executeQuery(countQuery);
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]Failed to count all article");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stat);
			close(conn);
		}
		return cnt;
	}
	//게시글 상세조회
	@Override
	public LostDto selectOne(int no) {
		// TODO Auto-generated method stub
		/*
		 * SELECT NO, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, RESERV, LOSTPLACE,LOSTPIC, DETAIL, SPECIES, CATE, ETC, WATCH FROM REGLOST WHERE NO=?
		 * */
		LostDto target = new LostDto();

		
		try {
			
			conn  = getConnection();
			ps    = conn.prepareStatement(selectOneQuery);
			
			ps.setInt(1, no);
			
			rs    = ps.executeQuery();
			
			if(rs.next()) {
				
				target.setNum(rs.getInt(1));
				target.setWriter(rs.getString(2));
				target.setTitle(rs.getString(3));
				target.setTel(rs.getString(4));
				
				Date tmp = rs.getDate(5);
				String        lostDate= sf.format(tmp);
				
				target.setLostDate(lostDate);
				target.setProvince(rs.getString(6));
				target.setReserv(rs.getString(7));
				target.setLostPlace(rs.getString(8));
				target.setLostPic(rs.getString(9));
				target.setDetail(rs.getString(10));
				target.setSpecies(rs.getString(11));
				target.setCate(rs.getString(12));
				target.setEtc(rs.getString(13));
				target.setWatch(rs.getInt(14));
				System.out.println(target);
			}
		}catch(Exception e) {
			System.out.println("[ERR]FAILED IN SELECT ONE!");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return target;
	}
	//게시글 등록
	@Override
	public int registerLost(LostDto dto) {
		// TODO Auto-generated method stub
		/*"INSERT INTO REGLOST VALUES(LOST_ARTICLE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,0)"
		 * */
		int  regRes = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(insertQuery);
			
			ps.setString(1, dto.getWriter());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getTel());
			ps.setString(4, dto.getLostDate());
			ps.setString(5, dto.getProvince());
			ps.setString(6, dto.getReserv());
			ps.setString(7,dto.getLostPlace());
			ps.setString(8, dto.getLostPic());
			ps.setString(9, dto.getDetail());
			ps.setString(10, dto.getSpecies());
			ps.setString(11, dto.getCate());
			ps.setString(12, dto.getEtc());
			
			regRes = ps.executeUpdate();
			
			if(regRes > 0) {
				System.out.println("실종신고 등록 성공");
				commit(conn);
			}else {
				System.out.println("실종신고 등록 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]FAILED TO REGISTER LOST ANIMAL");
			e.printStackTrace();
		}finally {
			close(ps);
			close(conn);
		}
		
		return regRes;
	}
	//게시글 수정
	@Override
	public int updateLostInfo(LostDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}	
	//게시글 삭제
	@Override
	public int deleteLostInfo(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalPage() {
		// TODO Auto-generated method stub
		int articleNum = countAllLostArticle();
		int pageNum    = 0;
		int temp       = articleNum%9;
		
		if(temp >=1 && temp <9) {
			pageNum = articleNum/9 + 1;
		}else {
			pageNum = articleNum/9;
		}
		
		return pageNum;
	}

	@Override
	public int updateWatch(int no) {
		// TODO Auto-generated method stub
		/*UPDATE REGLOST SET (WATCH=WATCH+1) WHERE NO=?*/
		int resUpWatch = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(updateWatchQuery);
			
			ps.setInt(1, no);
			
			resUpWatch = ps.executeUpdate();
			
			if(resUpWatch > 0) {
				System.out.println("조회수 갱신 성공");
				commit(conn);
			}else {
				System.out.println("조회수 갱신 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]조회수 갱신 실패");
			e.printStackTrace();
		}finally {
			close(ps);
			close(conn);
		}
		
		return resUpWatch;
	}

	@Override
	public int countAllReply(int num) {
		// TODO Auto-generated method stub
		//SELECT COUNT(REPLY_ORDER) FROM REPLY_ON_LOSTDETAIL WHERE NUM=? AND LEV > 0
		int cnt = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(countReplyQuery);
			
			ps.setInt(1, num);
			
			rs   = ps.executeQuery();
			
			cnt  = rs.getInt(1);
			
		}catch(Exception e) {
			System.out.println("[ERR]COUNT ALL REPLY ON ARTICLE#"+num);
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		return cnt;
	}

	//댓글은 30개씩 끊어서 보여줄 것
	@Override
	public int totalReplyPage(int no) {
		// TODO Auto-generated method stub
		int replyCnt = countAllReply(no);
		int page     = (int)(Math.ceil((double)replyCnt/30));
		
		return page;
	}

	@Override
	public List<ReplyDto> selectAllReply(int boardId, int num,int page) {
		// TODO Auto-generated method stub
		List<ReplyDto> list = new ArrayList<>();
		/*
		 SELECT * FROM (SELECT ROWNUM ORD, PROCESSED.* FROM (SELECT TARGET.* FROM 
		 (SELECT DISTINCT BOARD_ID, REPLY_ORDER, NUM, LEV, LEV_SEQ, 
		 DEPTH,REPLYTAB, ID, CONTENT, REGDATE, LIKENO, BAN FROM REPLY_ON_LOSTDETAIL START WITH DEPTH IS NULL 
		 CONNECT BY PRIOR LEV= DEPTH ORDER SIBLINGS BY LEV_SEQ) TARGET WHERE NUM=?) PROCESSED) 
		 WHERE (ORD BETWEEN ? AND ?)
		 * */
		//30개씩 끊어서 보여줄것
		final int size = 30;
		int startIdx=(page-1)*size+1;//1~30,31~60
		int finIdx  = startIdx+29;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(selectAllReplyQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, startIdx);
			ps.setInt(4, finIdx);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReplyDto dto = new ReplyDto();

				dto.setBoardId(rs.getInt(2));
				dto.setReplyOrder(rs.getInt(3));
				dto.setNum(rs.getInt(4));
				dto.setLev(rs.getInt(5));
				dto.setLevSeq(rs.getInt(6));
				dto.setDepth(String.valueOf(rs.getInt(7)));
				dto.setReplyTab(rs.getInt(8));
				dto.setId(rs.getString(9));
				dto.setContent(rs.getString(10));
				dto.setRegDate(sf.format(rs.getDate(11)));
				dto.setLikeNo(rs.getInt(12));
				dto.setBan(rs.getInt(13));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("[ERR]SELECT ALL REPLY ON ARTICLE PAGE");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		return list;
	}

	@Override
	public ReplyDto selectSpecificReply(int boardId, int num, int lev, int levSeq) {
		// TODO Auto-generated method stub
		/*
		SELECT BOARD_ID, REPLY_ORDER, NUM, LEV, LEV_SEQ, DEPTH, REPLYTAB, ID, CONTENT, REGDATE, LIKENO, 
		BAN FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? AND NUM=? AND LEV=? AND LEV_SEQ=?
		 * 
		 * */
		ReplyDto target = new ReplyDto();
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(selectOneReplyQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, lev);
			ps.setInt(4, levSeq);
			
			rs  = ps.executeQuery();
			
			if(rs.next()) {
				target.setBoardId(rs.getInt(1));
				target.setReplyOrder(rs.getInt(2));
				target.setNum(rs.getInt(3));
				target.setLev(rs.getInt(4));
				target.setLevSeq(rs.getInt(5));
				target.setDepth(String.valueOf(rs.getInt(6)));
				target.setReplyTab(rs.getInt(7));
				target.setId(rs.getString(8));
				target.setContent(rs.getString(9));
				target.setRegDate(sf.format(rs.getDate(10)));
				target.setLikeNo(rs.getInt(11));
				target.setBan(rs.getInt(12));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]특정 댓글 조회 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return target;
	}

	@Override
	public int registerReplyToArticle(ReplyDto child) {
		// TODO Auto-generated method stub
		/*
		INSERT INTO REPLY_ON_LOSTDETAIL VALUES(?,?,?,?,?,?,?,?,?,SYSDATE,0,0)
		 * 
		 * */
		int regRes = 0;
		int maxOrd = selectMaxOrderOfBoard(child.getBoardId());
		int maxSeq = 0;
		String depth =child.getDepth();
		int  dep = 0;
		
		System.out.println("max order: "+maxOrd);
		//levSeq 최댓값 찾기
		maxSeq  = selectMaxLevOrderOfBoard(child.getBoardId(), child.getNum(),child.getLev());
		System.out.println("부모노드의 시퀀스: "+maxSeq);
		
		
		if(depth==null) {
			dep=0;
			maxSeq =  0;
		}else {
			dep=Integer.valueOf(depth);
		}
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(insertReplyQuery);
			
			ps.setInt(1, child.getBoardId());
			ps.setInt(2, maxOrd+1);
			ps.setInt(3, child.getNum());
			ps.setInt(4, child.getLev()+1);
			ps.setInt(5, maxSeq+1);
			ps.setInt(6, dep+1);
			ps.setInt(7, child.getReplyTab()+1);
			ps.setString(8, child.getId());
			ps.setString(9, child.getContent());
			
			regRes = ps.executeUpdate();
			
			if(regRes >0) {
				commit(conn);
				System.out.println("댓글 작성 성공");
			}else {
				rollback(conn);
				System.out.println("댓글 작성 실패");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]댓글 작성 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		return regRes;
	}

	@Override
	public int selectMaxOrderOfBoard(int boardId) {
		// TODO Auto-generated method stub
		
		/*
		 * String  selectMaxOrderQuery = "SELECT MAX(REPLY_ORDER) FROM 
		 * REPLY_ON_LOSTDETAIL WHERE BOARD_ID=?";
		 * */
		int max =0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(selectMaxOrderQuery);
			
			ps.setInt(1, boardId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				max = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("해당 게시판의 최대 order 조회 완료");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return max;
	}

	@Override
	public int selectMaxLevOrderOfBoard(int boardId, int num, int lev) {
		// TODO Auto-generated method stub
		/*
		SELECT MAX(LEV_SEQ) FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? AND NUM=? AND LEV=?
		 * */
		int getMax = 0;
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(selectMaxLevSeqQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, lev);
			
			rs =ps.executeQuery();
			
			if(rs.next()) {
				getMax = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]MAX LEV ORDER FINDING FAILED");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return getMax;
	}

	@Override
	public int updateLevelForReply(int boardId, int num, int lev, int replyTab) {
		// TODO Auto-generated method stub
		/*
		 	String  updateLevQuery="UPDATE REPLY_ON_LOSTDETAIL SET LEV = 
		 	(LEV + 1), LEV_SEQ=(LEV_SEQ+1) WHERE  BOARD_ID=? AND NUM=? AND LEV=? AND REPLYTAB>?";
		 * */
		int resLevUpdate = 0;
		
		try {
			
			conn = getConnection();
			 
			ps   = conn.prepareStatement(updateLevQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, lev);
			ps.setInt(4, replyTab);
		
			
			resLevUpdate = ps.executeUpdate();
			
			if(resLevUpdate > 0) {
				System.out.println("레벨 조정 성공");
				commit(conn);
			}else {
				System.out.println("레벨 조정 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]댓글형 게시판 레벨 조정실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		return resLevUpdate;
	}

	@Override
	public Set<Integer> getOrderedReplyReply(int boardId, int num) {
		// TODO Auto-generated method stub
		//treeset은 sorted set으로 추가 정렬작업 필요없음
		Set<Integer> processed = new TreeSet<>();
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(exportOrdQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				processed.add(rs.getInt(1));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]order추출 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return processed;
	}

	@Override
	public int getReplyOrderValue(int boardId, int num, int order) {
		// TODO Auto-generated method stub
		Set<Integer> middle = getOrderedReplyReply(boardId, num);
		//리스트로 변환해서 GET으로 접근할것
	    List<Integer> listed = new LinkedList<>(middle); 
		int          result  = listed.get(order);
		
		return result;
	}

	@Override
	public ReplyDto getReparentReplyNode(int boardId, int num, int order) {
		// TODO Auto-generated method stub
		int trans = getReplyOrderValue(boardId, num, order);
		ReplyDto dto = new ReplyDto();
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(selectParentReplyQuery);
			
			ps.setInt(1, boardId);
			ps.setInt(2, num);
			ps.setInt(3, trans);
			
			rs  = ps.executeQuery();
			
			if(rs.next()) {
				dto.setBoardId(rs.getInt(1));
				dto.setReplyOrder(rs.getInt(2));
				dto.setNum(rs.getInt(3));
				dto.setLev(rs.getInt(4));
				dto.setLevSeq(rs.getInt(5));
				dto.setDepth(String.valueOf(rs.getInt(6)));
				dto.setReplyTab(rs.getInt(7));
				dto.setId(rs.getString(8));
				dto.setContent(rs.getString(9));
				dto.setRegDate(sf.format(rs.getDate(10)));
				dto.setLikeNo(rs.getInt(11));
				dto.setBan(rs.getInt(12));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]Reparent 과정 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return dto;
	}


	
	
}
