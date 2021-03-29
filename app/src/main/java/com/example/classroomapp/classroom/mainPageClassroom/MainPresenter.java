package com.example.classroomapp.classroom.mainPageClassroom;

import android.content.Context;

import com.example.classroomapp.data.classroomData.ClassroomRepository;
import com.example.classroomapp.model.ClassroomModel;

import java.util.List;
import java.util.logging.Handler;

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    MainContract.Repository repository;


    public MainPresenter(MainContract.View callback, Context context) {
        this.view = callback;
        this.repository =  new ClassroomRepository(context);

    }

    @Override
    public List<ClassroomModel> loadAllDataInRecyclerView() {
      return repository.getListFromDataBase();
    }

    @Override
    public void alertToDeleteClass(int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                repository.deleteClass(position);
                view.onSuccess("Class has been deleted");
            }
        }).start();
    }

    @Override
    public List<ClassroomModel> orderItemsDataByClassASC() {
        return repository.orderItemsByClassName();
    }

    @Override
    public List<ClassroomModel> orderItemsDataByClassDESC() {
        return repository.orderItemsByClassNameDESC();
    }

    @Override
    public List<ClassroomModel> orderItemsDataByCabinetASC() {
        return repository.orderItemsByCabinetACS();
    }

    @Override
    public List<ClassroomModel> orderItemsDataByCabinetDESC() {
        return repository.orderItemsByCabinetDESC();
    }


}
