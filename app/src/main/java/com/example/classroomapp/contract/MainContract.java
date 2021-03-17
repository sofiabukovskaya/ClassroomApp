package com.example.classroomapp.contract;

import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public interface MainContract {

    interface View {
        void onSuccess(String messageAlert);
        void updateListAfterDeleting(int position);
        void showCurrentClass(ClassroomModel currentClass);
        void editCurrentClassShow(ClassroomModel currentClass);
    }

    interface Presenter{
        void addButtonWasClicked(String name, int room, int floor);
        void editButtonWasClicked(Integer id,String name, int room, int floor);
        List<ClassroomModel> loadAllDataInRecyclerView();
        void alertToDeleteClass(int position);
        void selectCurrentClass(int position);
        void editCurrentClass(int position);
    }

    interface Repository{
        List<ClassroomModel> getListFromDataBase();
        long addClasses(ClassroomModel classroomModel);
        void deleteClass(int position);
        long updateClass(ClassroomModel classroomModel);
       ClassroomModel getCurrentClass(int position);

    }
}
