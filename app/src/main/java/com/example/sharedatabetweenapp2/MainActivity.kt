package com.example.sharedatabetweenapp2

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.lang.Exception

const val first_app_pkg_name = "com.example.sharedatabetweenapp1"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_load_data.setOnClickListener {
            loadDataFromFirstApp()
        }
    }


    private fun loadDataFromFirstApp() {
        val applicationInfo =
            packageManager.getApplicationInfo(first_app_pkg_name, PackageManager.GET_META_DATA)

        val file_path = "${applicationInfo.dataDir}/files/ankitfile.txt"

      //  txt_first_app_data.text = "${applicationInfo.dataDir}/files/ankitfile.txt"


        // Read Data from file
        var fileInputStream: FileInputStream
        val outputData = StringBuffer()
        try {
            fileInputStream = FileInputStream(File(file_path))

            var read: Int = 0

            while ({ read = fileInputStream.read(); read }() != -1) {
                outputData.append(read.toChar())
            }

            txt_first_app_data.text = "${outputData.toString()} loaded from ${file_path}"
        } catch (e: Exception) {
            txt_first_app_data.text = "Error $e in loading data into ${file_path}"
        }
    }
}
