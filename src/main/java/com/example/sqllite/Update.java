package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {
    EditText name,number;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name=(EditText)findViewById(R.id.name1);
        number=(EditText)findViewById(R.id.number1);
        update=(Button)findViewById(R.id.submit);
        Intent intent=getIntent();
        final String id=intent.getStringExtra("id");
        name.setText(intent.getStringExtra("name"));
        number.setText(intent.getStringExtra("number"));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLDatabase sb=new SQLDatabase(Update.this);
                sb.editcontact(Integer.parseInt(id),name.getText().toString(),number.getText().toString());
                Intent intent1=new Intent(Update.this,ContactList.class);
                overridePendingTransition(0,0);
                startActivity(intent1);
                overridePendingTransition(0,0);

            }
        });

    }
}
