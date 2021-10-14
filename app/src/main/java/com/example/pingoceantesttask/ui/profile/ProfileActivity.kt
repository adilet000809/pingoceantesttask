package com.example.pingoceantesttask.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.pingoceantesttask.R
import com.example.pingoceantesttask.data.manager.SessionManager
import com.example.pingoceantesttask.data.model.Result
import com.example.pingoceantesttask.ui.splash.SplashScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    @Inject lateinit var sessionManager: SessionManager
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val id = findViewById<TextView>(R.id.user_id_text_view)
        val name = findViewById<TextView>(R.id.user_name_text_view)
        val email = findViewById<TextView>(R.id.user_email_text_view)
        val phone = findViewById<TextView>(R.id.user_phone_text_view)
        val avatar = findViewById<TextView>(R.id.user_avatar_text_view)
        val position = findViewById<TextView>(R.id.user_position_text_view)
        val companyName = findViewById<TextView>(R.id.user_company_name_text_view)
        val timeZone = findViewById<TextView>(R.id.user_timezone_text_view)
        val loading = findViewById<ProgressBar>(R.id.user_loading)
        val logoutButton = findViewById<Button>(R.id.user_logout_button)

        profileViewModel.getProfile(sessionManager.fetchAuthToken()!!)

        profileViewModel.userProfileResult.observe(this@ProfileActivity, Observer {
            val userProfileResult = it ?: return@Observer

            when (userProfileResult.status) {
                Result.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
                Result.Status.SUCCESS -> {
                    loading.visibility = View.GONE
                    id.text = userProfileResult.data?.id
                    name.text = userProfileResult.data?.name
                    email.text = userProfileResult.data?.email
                    phone.text = userProfileResult.data?.phone
                    avatar.text = userProfileResult.data?.avatar
                    position.text = userProfileResult.data?.position
                    companyName.text = userProfileResult.data?.companyName
                    timeZone.text = userProfileResult.data?.timeZone
                }
                Result.Status.ERROR -> {
                    loading.visibility = View.GONE
                    Toast.makeText(this, userProfileResult.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        logoutButton.setOnClickListener {
            sessionManager.deleteAuthToken()
            startActivity(Intent(this, SplashScreenActivity::class.java))
        }

    }
}