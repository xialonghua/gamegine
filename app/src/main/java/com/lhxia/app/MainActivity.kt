package com.lhxia.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.GetChars
import com.lhxia.mybatis.types.AccountType
import com.lhxia.network.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GetMobileLoginVerifyCodeReq().apply {
            account = ""
            type = AccountType.MOBILE
            callback = object : HttpCallback<Resp<GetMobileLoginVerifyCodeResp>>{
                override fun onFailed(code: Int, e: Exception?) {
                }


                override fun onSuccess(t: Resp<GetMobileLoginVerifyCodeResp>) {
                    println(t)
                }

            }
        }.request()
    }
}
