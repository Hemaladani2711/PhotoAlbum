package com.example.hemaladani.photoalbum.Data;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemaladani on 10/9/17.
 */

public class ImagesFetchr {

    private static final String TAG="ImagesFetchr";
    private static final String url="https://s3.amazonaws.com/sc.va.util.weatherbug.com/interviewdata/mobilecodingchallenge/sampledata.json";
    private static final String urlImage="https://s3.amazonaws.com/sc.va.util.weatherbug.com/interviewdata/mobilecodingchallenge/";

    public List<Photos> fetchItems() throws JSONException {
        String Url= Uri.parse(url).buildUpon().build().toString();
        List<Photos> items=new ArrayList<>();
        try {

            String jsonString=getUrlString(Url);
            Log.i(TAG,jsonString);
            parseItems(items,jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;

    }
    private void parseItems(List<Photos>items,String jsonObject) throws JSONException {
        JSONArray jsonArray=new JSONArray(jsonObject);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject1=jsonArray.getJSONObject(i);
            Photos photos =new Photos();
            photos.setmImageName(jsonObject1.getString("filename"));
            photos.setmTitle(jsonObject1.getString("title"));
            photos.setmUrl(urlImage+jsonObject1.getString("filename"));
            items.add(photos);
            Log.i("JSonArray",photos.getmUrl());

        }
    }


    public byte[] getUrlBytes(String urlSpecs) throws IOException {
        URL url=new URL(urlSpecs);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        InputStream inputStream=connection.getInputStream();
        if(connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
            throw new IOException(connection.getResponseMessage()+" with "+urlSpecs);
        }

        int bytesRead=0;
        byte[]buffer=new byte[1024];
        while ((bytesRead=inputStream.read(buffer))>0){
            outputStream.write(buffer,0,bytesRead);
        }
        outputStream.close();
        connection.disconnect();
        return outputStream.toByteArray();
    }

    public String getUrlString(String urlSpecs)throws IOException{
        return new String(getUrlBytes(urlSpecs));
    }
}
