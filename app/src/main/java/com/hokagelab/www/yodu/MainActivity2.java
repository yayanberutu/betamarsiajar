package com.hokagelab.www.yodu;



import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {

    private static final int RC_CREATE_CONTACT = 1;
    private static final int RC_UPDATE_CONTACT = 2;
    private RecyclerView mContactsRecyclerView;
    private ContactRecyclerAdapter mContactRecyclerAdapter;
    private FloatingActionButton mAddContactFloatingActionButton;
    private ContactDAO mContactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar Teman");

        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getContactDAO();

        mContactsRecyclerView = findViewById(R.id.contactsRecyclerView);
        mContactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAddContactFloatingActionButton = findViewById(R.id.addContactFloatingActionButton);

        int colors[] = {ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, android.R.color.holo_red_light),
                ContextCompat.getColor(this, android.R.color.holo_orange_light),
                ContextCompat.getColor(this, android.R.color.holo_green_light),
                ContextCompat.getColor(this, android.R.color.holo_blue_dark),
                ContextCompat.getColor(this, android.R.color.holo_purple)};

        mContactRecyclerAdapter = new ContactRecyclerAdapter(this, new ArrayList<Contact>(), colors);
        mContactRecyclerAdapter.addActionCallback(new ContactRecyclerAdapter.ActionCallback() {
            @Override
            public void onLongClickListener(Contact contact) {
                Intent intent = new Intent(MainActivity2.this, UpdateContactActivity.class);
                intent.putExtra(UpdateContactActivity.EXTRA_CONTACT_ID, contact.getPhoneNumber());
                startActivityForResult(intent, RC_UPDATE_CONTACT);
            }
        });
        mContactsRecyclerView.setAdapter(mContactRecyclerAdapter);

        mAddContactFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, CreateContactActivity.class);
                startActivityForResult(intent, RC_CREATE_CONTACT);
            }
        });

        loadContacts();
    }

    private void loadContacts() {
        mContactRecyclerAdapter.updateData(mContactDAO.getContacts());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_CREATE_CONTACT && resultCode == RESULT_OK) {
            loadContacts();
        } else if (requestCode == RC_UPDATE_CONTACT && resultCode == RESULT_OK) {
            loadContacts();
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

