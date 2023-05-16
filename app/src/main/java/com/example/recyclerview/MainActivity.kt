package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // getting the recyclerview

       // val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val recyle=binding?.recyclerview

        // this creates a vertical layout Manager
        recyle?.layoutManager = LinearLayoutManager(this)

        val audioFiles = getAudioFilesFromStorage()
        val adapter = AudioAdapter(audioFiles as List<audioFileModel>)
        recyle?.adapter = adapter

        // ArrayList of class ItemsViewModel
        //val data = ArrayList<ItemsViewModel>()

        //ArrayList of class audioFileModel
        val data=ArrayList<audioFileModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        //for (i in 1..200) {
          //  data.add(ItemsViewModel(R.drawable.download, "Item " + i))
        //}



        // This will pass the ArrayList to our Adapter
       // val adapter = RecycleAdapter(data)

        // Setting the Adapter with the recyclerview
        recyle?.adapter = adapter

    }

    private fun getAudioFilesFromStorage(): List<audioFileModel> {
        val audioFiles = mutableListOf<audioFileModel>()
        val projection = arrayOf(
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DATA
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"
        val cursor = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )
        cursor?.use {
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val pathColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            while (it.moveToNext()) {
                val name = it.getString(nameColumn)
                val path = it.getString(pathColumn)
                audioFiles.add(audioFileModel(name, path))
            }
        }
        return audioFiles
    }


}