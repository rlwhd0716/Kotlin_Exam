package kr.com.rlwhd.kotlinexample.example.gallery

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_my_gallery.*
import kr.com.rlwhd.kotlinexample.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import kotlin.concurrent.timer

class MyGalleryActivity : AppCompatActivity() {
    private val TAG: String = this.javaClass.simpleName
    val REQUEST_READ_EXTERNAL_STORAGE = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_gallery)

        checkPermission()
    }

    private fun getAllPhotos() {
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")

        val fragments = ArrayList<Fragment>()
        if (cursor != null){
            while (cursor.moveToNext()) {
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.e(TAG, uri)
                fragments.add(PhotoFragment.newInstance(uri))
            }
            cursor.close()
        }

        val adapter = MyGalleryAdapter(supportFragmentManager)
        adapter.updateFragments(fragments)
        vp_gallery_frame.adapter = adapter

        timer(period = 3000){
            runOnUiThread {
                if(vp_gallery_frame.currentItem < adapter.count - 1) {
                    vp_gallery_frame.currentItem = vp_gallery_frame.currentItem + 1
                } else {
                    vp_gallery_frame.currentItem = 0
                }
            }
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                alert(
                    "사진 정보를 얻으려면 외부 저장소 권한이 필수로 필요합니다.",
                    "필수 권한") {
                    yesButton {
                        ActivityCompat.requestPermissions(
                            this@MyGalleryActivity,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_READ_EXTERNAL_STORAGE
                        )
                    }
                    noButton { }
                }.show()
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERNAL_STORAGE)
            }
        } else {
            getAllPhotos()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getAllPhotos()
                } else {
                    toast("권한 거부 됨")
                }
                return
            }
        }
    }
}