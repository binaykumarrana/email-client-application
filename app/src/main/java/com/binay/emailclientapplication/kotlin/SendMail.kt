package com.binay.emailclientapplication.kotlin

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.binay.emailclientapplication.util.Config
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 * Created by Binay on 01/09/17.
 */
class SendMail(context: Context, email: String, subject: String, message: String)  : AsyncTask<Void, Void, Void>() {

    //Declaring Variables
    private lateinit var context: Context
    private var session: Session? = null

    //Information to send email
    private lateinit var email: String
    private lateinit var subject: String
    private lateinit var message: String

    //Progressdialog to show while sending email
    private var progressDialog: ProgressDialog? = null

    //Class Constructor
    fun SendMail(context: Context, email: String, subject: String, message: String) {
        //Initializing variables
        this.context = context
        this.email = email
        this.subject = subject
        this.message = message
    }

    protected override fun onPreExecute() {
        super.onPreExecute()
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context, "Sending message", "Please wait...", false, false)
    }

    protected override fun onPostExecute(aVoid: Void) {
        super.onPostExecute(aVoid)
        //Dismissing the progress dialog
        progressDialog!!.dismiss()
        //Showing a success message
        Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show()
    }

    protected override fun doInBackground(vararg params: Void): Void? {
        //Creating properties
        val props = Properties()

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com")
        props.put("mail.smtp.socketFactory.port", "465")
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.port", "465")

        //Creating a new session
        session = Session.getDefaultInstance(props,
                object : javax.mail.Authenticator() {
                    //Authenticating the password
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(Config.EMAIL, Config.PASSWORD)
                    }
                })

        try {
            //Creating MimeMessage object
            val mm = MimeMessage(session)

            //Setting sender address
            mm.setFrom(InternetAddress(Config.EMAIL))
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, InternetAddress(email))
            //Adding subject
            mm.subject = subject
            //Adding message
            mm.setText(message)

            //Sending email
            Transport.send(mm)

        } catch (e: MessagingException) {
            e.printStackTrace()
        }

        return null
    }
}
