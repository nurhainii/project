package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    Button btnscan;
    Button gen;
    ImageView img;
    public static EditText txtresult;
    String a;

    MultiFormatWriter multi= new MultiFormatWriter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnscan = (Button) findViewById(R.id.btn_scan);
        txtresult = (EditText) findViewById(R.id.txt_result);
        gen = (Button)findViewById(R.id.btnQR);
        img = (ImageView) findViewById(R.id.image);

        //button ngescan
        btnscan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
              //  String stxtresult=txtresult.getText().toString();
                // startActivity(new Intent(getApplicationContext(),ScanCodeActivity.class));
                Intent i = new Intent(MainActivity.this, ScanCode.class);
                startActivityForResult(i, 1);
            }
        });

        //button ngebuat qr
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = txtresult.getText().toString(); //masukin text ke string
                try {
                    BitMatrix bitMatrix = multi.encode(a, BarcodeFormat.QR_CODE,200,200); //buat masukin bitnya 0101,
                    // encode buat mengencode text2qr jadi QR_CODE(biasa diubah jadi yg lain),panjang lebar qrcode harus sama(200,200)
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix); //matrix diubah jadi bitmap
                    img.setImageBitmap(bitmap); //bitmap diubah jadi img
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if(resultCode == RESULT_OK) {
                txtresult.setText(data.getStringExtra("qrText"));
            }
        }
        else
        {
            if(resultCode == RESULT_OK)
            {
                txtresult.setText(data.getStringExtra("qrText"));
            }
        }

    }
}
