package com.example.classroomapp;

public class AddClassPresenter implements AddClassContract.Presenter{
    AddClassContract.View view;
    MainContract.Repository repository;
    ClassroomModel classroomModel;

    @Override
    public void addButtonWasClicked(String name, int room, int floor) {

    }
}
