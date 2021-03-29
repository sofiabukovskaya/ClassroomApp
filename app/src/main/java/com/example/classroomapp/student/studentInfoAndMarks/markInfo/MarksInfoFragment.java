package com.example.classroomapp.student.studentInfoAndMarks.markInfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroomapp.R;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark.AddMarkActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MarksInfoFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerViewMarks;
    public MarksInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_marks_info_fragent, container, false);
        floatingActionButton = rootView.findViewById(R.id.floatingActionButtonMarks);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddMarkActivity.class));
            }
        });
        return rootView;
    }

}