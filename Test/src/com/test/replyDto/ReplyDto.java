package com.test.replyDto;

//답글에 대한 dto객체
public class ReplyDto {
	/*
	 * BOARD_ID, REPLY_ORDER, NO, LEVEL, LEV_SEQ, REPLYTAB, ID, CONTENT, REGDATE, LIKENO, BAN
	 * 
	 * 
	 * */
	private int 	boardId;
	private int 	replyOrder;
	private int 	num;
	private int 	lev;
	private int   	levSeq;
	private String     depth;
	private int 	replyTab;
	private String 	id;
	private String  content;
	private String  regDate;
	private int     likeNo;
	private int     ban;
	
	public ReplyDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReplyDto(int boardId, int replyOrder, int num, int lev, int levSeq, String depth, int replyTab, String id, String content,
			String regDate, int likeNo, int ban) {
		super();
		this.boardId = boardId;
		this.replyOrder = replyOrder;
		this.num = num;
		this.lev = lev;
		this.levSeq = levSeq;
		this.depth = depth;
		this.replyTab = replyTab;
		this.id = id;
		this.content = content;
		this.regDate = regDate;
		this.likeNo = likeNo;
		this.ban = ban;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getReplyOrder() {
		return replyOrder;
	}

	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getLevSeq() {
		return levSeq;
	}

	public void setLevSeq(int levSeq) {
		this.levSeq = levSeq;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public int getReplyTab() {
		return replyTab;
	}

	public void setReplyTab(int replyTab) {
		this.replyTab = replyTab;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public int getBan() {
		return ban;
	}

	public void setBan(int ban) {
		this.ban = ban;
	}

	@Override
	public String toString() {
		return "ReplyDto [boardId=" + boardId + ", replyOrder=" + replyOrder + ", num=" + num + ", lev=" + lev
				+ ", levSeq=" + levSeq + ", depth=" + depth + ", replyTab=" + replyTab + ", id=" + id + ", content="
				+ content + ", regDate=" + regDate + ", likeNo=" + likeNo + ", ban=" + ban + "]";
	}

	
	
	
	
}
