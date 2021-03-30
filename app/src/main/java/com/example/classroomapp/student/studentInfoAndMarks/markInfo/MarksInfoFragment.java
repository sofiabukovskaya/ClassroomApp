package com.example.classroomapp.student.studentInfoAndMarks.markInfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.classroomapp.R;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.student.addStudent.AddStudentActivity;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsActivity;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark.AddMarkActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MarksInfoFragment extends Fragment implements MarkInfoContract.View, SearchView.OnQueryTextListener {

    private MarkInfoContract.Presenter markPresenter;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerViewMarks;
    private Integer idStudent;
    private MarkAdapter markAdapter;

    public MarksInfoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        idStudent = getArguments().getInt("studentId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_marks_info_fragent, container, false);
        floatingActionButton = rootView.findViewById(R.id.floatingActionButtonMarks);
        recyclerViewMarks = rootView.findViewById(R.id.recyclerViewMarks);
        markPresenter = new MarkInfoPresenter(this, getContext());
        markAdapter = new MarkAdapter(getContext(),recyclerViewMarks, markPresenter.loadAllDataInRecyclerView(idStudent));
        recyclerViewMarks.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMarks.setAdapter(markAdapter);
        setHasOptionsMenu(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMarkActivity.class);
                intent.putExtra("studentId",idStudent);
                startActivity(intent);
            }
        });
        return rootView;
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_for_mark, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Search mark by subject name");
        searchView.setOnQueryTextListener(this);
    }



    @Override
    public void onSuccess(String messageAlert) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<MarkModel> filteredNewList = new ArrayList<>();
        markPresenter.loadAllDataInRecyclerView(idStudent);
        for(MarkModel markModel: markPresenter.loadAllDataInRecyclerView(idStudent)) {
            String classModelName = markModel.getSubjectName().toLowerCase();
            if(classModelName.contains(newText)) {
                filteredNewList.add(markModel);
            }
        }
        markAdapter.setFilter(filteredNewList);
        return true;
    }
}