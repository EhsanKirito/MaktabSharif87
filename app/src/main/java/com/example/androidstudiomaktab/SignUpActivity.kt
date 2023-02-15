package com.example.androidstudiomaktab

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    private val infoSaver = DataBase
    lateinit var userTxtUp:EditText
    lateinit var passTxtUp:EditText
    companion object {
        private val tag: String by lazy {
            SignUpActivity::class.java.name
        }

        fun newIntent(context: Context, user:String, pass: String): Intent {
            return Intent(context, SignUpActivity::class.java).apply {
                putExtra("userReturn", user)
                putExtra("passReturn", pass)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        userTxtUp = findViewById(R.id.txtUsernameUp)
        passTxtUp = findViewById(R.id.txtPasswordUp)

        userTxtUp.setText((intent?.getStringExtra("userReturn")))
        passTxtUp.setText((intent?.getStringExtra("passReturn")))

        findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            if (userTxtUp.text.toString().isEmpty())
                Toast.makeText(this, "Username can't be blank", Toast.LENGTH_SHORT).show()
            else if (passTxtUp.text.toString().isEmpty())
                Toast.makeText(this, "Password can't be blank", Toast.LENGTH_SHORT).show()
            else {

                if(infoSaver.registerUser(userTxtUp.text.toString(), passTxtUp.text.toString())){
                    Toast.makeText(this, "Register successfully done", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    override fun onBackPressed() {
        val signUpIntent = Intent().apply {
            putExtra("NewUserUp", userTxtUp.text.toString())
            putExtra("NewPassUp", passTxtUp.text.toString())
        }
        setResult(RESULT_OK, signUpIntent)
        super.onBackPressed()
    }
}