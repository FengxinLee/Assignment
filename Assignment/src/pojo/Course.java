package  pojo;

import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	private int courseId;

	private String courseDes;

	private String courseName;

	public Course() {
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseDes() {
		return this.courseDes;
	}

	public void setCourseDes(String courseDes) {
		this.courseDes = courseDes;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseDes=" + courseDes + ", courseName=" + courseName + "]";
	}

}