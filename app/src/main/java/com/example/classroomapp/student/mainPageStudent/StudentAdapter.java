package com.example.classroomapp.student.mainPageStudent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroomapp.R;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.StudentModel;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private Context context;
    private RecyclerView recyclerView;
    private List<StudentModel> studentModelList;
    Integer positionIdStudent;

    public StudentAdapter(Context context, List<StudentModel> studentModelList, RecyclerView recyclerView) {
        this.context = context;
        this.studentModelList = studentModelList;
        this.recyclerView = recyclerView;
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
        positionIdStudent = studentModelList.get(position).getStudentId();

        holder.studentFirstName.setText(studentModel.getFirstName());
        holder.studentId.setText(studentModel.getStudentId() + " ");
        holder.studentSecondName.setText(studentModel.getLastName() + " ");
        holder.deleteStudent.setImageResource(R.drawable.delete_forever_24);
        holder.editStudent.setImageResource(R.drawable.edit_);
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
}
