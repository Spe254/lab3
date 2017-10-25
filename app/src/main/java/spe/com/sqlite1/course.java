package spe.com.sqlite1;

/**
 * Created by hp on 10/25/2017.
 */

public class course {
    int _course_id;
    String _course_name;
    String _course_admin;


    public course() {

    }

    public course(int _course_id, String _course_name, String _course_admin) {
        this._course_id = _course_id;
        this._course_name = _course_name;
        this._course_admin = _course_admin;
    }

    public course(String _course_name, String _course_admin) {
        this._course_name = _course_name;
        this._course_admin = _course_admin;
    }

    public void setId(int _course_id) {
        this._course_id = _course_id;
    }

    public int getId() {
        return _course_id;
    }

    public void setCourseName(String _course_name) {
        this._course_name = _course_name;
    }

    public String getCourseName() {
        return _course_name;
    }

    public void setCourseAdmin(String _course_admin) {
        this._course_admin = _course_admin;
    }

    public String getCourseAdmin() {
        return _course_admin;
    }
}
