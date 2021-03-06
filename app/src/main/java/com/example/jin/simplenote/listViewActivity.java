package com.example.jin.simplenote;

/**
 * Created by Jin on 2014-12-22.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class listViewActivity extends Activity {
    private DBAdapter mDb;
    private ArrayList<Info> mInfo;
    private ArrayAdapter<Info> mAdapter;

    ListView listView;
    Intent intent;
    Button btnGraph;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);


        listView=(ListView) findViewById(R.id.listView);
        btnGraph = (Button) findViewById(R.id.btnGraph);
        intent=getIntent();

        mDb = new DBAdapter(this);
        mInfo = mDb.getAllInfo();
        mAdapter = new ArrayAdapter<Info>(this, android.R.layout.simple_list_item_1, mInfo);
        listView.setAdapter(mAdapter);


        //Below if statement is for main btn
        String x = "";
        if(!x.equals(intent.getStringExtra("edit2"))){
            mDb.insertInfo(intent.getStringExtra("edit1"), intent.getStringExtra("edit2"), intent.getStringExtra("edit3"));
        }
        refreshList();

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                final Info i = mInfo.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(listViewActivity.this);
                builder.setTitle("");

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDb.deleteInfo(i.getId());
                        refreshList();
                    }
                });

                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                   @Override
                public void onClick(DialogInterface dialog, int which) {
                       Intent intent1 = new Intent(listViewActivity.this, EditActivity.class);
                       intent1.putExtra("edit1",i.getMeeting());
                       intent1.putExtra("edit2",i.getTime());
                       intent1.putExtra("edit3",i.getRate());
                       intent1.putExtra("edit4",i.getId()); // ID is needed to update the info later

                       listViewActivity.this.startActivityForResult(intent1, 0);
                       //mDb.deleteInfo(i.getId()); DO NOT DELETE; UPDATE
                       refreshList();

                   }
                });

                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                return false;

            }
        });

        btnGraph.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v){
               Intent intent2 = new Intent(listViewActivity.this, GraphActivity.class);
               listViewActivity.this.startActivity(intent2);
           }
        });

    }

    //onActivityResult function is for edit activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){ //USE INTENT INSTEAD
        super.onActivityResult(requestCode,resultCode,data);

        int id = data.getIntExtra("edit4",0);
        Info i = new Info(id,data.getStringExtra("edit1"), data.getStringExtra("edit2"), data.getStringExtra("edit3"));
        //mDb.insertInfo(data.getStringExtra("edit1"), data.getStringExtra("edit2"), data.getStringExtra("edit3"));
        mDb.updateInfo(i);
        refreshList();

            }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Delete All");
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Erase hard drive")
                .setMessage("Are you sure?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mDb.deleteAll();
                        refreshList();
                    }
                })
                .setNegativeButton("No",null)
                .show();


        return super.onOptionsItemSelected(item);
    }

    protected void onDestroy() {
        mDb.close();
        super.onDestroy();
    }

    private void refreshList() {
        mInfo.clear();
        mInfo.addAll(mDb.getAllInfo());
        mAdapter.notifyDataSetInvalidated();
}

}
