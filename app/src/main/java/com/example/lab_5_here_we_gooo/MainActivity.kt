package com.example.lab_5_here_we_gooo

import android.app.Application
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.lab_5_here_we_gooo.databases.CustomConvoBase
import com.example.lab_5_here_we_gooo.entities.CustomConvo
import com.example.lab_5_here_we_gooo.objects.ConvoDao
import com.example.lab_5_here_we_gooo.pages.AddUserPage
import com.example.lab_5_here_we_gooo.pages.ConvoPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class DerekExampleApp: Application(){
    private lateinit var _db: CustomConvoBase
    val db: CustomConvoBase
        get() = _db

    override fun onCreate() {
        super.onCreate()

        _db = Room
            .databaseBuilder(
                this.baseContext,
                CustomConvoBase::class.java,
                "room_example")
            .build()
    }

    override fun onTerminate() {
        super.onTerminate()

        _db.close()
    }
}


class MainActivity() : ComponentActivity(){
    private lateinit var _db: CustomConvoBase

    override fun onStart() {
        super.onStart()
        _db = Room
            .databaseBuilder(
                this.baseContext,
                CustomConvoBase::class.java,
                "room_example")
            .allowMainThreadQueries()
            .build()
    }

    override fun onStop() {
        super.onStop()
        _db.close()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "Dereks") {
                composable("main"){ Convos(cVM = ConvoViewModel(_db.customDaoek()), onConvoClicked = {navController.navigate("main$it")})}
                composable("convo/{convoid}", arguments = listOf(navArgument("convoid"){
                    nullable = false
                    defaultValue = ""
                    build()}))
                {
                    it.arguments?.let { it1 -> ConvoPage(it1.getLong(it.id)) }
                }
                composable("user"){AddUserPage()}
            }

        }
    }
}

class ConvoViewModel(private val convoDao: ConvoDao): ViewModel(){
    companion object Factory{
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as DerekExampleApp
                ConvoViewModel(convoDao = app.db.customDaoek())
            }
        }
    }

    private var _dereks = MutableStateFlow(emptyList<CustomConvo>())
    init {
        viewModelScope.launch {
            _dereks.emit(convoDao.getAllDereks())
        }
    }

    val dereks = _dereks

    fun createDerek(superDEREKname: String){
        val derek = CustomConvo(Random(System.currentTimeMillis()).nextLong(), superDEREKname)
        convoDao.insertCustomDerek(derek)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Convos(cVM: ConvoViewModel, onConvoClicked: (id:Long) -> Unit){
    val convos by cVM.dereks.collectAsState()

    Scaffold(
        topBar = {
                 TopAppBar(title = { Text("Convos") })
        },
        floatingActionButton = {
            IconButton(onClick = {
                cVM.createDerek("derek")
            }){
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add_derek")
            }
        }
    ) {
        padding -> LazyColumn(modifier = Modifier.padding(padding)){
            items(items = convos, key = {it.id}){
                Card(modifier = Modifier.clickable { onConvoClicked(it.id) }) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(it.superDEREKname)
                    }
                }
            }
        }
    }
}