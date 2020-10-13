package com.example.textscanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static java.nio.file.Paths.get;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find imageview
        findViewById(R.id.imageId);
        //Find textview
        findViewById(R.id.textId);
        //Check app permisssions for Camera
        if(checkSelfPermission(Manifest.permission.CAMERA) !=  PackageManager.PERMISSION_GRANTED){
            //Grant permission
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
        }
    }

    //TODO: Complete this method(it is within the activity_main.xml)
    public void doProcess(View view) {
        //open camera > make an intent object
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        //Extract image
        Bitmap bitmap = (Bitmap) bundle.get("data");
        //Set image
        imageView.setImageBitmap(bitmap);
        //process image

    }
}