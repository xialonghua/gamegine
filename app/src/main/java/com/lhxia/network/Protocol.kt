package com.lhxia.network

import com.lhxia.mybatis.types.AccountType

class GetMobileLoginVerifyCodeReq : Req<GetMobileLoginVerifyCodeResp>("/user/getMobileLoginVerifyCode", null, GetMobileLoginVerifyCodeResp::class.java) {
    var type : AccountType? = null
    var account : String? = null
}
class GetMobileLoginVerifyCodeResp(var name : String)