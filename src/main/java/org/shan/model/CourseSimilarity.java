package org.shan.model;

public class CourseSimilarity {
	private int courseID1;
	private int courseID2;
	private double similarity;

	public CourseSimilarity() {
		super();
	}

	public CourseSimilarity(int courseID1, int courseID2, double similarity) {
		super();
		this.courseID1 = courseID1;
		this.courseID2 = courseID2;
		this.similarity = similarity;
	}

	public int getCourseID1() {
		return courseID1;
	}

	public void setCourseID1(int courseID1) {
		this.courseID1 = courseID1;
	}

	public int getCourseID2() {
		return courseID2;
	}

	public void setCourseID2(int courseID2) {
		this.courseID2 = courseID2;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

}
