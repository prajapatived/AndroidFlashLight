package com.dev.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dev.flashlight.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.btn.getText().toString().equals("on")){
                binding.btn.setText(R.string.off);
                changeLightState(true);
                
                }else {
                    binding.btn.setText(R.string.on);
                    changeLightState(false);
                }
            }
        });

    }

    private void changeLightState(boolean state) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            CameraManager cameraManager=(CameraManager) getSystemService(CAMERA_SERVICE);
            String camId=null;

            try {
                camId=cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(camId, state);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        binding.btn.setText(R.string.on);
    }
}