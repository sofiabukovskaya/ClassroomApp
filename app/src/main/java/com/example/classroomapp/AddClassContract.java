package com.example.classroomapp;

public interface AddClassContract {
    interface View{
        void onSuccess(String messageAlert);
        void onError(String messageErrorAlert);
    }

    interface Presenter{
        void addButtonWasClicked(String name, int room, int floor);
    }
}
