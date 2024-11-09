package com.example.frontcapstone

import TestAuthActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.presentation.navigation.Navigator
import com.example.frontcapstone.ui.theme.FrontCapstoneTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {

    private lateinit var authManager: AuthManager
    private val signInResultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        authManager.handleSignInResult(result.data)
    }

    private fun signIn() {
        val signInIntent = authManager.signInIntent()
        signInResultLauncher.launch(signInIntent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // AuthManager 인스턴스 초기화
        authManager = AuthManager(this)

        enableEdgeToEdge()
        setContent {
            FrontCapstoneTheme(dynamicColor = false) {

                SignInButton(
                    authManager  = authManager,
                    onClick = {
                        signIn()


                    }
                )
                //Navigator()
            }

        }

    }
}

@Composable
fun SignInButton(authManager: AuthManager,onClick:()->Unit){
    var id by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Button(onClick = {
            onClick()
            id = authManager.getUser()?.uid ?: "none"

        }) {
            Text(text = id)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrontCapstoneTheme {
        Column {
//            TopMenu(title = "try", navigationBack = {})
//            BottomMenu()
        }

    }
}