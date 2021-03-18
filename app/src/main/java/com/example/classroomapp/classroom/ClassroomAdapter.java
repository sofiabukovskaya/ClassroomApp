package com.example.classroomapp.classroom;

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
import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder>  {

    private RecyclerView recyclerView;
    private List<ClassroomModel> classroomModelList;
    private Context context;
    private CallBackPosition callBackPosition;
    private Integer positionId;

    public interface CallBackPosition{
        void deleteClassGetPosition(int position);
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

        holder.classroomId.setText(classroom.getId() + " ");
        holder.classroomName.setText(classroom.getClassroomName());
        holder.classroomNumberCabinet.setText(classroom.getClassroomRoomNumber() + " ");
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
               passDataToEditClassroomActivity(classroom);
            }
        });

        holder.classroomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passDataToShowClassroomActivity(classroom);
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

    public void passDataToShowClassroomActivity(ClassroomModel classroom){
        Intent intent = new Intent(context, ShowCurrentClassActivity.class);
        intent.putExtra("classroomId",classroom.getId());
        intent.putExtra("classroomName", classroom.getClassroomName());
        intent.putExtra("classroomRoom", classroom.getClassroomRoomNumber());
        intent.putExtra("classroomFloor", classroom.getClassroomFloor());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void passDataToEditClassroomActivity(ClassroomModel classroom){
        Intent intent = new Intent(context, EditClassActivity.class);
        intent.putExtra("classroomId",classroom.getId());
        intent.putExtra("classroomName", classroom.getClassroomName());
        intent.putExtra("classroomRoom", classroom.getClassroomRoomNumber());
        intent.putExtra("classroomFloor", classroom.getClassroomFloor());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        notifyDataSetChanged();
    }
}
