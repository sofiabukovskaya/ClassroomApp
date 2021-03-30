package com.example.classroomapp.data.studentData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.classroomapp.data.classroomData.DataBaseClassroom;

public class DataBaseStudent extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="classrooms_database.db";
    public static final String TABLE_NAME="students";


    //columns name
    public static final String COLUMN_ID="ID";
    public static final String COLUMN_NAME="First_name";
    public static final String COLUMN_LAST_NAME="Last_name";
    public static final String COLUMN_MIDDLE_NAME="Middle_name";
    public static final String COLUMN_STUDENT_GENDER="Gender";
    public static final String COLUMN_STUDENT_AGE="Age";
    public static final String COLUMN_CLASSROOM_ID="Classroom_id";

    public DataBaseStudent(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LAST_NAME+ " TEXT, " +
                COLUMN_MIDDLE_NAME + " TEXT, " +
                COLUMN_STUDENT_GENDER + " TEXT, " +
                COLUMN_STUDENT_AGE + " INTEGER, " +
                COLUMN_CLASSROOM_ID + " INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
