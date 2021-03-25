package com.example.classroomapp.classroom.mainPageClassroom;

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
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsActivity;

import java.util.ArrayList;
import java.util.List;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder>  {

    private RecyclerView recyclerView;
    private List<ClassroomModel> classroomModelList;
    private Context context;
    private CallBackPosition callBackPosition;
    private Integer positionId;

    public interface CallBackPosition{
        void deleteClassGetPosition(int position);
        void showCurrentClass(ClassroomModel classroomModel);
        void editCurrentClass(ClassroomModel classroomModel);
    }

    public ClassroomAdapter(Context context, List<ClassroomModel> classroomModelList, RecyclerView recyclerView, CallBackPosition callBackPosition){
        this.context = context;
        this.classroomModelList = classroomModelList;
        this.recyclerView = recyclerView;
        this.callBackPosition = callBackPosition;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.classroom_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassroomModel classroom = classroomModelList.get(position);
        positionId = classroomModelList.get(position).getId();

        holder.classroomId.setText(Integer.toString(classroom.getId()));
        holder.classroomName.setText(classroom.getClassroomName());
        holder.classroomNumberCabinet.setText(Integer.toString(classroom.getClassroomRoomNumber()));
        holder.deleteClass.setImageResource(R.drawable.delete_forever_24);
        holder.editClass.setImageResource(R.drawable.edit_);
        holder.deleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPosition.deleteClassGetPosition(positionId);
                classroomModelList.remove(position);
            }
        });
        holder.editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPosition.editCurrentClass(classroom);
                notifyDataSetChanged();
            }
        });

        holder.classroomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackPosition.showCurrentClass(classroom);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classroomModelList == null ? 0 : classroomModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView classroomId,classroomName, classroomNumberCabinet;
        ImageButton editClass, deleteClass ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classroomId = itemView.findViewById(R.id.classroom_id);
            classroomName = itemView.findViewById(R.id.classroom_name);
            classroomNumberCabinet = itemView.findViewById(R.id.classroom_number);
            editClass = itemView.findViewById(R.id.buttonEditClass);
            deleteClass = itemView.findViewById(R.id.buttonDeleteClass);
        }
    }
    public void setFilter(ArrayList<ClassroomModel> filteredNewList) {
            classroomModelList = new ArrayList<>();
            classroomModelList.addAll(filteredNewList);
            notifyDataSetChanged();
    }
}
