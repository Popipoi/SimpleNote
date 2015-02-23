package com.example.jin.simplenote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity {

    EditText editText1;
    Button btnSave, btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText1 = (EditText) findViewById(R.id.editText);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnMain = (Button) findViewById(R.id.btnMain);

        btnSave.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               Intent intent1 = new Intent(MainActivity.this, listViewActivity.class);
               intent1.putExtra("edit1",editText1.getText().toString());


               //Need to put present time into edit2 by intent.putExtra

               Calendar cal = Calendar.getInstance(TimeZone.getDefault());
               intent1.putExtra("edit2",cal.getTime().toLocaleString());
               startActivityForResult(intent1, 0);
               editText1.setText("");
           }
        });

        btnMain.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent2 = new Intent (MainActivity.this, listViewActivity.class);
                intent2.putExtra("edit1","");
                intent2.putExtra("edit2","");
                MainActivity.this.startActivityForResult(intent2,0);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
