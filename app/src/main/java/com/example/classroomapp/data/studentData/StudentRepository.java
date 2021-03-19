package com.example.classroomapp.data.studentData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.classroomapp.data.classroomData.DataBaseClassroom;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.mainPageStudent.CurrentClassAndStudentsContract;
import com.example.classroomapp.model.ClassroomModel;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements CurrentClassAndStudentsContract.Repository {

    private DataBaseStudent dataBaseStudent;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<StudentModel> studentModelArrayList;

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

    private Cursor getCurrentStudents(){
        String query = "SELECT * FROM " + DataBaseStudent.TABLE_NAME +
                " INNER JOIN " + DataBaseClassroom.TABLE_NAME +
                " ON " + DataBaseClassroom.COLUMN_ID + " = " + DataBaseStudent.COLUMN_CLASSROOM_ID;
        sqLiteDatabase = dataBaseStudent.getWritableDatabase();

        Cursor cursor = null;
        if(sqLiteDatabase != null) {
            cursor = sqLiteDatabase.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean IsTableEmpty() {
        Cursor cursor = getAllEntries();
        return !(cursor.getCount() > 0);
    }

    @Override
    public List<StudentModel> getAllStudentsFromDatabase() {
        studentModelArrayList = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DataBaseStudent.COLUMN_ID));
            String firstName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_LAST_NAME));
            String middleName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_MIDDLE_NAME));
            String gender = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_STUDENT_GENDER));
            int ageStudent = cursor.getInt(cursor.getColumnIndex(DataBaseStudent.COLUMN_STUDENT_AGE));
            int classroomId = cursor.getInt(cursor.getColumnIndex(DataBaseStudent.COLUMN_CLASSROOM_ID));
            studentModelArrayList.add(new StudentModel(id, firstName, lastName, middleName, gender, ageStudent, classroomId));
        }
        cursor.close();
        return studentModelArrayList;
    }
    @Override
    public List<StudentModel> getStudentsFromCurrentClass() {
        studentModelArrayList = new ArrayList<>();
        Cursor cursor = getCurrentStudents();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DataBaseStudent.COLUMN_ID));
            String firstName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_LAST_NAME));
            String middleName = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_MIDDLE_NAME));
            String gender = cursor.getString(cursor.getColumnIndex(DataBaseStudent.COLUMN_STUDENT_GENDER));
            int ageStudent = cursor.getInt(cursor.getColumnIndex(DataBaseStudent.COLUMN_STUDENT_AGE));
            int classroomId = cursor.getInt(cursor.getColumnIndex(DataBaseClassroom.COLUMN_ID));
            studentModelArrayList.add(new StudentModel(id, firstName, lastName, middleName, gender, ageStudent, classroomId));
        }
        cursor.close();
        return studentModelArrayList;
    }

    public long addStudent(StudentModel student){
            sqLiteDatabase = dataBaseStudent.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseStudent.COLUMN_NAME, student.getFirstName());
            contentValues.put(DataBaseStudent.COLUMN_LAST_NAME, student.getLastName());
            contentValues.put(DataBaseStudent.COLUMN_MIDDLE_NAME, student.getMiddleName());
            contentValues.put(DataBaseStudent.COLUMN_STUDENT_GENDER, student.getStudentGender());
            contentValues.put(DataBaseStudent.COLUMN_STUDENT_AGE, student.getStudentAge());
            contentValues.put(DataBaseStudent.COLUMN_CLASSROOM_ID, student.getClassroomId());
            return sqLiteDatabase.insert(DataBaseStudent.TABLE_NAME, null, contentValues);
        }

    @Override
    public void deleteStudentFromClass(int position) {

    }
}
