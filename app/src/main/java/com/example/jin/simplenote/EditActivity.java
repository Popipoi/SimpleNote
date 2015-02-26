package com.example.jin.simplenote;

/**
 * Created by Jinhyeok on 2015-02-25.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



//This is separate the activity for edit function which use same layout as MainActivity
//Need this because data need to be received from list view activity.
//Only difference is that this receives data and display them to edit(using constant time).
//Also finish() the activity when edit is done;
//returns original data when menu is pressed
public class EditActivity extends ActionBarActivity {

    EditText editText1;
    Button btnSave, btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Intent intent = getIntent();
        editText1 = (EditText) findViewById(R.id.editText);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnMain = (Button) findViewById(R.id.btnMain);

        editText1.setText(intent.getStringExtra("edit1"));

        btnSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1 = new Intent();
                intent1.putExtra("edit1",editText1.getText().toString());
                intent1.putExtra("edit2",intent.getStringExtra("edit2"));
                setResult(RESULT_OK,intent1);
                EditActivity.this.finish();
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent2 = new Intent ();
                intent2.putExtra("edit1",intent.getStringExtra("edit1"));
                intent2.putExtra("edit2",intent.getStringExtra("edit2"));
                setResult(RESULT_OK,intent2);
                EditActivity.this.finish();
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
