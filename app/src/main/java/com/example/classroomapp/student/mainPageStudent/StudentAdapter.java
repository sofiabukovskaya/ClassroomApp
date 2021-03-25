package com.example.classroomapp.student.mainPageStudent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroomapp.R;
import com.example.classroomapp.classroom.editClassroom.EditClassActivity;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.editStudent.EditStudentActivity;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private Context context;
    private RecyclerView recyclerView;
    private List<StudentModel> studentModelList;
    Integer positionIdStudent = 0;
    CallBackPosition callBackPosition;

    public interface CallBackPosition{
        void deleteStudentGetPosition(int position);
        void showCurrentStudent(StudentModel studentModel);
        void editStudentInformation(StudentModel studentModel);
    }

    public StudentAdapter(Context context, List<StudentModel> studentModelList, RecyclerView recyclerView, CallBackPosition callBackPosition) {
        this.context = context;
        this.studentModelList = studentModelList;
        this.recyclerView = recyclerView;
        this.callBackPosition = callBackPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_row, parent, false);
        return new StudentAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModel studentModel = studentModelList.get(position);

        holder.studentFirstName.setText(studentModel.getFirstName());
        holder.studentId.setText(Integer.toString(position + 1));
        holder.studentSecondName.setText(studentModel.getLastName());
        holder.deleteStudent.setImageResource(R.drawable.delete_forever_24);
        holder.editStudent.setImageResource(R.drawable.edit_);
        holder.deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionIdStudent = studentModel.getStudentId();
                callBackPosition.deleteStudentGetPosition(positionIdStudent);
                studentModelList.remove(position);
            }
        });
        holder.editStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPosition.editStudentInformation(studentModel);
                notifyDataSetChanged();
            }
        });
        holder.studentFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPosition.showCurrentStudent(studentModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentModelList == null ? 0 : studentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView studentId,studentFirstName, studentSecondName;
        ImageButton editStudent, deleteStudent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentId = itemView.findViewById(R.id.student_id);
            studentFirstName = itemView.findViewById(R.id.student_name);
            studentSecondName = itemView.findViewById(R.id.student_lastname);
            editStudent = itemView.findViewById(R.id.buttonEditStudent);
            deleteStudent = itemView.findViewById(R.id.buttonDeleteStudent);
        }
    }

    public void setFilter(ArrayList<StudentModel> filteredNewList) {
        studentModelList = new ArrayList<>();
        studentModelList.addAll(filteredNewList);
        notifyDataSetChanged();
    }
}
