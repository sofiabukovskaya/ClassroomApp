package com.example.classroomapp.model;

public class ClassroomModel {
    private int id;
    private String classroomName;
    private int classroomRoomNumber;
    private int classroomFloor;
    private int studentCount;

    public ClassroomModel(int id, String classroomName, int classroomRoomNumber, int classroomFloor, int studentCount) {
        this.id = id;
        this.classroomName = classroomName;
        this.classroomRoomNumber = classroomRoomNumber;
        this.classroomFloor = classroomFloor;
        this.studentCount = studentCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public int getClassroomRoomNumber() {
        return classroomRoomNumber;
    }

    public void setClassroomRoomNumber(int classroomRoomNumber) {
        this.classroomRoomNumber = classroomRoomNumber;
    }

    public int getClassroomFloor() {
        return classroomFloor;
    }

    public void setClassroomFloor(int classroomFloor) {
        this.classroomFloor = classroomFloor;
    }
}
