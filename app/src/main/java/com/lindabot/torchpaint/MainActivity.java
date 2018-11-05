package com.lindabot.torchpaint;

import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CameraManager cameraManager;
    String cameraID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try{
            cameraID = cameraManager.getCameraIdList()[0];
        } catch (Exception e) {
            Log.d("Error", "No camera found");
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
           SetTorch(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            SetTorch(true);
            return true;
        }
        return false;
    }

    public void SetTorch(boolean state){
        try{
            cameraManager.setTorchMode(cameraID, state);
        } catch (Exception e) {
            Log.d("Error", "Can't turn on torch");
        }
    }

}
