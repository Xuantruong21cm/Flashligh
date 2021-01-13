package com.example.flashligh.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Exception

class SMS_Receiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"okokok",Toast.LENGTH_SHORT).show()
        var cameraManager : CameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            var cameraID : String = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraID,true)
        }catch (e : Exception){}
    }
}