package rs.raf.projekatjun.dejan_kulic_10619rn.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.raf.projekatjun.dejan_kulic_10619rn.R;
import rs.raf.projekatjun.dejan_kulic_10619rn.database.RoomDatabase;
import rs.raf.projekatjun.dejan_kulic_10619rn.database.entity.Meeting;
import rs.raf.projekatjun.dejan_kulic_10619rn.models.CityTime;
import rs.raf.projekatjun.dejan_kulic_10619rn.retrofit.CityApi;
import rs.raf.projekatjun.dejan_kulic_10619rn.retrofit.CityTimeService;
import timber.log.Timber;

public class AddEventActivity extends AppCompatActivity {

    private AutoCompleteTextView autocomp;
    private Button setDate;
    private Button setTime;
    private Spinner spinn;
    private Button checkTimeButton;
    private Button saveButton;

    //view
    private EditText nameEt;
    private EditText descEt;
    private EditText urlEt;

    final CityTimeService cityTimeService = new CityTimeService();

    private CityApi cityApi;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        init();

    }
    private void init(){
        initView();
        initAutoComplete();
        initSpinner();
        initListeners();
        initObservers();

    }

    private void initObservers(){
        cityTimeService.getEasternStandardTime().observe(
                this,v->{
                    checkTimeButton.setText(v.getDatetime());
                }
        );
    }

    String hoursBaza = new String();
    String minutesBaza = new String();
    private void initListeners(){
        saveButton.setOnClickListener(v-> {
            final RoomDatabase myRoomDatabase = RoomDatabase.getDatabase(this);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    long personId = myRoomDatabase.meetingDao().insert(new Meeting(
                            nameEt.getText().toString(), descEt.getText().toString(),
                            "Test", "Hours: "+ hoursBaza + ",minutes: "+ minutesBaza, urlEt.getText().toString(),spinn.getSelectedItem().toString()
                    ));
                }
            }).start();

        });
        checkTimeButton.setOnClickListener(v->{
            cityTimeService.invokeCityService("Europe", autocomp.getText().toString());
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hoursBaza = String.format("%02d", selectedHour);
                        minutesBaza = String.format("%02d", selectedMinute);
                        setTime.setText( String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
    private void initAutoComplete(){
        Resources res = getResources();
        String[] gradovi = res.getStringArray(R.array.cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line,gradovi);
        autocomp.setAdapter(adapter);
        autocomp.setThreshold(1);
        autocomp.setDropDownWidth(300);
    }
    private void initView(){
        saveButton = findViewById(R.id.saveButton);
        checkTimeButton = findViewById(R.id.checkTimeButton);
        autocomp = findViewById(R.id.autocmp);
        setDate = findViewById(R.id.setDateButton);
        setTime = findViewById(R.id.setTimeButton);
        spinn = findViewById(R.id.spinner);
        nameEt = findViewById(R.id.eventNameEt);
        descEt = findViewById(R.id.description);
        urlEt = findViewById(R.id.url);
    }
    private void initSpinner(){
        String[] arraySpinner = new String[] {
                "High", "Medium", "Low"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(adapter);
    }


    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH );

        setDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}