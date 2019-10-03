package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SQLDatabase  extends SQLiteOpenHelper {
    private  static  final  int  DATABASE_VERSION=1;
    private  static  final String DATABASE_NAME="contact manager";
    private static final String TABLE_CONTACT="contact";
    private static  final String KEY_ID="id";
    private static  final String KEY_NAME="name";
    private static  final String KEY_PHONE_NUMBER="phonenumber";
    Context context;




    public SQLDatabase(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        this.context=context;

    }

    //creating table
    @Override
    public void onCreate(SQLiteDatabase sb) {
        String CREATE_TABLE_CONTACT="CREATE TABLE "+TABLE_CONTACT+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_PHONE_NUMBER+" TEXT)";
        sb.execSQL(CREATE_TABLE_CONTACT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sb, int i, int j) {
        sb.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACT);

    }

    public void insertContact(Contact contact) {
        SQLiteDatabase slb= getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,contact.getName());//contact name
        cv.put(KEY_PHONE_NUMBER,contact.getPhoneNumber());//contact phone
        slb.insert(TABLE_CONTACT,null,cv);
        slb.close();


    }
    // code to get all contacts in a list view

    public List<Contact> getAllContacts()
    {
        List<Contact> contactList=new ArrayList<>();
        String selectQuery ="SELECT * FROM " +TABLE_CONTACT;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Contact contact=new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);

            }while (cursor.moveToNext());
        }
        return  contactList;

    }
    public void editcontact(int id,String name,String number){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE_NUMBER, number);

        // updating row
        db.update(TABLE_CONTACT, values, KEY_ID + " ="+id,null);
        Toast.makeText(context,"Edit contact",Toast.LENGTH_SHORT).show();


    }
    public void deletecontact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
}
