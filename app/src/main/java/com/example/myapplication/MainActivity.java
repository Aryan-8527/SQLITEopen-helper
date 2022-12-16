package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name , contact , dob ;
    Button btninsert ,  btnupate , btndelete , btnview ;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                  //Edit text //
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob =findViewById(R.id.dob);

                 //button //
        btninsert = findViewById(R.id.btnInsert);
        btndelete = findViewById(R.id.btnDelete);
        btnupate = findViewById(R.id.btnUpdate);
        btnview = findViewById(R.id.btnView);
        DB = new DBhelper(this);

                        // inserting //
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = contact.getText().toString();
                String dobtxt = dob.getText().toString();

                Boolean chceckinsertdata = DB.insertuserdata(nametxt , contacttxt , dobtxt);
                if (chceckinsertdata ==true)
                    Toast.makeText(MainActivity.this, "User Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New User Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

                          //updates //
        btnupate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = contact.getText().toString();
                String dobtxt = dob.getText().toString();

                Boolean chceckupdatedata = DB.updateuserdata(nametxt , contacttxt , dobtxt);
                if (chceckupdatedata ==true){
                    Toast.makeText(MainActivity.this, "user Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "user Not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

                    // for deleteing //
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();

                Boolean chceckdeletedata = DB.deleteuserdata(nametxt);
                if (chceckdeletedata == true) {
                    Toast.makeText(MainActivity.this, "user Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "user Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

                      // for viewing //
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "User Entry not Viewed", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (res.moveToNext()){
                    stringBuffer.append("Name: "+res.getString(0)+"\n");
                    stringBuffer.append("Contact: "+res.getString(1)+"\n");
                    stringBuffer.append("Date Of Birth: "+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(stringBuffer.toString());
                builder.show();
            }
        });
    }
}