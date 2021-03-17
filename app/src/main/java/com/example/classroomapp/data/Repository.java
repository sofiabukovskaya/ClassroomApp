package com.example.classroomapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.classroomapp.contract.MainContract;
import com.example.classroomapp.model.ClassroomModel;

import java.util.ArrayList;
import java.util.List;

public class Repository implements MainContract.Repository {

    private DataBaseClassroom dataBaseClassroom;
    private SQLiteDatabase sqLiteDatabase;
    private Integer positionId;
    private ArrayList<ClassroomModel> classrooms;


    public Repository(Context context) {
        dataBaseClassroom = new DataBaseClassroom(context.getApplicationContext());
    }

    private Cursor getAllEntries(){
        String query = "SELECT * FROM " + DataBaseClassroom.TABLE_NAME;
        sqLiteDatabase = dataBaseClassroom.getReadableDatabase();

        Cursor cursor = null;
        if(sqLiteDatabase != null) {
            cursor = sqLiteDatabase.rawQuery(query, null);
        }
        return cursor;
    }


    @Override
    public List<ClassroomModel> getListFromDataBase() {
        classrooms = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DataBaseClassroom.COLUMN_NAME));
            int room = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_ROOM_NUMBER));
            int floor = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_FLOOR_NUMBER));
            int student_count = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_STUDENTS_COUNT));
            classrooms.add(new ClassroomModel(id, name, room, floor, student_count));
        }
        cursor.close();
        return classrooms;
    }

    @Override
    public long addClasses(ClassroomModel classroomModel) {

        sqLiteDatabase = dataBaseClassroom.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseClassroom.COLUMN_NAME, classroomModel.getClassroomName());
        contentValues.put(DataBaseClassroom.COLUMN_ROOM_NUMBER, classroomModel.getClassroomRoomNumber());
        contentValues.put(DataBaseClassroom.COLUMN_FLOOR_NUMBER, classroomModel.getClassroomFloor());
        contentValues.put(DataBaseClassroom.COLUMN_STUDENTS_COUNT, classroomModel.getStudentCount());
        return sqLiteDatabase.insert(DataBaseClassroom.TABLE_NAME, null, contentValues);

    }


    @Override
    public void deleteClass(int position) {
        sqLiteDatabase = dataBaseClassroom.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + DataBaseClassroom.TABLE_NAME + " WHERE " +
                DataBaseClassroom.COLUMN_ID + " = " + position + ";");
        sqLiteDatabase.execSQL("UPDATE " + DataBaseClassroom.TABLE_NAME + " SET " + DataBaseClassroom.COLUMN_ID + " = " +
                DataBaseClassroom.COLUMN_ID + " -1 " + " WHERE " + DataBaseClassroom.COLUMN_ID + " > " + position + ";");
        sqLiteDatabase.close();
    }

    @Override
    public long updateClass(ClassroomModel classroomModel) {
        sqLiteDatabase = dataBaseClassroom.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseClassroom.COLUMN_NAME, classroomModel.getClassroomName());
        contentValues.put(DataBaseClassroom.COLUMN_ROOM_NUMBER, classroomModel.getClassroomRoomNumber());
        contentValues.put(DataBaseClassroom.COLUMN_FLOOR_NUMBER, classroomModel.getClassroomFloor());
        contentValues.put(DataBaseClassroom.COLUMN_STUDENTS_COUNT, classroomModel.getStudentCount());
        return sqLiteDatabase.update(DataBaseClassroom.TABLE_NAME, contentValues,DataBaseClassroom.COLUMN_ID + " = " +  classroomModel.getId(),null);

    }

    @Override
    public ClassroomModel getCurrentClass(int position) {
        ClassroomModel classroomModel = null;
        positionId = position;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DataBaseClassroom.TABLE_NAME + " WHERE " + DataBaseClassroom.COLUMN_ID +
                " = " + positionId,
                null);
        if(cursor.moveToFirst()){
            int idClass = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_ID));
            String className = cursor.getString(cursor.getColumnIndex(DataBaseClassroom.COLUMN_NAME));
            int classroomNumber = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_ROOM_NUMBER));
            int classFloor = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_FLOOR_NUMBER));
            int studentsNumber = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_STUDENTS_COUNT));
            classroomModel = new ClassroomModel(idClass, className, classroomNumber,classFloor,studentsNumber);
        }
        cursor.close();
        return classroomModel;
    }

}
