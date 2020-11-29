package pojo;

import java.io.Serializable;


public class Assign implements Serializable {
	private static final long serialVersionUID = 1L;

	private int assignId;

	private String assignAnswer;

	private String assignContent;

	private int courseId;

	public Assign() {
	}

	public int getAssignId() {
		return this.assignId;
	}

	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}

	public String getAssignAnswer() {
		return this.assignAnswer;
	}

	public void setAssignAnswer(String assignAnswer) {
		this.assignAnswer = assignAnswer;
	}

	public String getAssignContent() {
		return this.assignContent;
	}

	public void setAssignContent(String assignContent) {
		this.assignContent = assignContent;
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Assign [assignId=" + assignId + ", assignAnswer=" + assignAnswer + ", assignContent=" + assignContent
				+ ", courseId=" + courseId + "]";
	}

}