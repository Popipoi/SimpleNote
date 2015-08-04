package com.example.jin.simplenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sanhakim on 2015-07-30.
 */
public class GraphActivity extends Activity {
    private DBAdapter mDb;
    private ArrayList<Info> mInfo;

    //I do not think we need ArrayAdapter - Jin
    private ArrayAdapter<Info> mAdapter;

    Intent intent;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphview);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        intent = getIntent();

        //import data from db
        mDb = new DBAdapter(this);
        mInfo = mDb.getAllInfo();

        int dataSize = mInfo.size();

        //Plot Points
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
        });
        for (int i = 0, j = dataSize-1; i<dataSize; i++,j--) {
            series.appendData(new DataPoint(i,Integer.parseInt(mInfo.get(j).getRate())),true,1000);
        }

        graph.addSeries(series);

    }
    public void onBackPressed() {
        GraphActivity.this.finish();
    }

    public Date StringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
        final String NEW_FORMAT = "MM-dd-yyyy";

        try {

            Date date1 = formatter.parse(dateString);
            formatter.applyPattern(NEW_FORMAT);
            String newDateString = formatter.format(date1);
            Date date2 = formatter.parse(newDateString);
            return date2;

        } catch (ParseException e) {
            e.printStackTrace();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date date = new Date();
            return date; //NotSureAboutThis
        }
    }
}

