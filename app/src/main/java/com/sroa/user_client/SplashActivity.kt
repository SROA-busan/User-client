package com.sroa.user_client

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import com.sroa.user_client.sign.SignInActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        Handler().postDelayed({
            val intent = Intent(this, SignInActivity::class.java)

            startActivity(intent)
            finish()
        }, 2000)
    }

    //릴리즈용 해시키
    fun getHashKey() {
        lateinit var packageInfo: PackageInfo
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (packageInfo == null)
            Log.e("keyHash", "KeyHash:null")

        packageInfo.signatures.forEach {
            try {
                var md = MessageDigest.getInstance("SHA")
                md.update(it.toByteArray())
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature" + it, e)
            }
        }
    }
}