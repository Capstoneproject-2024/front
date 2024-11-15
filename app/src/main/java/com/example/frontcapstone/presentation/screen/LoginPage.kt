package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.AuthManager
import com.example.frontcapstone.R
import com.example.frontcapstone.viemodel.MainViewModel
import kotlin.math.round

@Composable
fun LoginPage(
    mainViewModel: MainViewModel,
    authManager: AuthManager,
    onClick:()->Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier.weight(1.0f)
            )
            Image(
                painter = painterResource(id = R.drawable.qta_logo),
                contentDescription = "logo",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop

            )
            Spacer(
                modifier = Modifier.weight(1.0f)
            )
            ElevatedButton(
                onClick = onClick,
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.continue_with_google),
                    contentDescription = "google_login",
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 7.dp)

                )
            }

            Spacer(
                modifier = Modifier.size(10.dp)
            )
            ElevatedButton(
                onClick = { authManager.signOut() },
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.continue_with_google),
                    contentDescription = "google_login",
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 7.dp)

                )
            }
        }


    }
}