package com.lhxia.network

import com.google.gson.reflect.TypeToken
import okhttp3.*
import okio.BufferedSink
import java.io.IOException
import java.util.concurrent.TimeUnit

val httpClient : OkHttpClient = OkHttpClient.Builder().apply {

    connectTimeout(10, TimeUnit.SECONDS)
    readTimeout(10, TimeUnit.SECONDS)
    writeTimeout(10, TimeUnit.SECONDS)
}.build()

interface GsonSerializable

open class Req<T>(val path: String, var callback: HttpCallback<Resp<T>>?, var respClz: Class<*>?) : GsonSerializable{

    var call : Call? = null

    fun request(){
        this.call = httpClient.newCall(Request.Builder().apply {
            url(url + path)
            post(LHRequestBody(this@Req))
        }.build()).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    callback?.onFailed(NETWORK_ERROR, e)
                }

                override fun onResponse(call: Call?, response: Response?) {
                    if (response?.code() == 200){
                        val body = response.body()?.string()

                        val t = object : TypeToken<Map<String, Any>>(){}.type
                        val map = responesGson.fromJson<Map<String, Any>>(body, t)
                        val resp = Resp<T>()
                        resp.code = map["code"].toString().toDouble().toInt()
                        resp.sysTime = map["sysTime"].toString().toDouble().toLong()
                        resp.data = responesGson.fromJson<T>(responesGson.toJson(map["data"]), respClz)

                        if (resp.code == SUCCESS){
                            callback?.onSuccess(resp)
                        }else {
                            callback?.onFailed(resp.code, null)
                        }
                    }else {
                        callback?.onFailed(NETWORK_ERROR, null)
                    }
                }
            })
        }
    }


    fun cancel(){
        if (this.call == null){
            return
        }
        if (this.call?.isCanceled!!){
            this.call?.cancel()
            this.call = null
        }
    }

    inner class LHRequestBody(private val req : Req<T>) : RequestBody() {

        override fun contentType(): MediaType? = MediaType.parse("application/json")

        override fun writeTo(sink: BufferedSink?) {
            sink?.write(requestGson.toJson(req).toByteArray())
        }
    }
}

open class Resp<T>(var code : Int = 0, var data : T? = null, var sysTime : Long = 0) : GsonSerializable{
    constructor() : this(0, null, 0)
}

interface HttpCallback<in T>{
    fun onSuccess(t : T)

    fun onFailed(code : Int, e : Exception?)
}
