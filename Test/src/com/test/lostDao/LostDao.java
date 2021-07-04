package com.test.lostDao;

import java.util.List;
import java.util.Set;

import com.test.lostDto.LostDto;
import com.test.replyDto.ReplyDto;

public interface LostDao {
	String countQuery     = "SELECT COUNT(NUM) FROM REGLOST";
	String selectAllQuery = "SELECT * FROM (SELECT ROWNUM CNT, NUM, WRITER, TITLE, TEL, LOSTDATE, PROVINCE, RESERV, LOSTPLACE,LOSTPIC, DETAIL, SPECIES, CATE, ETC, WATCH FROM (SELECT * FROM REGLOST ORDER BY NUM DESC)) WHERE CNT BETWEEN ? AND ?";
	String selectOneQuery = "SELECT NUM,WRITER,TITLE,TEL,LOSTDATE,PROVINCE,RESERV,LOSTPLACE,LOSTPIC,DETAIL,SPECIES,CATE,ETC,WATCH FROM REGLOST WHERE NUM=?";
	String insertQuery    = "INSERT INTO REGLOST VALUES(LOST_ARTICLE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,0)";
	//조회수 갱신
	String updateWatchQuery="UPDATE REGLOST SET WATCH=(WATCH+1) WHERE NUM=?";
	
	//해당 게시글의 전체 댓글 수 조회
	String countReplyQuery = "SELECT COUNT(REPLY_ORDER) FROM REPLY_ON_LOSTDETAIL WHERE NUM=? AND LEV > 0";
	//해당 게시글의 모든 댓글 조회(페이지별)-계층형질의 이용
	String selectAllReplyQuery="SELECT * FROM (SELECT ROWNUM ORD, PROCESSED.* FROM (SELECT TARGET.* FROM (SELECT BOARD_ID, REPLY_ORDER, NUM, LEV, LEV_SEQ, DEPTH, REPLYTAB, ID, CONTENT, REGDATE, LIKENO, BAN FROM REPLY_ON_LOSTDETAIL START WITH DEPTH IS NULL CONNECT BY NOCYCLE PRIOR REPLY_ORDER=NUM ORDER SIBLINGS BY DEPTH,LEV_SEQ) TARGET WHERE BOARD_ID=? AND NUM=?) PROCESSED) WHERE (ORD BETWEEN ? AND ?)";
	//특정 댓글 선택
	String selectOneReplyQuery="SELECT BOARD_ID, REPLY_ORDER, NUM, LEV, LEV_SEQ, DEPTH, REPLYTAB, ID, CONTENT, REGDATE, LIKENO, BAN FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? AND NUM=? AND LEV=? AND LEV_SEQ=?";
	//댓글 작성
	String  insertReplyQuery = "INSERT INTO REPLY_ON_LOSTDETAIL VALUES(?,?,?,?,?,?,?,?,?,SYSDATE,0,0)";
	//replyOrder는 기본키제약조건으로 무결성을 지켜야 하므로 최댓값을 이용할 것
	String  selectMaxOrderQuery = "SELECT MAX(REPLY_ORDER) FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=?";
	//levSeq 최댓값 찾기
	String  selectMaxLevSeqQuery="SELECT MAX(LEV_SEQ) FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? AND NUM=? AND LEV=?";
	//레벨값 조정
	String  updateLevQuery="UPDATE REPLY_ON_LOSTDETAIL SET LEV = (LEV + 1), LEV_SEQ=(LEV_SEQ+1) WHERE  BOARD_ID=? AND NUM=? AND LEV=? AND REPLYTAB>?";
	//해당 게시판의 REPLY_ORDER만 추출해내기
	String  exportOrdQuery="SELECT REPLY_ORDER FROM REPLY_ON_LOSTDETAIL WHERE  BOARD_ID=? AND NUM=?";
	//order로 부모노드 찾기
	String  selectParentReplyQuery= "SELECT BOARD_ID, REPLY_ORDER, NUM, LEV, LEV_SEQ, DEPTH, REPLYTAB, ID, CONTENT, REGDATE, LIKENO, BAN FROM REPLY_ON_LOSTDETAIL WHERE BOARD_ID=? AND NUM=? AND REPLY_ORDER=?";
	//게시글 수 조회
	public int countAllLostArticle();
	//게시글 전체 조회
	public List<LostDto> selectAllLost(int page);
	//특정 게시글 조회
	public LostDto selectOne(int no);
	//게시글 작성
	public int     registerLost(LostDto dto);
	//게시글 수정
	public int     updateLostInfo(LostDto dto);
	//게시글 삭제
	public int     deleteLostInfo(int no);
	//게시글 총 페이지
	public int     totalPage();
	//조회수 수정
	public int     updateWatch(int no);
	
	
	//모든 댓글 수 조회
	public int            countAllReply(int num);
	//댓글 총 페이지
	public int 			  totalReplyPage(int num);
	//모든 댓글 조회
	public List<ReplyDto> selectAllReply(int boardId, int num, int page);
	//특정 댓글 작성
	public ReplyDto 	  selectSpecificReply(int boardId, int num, int lev,int levSeq);
	//댓글 작성
	public int			  registerReplyToArticle(ReplyDto child);
	//ORDER 최댓값조회
	public int			  selectMaxOrderOfBoard(int boardId);
	//lev seq 최댓값 조회
	public int 			  selectMaxLevOrderOfBoard(int boardId, int num, int lev);
	//레벨값 조정
	public int			  updateLevelForReply(int boardId, int num, int lev, int replyTab);
	//부모노드를 찾는 다른 방법
	//order를 키로 생각해서 정렬한 후, order로 전달받은 인자로 그 REPLY_ORDER값을 추출
	//그 후에 이의 dto객체 추출
	public Set<Integer>   getOrderedReplyReply(int boardId, int num);
	//인자로 받은 order로 REPLY_ORDER값 찾기
	public int			  getReplyOrderValue(int boardId, int num, int order);
	//찾은 REPLY_ORDER값으로 부모노드 찾기
	public ReplyDto       getReparentReplyNode(int boardId, int num, int order);
}
