/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.orient.course.model;

/**
 *
 * @author Ideas.az
 */
public abstract class CourseModel {

	private Long r;
	private Long id;
	private int  status;

	public Long getR() {
		return r;
	}

	public void setR(Long r) {
		this.r = r;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
}
