package com.example.komunikatorbs

import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val ID = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        onClick()


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun onClick(): Boolean {
        buttonLogin.setOnClickListener {
            if(editTextLogin.text.isNotEmpty()){
                showShoutbox()
                getMsg()
            }
        }

        settings.setOnClickListener{
            drawer_layout.closeDrawer(GravityCompat.START)
            showLogin()
        }
        shoutbox.setOnClickListener {
            showShoutbox()
            drawer_layout.closeDrawer(GravityCompat.START)
            getMsg()
        }

        buttonSend.setOnClickListener{
            if(editTextSend.text.isNotEmpty() && editTextLogin.text.isNotEmpty()){
                val json = JSONObject()
                json.put("content", editTextSend.text)
                json.put("login", editTextLogin.text)

                HttpTask {
                    if (it == null) {
                        println("connection error")
                        return@HttpTask
                    }
                    println(it)
                }.execute("POST", "http://tgryl.pl/shoutbox/message", json.toString())
                getMsg()
                editTextSend.setText("")
            }
        }

        listViewMSG.setOnItemClickListener { _, _, position, _ ->
            //Toast.makeText(this, "Position Clicked:"+" "+position,Toast.LENGTH_SHORT).show()
            //Toast.makeText(this, ID.get(position),Toast.LENGTH_SHORT).show()
            //removeMsg(ID.get(position))
            //Toast.makeText(this, listViewMSG.getChildAt(position),Toast.LENGTH_SHORT).show()

            //getMsg()
        }
        return true
    }


    operator fun JSONArray.iterator(): Iterator<JSONObject> =
        (0 until length()).asSequence().map { get(it) as JSONObject }.iterator()

    class HttpTask(callback: (String?) -> Unit) : AsyncTask<String, Unit, String>()  {

        var callback = callback

        override fun doInBackground(vararg params: String): String? {
            val url = URL(params[1])
            val httpClient = url.openConnection() as HttpURLConnection
            httpClient.setReadTimeout(10000)
            httpClient.setConnectTimeout(10000)
            httpClient.requestMethod = params[0]

            if (params[0] == "POST") {
                httpClient.instanceFollowRedirects = false
                httpClient.doOutput = true
                httpClient.doInput = true
                httpClient.useCaches = false
                httpClient.setRequestProperty("Content-Type", "application/json; charset=utf-8")
            }
            try {
                if (params[0] == "POST") {
                    httpClient.connect()
                    val os = httpClient.getOutputStream()
                    val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
                    writer.write(params[2])
                    writer.flush()
                    writer.close()
                    os.close()
                }
                if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                    val stream = BufferedInputStream(httpClient.inputStream)
                    val data: String = readStream(inputStream = stream)
                    return data
                } else {
                    println("ERROR ${httpClient.responseCode}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                httpClient.disconnect()
            }

            return null
        }

        fun readStream(inputStream: BufferedInputStream): String {
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            bufferedReader.forEachLine { stringBuilder.append(it) }
            return stringBuilder.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            callback(result)
        }
    }

    fun showShoutbox(){
        Layout1.visibility = View.INVISIBLE
        Layout2.visibility = View.VISIBLE
    }

    fun showLogin(){
        Layout1.visibility = View.VISIBLE
        Layout2.visibility = View.INVISIBLE
    }

    fun getMsg(){
        HttpTask {
            if (it == null) {
                println("connection error")
                return@HttpTask
            }
            val msg = arrayListOf<String>()
            ID.clear()
            for (json in JSONArray(it)) {
                println(json)
                msg.add(json.getString("content")+" From: " + json.getString("login"))
                ID.add(json.getString("id"))
            }
            val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,msg)
            listViewMSG.adapter = adapter
        }.execute("GET", "http://tgryl.pl/shoutbox/messages")
    }

    fun removeMsg(del: String){
        HttpTask {
            if (it == null) {
                println("connection error")
                return@HttpTask
            }
        }.execute("DELETE", "http://tgryl.pl/shoutbox/messages/$del")
        println("http://tgryl.pl/shoutbox/messages/$del")
    }

    fun replaceMsg(del: String){
        HttpTask {
            if (it == null) {
                println("connection error")
                return@HttpTask
            }
        }.execute("DELETE", "http://tgryl.pl/shoutbox/messages/$del")
        println("http://tgryl.pl/shoutbox/messages/$del")
    }

}
