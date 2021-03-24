package com.example.classroomapp.student.studentInfoAndMarks.markInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.classroomapp.R;

public class MarksInfoFragment extends Fragment {


    public MarksInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_marks_info_fragent, container, false);
    }
}