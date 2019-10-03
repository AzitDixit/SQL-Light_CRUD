package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ListIterator;

public class ContactList extends AppCompatActivity {

    ListView list;
    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        list=(ListView)findViewById(R.id.list);

        SQLDatabase sql=new SQLDatabase(this);
        List<Contact> contactList=sql.getAllContacts();
        adapter=new MyListAdapter(this,R.layout.listrow,contactList);
        list.setAdapter(adapter);

    }



    public class MyListAdapter extends BaseAdapter {
        int layout;
        Context context;
        List<Contact> list;

        public MyListAdapter(Context context, int layout, List<Contact> list) {
            this.layout = layout;
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row=view;
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);


            TextView textView1=row.findViewById(R.id.id);
            TextView textView2=row.findViewById(R.id.name);
            TextView textView3=row.findViewById(R.id.phnno);
            ImageButton imageButton=row.findViewById(R.id.edit);
            ImageButton imageButton1=row.findViewById(R.id.delete);
            final Contact contact=list.get(i);
             final SQLDatabase db=new SQLDatabase(context);
           imageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deletecontact(contact);
                    list.remove(contact);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show();
                }
            });
           imageButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent=new Intent(ContactList.this,Update.class);
                   intent.putExtra("id",String.valueOf(contact.getID()));
                   intent.putExtra("name",contact.getName());
                   intent.putExtra("number",contact.getPhoneNumber());
                   overridePendingTransition(0,0);
                   startActivity(intent);
                   overridePendingTransition(0,0);

               }
           });

           // textView1.setText(String.valueOf(contact.getID()));
            textView1.setText(String.valueOf(i+1));
            textView2.setText(contact.getName());
            textView3.setText(contact.getPhoneNumber());
            return row;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1=new Intent(ContactList.this,MainActivity.class);
        overridePendingTransition(0,0);
        startActivity(intent1);
        overridePendingTransition(0,0);
        finish();
    }
}













/*package com.example.sqllite;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.TextView;

        import java.util.List;
        import java.util.ListIterator;

public class ContactList extends AppCompatActivity {
    TextView textView,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        textView=(TextView)findViewById(R.id.textview);
        textView1=(TextView)findViewById(R.id.textvirew1);
        SQLDatabase sql=new SQLDatabase(this);
        List<Contact> contactList=sql.getAllContacts();
        ListIterator<Contact> li=contactList.listIterator();
        StringBuffer stringBuffer=new StringBuffer();
        while (li.hasNext()){
            Contact contact=li.next();
            stringBuffer.append(contact.getID()+"  "+contact.getName()+"  "+contact.getPhoneNumber()+"\n");
        }
        textView1.setText(stringBuffer.toString());

    }
}*/
































