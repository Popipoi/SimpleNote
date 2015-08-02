package com.example.jin.simplenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by sanhakim on 2015-07-30.
 */
public class GraphActivity extends Activity {
    private DBAdapter mDb;
    private ArrayList<Info> mInfo;
    private ArrayAdapter<Info> mAdapter;

    Intent intent;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphview);
        intent=getIntent();

    }
    public void onBackPressed() {
        GraphActivity.this.finish();
    }
}
