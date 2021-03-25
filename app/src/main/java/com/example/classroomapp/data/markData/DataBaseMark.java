package com.example.classroomapp.data.markData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseMark extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="classrooms_database.db";
    static final String TABLE_NAME="marks";

    //columns name
    public static final String COLUMN_ID="mark_ID";
    public static final String COLUMN_SUBJECT_NAME="Subject_name";
    public static final String COLUMN_MARK="Mark";
    public static final String COLUMN_DATE_MARK="Mark_date";
    public static final String COLUMN_TYPE_OF_MARK="Type_mark";
    public static final String COLUMN_STUDENT_ID="Student_id";

    public DataBaseMark(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_SUBJECT_NAME + " TEXT, " +
                COLUMN_MARK + " INTEGER, " +
                COLUMN_DATE_MARK + " DATE, " +
                COLUMN_TYPE_OF_MARK + " TEXT, " +
                COLUMN_STUDENT_ID + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
