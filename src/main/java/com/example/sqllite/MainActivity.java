package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText create,phone;
    Button save,read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create=(EditText)findViewById(R.id.create);
        phone=(EditText)findViewById(R.id.phone);
        save=(Button)findViewById(R.id.save);
        read=(Button)findViewById(R.id.read);

    }

    public void save(View view) {

        SQLDatabase  sb=new SQLDatabase(this);
        String name=create.getText().toString();
        String phn=phone.getText().toString();
        sb.insertContact(new Contact(name,phn));
        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
        create.setText("");
        phone.setText("");

    }


    public void contact(View view) {
        Intent intent=new Intent(MainActivity.this,ContactList.class);
        overridePendingTransition(0,0);
        startActivity(intent);
        overridePendingTransition(0,0);

    }
}
