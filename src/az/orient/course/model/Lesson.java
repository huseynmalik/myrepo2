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
public class Lesson extends CourseModel{

    private String lessonName;
    private Integer lessonTime;
    private Double lessonPrice;

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(Integer lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Double getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(Double lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    @Override
    public String toString() {
        return "Lesson [lessonName=" + lessonName + ", lessonTime=" + lessonTime + ", lessonPrice=" + lessonPrice + "]";
    }

}
