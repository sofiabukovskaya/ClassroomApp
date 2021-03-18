package com.example.classroomapp.contract;

import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public interface AddClassroomContract {

    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        void addButtonWasClicked(String name, int room, int floor);
    }

    interface Repository{
        long addClasses(ClassroomModel classroomModel);
    }
}
