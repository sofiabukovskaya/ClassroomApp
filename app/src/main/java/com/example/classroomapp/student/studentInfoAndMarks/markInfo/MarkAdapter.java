package com.example.classroomapp.student.studentInfoAndMarks.markInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroomapp.R;
import com.example.classroomapp.model.ClassroomModel;
import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.mainPageStudent.StudentAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.ViewHolder> {

    private Context context;
    private RecyclerView recyclerView;
    private List<MarkModel> markModelList;
    private CallBackPositionMark callBackPosition;
    Integer currentMarkID;

    public interface CallBackPositionMark{
        void deleteStudentGetPosition(int position);
    }
    public MarkAdapter(Context context, RecyclerView recyclerView, List<MarkModel> markModelList, CallBackPositionMark callBackPosition) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.markModelList = markModelList;
        this.callBackPosition = callBackPosition;
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

        MarkModel markModel  = markModelList.get(position);

        holder.markId.setText(Integer.toString(position +1));
        holder.subjectName.setText(markModel.getSubjectName());
        holder.dateTextView.setText(String.valueOf(markModel.getMarkDate()));
        holder.markValue.setText(Integer.toString(markModel.getMark()));
        holder.deleteMark.setImageResource(R.drawable.delete_forever_24);
        holder.deleteMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMarkID = markModel.getMark_ID();
                callBackPosition.deleteStudentGetPosition(currentMarkID);
                markModelList.remove(position);
            }
        });
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

    public void setFilter(ArrayList<MarkModel> filteredNewList) {
        markModelList = new ArrayList<>();
        markModelList.addAll(filteredNewList);
        notifyDataSetChanged();
    }
}
