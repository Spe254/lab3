package spe.com.sqlite1;

/**
 * Created by hp on 10/19/2017.
 */

public class contact {
    int _id;
    String _name;
    String _phone_number;


    public contact() {

    }

    public contact(int id, String name, String _phone_number) {
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    public contact(String name, String phone_number) {
        this._name = name;
        this._phone_number = phone_number;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getId() {
        return _id;
            }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getName() {
        return _name;
    }

    public void setPhoneNumber(String _phone_number) {
        this._phone_number = _phone_number;
    }

    public String getPhoneNumber() {
        return _phone_number;
    }
}
