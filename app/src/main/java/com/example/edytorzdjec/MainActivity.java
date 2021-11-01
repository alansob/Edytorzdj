package com.example.edytorzdjec;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox checkbox;
    ImageView imgView;
    ImageButton imgBtn1;
    ImageButton imgBtn2;
    ImageButton imgBtn3;
    ImageButton imgBtn4;
    private int currentPicture;
    int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    int[] images2 = {R.drawable.image4, R.drawable.image3, R.drawable.image2, R.drawable.image1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbox = (CheckBox) findViewById(R.id.checkBox);
        imgBtn1 = (ImageButton) findViewById(R.id.next);
        imgBtn2 = (ImageButton) findViewById(R.id.previous);
        imgBtn3 = (ImageButton) findViewById(R.id.camera);
        imgBtn4 = (ImageButton) findViewById(R.id.clear);
        imgView = (ImageView) findViewById(R.id.imageView);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            },100);
        }

        // int picture = 0;
       // int currentPicture = 0;

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox.isChecked()){
                    //Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_LONG).show();

                    imgView.setVisibility(view.INVISIBLE);
                }else{
                    imgView.setVisibility(view.VISIBLE);
                }
            }
        });
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPicture++;
                currentPicture = currentPicture % images.length;
                imgView.setImageResource(images[currentPicture]);

              /*  int picture = 0;
                int currentPicture = 0;

                if(currentPicture == 0){
                    imgView.setImageResource(R.drawable.image2);
                }
                if(currentPicture == 1){
                    imgView.setImageResource(R.drawable.image3);
                }
                if(currentPicture == 2){
                    imgView.setImageResource(R.drawable.image4);
                }
                if(currentPicture == 3){
                    imgView.setImageResource(R.drawable.image1);
                }*/
            }
        });

        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPicture++;
                currentPicture =  currentPicture % images2.length;
                imgView.setImageResource(images2[currentPicture]);
            }
        });

        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(bitmap);
        }
    }
}