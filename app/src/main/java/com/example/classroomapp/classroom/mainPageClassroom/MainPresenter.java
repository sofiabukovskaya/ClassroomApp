package com.example.classroomapp.classroom.mainPageClassroom;

import android.content.Context;

import com.example.classroomapp.data.classroomData.ClassroomRepository;
import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    MainContract.Repository repository;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
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

}
