package com.example.barkodeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatusActivity extends AppCompatActivity {
    String barcode;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        barcode = getIntent().getStringExtra("barcode");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_main);

        TextView curr = (TextView)findViewById(R.id.curr);
        ImageButton logo = (ImageButton)findViewById(R.id.logo);
        Button on = (Button)findViewById(R.id.on);
        Button off = (Button)findViewById(R.id.off);
        Button idle = (Button)findViewById(R.id.idle);

        reff = FirebaseDatabase.getInstance().getReference().child("ventilators");
        curr.setText("Barcode: "+barcode+"\n"+"");
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  reff = FirebaseDatabase.getInstance().getReference().child("ventilators");
                  reff.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.hasChild(barcode)) {
                                reff.child(barcode).child("status").setValue("on");
                                Intent myIntent = new Intent(StatusActivity.this, MainActivity.class);
                                startActivity(myIntent);
                            }else{
                                Intent myIntent = new Intent(StatusActivity.this, MainActivity.class);
                                startActivity(myIntent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                Intent i = new Intent(StatusActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("ventilators");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(barcode)) {
                            reff.child(barcode).child("status").setValue("off");
                            Intent myIntent = new Intent(StatusActivity.this, MainActivity.class);
                            startActivity(myIntent);
                        }else{
                            Intent myIntent = new Intent(StatusActivity.this, MainActivity.class);
                            startActivity(myIntent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent i = new Intent(StatusActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        idle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("ventilators");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(barcode)) {
                            reff.child(barcode).child("status").setValue("idle");
                            Intent myIntent = new Intent(StatusActivity.this, MainActivity.class);
                            startActivity(myIntent);
                        }else{
                            Intent myIntent = new Intent(StatusActivity.this, MainActivity.class);
                            startActivity(myIntent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent i = new Intent(StatusActivity.this, MainActivity.class);
                startActivity(i);
            }
        });



    }
}
