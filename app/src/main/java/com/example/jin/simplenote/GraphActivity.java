package com.example.jin.simplenote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
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
            //series.appendData(new DataPoint(i,Integer.parseInt(mInfo.get(j).getRate())),true,1000);
            series.appendData(new DataPoint(StringToDate(mInfo.get(j).getTime()),Integer.parseInt(mInfo.get(j).getRate())),true,1000);
            graph.refreshDrawableState();
        }

        graph.addSeries(series);


        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);


        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);



        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(GraphActivity.this));

        if (dataSize == 1) {
            graph.getGridLabelRenderer().setNumHorizontalLabels(1); // only 4 because of the space
        }
        else if(dataSize == 2){
            graph.getGridLabelRenderer().setNumHorizontalLabels(2); // only 4 because of the space
        }
        else {
            graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
        }
// set manual x bounds to have nice steps
  /*      graph.getViewport().setMinX(d1.getTime());
        graph.getViewport().setMaxX(d3.getTime());
        graph.getViewport().setXAxisBoundsManual(true); */

    }
    public void onBackPressed() {
        GraphActivity.this.finish();
    }

    public Date StringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy hh:mm:ss aa");

        try {

            Date date1 = formatter.parse(dateString);
            return date1;

        } catch (ParseException e) {
            e.printStackTrace();
            Date date = new Date(1);
            return date; //NotSureAboutThis
        }
    }
}

