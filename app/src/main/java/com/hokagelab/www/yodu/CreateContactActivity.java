package com.hokagelab.www.yodu;



import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Date;

public class CreateContactActivity extends AppCompatActivity {

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneNumberEditText;
    private Button mSaveButton;
    private ContactDAO mContactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data Teman");

        mContactDAO = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build()
                .getContactDAO();

        mFirstNameEditText = findViewById(R.id.firstNameEditText);
        mLastNameEditText = findViewById(R.id.lastNameEditText);
        mPhoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        mSaveButton = findViewById(R.id.saveButton);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstNameEditText.getText().toString();
                String lastName = mLastNameEditText.getText().toString();
                String phoneNumber = mPhoneNumberEditText.getText().toString();

                if (firstName.length() == 0 || lastName.length() == 0 || phoneNumber.length() == 0) {
                    Toast.makeText(CreateContactActivity.this, "Please make sure all details are correct", Toast.LENGTH_SHORT).show();
                    return;
                }

                Contact contact = new Contact();
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setPhoneNumber(phoneNumber);
                contact.setCreatedDate(new Date());

                //Insert to database
                try {
                    mContactDAO.insert(contact);
                    setResult(RESULT_OK);
                    finish();
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(CreateContactActivity.this, "A cotnact with same phone number already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
