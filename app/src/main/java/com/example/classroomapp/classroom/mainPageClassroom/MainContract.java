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
        List<ClassroomModel> orderItemsDataByClassASC();
        List<ClassroomModel> orderItemsDataByClassDESC();
        List<ClassroomModel> orderItemsDataByCabinetASC();
        List<ClassroomModel> orderItemsDataByCabinetDESC();
    }

    interface Repository{
        List<ClassroomModel> getListFromDataBase();
        void deleteClass(int position);
        List<ClassroomModel> orderItemsByClassName();
        List<ClassroomModel> orderItemsByClassNameDESC();
        List<ClassroomModel> orderItemsByCabinetACS();
        List<ClassroomModel> orderItemsByCabinetDESC();
    }
}
