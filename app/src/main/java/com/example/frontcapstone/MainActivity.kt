package com.example.frontcapstone

import android.os.Bundle
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frontcapstone.presentation.navigation.Navigator
import com.example.frontcapstone.ui.theme.FrontCapstoneTheme
import com.example.frontcapstone.viemodel.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var authManager: AuthManager
    private val signInResultLauncher =
        registerForActivityResult(StartActivityForResult()) { result ->
            authManager.handleSignInResult(result.data)

        }

    private fun googleSignIn() {
        val signInIntent = authManager.signInIntent()
        signInResultLauncher.launch(signInIntent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            FrontCapstoneTheme(dynamicColor = false) {
//                val query_start = R.string.query_start
                val mainViewModel = viewModel<MainViewModel>()
                // AuthManager 인스턴스 초기화
                authManager = AuthManager(this,mainViewModel)
//                mainViewModel.appInit(1)

//                SignInButton(
//                    authManager = authManager,
//                    onClick = {
//                        signIn()
//
//
//                    }
//                )
                Navigator(
                    mainViewModel = mainViewModel,
                    googleSignIn = { googleSignIn() },
                    authManager = authManager
                )
            }

        }

    }
}


@Composable
fun SignInButton(authManager: AuthManager, onClick: () -> Unit) {
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
