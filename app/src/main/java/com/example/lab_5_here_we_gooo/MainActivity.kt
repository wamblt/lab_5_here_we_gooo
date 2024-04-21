package com.example.lab_5_here_we_gooo

import android.app.Application
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab_5_here_we_gooo.databases.CustomDerekBase


//class MainApplication: Application() {
//    lateinit var _db: RoomDatabase      //wouldn't work with val
//    override fun onCreate() {
//        super.onCreate()
//        _db = Room.databaseBuilder(baseContext, CustomDerekBase::class.java, "reallyCoolDerekBase").build()
//    }
//
//    override fun onTerminate() {
//        _db.close()
//        super.onTerminate()
//
//    }
//}
class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            MEssageCard("android")
        }
    }
}
@Composable
fun MEssageCard(name: String){
    Text(text = "Hello $name!")
}
//    @Composable
//    fun UserCreationInputField(modifier: Modifier = Modifier, fieldName: String, onChange: (String)-> Unit){
//        var contents by rememberSaveable{
//           mutableStateOf("")
//        }
//
//        Row(horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)){
//            Text(fieldName)
//            TextField(value = "", onValueChange = {contents = it
//                                                  onChange(it)},
//                modifier = Modifier.fillMaxWidth(0.9f))
//        }
//    }
//
//    @Composable
//    fun Dereks(){
//        return Scaffold {
//            Column(modifier = androidx.compose.ui.Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally){
//                UserCreationInputField(fieldName = "UserName"){
//                    Log.d("UserCreationScreen", "Got username content: ${it}")
//                }
//                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)){
//                    Text("Username")
//                    TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(0.9f))
//                }
//                Row{
//
//                }
//            }
//        }
//    }
//
//   @Composable
//   fun materialTest(onClick: () -> Unit){
//       Button(onClick = {onClick()}){
//           Text("Filled")
//       }
//   }