package com.example.jin.simplenote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity {

    EditText editText1;
    Button btnSave, btnMain, btnGraph;
    SeekBar rateBar;
    String rating = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText1 = (EditText) findViewById(R.id.editText);
        btnGraph = (Button) findViewById(R.id.btnGraph);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnMain = (Button) findViewById(R.id.btnMain);
        rateBar = (SeekBar) findViewById(R.id.seekBar);

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, listViewActivity.class);
                intent1.putExtra("edit1", editText1.getText().toString());

                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                intent1.putExtra("edit2", cal.getTime().toLocaleString());
                editText1.setText("");

                intent1.putExtra("edit3", rating);
                startActivityForResult(intent1, 0);
            }
        });
        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GraphActivity1.class ));
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, listViewActivity.class);
                intent2.putExtra("edit1", "");
                intent2.putExtra("edit2", "");
                intent2.putExtra("edit3", "");
                MainActivity.this.startActivityForResult(intent2, 0);
            }
        });


        rateBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar rateBar, int progressValue, boolean fromUser) {
                progress = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"Rate: " + progress +" Recorded", Toast.LENGTH_SHORT).show();
                rating = Integer.toString(progress);
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
