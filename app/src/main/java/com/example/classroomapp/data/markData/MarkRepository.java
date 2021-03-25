package com.example.classroomapp.data.markData;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.classroomapp.data.studentData.DataBaseStudent;
import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.MarkInfoContract;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark.AddMarkContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MarkRepository implements AddMarkContract.Repository, MarkInfoContract.Repository {
    private DataBaseMark dataBaseMark;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<MarkModel> markModelArrayList;

    public MarkRepository(Context context) {
        this.dataBaseMark = dataBaseMark;
    }


    @Override
    public long addMark(MarkModel markModel) {
        sqLiteDatabase = dataBaseMark.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseMark.COLUMN_SUBJECT_NAME, markModel.getSubjectName());
        contentValues.put(DataBaseMark.COLUMN_MARK, markModel.getMark());
        contentValues.put(DataBaseMark.COLUMN_DATE_MARK, dateFormat.format(markModel.getMarkDate()));
        contentValues.put(DataBaseMark.COLUMN_TYPE_OF_MARK, markModel.getTypeOfMark());
        contentValues.put(DataBaseMark.COLUMN_STUDENT_ID, markModel.getStudentId());
        return sqLiteDatabase.insert(DataBaseMark.TABLE_NAME, null, contentValues);
    }

    @Override
    public List<MarkModel> getMarksForCurrentStudent(int studentId) {
        return null;
    }

    @Override
    public void deleteMark(int position) {

    }
}
