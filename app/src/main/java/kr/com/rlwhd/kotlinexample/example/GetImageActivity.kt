package kr.com.rlwhd.kotlinexample.example

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_get_image.*
import kr.com.rlwhd.kotlinexample.R
import org.jetbrains.anko.toast
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class GetImageActivity : AppCompatActivity(), PermissionListener {
    private val TAG = javaClass.simpleName
    private var isPermission = true
    private val PICK_FROM_ALBUM = 1
    private val PICK_FROM_CAMERA = 2
    private var tempFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_image)

        tedPermission()
        lottieAnim()

        bt_get_image.setOnClickListener {
            if (isPermission) goToAlbum()
            else toast(resources.getString(R.string.permission_2))
        }

        bt_camera.setOnClickListener {
            if (isPermission) takePhoto()
            else toast(resources.getString(R.string.permission_2))
        }
    }

    private fun lottieAnim() {
        lav_anim.setAnimation("animation_switch.json")
        lav_anim.setOnClickListener {
            lav_anim.playAnimation()
        }
//        lav_anim.loop(true)
        lav_anim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {

            toast("취소 되었습니다.")

            if (tempFile?.exists()!!) {
                if (tempFile?.delete()!!) {
                    Log.e(TAG, tempFile?.absolutePath + " 삭제 성공")
                    tempFile = null
                }
            }
            return
        }

        if (requestCode == PICK_FROM_ALBUM) {
            val photoUri = data?.data
            Log.e(TAG, "PICK_FROM_ALBUM photoUri : $photoUri")

            var cursor: Cursor? = null

            try {
                val proj = arrayOf(MediaStore.Images.Media.DATA)

                assert(photoUri != null)
                cursor = contentResolver.query(photoUri, proj, null, null, null)

                assert(cursor != null)
                val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

                cursor.moveToFirst()

                tempFile = File(cursor.getString(column_index))
                Log.e(TAG, "tempFile Uri : ${Uri.fromFile(tempFile)}")
            } finally {
                cursor?.close()
            }
            setImage()
        } else if (requestCode == PICK_FROM_CAMERA) {
            setImage()
        }
    }

    /**
     *  앨범에서 이미지 가져오기
     */
    private fun goToAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    /**
     *  카메라에서 이미지 가져오기
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            tempFile = createImageFile()
        } catch (e: IOException) {
            toast("이미지 처리 오류! 다시 시도해주세요.")
            finish()
            Log.e(TAG, "Image Create Error - $e")
        }
        if (tempFile != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val photoUri = FileProvider.getUriForFile(this, "{package name}.provider", tempFile!!)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, PICK_FROM_CAMERA)
            } else {
                val photoUri = Uri.fromFile(tempFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, PICK_FROM_CAMERA)
            }
        }
    }

    /**
     *  폴더 및 파일 만들기
     */
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // 이미지 파일 이름 ( blackJin_{시간}_ )
        val timeStamp = SimpleDateFormat("HHmmss").format(Date())
        val imageFileName = "example_" + timeStamp + "_"

        // 이미지가 저장될 폴더 이름 ( blackJin )
        val storageDir = File("${Environment.getExternalStorageDirectory()}/blackJin/")
        if (!storageDir.exists()) storageDir.mkdirs()
        return File.createTempFile(imageFileName, ".jpg", storageDir) // 빈 파일 생성
    }

    /**
     *  tempFile 을 bitmap 으로 변환 후 ImageView 에 설정한다.
     */
    private fun setImage() {
        val options = BitmapFactory.Options()
        val originalBm = BitmapFactory.decodeFile(tempFile?.absolutePath, options)
        Log.d(TAG, "setImage : ${tempFile?.absolutePath}")

        iv_get_image.setImageBitmap(originalBm)

        /**
         *  tempFile 사용 후 null 처리를 해줘야 합니다.
         *  (resultCode != RESULT_OK) 일 때 tempFile 을 삭제하기 때문에
         *  기존에 데이터가 남아 있게 되면 원치 않은 삭제가 이뤄집니다.
         */
        tempFile = null
    }

    /**
     *  권한 설정
     */
    private fun tedPermission() {
        TedPermission.with(this)
            .setPermissionListener(this)
            .setRationaleMessage(resources.getString(R.string.permission_1))
            .setDeniedMessage(resources.getString(R.string.permission_2))
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
            .check()

    }

    override fun onPermissionGranted() {
        isPermission = true
    }

    override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
        isPermission = false
    }

}

















