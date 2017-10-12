package com.example.hemaladani.photoalbum.View;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.hemaladani.photoalbum.Data.ImagesFetchr;
import com.example.hemaladani.photoalbum.Data.Photos;
import com.example.hemaladani.photoalbum.R;
import com.example.hemaladani.photoalbum.Data.photoAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG="MainActivity";
    RecyclerView recyclerView;
    List<Photos> items;

    @Override
    protected void onResume() {
        super.onResume();
        setUpAdapter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        items=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.containerview);



        int intOrientation = getResources().getConfiguration().orientation;
        if (intOrientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));         }
        else {
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));

        }

        new FetchItemTask().execute();

        Log.i(TAG,"Background Thread Started");


    }


    public void setUpAdapter(){

        recyclerView.setAdapter(new photoAdapter(items));

    }

    class FetchItemTask extends AsyncTask<Void,Void,List<Photos>> {


        @Override
        protected void onPostExecute(List<Photos> list) {
            super.onPostExecute(list);
            items=list;
            Log.i("List Size",""+list.size());
            setUpAdapter();
        }

        @Override
        protected List<Photos> doInBackground(Void... voids) {

            try {
                return new ImagesFetchr().fetchItems();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;


        }


    }

}
