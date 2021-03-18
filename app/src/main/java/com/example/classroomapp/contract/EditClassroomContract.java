package com.example.classroomapp.contract;

import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public interface EditClassroomContract {

    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        void editButtonWasClicked(Integer id,String name, int room, int floor);
    }

    interface Repository{
        long updateClass(ClassroomModel classroomModel);
    }
}
