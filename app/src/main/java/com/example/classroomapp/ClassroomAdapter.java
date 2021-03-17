package com.example.classroomapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroomapp.contract.MainContract;
import com.example.classroomapp.model.ClassroomModel;

import java.util.List;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder>  {

    private MainContract.Presenter presenter;
    private RecyclerView recyclerView;
    private List<ClassroomModel> classroomModelList;
    private Context context;
    private Integer positionId;

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
        positionId = classroomModelList.get(position).getId();

        holder.classroomId.setText(classroom.getId() + " ");
        holder.classroomName.setText(classroom.getClassroomName());
        holder.classroomNumberCabinet.setText(classroom.getClassroomRoomNumber() + " ");
        holder.deleteClass.setImageResource(R.drawable.delete_forever_24);
        holder.editClass.setImageResource(R.drawable.edit_);
        holder.deleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.alertToDeleteClass(positionId);
                classroomModelList.remove(position);
            }
        });
        holder.editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionId = classroomModelList.get(position).getId();
//                showCurrentPresenter.selectCurrentClass(positionId);
            }
        });

        holder.classroomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectCurrentClass(positionId);
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

}
