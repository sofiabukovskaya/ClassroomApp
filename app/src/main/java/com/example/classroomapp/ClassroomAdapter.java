package com.example.classroomapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder> {

    MainContract.Presenter presenter;
    RecyclerView recyclerView;
    List<ClassroomModel> classroomModelList;
    Context context;

    public ClassroomAdapter(Context context, List<ClassroomModel> classroomModelList, RecyclerView recyclerView, MainContract.Presenter presenter){
        this.context = context;
        this.classroomModelList = classroomModelList;
        this.recyclerView = recyclerView;
        this.presenter  = presenter;
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
        holder.classroomId.setText(classroom.getId() + " ");
        holder.classroomName.setText(classroom.getClassroomName());
        holder.classroomNumberCabinet.setText(classroom.getClassroomRoomNumber() + " ");
        holder.deleteClass.setImageResource(R.drawable.delete_forever_24);
        holder.editClass.setImageResource(R.drawable.edit_);
        holder.deleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionId = classroomModelList.get(position).getId();
                presenter.alertToDeleteClass(positionId);
                classroomModelList.remove(position);
            }
        });
        holder.editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionId = classroomModelList.get(position).getId();
                presenter.editData(positionId);
            }
        });

        holder.classroomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return classroomModelList == null ? 0 : classroomModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView classroomId;
        TextView classroomName;
        TextView classroomNumberCabinet;
        ImageButton editClass;
        ImageButton deleteClass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classroomId = itemView.findViewById(R.id.classroom_id);
            classroomName = itemView.findViewById(R.id.classroom_name);
            classroomNumberCabinet = itemView.findViewById(R.id.classroom_number);
            editClass = itemView.findViewById(R.id.buttonEditClass);
            deleteClass = itemView.findViewById(R.id.buttonDeleteClass);
        }
    }

}
