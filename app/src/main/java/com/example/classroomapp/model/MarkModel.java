package com.example.classroomapp.model;

import java.util.Date;

public class MarkModel {
     private Integer mark_ID;
     private String subjectName;
     private Integer mark;
     private Date markDate;
     private String typeOfMark;
     private Integer studentId;

    public MarkModel(Integer mark_ID, String subjectName, Integer mark, Date markDate, String typeOfMark, Integer studentId) {
        this.mark_ID = mark_ID;
        this.subjectName = subjectName;
        this.mark = mark;
        this.markDate = markDate;
        this.typeOfMark = typeOfMark;
        this.studentId = studentId;
    }

    public Integer getMark_ID() {
        return mark_ID;
    }

    public void setMark_ID(Integer mark_ID) {
        this.mark_ID = mark_ID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }

    public String getTypeOfMark() {
        return typeOfMark;
    }

    public void setTypeOfMark(String typeOfMark) {
        this.typeOfMark = typeOfMark;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
