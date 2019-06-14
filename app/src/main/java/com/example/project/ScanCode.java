package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCode extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    ZXingScannerView ScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView= new ZXingScannerView(this);
        setContentView(ScannerView);
    }

    @Override
    public void handleResult(Result result)
    {
        JSONObject qrJSON = GenerateJson(result);

        Intent intent = new Intent();
        intent.putExtra("qrText", qrJSON.toString());
        setResult(RESULT_OK, intent);
        finish();
    }
    //json
    public JSONObject GenerateJson(Result result)
    {
        String resultScan = result.getText();

        String[] separated = resultScan.split("#");

        JSONObject json = new JSONObject();
        try {
            json.put("No Phone",separated[0]);
            json.put("Name",separated[1]);
            String jsonStr=json.toString();

            Log.d("hasil",jsonStr);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }
    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
    @Override
    protected void onStop(){
        super.onStop();
        ScannerView.stopCamera();
    }
}
