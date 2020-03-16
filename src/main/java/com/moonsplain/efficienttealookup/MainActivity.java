package com.moonsplain.efficienttealookup;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: add search bar at top, once data is available to peruse
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.done);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Will lead to custom input screen", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //TODO: insert tea from custom input screen
            }
        });
        TextView clock = findViewById(R.id.clock);

        ArrayList<Tea> teas = new ArrayList<Tea>();
        teas.add( new Tea("Sencha", 73, 90, 1));
        teas.add( new Tea("English Breakfast", 100, 300, 1));

        final ListView listV = findViewById(R.id.listV);
        ArrayAdapter<Tea> adapter = new ArrayAdapter<Tea>(this,
                android.R.layout.simple_list_item_1, teas);

        listV.setAdapter(adapter);


        listV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            TextView clock = findViewById(R.id.clock);
            boolean go = false;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*TODO: set timer, use AlarmManager
                   https://developer.android.com/reference/android/app/AlarmManager.html
                    IF CLOCK TEXT not "" or "done":
                        clear current clock
                */

                final Tea t = (Tea) parent.getItemAtPosition(position);
                int time = t.getTime();
                CountDownTimer c = new CountDownTimer(time*1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        clock.setText(t.name +":\n"+String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        //TODO: done noise
                        clock.setText("done!\n");
                        mp.start();
                    }
                }.start();

            }

        });
        Tea greenTest = new Tea("Brost", 73, 3, 1);
        greenTest.setColor("#E9D65E");

        insertTea(greenTest, adapter, teas);
        /*TODO: populate listV with tea data

        populate(listV, teas); //put resources onscreen

        */
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

    public void insertTea(Tea t, ArrayAdapter adapter, ArrayList<Tea> teas){
        int temp = (int)t.temp;
        //todo: check if F, convert
        teas.add(t);

        adapter.notifyDataSetChanged();
    }


}
