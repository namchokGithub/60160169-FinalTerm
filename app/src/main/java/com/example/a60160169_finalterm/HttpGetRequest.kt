package com.example.a60160169_finalterm

import android.os.AsyncTask
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class HttpGetRequest : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg urls: String?): String {
        var result: String? = ""
        try {
            val httpGet = HttpGet(urls.get(0))
            val client: HttpClient = DefaultHttpClient()
            val response: HttpResponse = client.execute(httpGet)
            val statusCode: Int = response.statusLine.statusCode
            if (statusCode == 200) {
                val inputStream: InputStream = response.entity.content
                val reader = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    result += line
                }
            }
        } catch (e: ClientProtocolException) {
        } catch (e: IOException) {
        }
        return result!!
    }
}