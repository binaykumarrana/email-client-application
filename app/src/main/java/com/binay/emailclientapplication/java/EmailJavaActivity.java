package com.binay.emailclientapplication.java;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.binay.emailclientapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Binay on 01/09/17.
 */

public class EmailJavaActivity extends AppCompatActivity {
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextSubject)
    EditText editTextSubject;
    @BindView(R.id.editTextMessage)
    EditText editTextMessage;
    @BindView(R.id.buttonSend)
    Button buttonSend;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void sendEmail() {
        //Getting content for email
        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @OnClick(R.id.buttonSend)
    public void actionSend() {
        sendEmail();
    }
}
