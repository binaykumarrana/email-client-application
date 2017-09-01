package com.binay.emailclientapplication.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import com.binay.emailclientapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class EmailKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSend.setOnClickListener { sendEmail() }
    }

    private fun sendEmail() {
        //Getting content for email
        val email = editTextEmail.text.toString().trim()
        val subject = editTextSubject.text.toString().trim()
        val message = editTextMessage.text.toString().trim()

        //Creating SendMail object
        val sm = SendMail(this, email, subject, message)

        //Executing sendmail to send email
        sm.execute()
    }
}
