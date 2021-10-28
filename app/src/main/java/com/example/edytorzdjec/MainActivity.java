package com.example.edytorzdjec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                imgView.setImageResource(R.drawable.image1);
            }
        });

        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgView.setImageResource(R.drawable.image2);
            }
        });
    }
}