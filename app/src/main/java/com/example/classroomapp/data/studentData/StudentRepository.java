package com.example.classroomapp.data.studentData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.classroomapp.data.classroomData.DataBaseClassroom;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.addStudent.AddStudentContract;
import com.example.classroomapp.student.editStudent.EditStudentContract;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsContract;
import com.example.classroomapp.model.ClassroomModel;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements CurrentClassAndStudentsContract.Repository, AddStudentContract.Repository, EditStudentContract.Repository {

    private DataBaseStudent dataBaseStudent;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<StudentModel> studentModelArrayList;

    String orderByFirstNameASC = DataBaseStudent.COLUMN_NAME + " ASC";
    String orderByFirstNameDESC = DataBaseStudent.COLUMN_NAME + " DESC";
    String orderBySecondNameASC = DataBaseStudent.COLUMN_LAST_NAME + " ASC";
    String orderBySecondNameDESC = DataBaseStudent.COLUMN_LAST_NAME + " DESC";
    public StudentRepository(Context context) {
            dataBaseStudent = new DataBaseStudent(context.getApplicationContext());
    }

    private Cursor getAllEntries(){
        String query = "SELECT * FROM " + DataBaseStudent.TABLE_NAME;
        sqLiteDatabase = dataBaseStudent.getReadableDatabase();

        Cursor cursor = null;
        if(sqLiteDatabase != null) {
            cursor = sqLiteDatabase.rawQuery(query, null);
        }
        return cursor;
    }

    private Cursor getAllSortedEntries(String orderBy){
        String query = "SELECT * FROM " + DataBaseClassroom.TABLE_NAME + " ORDER BY " + orderBy;
        sqLiteDatabase = dataBaseStudent.getReadableDatabase();
        Cursor cursor = null;
        if(sqLiteDatabase != null) {
            cursor = sqLiteDatabase.rawQuery(query, null);
        }
        return cursor;
    }
    @Override
    public List<StudentModel> getStudentsFromCurrentClass(int classroomId) {
        studentModelArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + DataBaseStudent.TABLE_NAME +
                " INNER JOIN " + DataBaseClassroom.TABLE_NAME +
                " ON " + DataBaseClassroom.TABLE_NAME + "." + DataBaseClassroom.COLUMN_ID + "=" + DataBaseStudent.TABLE_NAME + "." + DataBaseStudent.COLUMN_CLASSROOM_ID +
                " WHERE " + DataBaseStudent.TABLE_NAME + "." + DataBaseStudent.COLUMN_CLASSROOM_ID + "=" + classroomId;
        String countQuery = "SELECT "+ DataBaseStudent.COLUMN_ID +" FROM " + DataBaseStudent.TABLE_NAME + " WHERE " + DataBaseStudent.TABLE_NAME + "." + DataBaseStudent.COLUMN_CLASSROOM_ID + "=" + classroomId ;
        SQLiteDatabase db = dataBaseStudent.getReadableDatabase();
        Cursor cursorCount = db.rawQuery(countQuery, null);
        int count = cursorCount.getCount();

        sqLiteDatabase = dataBaseStudent.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseStudent.COLUMN_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_LAST_NAME));
                String middleName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_MIDDLE_NAME));
                String gender = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_STUDENT_GENDER));
                int ageStudent = cursor.getInt(cursor.getColumnIndex(DataBaseStudent.COLUMN_STUDENT_AGE));
                int classroomID = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_ID));
                studentModelArrayList.add(new StudentModel(id, firstName, lastName, middleName, gender, ageStudent, classroomID));
            }
        sqLiteDatabase.execSQL("UPDATE " + DataBaseClassroom.TABLE_NAME + " SET " + DataBaseClassroom.COLUMN_STUDENTS_COUNT + " = " +
               count + ";");
        cursor.close();
        cursorCount.close();
        return studentModelArrayList;
    }


    @Override
    public void deleteStudentFromClass(int position) {
        sqLiteDatabase = dataBaseStudent.getWritableDatabase();

        sqLiteDatabase.execSQL("DELETE FROM " + DataBaseStudent.TABLE_NAME + " WHERE " + DataBaseStudent.TABLE_NAME + "." +
                DataBaseStudent.COLUMN_ID + " = " + position + ";");
        sqLiteDatabase.execSQL("UPDATE " + DataBaseStudent.TABLE_NAME + " SET " +  DataBaseStudent.COLUMN_ID + " = " +
                DataBaseStudent.COLUMN_ID + " -1 " + " WHERE " + DataBaseStudent.COLUMN_ID + " > " + position + ";");
        sqLiteDatabase.close();
    }

    @Override
    public List<StudentModel> orderItemsByFirstName() {
        return null;
    }

    @Override
    public List<StudentModel> orderItemsByFirstNameDESC() {
        return null;
    }

    @Override
    public List<StudentModel> orderItemsBySecondNameACS() {
        return null;
    }

    @Override
    public List<StudentModel> orderItemsBySecondNameDESC() {
        return null;
    }

    @Override
    public long addStudent(StudentModel studentModel) {
        sqLiteDatabase = dataBaseStudent.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseStudent.COLUMN_NAME, studentModel.getFirstName());
            contentValues.put(DataBaseStudent.COLUMN_LAST_NAME, studentModel.getLastName());
            contentValues.put(DataBaseStudent.COLUMN_MIDDLE_NAME, studentModel.getMiddleName());
            contentValues.put(DataBaseStudent.COLUMN_STUDENT_GENDER, studentModel.getStudentGender());
            contentValues.put(DataBaseStudent.COLUMN_STUDENT_AGE, studentModel.getStudentAge());
            contentValues.put(DataBaseStudent.COLUMN_CLASSROOM_ID, studentModel.getClassroomId());

            return sqLiteDatabase.insert(DataBaseStudent.TABLE_NAME, null, contentValues);
    }


    @Override
    public long updateStudent(StudentModel studentModel) {
        sqLiteDatabase = dataBaseStudent.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseStudent.COLUMN_NAME, studentModel.getFirstName());
        contentValues.put(DataBaseStudent.COLUMN_LAST_NAME, studentModel.getLastName());
        contentValues.put(DataBaseStudent.COLUMN_MIDDLE_NAME, studentModel.getMiddleName());
        contentValues.put(DataBaseStudent.COLUMN_STUDENT_GENDER, studentModel.getStudentGender());
        contentValues.put(DataBaseStudent.COLUMN_STUDENT_AGE, studentModel.getStudentAge());
        return sqLiteDatabase.update(DataBaseStudent.TABLE_NAME, contentValues,DataBaseStudent.COLUMN_ID + " = " +  studentModel.getStudentId(),null);

    }
}
