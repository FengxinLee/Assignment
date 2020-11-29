package pojo;

import java.io.Serializable;


public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int answerId;

	private String answerContent;


	private int answerIswrite;


	private int answerMark;


	private int assignId;


	private String userId;

	public Answer() {
	}

	public int getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswerContent() {
		return this.answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public int getAnswerIswrite() {
		return this.answerIswrite;
	}

	public void setAnswerIswrite(int answerIswrite) {
		this.answerIswrite = answerIswrite;
	}

	public int getAnswerMark() {
		return this.answerMark;
	}

	public void setAnswerMark(int answerMark) {
		this.answerMark = answerMark;
	}

	public int getAssignId() {
		return this.assignId;
	}

	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerContent=" + answerContent + ", answerIswrite=" + answerIswrite
				+ ", answerMark=" + answerMark + ", assignId=" + assignId + ", userId=" + userId + "]";
	}

}