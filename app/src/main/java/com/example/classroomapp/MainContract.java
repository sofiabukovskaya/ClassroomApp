package com.example.classroomapp;

import java.util.List;

public interface MainContract {

    interface View {
        void onSuccess(String messageAlert);
        void onError(String messageErrorAlert);
        void updateListAfterDeleting(int position);
        void updateData(int position);
    }

    interface Presenter{
        void addButtonWasClicked(String name, int room, int floor);
        List<ClassroomModel> loadAllDataInRecyclerView();
        void alertToDeleteClass(int position);
        void editData(int position);
    }

    interface Repository{
        List<ClassroomModel> getListFromDataBase();
        long addClasses(ClassroomModel classroomModel);
        void deleteClass(int position);
    }
}
