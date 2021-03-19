package com.example.classroomapp.student.addStudent;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GenderSpinnerAdapter extends ArrayAdapter<String> {

    public GenderSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
