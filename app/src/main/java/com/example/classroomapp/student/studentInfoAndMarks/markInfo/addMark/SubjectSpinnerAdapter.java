package com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class SubjectSpinnerAdapter extends ArrayAdapter<String> {
    public SubjectSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
