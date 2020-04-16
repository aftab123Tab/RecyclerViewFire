package com.example.recyclerviewfire;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText usernameET, userstatusET;
    RecyclerView objectRecyclerView;
    FirebaseFirestore objectfirebasefirestore;
    FbAdapter objectFbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            usernameET = findViewById(R.id.usernameET);
            userstatusET = findViewById(R.id.userstatusET);
            objectRecyclerView = findViewById(R.id.RV);
            objectfirebasefirestore = FirebaseFirestore.getInstance();
            addstatusToRV();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void addstatus(View view) {
        try {
            if (!usernameET.getText().toString().isEmpty()
                    && !userstatusET.getText().toString().isEmpty()) {

                Map<String, String> objectMap = new HashMap<>();
                objectMap.put("status", userstatusET.getText().toString());
                objectfirebasefirestore.collection("status")
                        .document(usernameET.getText().toString())
                        .set(objectMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "status is added", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "status fail", Toast.LENGTH_SHORT).show();
                            }
                        });

            } else {

                Toast.makeText(this, "Please Enter values", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void addstatusToRV ()
    {
        try {
            CollectionReference objectQuery=objectfirebasefirestore.collection("status");
            FirestoreRecyclerOptions<modelclass> objectOptions
                    =new FirestoreRecyclerOptions.Builder<modelclass>()
                    .setQuery(objectQuery,modelclass.class)
                    .build();

            objectFbAdapter=new FbAdapter(objectOptions);
            objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            objectRecyclerView.setAdapter(objectFbAdapter);




        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        objectFbAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        objectFbAdapter.stopListening();
    }
}
