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

public class StudentDBHandler extends SQLiteOpenHelper {


    //all Static variables
    // Database version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "studentManager";
    //contact table name
    private static final String TABLE_STUDENT = "student";
    // Contacts Table Columns Names
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COURSE = "course";

    public StudentDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Creating Tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + " (" + KEY_STUDENT_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, " + KEY_COURSE + " TEXT )";
        db.execSQL(CREATE_STUDENT_TABLE);


    }
    //upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_STUDENT);
        //create tables again
        onCreate(db);

    }
    //adding new contact
    //public void addContact(contact contact){}

    //getting single contact

    //public contact getcontact(int Id){}

    //getting all contacts
    //public List<contact> getAllContact(){}
    // getting contact count
    //public int getcontactCount() {}

    //updating single contact
    //public int updatecontact (contact contact) {}

    //deleting single contact
    public void deleteStudent(student student) {
    }

    //adding new contact
    public void addStudent(student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());//Contact Name
        values.put(KEY_COURSE, student.getCourse());// contact phone number

        //inserting rows
        db.insert(TABLE_STUDENT, null, values);
        db.close();//closing database connection
    }

    //getting single contact

    public student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[]{KEY_STUDENT_ID, KEY_NAME, KEY_COURSE}, KEY_STUDENT_ID
                + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        student student = new student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return contact
        return student;
    }

    //getting all contact
    public List<student> getAllstudent() {
        List<student> studentList = new ArrayList<student>();


        //select all query

        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                student student = new student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setCourse(cursor.getString(2));
                //adding contact to list
                studentList.add(student);

            }
            while (cursor.moveToNext());
        }
        //return contact list
        return studentList;
    }

    //getting contacts count
    public int getstudentCount() {
        String countQuery = "SELECT * FROM" + TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        //return count
        return cursor.getCount();
    }

    //update single contact

    public int updatestudent(student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_COURSE, student.getCourse());
        //updating row
        return db.update(TABLE_STUDENT, values, KEY_STUDENT_ID + "= ?", new String[]{String.valueOf(student.getId())});
    }

    //deleting single contact
    public void deletestudent(student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_STUDENT_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
        db.close();

    }
}