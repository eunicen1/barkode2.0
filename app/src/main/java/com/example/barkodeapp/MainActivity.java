package com.example.barkodeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.chaquo.python.PyObject;
//import com.chaquo.python.Python;
//import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        TextView list = (TextView)findViewById(R.id.list);
        ImageButton logo = (ImageButton)findViewById(R.id.logo);
        Button scan = (Button)findViewById(R.id.off);
        Button generate = (Button)findViewById(R.id.on);

        reff = FirebaseDatabase.getInstance().getReference().child("ventilators");
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String giant= "";
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String bc = postSnapshot.child("barcode").getValue().toString();
                    String lc = postSnapshot.child("location").getValue().toString();
                    String st = postSnapshot.child("status").getValue().toString();
                    String sn =(postSnapshot.child("snap").getValue()).toString();


                    if(((postSnapshot.child("snap").getValue()).toString()).equals("0")){
                        sn = "First scan";
                    }
                    if(((postSnapshot.child("snap").getValue()).toString()).equals("1")){
                        sn = "Second scan";
                    }
                    giant += bc + " | " + lc + " | " + st + " | " + sn+"\n";
                }
                list.setText(giant);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerateActivity.class);
                startActivity(i);
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(i);
            }
        });

    }
}