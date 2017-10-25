package spe.com.sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 10/25/2017.
 */

public class CourseHandler extends SQLiteOpenHelper {


    //all Static variables
    // Database version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "courseManager";
    //contact table name
    private static final String TABLE_COURSE = "course";
    // Contacts Table Columns Names
    private static final String KEY_COURSE_ID = "_course_id";
    private static final String KEY_NAME = "course_name";
    private static final String KEY_COURSE_ADMIN = "course_admin";

    public CourseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Creating Tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_COURSE + " (" + KEY_COURSE_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, " + KEY_COURSE_ADMIN + " TEXT )";
        db.execSQL(CREATE_STUDENT_TABLE);


    }
    //upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_COURSE);
        //create tables again
        onCreate(db);

    }

    public void deleteCourse(course course) {
    }


    public void addCourse(course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, course.getCourseName());
        values.put(KEY_COURSE_ADMIN, course.getCourseAdmin());

        //inserting rows
        db.insert(TABLE_COURSE, null, values);
        db.close();//closing database connection
    }

    //getting single contact

    public course getCourse(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COURSE, new String[]{KEY_COURSE_ID, KEY_NAME, KEY_COURSE_ADMIN}, KEY_COURSE_ID
                + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        course course = new course(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return course;
    }


    public List<course> getAllcourse() {
        List<course> courseList = new ArrayList<course>();


        //select all query

        String selectQuery = "SELECT * FROM " + TABLE_COURSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                course course = new course();
                course.setId(Integer.parseInt(cursor.getString(0)));
                course.setCourseName(cursor.getString(1));
                course.setCourseAdmin(cursor.getString(2));
                //adding contact to list
                courseList.add(course);

            }
            while (cursor.moveToNext());
        }

        return courseList;
    }


    public int getcourseCount() {
        String countQuery = "SELECT * FROM" + TABLE_COURSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        //return count
        return cursor.getCount();
    }

    //update single contact

    public int updatecourse(course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, course.getCourseName());
        values.put(KEY_COURSE_ADMIN, course.getCourseAdmin());
        //updating row
        return db.update(TABLE_COURSE, values, KEY_COURSE_ID + "= ?", new String[]{String.valueOf(course.getId())});
    }
    //deleting single contact
    public void deletecourse(course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE, KEY_COURSE_ID + " = ?",
                new String[]{String.valueOf(course.getId())});
        db.close();

    }

}
