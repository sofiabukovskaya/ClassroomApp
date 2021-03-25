package com.example.classroomapp.student.studentInfoAndMarks.studentInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.classroomapp.R;

public class StudentInfoFragment extends Fragment {
    TextView idStudentTextView, firstNameStudentTextView, secondNameStudentTextView, middleNameStudentTextView, genderStudentTextView, ageStudentTextView;
    Integer idStudent,ageStudent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
             assert getArguments() != null;
            idStudent = getArguments().getInt("studentId");
            ageStudent = getArguments().getInt("studentAge");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_info, container, false);
        idStudentTextView = rootView.findViewById(R.id.id_current_student);
        firstNameStudentTextView = rootView.findViewById(R.id.firstName_current_student);
        secondNameStudentTextView = rootView.findViewById(R.id.lastName_current_student);
        middleNameStudentTextView = rootView.findViewById(R.id.middleName_currentStudent);
        genderStudentTextView = rootView.findViewById(R.id.gender_currentStudent);
        ageStudentTextView = rootView.findViewById(R.id.age_currentStudent);
        setInformationAboutStudent();
        return rootView;
    }

    @SuppressLint("SetTextI18n")
    public void setInformationAboutStudent() {
        assert getArguments() != null;
        idStudentTextView.setText("# " +String.valueOf(idStudent));
        firstNameStudentTextView.setText(getArguments().getString("studentFirstName"));
        secondNameStudentTextView.setText(getArguments().getString("studentSecondName"));
        middleNameStudentTextView.setText(getArguments().getString("studentMiddleName"));
        genderStudentTextView.setText(getArguments().getString("studentGender"));
        ageStudentTextView.setText(String.valueOf(ageStudent) + " years old");

    }
}