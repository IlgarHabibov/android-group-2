package az.altacademy.androidgroup2

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.text.isDigitsOnly
import az.altacademy.androidgroup2.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.File

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var uri: Uri? = null

    private val firstActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { data ->
            if (data.resultCode == Constants.FIRST_ACTIVITY_RESULT_CODE) {
                val result = data.data?.getStringExtra("first")
                Toast.makeText(this, "result = $result", Toast.LENGTH_SHORT).show()
            }
        }

    private val cameraRequest =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isPictureTaken ->
            if (uri != null && isPictureTaken) {
                binding?.imageView?.setImageURI(uri)
            }
        }


    private val cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                takePicture()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val takePicturePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val cameraPermissionResult = result[Manifest.permission.CAMERA]
            val writePermissionResult = result[Manifest.permission.WRITE_EXTERNAL_STORAGE]
            if (cameraPermissionResult == true && writePermissionResult == true) {
                takePicture()
            }
        }

    private val phonePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                val phone = binding?.nameInputEditText?.text?.toString()
                call(phone ?: "")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initViews()


    }

    private fun initViews() {

        binding?.nameInputEditText?.setText("0")

        binding?.done?.setOnClickListener {

//            sendText()
//            phonePermission.launch(Manifest.permission.CALL_PHONE)

            takePicturePermission.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }
    }

    private fun takePicture() {
        uri = null
        uri = createTempPictureUri()
        if (this.uri != null){
            cameraRequest.launch(uri!!)
        }

    }

    private fun onNextClick() {
        val text = binding?.nameInputEditText?.text.toString()
        val number = if (text.isDigitsOnly()) text.toInt() else 0
        val intent = Intent(this, FirstActivity::class.java)

        intent.putExtra(Constants.FULL_NAME, number)
        intent.putExtra(Constants.AGE, 100)
//        startActivity(intent)
        firstActivityResult.launch(intent)
    }

    private fun createTempPictureUri(
        fileName: String = "picture_${System.currentTimeMillis()}",
        fileExtension: String = ".png",
        provider: String = "az.altacademy.androidgroup2.fileprovider",
    ): Uri {
        val tempFile = File.createTempFile(
            fileName, fileExtension, externalCacheDir
        )
        tempFile.createNewFile()
        return FileProvider.getUriForFile(applicationContext, provider, tempFile)
    }


    private fun sendIntent() {
        val url = "https://atlacademy.az/"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun dial(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $phoneNumber"))
        startActivity(intent)
    }

    private fun call(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel: $phoneNumber"))
        startActivity(intent)
    }


    private fun sendText() {
        val text = "Hello Android"
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, "Ne ile paylasmaq isteyirsiniz ... "))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        firstActivityResult.unregister()
        cameraRequest.unregister()
        cameraPermission.unregister()
    }

}