package com.example.classroomapp.student.studentInfoAndMarks.markInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroomapp.R;
import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.mainPageStudent.StudentAdapter;

import java.text.DateFormat;
import java.util.List;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.ViewHolder> {

    private Context context;
    private RecyclerView recyclerView;
    private List<MarkModel> markModelList;

    public MarkAdapter(Context context, RecyclerView recyclerView, List<MarkModel> markModelList) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.markModelList = markModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mark_row, parent, false);
        return new MarkAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        MarkModel markModel  = markModelList.get(position);
        holder.markId.setText(Integer.toString(position +1));
        holder.subjectName.setText(markModel.getSubjectName());
        holder.dateTextView.setText(String.valueOf(markModel.getMarkDate()));
        holder.markValue.setText(Integer.toString(markModel.getMark()));
        holder.deleteMark.setImageResource(R.drawable.delete_forever_24);

    }

    @Override
    public int getItemCount() {
        return markModelList == null ? 0 : markModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView markId, subjectName, dateTextView, markValue;
        private ImageButton deleteMark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            markId = itemView.findViewById(R.id.mark_ID);
            subjectName = itemView.findViewById(R.id.subject_Name);
            dateTextView = itemView.findViewById(R.id.date_TextView);
            markValue = itemView.findViewById(R.id.mark_Mark);
            deleteMark = itemView.findViewById(R.id.buttonDeleteMark);
        }
    }
}
