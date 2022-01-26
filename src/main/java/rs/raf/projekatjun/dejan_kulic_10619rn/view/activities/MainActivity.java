package rs.raf.projekatjun.dejan_kulic_10619rn.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

import rs.raf.projekatjun.dejan_kulic_10619rn.R;

public class MainActivity extends AppCompatActivity {

    private Button addEvent;
    private Button showEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        initView();
        initListeners();
    }
    private void initView(){
        addEvent = findViewById(R.id.addEventButton);
        showEvents = findViewById(R.id.showEventButton);


    }
    private void initListeners(){
        showEvents.setOnClickListener(v->{
            Intent intent = new Intent(this,ShowEventsActivity.class);
            startActivity(intent);
            finish();
        });
        addEvent.setOnClickListener(v->{
            Intent intent = new Intent(this,AddEventActivity.class);
            startActivity(intent);
            finish();
        });
    }
}