package spe.com.sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 10/19/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    //all Static variables
    // Database version
    private static final int DATABASE_VERSION =1;
    // Database Name
    private static final String DATABASE_NAME = "contactManager";
    //contact table name
    private static final  String TABLE_CONTACT = "contact";
    // Contacts Table Columns Names
    private static final  String KEY_ID ="id";
    private static final  String KEY_NAME ="name";
    private static final  String KEY_PH_NO ="phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Creating Tables

              @Override
    public void onCreate(SQLiteDatabase db) {
                  String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACT +" (" + KEY_ID + " INTEGER PRIMARY KEY, "
                          + KEY_NAME + " TEXT, " + KEY_PH_NO + " TEXT )";
                  db.execSQL(CREATE_CONTACTS_TABLE);


    }
    //upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACT);
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
    public void deleteContact(contact contact) {}

    //adding new contact
     public void addContact(contact contact) {
         SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(KEY_NAME, contact.getName());//Contact Name
         values.put(KEY_PH_NO, contact.getPhoneNumber());// contact phone number

         //inserting rows
         db.insert(TABLE_CONTACT, null, values);
         db.close();//closing database connection
     }

         //getting single contact

         public contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.query(TABLE_CONTACT, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO }, KEY_ID
             + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if(cursor !=null)
            cursor.moveToFirst();
        contact contact = new contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return contact
        return contact;
         }

        //getting all contact
        public List<contact> getAllcontact() {
            List<contact> contactList = new ArrayList<contact>();


            //select all query

            String selectQuery = "SELECT * FROM " + TABLE_CONTACT;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            //looping through all rows and adding to list

            if (cursor.moveToFirst()) {
                do {
                    contact contact = new contact();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhoneNumber(cursor.getString(2));
                    //adding contact to list
                    contactList.add(contact);

                }
                while (cursor.moveToNext());
            }
            //return contact list
            return contactList;
        }

            //getting contacts count
        public int getcontactCount() {
            String countQuery = "SELECT * FROM" + TABLE_CONTACT;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();
            //return count
            return cursor.getCount();
        }

            //update single contact

        public int updatecontact (contact contact){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, contact.getName());
            values.put(KEY_PH_NO, contact.getPhoneNumber());
            //updating row
            return db.update(TABLE_CONTACT, values, KEY_ID + "= ?", new String[]{String.valueOf(contact.getId())});
        }

        //deleting single contact
        public void deletecontact (contact contact){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_CONTACT, KEY_ID + " = ?",
                    new String[]{String.valueOf(contact.getId())});
            db.close();
        }
}






