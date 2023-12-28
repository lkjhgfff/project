package com.example.neww_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class NoteClass extends AppCompatActivity {

    ImageButton simpleButton1, simpleButton2, simpleButton3;
    Button save, openNoteAdd;
    EditText namePut;
    TextInputEditText discPut;
    LinearLayout addNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_class);

        // create a arraylist of the type NumbersView
        final ArrayList<NumbersView> arrayList = new ArrayList<NumbersView>();
        SharedPreferences prefs = this.getSharedPreferences("com.example.neww_project", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        addNote = findViewById(R.id.add_note);
        save = findViewById(R.id.buttonadd);
        openNoteAdd = findViewById(R.id.button);
        namePut = findViewById(R.id.note_input);
        discPut = findViewById(R.id.note_input2);

        openNoteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("noteNumber", prefs.getInt("noteNumber", 0) + 1);
                editor.putString("name/" + prefs.getInt("noteNumber", 0), namePut.getText().toString());
                editor.putString("disc/" + prefs.getInt("noteNumber", 0), discPut.getText().toString());
                editor.commit();
                addNote.setVisibility(View.INVISIBLE);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        for(int i = prefs.getInt("noteNumber", 0)-1; i > -1; i--) {
            arrayList.add(new NumbersView(R.drawable.menu_1, prefs.getString("name/" + i, ""), prefs.getString("disc/" + i, "")));
        }



        // Now create the instance of the NumebrsViewAdapter and pass
        // the context and arrayList created above
        NumbersViewAdapter numbersArrayAdapter = new NumbersViewAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView numbersListView = findViewById(R.id.listView);

        // set the numbersViewAdapter for ListView
        numbersListView.setAdapter(numbersArrayAdapter);
        simpleButton1 = (ImageButton) findViewById(R.id.button1);//get id of button 1
        simpleButton2 = (ImageButton) findViewById(R.id.button3);//get id of button 1
        simpleButton3 = (ImageButton) findViewById(R.id.button4);//get id of button 1

        simpleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteClass.this, MainActivity.class));
            }
        });
        simpleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteClass.this, MainActivity3.class));
            }
        });
        simpleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteClass.this, MainActivity4.class));
            }
        });
    }
}