package com.example.classroomapp.classroom.mainPageClassroom;

import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public interface MainContract {

    interface View {
        void onSuccess(String messageAlert);
    }

    interface Presenter{
        List<ClassroomModel> loadAllDataInRecyclerView();
        void alertToDeleteClass(int position);
    }

    interface Repository{
        List<ClassroomModel> getListFromDataBase();
        void deleteClass(int position);
    }
}
