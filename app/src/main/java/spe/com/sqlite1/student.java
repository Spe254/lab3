package spe.com.sqlite1;

/**
 * Created by hp on 10/25/2017.
 */

public class student {
    int _student_id;
    String _name;
    String _course;


    public student() {

    }

    public student(int _student_id, String name, String _course) {
        this._student_id = _student_id;
        this._name = name;
        this._course = _course;
    }

    public student(String name, String _course) {
        this._name = name;
        this._course = _course;
    }

    public void setId(int _student_id) {
        this._student_id = _student_id;
    }

    public int getId() {
        return _student_id;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getName() {
        return _name;
    }

    public void setCourse(String _course) {
        this._course = _course;
    }

    public String getCourse() {
        return _course;
    }
}
