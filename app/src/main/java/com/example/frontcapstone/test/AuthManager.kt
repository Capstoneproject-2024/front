package com.example.frontcapstone

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.frontcapstone.viemodel.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthManager(
    private val context: Context,
    private val mainViewModel: MainViewModel
) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient

    init {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id)) // 웹 클라이언트 ID
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)

        val user = auth.currentUser
        user?.let {
            mainViewModel.updateUserState(user.displayName, user.email, user.uid)
            // 사용자가 로그인된 경우, 사용자 정보 출력
            Log.d("AuthManager", "User ID: ${it.uid}")
            Log.d("AuthManager", "User Email: ${it.email}")
            Log.d("AuthManager", "User Name: ${it.displayName}")
        }
    }

    // Google Sign-In Intent
    fun signInIntent() = googleSignInClient.signInIntent

    // Google 인증 처리
    fun handleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let {
                val credential = GoogleAuthProvider.getCredential(it.idToken, null)
                auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        // 로그인 성공
                        val user = auth.currentUser
                        user?.let {
                            mainViewModel.updateUserState(user.displayName, user.email, user.uid)
                            // 사용자가 로그인된 경우, 사용자 정보 출력
                            Log.d("AuthManager", "User ID: ${it.uid}")
                            Log.d("AuthManager", "User Email: ${it.email}")
                            Log.d("AuthManager", "User Name: ${it.displayName}")
                        }
                        // 성공 후 처리
                    } else {
                        Log.e("AuthManager", "Authentication Failed", authTask.exception)
                        // 로그인 실패
                    }
                }
            }
        } catch (e: ApiException) {
            // 예외 처리
            Log.e("AuthManager", "Google sign-in failed", e)
        }
    }


    fun signOut(){
        auth.signOut()
    }

    ///INTERFACE

    // Firebase Authentication 인스턴스 접근
    fun getAuthInstance() = auth

    fun getUser(): FirebaseUser? {
        return auth.currentUser
    }

}
