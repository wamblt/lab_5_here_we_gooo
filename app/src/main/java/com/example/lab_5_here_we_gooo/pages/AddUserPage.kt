package com.example.lab_5_here_we_gooo.pages

import android.app.Application
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import com.example.lab_5_here_we_gooo.databases.CustomDerekBase
import com.example.lab_5_here_we_gooo.databases.CustomUserDatabase
import com.example.lab_5_here_we_gooo.entities.User
import com.example.lab_5_here_we_gooo.objects.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random


class UserApp: Application(){
    private lateinit var _db: CustomUserDatabase

    val db: CustomUserDatabase
        get() = _db

    override fun onCreate() {
        super.onCreate()

        _db = Room.databaseBuilder(this.baseContext, CustomUserDatabase::class.java, "user").build()
    }
}

class AddUserPage : ComponentActivity() {
    private lateinit var _db: CustomUserDatabase
    override fun onStart() {
        super.onStart()
        _db = Room.databaseBuilder(this.baseContext, CustomUserDatabase::class.java, "user").build()

    }

    override fun onStop() {
        super.onStop()
        _db.close()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            Users(uVM = UserViewModel(_db.customUserDao()))
        }
    }
}

class UserViewModel(private val userDao: UserDao): ViewModel(){
    companion object Factory{
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as UserApp
                UserViewModel(userDao = app.db.customUserDao())
            }
        }
    }

    private var _users = MutableStateFlow(emptyList<User>())
    init {
        viewModelScope.launch {
            _users.emit(userDao.getAllUsers())
        }
    }

    val users = _users

    fun createUser(name: String, age: Int){
        val user = User(Random(System.currentTimeMillis()).nextLong(), name, age)
        userDao.insertUser(user)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Users(uVM: UserViewModel){
    val users by uVM.users.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Convos") })
        },
        floatingActionButton = {
            IconButton(onClick = {
                uVM.createUser("derek", 25)
            }){
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add_derek")
            }
        }
    ) {
            padding -> LazyColumn(modifier = Modifier.padding(padding)){
        items(items = users, key = {it.id}){
            Card {
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(it.userName)

                }
                Text("Age: ${it.userAge}")
            }
        }
    }
    }
}




