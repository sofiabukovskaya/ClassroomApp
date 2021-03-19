package com.example.classroomapp.student.addStudent;

import com.example.classroomapp.model.ClassroomModel;

public interface AddStudentContract {

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
