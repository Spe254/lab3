package spe.com.sqlite1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        /**
         * CRUD Operations
         */
        //inserting contacts
        Log.d("Insert: ", "Inserting ...");
        db.addContact(new contact("Ravi", "555777799"));
        db.addContact(new contact("John", "58765432"));
        db.addContact(new contact("Tommy", "66653468990"));
        db.addContact(new contact("Karthik", "44332117"));
        //reading all contacts
        Log.d("Reading:", "Reading all contacts..");
        List<contact> contact = db.getAllcontact();
        for (contact cn : contact) {
            String log = "Id:" + cn.getId() + " ,Name:" + cn.getName() + " ,Phone()" + cn.getPhoneNumber();
            Log.d("Name:", log);
        }
        StudentDBHandler db1 = new StudentDBHandler(this);

        Log.d("Insert: ", "Inserting ...");
        db1.addStudent(new student("Mika", "Art & Design"));
        db1.addStudent(new student("Camilla", "Financial Economics"));
        db1.addStudent(new student("Donovan", "Acturial Science"));
        db1.addStudent(new student("Dylan", "Law"));
        //reading all contacts
        Log.d("Reading:", "Reading all student..");
        List<student> student = db1.getAllstudent();
        for (student cn : student) {
            String log = "Id:" + cn.getId() + " ,Name:" + cn.getName() + " ,Course()" + cn.getCourse();
            Log.d("Name:", log);



        }
        CourseHandler db2 = new CourseHandler(this);

        Log.d("Insert: ", "Inserting ...");
        db2.addCourse(new course ("Art & Design", "Missono"));
        db2.addCourse(new course("Financial Economics", "Mwangi"));
        db2.addCourse(new course("Acturial Science", "Reena"));
        db2.addCourse(new course("Law", "Yula"));
        //reading all contacts
        Log.d("Reading:", "Reading all course..");
        List<course> course = db2.getAllcourse();
        for (course cn : course) {
            String log = "Id:" + cn.getId() + " ,Name:" + cn.getCourseName() + " ,Course()" + cn.getCourseAdmin();
            Log.d("Name:", log);

        }

        }
}
