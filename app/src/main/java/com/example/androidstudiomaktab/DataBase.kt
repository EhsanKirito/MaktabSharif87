package com.example.androidstudiomaktab

object DataBase {
    private val userPass = HashMap<String, String>()

    fun registerUser(userName: String, passWord: String):Boolean{
        if (userPass.containsKey(userName))  return false
        else {
            userPass[userName] = passWord
            return true
        }
    }

    fun checkLogin(userName: String, passWord: String):Boolean {
        if (!userPass.containsKey(userName)) return false
        return (userPass[userName] == passWord)
    }
}

