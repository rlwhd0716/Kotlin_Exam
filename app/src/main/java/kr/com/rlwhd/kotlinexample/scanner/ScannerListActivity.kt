package kr.com.rlwhd.kotlinexample.scanner

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.labters.documentscanner.ImageCropActivity
import com.labters.documentscanner.helpers.ScannerConstants
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.content_todo_list.*
import kr.com.rlwhd.kotlinexample.R
import kr.com.rlwhd.kotlinexample.data.TodoData
import kr.com.rlwhd.kotlinexample.example.todo.EditActivity
import kr.com.rlwhd.kotlinexample.example.todo.TodoListAdapter
import kr.com.rlwhd.kotlinexample.scanner.ScannerActivity
import org.jetbrains.anko.startActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ScannerListActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    val realm = Realm.getDefaultInstance()
    private lateinit var mCurrentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_list)
        setSupportActionBar(toolbar)

        // 전체 할 일 정보를 가져와서 날짜순으로 내림차순 정렬렬
        val realmResult = realm.where<TodoData>().findAll().sort("date", Sort.DESCENDING)
        val adapter = TodoListAdapter(realmResult)
        lv_todo_list.adapter = adapter

        realmResult.addChangeListener { _ -> adapter.notifyDataSetChanged() }

        lv_todo_list.setOnItemClickListener { parent, view, position, id ->
            // 할 일 수정
//            startActivity<EditActivity>("id" to id)
        }

//        ScannerConstants.selectedImageBitmap = btimap
//        startActivityForResult(Intent(MainActivity@this, ImageCropActivity::class.java),Constants.REQUEST_CROP)

        // 새 할 일 추가
        fab_todo_add.setOnClickListener {
            cameraIntent()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun cameraIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (e: IOException) {
                Log.e(TAG, "IOException - $e")
            }
            if (photoFile != null) {
                val builder = StrictMode.VmPolicy.Builder()
                StrictMode.setVmPolicy(builder.build())
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
                startActivityForResult(cameraIntent, 1231)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        val image = File.createTempFile(
            imageFileName, // prefix
            ".jpg", // suffix
            storageDir      // directory
        )

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.absolutePath
        return image
    }

}
