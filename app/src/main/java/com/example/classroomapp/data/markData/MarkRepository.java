package com.example.classroomapp.data.markData;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.classroomapp.data.classroomData.DataBaseClassroom;
import com.example.classroomapp.data.studentData.DataBaseStudent;
import com.example.classroomapp.model.MarkModel;
import com.example.classroomapp.model.StudentModel;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.MarkInfoContract;
import com.example.classroomapp.student.studentInfoAndMarks.markInfo.addMark.AddMarkContract;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MarkRepository implements AddMarkContract.Repository, MarkInfoContract.Repository {
    private DataBaseMark dataBaseMark;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<MarkModel> markModelArrayList;

    public MarkRepository(Context context) {
        this.dataBaseMark = new DataBaseMark(context.getApplicationContext());
    }


    @Override
    public long addMark(MarkModel markModel) {
        sqLiteDatabase = dataBaseMark.getWritableDatabase();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseMark.COLUMN_SUBJECT_NAME, markModel.getSubjectName());
        contentValues.put(DataBaseMark.COLUMN_MARK, markModel.getMark());
        contentValues.put(DataBaseMark.COLUMN_DATE_MARK, markModel.getMarkDate());
        contentValues.put(DataBaseMark.COLUMN_TYPE_OF_MARK, markModel.getTypeOfMark());
        contentValues.put(DataBaseMark.COLUMN_STUDENT_ID, markModel.getStudentId());
        return sqLiteDatabase.insert(DataBaseMark.TABLE_NAME, null, contentValues);
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(dateFormat/*"yyyy/MM/dd HH:mm:ss"*/);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<MarkModel> getMarksForCurrentStudent(int studentId) {
        markModelArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + DataBaseMark.TABLE_NAME +
                " INNER JOIN " + DataBaseStudent.TABLE_NAME +
                " ON " + DataBaseStudent.TABLE_NAME + "." + DataBaseStudent.COLUMN_ID + "=" + DataBaseMark.TABLE_NAME + "." + DataBaseMark.COLUMN_STUDENT_ID +
                " WHERE " + DataBaseMark.TABLE_NAME + "." + DataBaseMark.COLUMN_STUDENT_ID + "=" + studentId;
        String countQuery = "SELECT "+ DataBaseMark.COLUMN_ID +" FROM " + DataBaseMark.TABLE_NAME + " WHERE " + DataBaseMark.TABLE_NAME + "." + DataBaseMark.COLUMN_STUDENT_ID + "=" + studentId ;
        SQLiteDatabase db = dataBaseMark.getReadableDatabase();
        Cursor cursorCount = db.rawQuery(countQuery, null);

        sqLiteDatabase = dataBaseMark.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DataBaseMark.COLUMN_ID));
            String subjectName = cursor.getString(cursor.getColumnIndex(DataBaseMark.COLUMN_SUBJECT_NAME));
            int mark = cursor.getInt(cursor.getColumnIndex(DataBaseMark.COLUMN_MARK));
            String markDate = cursor.getString(cursor.getColumnIndex(DataBaseMark.COLUMN_DATE_MARK));
            String typeOfMark = cursor.getString(cursor.getColumnIndex(DataBaseMark.COLUMN_TYPE_OF_MARK));
            int studentID = cursor.getInt(cursor.getColumnIndex(DataBaseMark.COLUMN_STUDENT_ID));
            markModelArrayList.add(new MarkModel(id, subjectName, mark, markDate, typeOfMark, studentID));
        }
        cursor.close();
        cursorCount.close();
        return markModelArrayList;
    }

    @Override
    public void deleteMark(int position) {

    }
}
