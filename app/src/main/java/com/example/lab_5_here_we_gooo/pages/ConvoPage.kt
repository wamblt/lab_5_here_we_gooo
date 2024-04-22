package com.example.lab_5_here_we_gooo.pages

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.lab_5_here_we_gooo.entities.CustomConvo
import com.example.lab_5_here_we_gooo.objects.DerekDao

class ConvoPage(val convoId: Long): ComponentActivity(){

    private val convoDao: DerekDao
        get() {
            return convoDao
        }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent{

            val convo = convoDao.getDerekById(convoId)

            Text(text = "ID: ${convo.id}, Name: ${convo.superDEREKname}")
        }
    }
}