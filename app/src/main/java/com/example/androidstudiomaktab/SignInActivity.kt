package com.example.androidstudiomaktab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class SignInActivity : AppCompatActivity() {
    private val infoSaver = DataBase
    private lateinit var ac: ActivityResultLauncher<Intent>
    lateinit var btnSignIn:Button
    lateinit var btnSignUp:Button

    lateinit var userTxtIn:EditText
    lateinit var passTxtIn:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        userTxtIn = findViewById(R.id.txtUsernameIn)
        passTxtIn = findViewById(R.id.txtPasswordIn)
        btnSignIn = findViewById(R.id.btnSignIn)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignIn.setOnClickListener {
            if (userTxtIn.text.toString().isEmpty())
                Toast.makeText(this, "Please enter a Username", Toast.LENGTH_SHORT).show()
            else if (passTxtIn.text.toString().isEmpty())
                Toast.makeText(this, "Please enter the Password", Toast.LENGTH_SHORT).show()
            else {
                if(infoSaver.checkLogin(userTxtIn.text.toString(), passTxtIn.text.toString())) {
                    Snackbar.make(it, "Login was Successful", Snackbar.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
            ac = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                if (it.resultCode == RESULT_OK) {
                    userTxtIn.setText(it.data?.extras?.getString("NewUserUp") ?: "NULL")
                    passTxtIn.setText(it.data?.extras?.getString("NewPassUp") ?: "NULL")
                } else   Toast.makeText(this, "Result not found", Toast.LENGTH_SHORT).show()
            }


        btnSignUp.setOnClickListener {
            ac.launch(SignUpActivity.newIntent(this, userTxtIn.text.toString(), passTxtIn.text.toString()))
        }


        }
    }
