package com.example.sandman.project5_bs;

/**
 * Created by Sandman on 11/5/2014.
 */
public class Contact {
    private String _table, _name, _phone, _email, _address;
    private int _id;

    public Contact (int id, String name, String phone, String email, String address, String table) {
        _id = id;
        _table = table;
        _name = name;
        _phone = phone;
        _email = email;
        _address = address;
    }

    public int getID() { return _id; }
    public String getTable() {return _table; }
    public String getName() { return _name; }
    public String getPhone() { return _phone; }
    public String getEmail() { return _email; }
    public String getAddress() { return _address; }
    @Override
    public String toString(){
        return _name;
    }
}
