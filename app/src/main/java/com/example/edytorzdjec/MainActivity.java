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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variables

    CheckBox checkbox;
    ImageView imgView;
    ImageButton imgBtn1;
    ImageButton imgBtn2;
    ImageButton imgBtn3;
    ImageButton imgBtn4;
    Button setRotate;
    Button setScale;
    EditText rotateText;
    EditText scaleText;
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
        setRotate = (Button) findViewById(R.id.set_rotate);
        setScale = (Button) findViewById(R.id.set_scale);
        rotateText = (EditText) findViewById(R.id.rotate);
        scaleText = (EditText) findViewById(R.id.scale);

        //camera

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            },100);
        }

        //checbox

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

        //next button

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPicture++;
                currentPicture = currentPicture % images.length;
                imgView.setImageResource(images[currentPicture]);
            }
        });

        //previous button

        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPicture++;
                currentPicture =  currentPicture % images2.length;
                imgView.setImageResource(images2[currentPicture]);
            }
        });

        //camera button

        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        //clear button

        imgBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgView.setImageResource(0);
            }
        });

        //set rotate

        setRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(rotateText.getText().toString());
                    imgView.setRotation(x);
            }
        });

        //set scale

        setScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(scaleText.getText().toString());
                int y = Integer.parseInt(scaleText.getText().toString());
                imgView.setScaleX(x);
                imgView.setScaleY(y);
            }
        });
    }

    //end camera

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(bitmap);
        }
    }
}