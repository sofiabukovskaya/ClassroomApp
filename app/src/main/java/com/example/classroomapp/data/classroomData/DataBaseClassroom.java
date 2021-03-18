package com.example.classroomapp.data.classroomData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseClassroom extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="classrooms_database.db";
    public static final String TABLE_NAME="classroom";

    //columns name
    public static final String COLUMN_ID="ID";
    public static final String COLUMN_NAME="Name";
    public static final String COLUMN_ROOM_NUMBER="Room_number";
    public static final String COLUMN_FLOOR_NUMBER="Floor_number";
    public static final String COLUMN_STUDENTS_COUNT="Students_count";

    public DataBaseClassroom(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ROOM_NUMBER + " INTEGER, " +
                COLUMN_FLOOR_NUMBER + " INTEGER, " +
                COLUMN_STUDENTS_COUNT + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
