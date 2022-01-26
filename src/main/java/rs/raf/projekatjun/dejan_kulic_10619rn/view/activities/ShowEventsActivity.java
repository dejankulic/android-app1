package rs.raf.projekatjun.dejan_kulic_10619rn.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import rs.raf.projekatjun.dejan_kulic_10619rn.R;
import rs.raf.projekatjun.dejan_kulic_10619rn.database.RoomDatabase;
import rs.raf.projekatjun.dejan_kulic_10619rn.database.entity.Meeting;
import rs.raf.projekatjun.dejan_kulic_10619rn.view.recycler.adapter.MeetingAdapter;
import rs.raf.projekatjun.dejan_kulic_10619rn.view.recycler.differ.DiffItemCallback;
import rs.raf.projekatjun.dejan_kulic_10619rn.viewModel.MeetingViewModel;
import timber.log.Timber;

public class ShowEventsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MeetingViewModel meetingViewModel;
    private MeetingAdapter meetingAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);

        init();

    }
    private void init(){
        initView();
        initRecycler();
        initListeners();
        initObservers();

    }
    private void initListeners(){

    }


    private void initObservers(){
        final RoomDatabase myRoomDatabase = RoomDatabase.getDatabase(this);

        myRoomDatabase.meetingDao().getAllData().observe(ShowEventsActivity.this,
                new Observer<List<Meeting>>() {
                    @Override
                    public void onChanged(List<Meeting> meetings) {
                        meetingAdapter.submitList(meetings);
                    }
                });
    }
    private void initView(){
        recyclerView = findViewById(R.id.recyclerView);
    }
    private void initRecycler(){
        meetingAdapter = new MeetingAdapter(new DiffItemCallback(), meeting ->{
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, meeting.getName() + "\n" +
                    meeting.getDescription() + "\n"+meeting.getTime() + "\n" + meeting.getUrl());
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return null;
        },delete ->{

            final RoomDatabase myRoomDatabase = RoomDatabase.getDatabase(this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Timber.e("@@" + delete.getName());
                    myRoomDatabase.meetingDao().delete(delete);

                }
            }).run();
            return null;

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(meetingAdapter);
    }

    private void obrisi(Meeting meeting){
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}