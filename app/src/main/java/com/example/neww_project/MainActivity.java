package com.example.neww_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.time.Period;

public class MainActivity extends AppCompatActivity {

    ImageButton simpleButton1, simpleButton2, simpleButton3;
    Button choiceButton, saveButton;
    SharedPreferences sharedpreference;
    TextView date, nyears, nmonths, ndays;
    Calendar c;
    DatePickerDialog dpd;
    PendingIntent pendingIntent;

    RadioButton rb1, rb2, rb3, rb4;
    LinearLayout var1, var2, var3, var4, choiceLayout;
    TimePicker time_picker1, time_picker2;
    int year = 0, month = 0, day = 0, hour = 0, minute = 0, nowYear = 0, nowMonth = 0, nowDay = 0, nowHour = 0, nowMinute = 0;
    int pickYear = 2000, pickMonth = 0, pickDay = 20000, years = 0, months = 0, days = 0;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 500;
    int stage = 0;

    @Override
    protected void onResume() {

        handler.postDelayed( runnable = new Runnable() {

            public void run() {

                if (pickYear != 2000) {
                GetDiff();}
                handler.postDelayed(runnable, delay);
            }
        }, delay);

        super.onResume();
        //FullScreencall();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SharedPreferences
        SharedPreferences prefs = this.getSharedPreferences("com.example.neww_project", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


        //Choice device
        if ((prefs.getInt("key", 0) != 1) && (prefs.getInt("key", 0) != 2) && (prefs.getInt("key", 0) != 3) && (prefs.getInt("key", 0) != 4)) {
            startActivity(new Intent(MainActivity.this, Choice.class));
        }

        //Pick date
        //date = (TextView) findViewById(R.id.textView);
        nyears = findViewById(R.id.years);
        nmonths = findViewById(R.id.months);
        ndays = findViewById(R.id.days);

        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        rb1 = findViewById(R.id.radio_b1);
        rb2 = findViewById(R.id.radio_b2);
        rb3 = findViewById(R.id.radio_b3);
        rb4 = findViewById(R.id.radio_b4);

        var1 = findViewById(R.id.var1);
        var2 = findViewById(R.id.var2);
        var3 = findViewById(R.id.var3);

        time_picker1 = findViewById(R.id.timePicker1);
        time_picker2 = findViewById(R.id.timePicker2);

        if (prefs.getInt("Year", 0) == 0 && prefs.getInt("key", 0) != 0)   {
            Toast.makeText(this, "Выберите дату начала лечения", Toast.LENGTH_LONG).show();
            dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yearq, int monthq, int dayOfMonth) {

                    editor.putInt("Year", yearq);
                    editor.putInt("Month", monthq);
                    editor.putInt("Day", dayOfMonth);
                    editor.commit();
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);

                }
            }, day, month, year);
            dpd.show();

    }

        handler.postDelayed( runnable = new Runnable() {

            public void run() {

                if (prefs.getInt("Year", 0) != 0 && pickYear == 2000) {
                    pickYear = prefs.getInt("Year", 0);
                    pickMonth = prefs.getInt("Month", 0);
                    pickDay = prefs.getInt("Day", 0);
                }
                handler.postDelayed(runnable, delay);
            }
        }, delay);

        //открыть и скрыть экран
        choiceButton = findViewById(R.id.button);
        saveButton = findViewById(R.id.buttonadd);
        choiceLayout = findViewById(R.id.choiceLayout);

        choiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    choiceLayout.setVisibility(View.VISIBLE);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alarmIntent = new Intent(MainActivity.this, MyAlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
                start(time_picker2.getHour(), time_picker2.getMinute());
                choiceLayout.setVisibility(View.INVISIBLE);

            }
        });

        simpleButton1 = (ImageButton) findViewById(R.id.button2);//get id of button 1
        simpleButton2 = (ImageButton) findViewById(R.id.button3);//get id of button 1
        simpleButton3 = (ImageButton) findViewById(R.id.button4);//get id of button 1

        simpleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoteClass.class));
            }
        });
        simpleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });
        simpleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });

    }
    public void FullScreencall() {
        if(Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if(Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void GetDiff() {
        Calendar cal = Calendar.getInstance();
        nowYear = cal.get(Calendar.YEAR);
        nowMonth = cal.get(Calendar.MONTH);
        nowDay = cal.get(Calendar.DAY_OF_MONTH);
        nowHour = cal.get(Calendar.HOUR);
        nowMinute = cal.get(Calendar.MINUTE);

        Date nowDate = new Date(nowYear, nowMonth, nowDay, nowHour, nowMinute, 0);
        Date pickDate = new Date(pickYear, pickMonth, pickDay, hour, minute, 0);
        long diffInMillisec = nowDate.getTime() - pickDate.getTime();
        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);
        long seconds = diffInSec % 60;
        diffInSec /= 60;
        long minutes = diffInSec % 60;
        diffInSec /= 60;
        long hours = diffInSec % 12;
        LocalDate bday = LocalDate.of(pickYear, pickMonth + 1, pickDay);
        LocalDate today = LocalDate.now();
        Period age = Period.between(bday, today);
        years = age.getYears();
        months = age.getMonths();
        days = age.getDays();

        nyears.setText("" + years);
        nmonths.setText("" + months);
        ndays.setText("" + days);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.radio_b1) {
            if(rb1.isChecked())
                var1.setVisibility(View.VISIBLE);
                var2.setVisibility(View.INVISIBLE);
                var3.setVisibility(View.INVISIBLE);
                stage = 1;
        }
        if(v.getId() == R.id.radio_b2) {
            if(rb2.isChecked())
                var1.setVisibility(View.VISIBLE);
                var2.setVisibility(View.INVISIBLE);
                var3.setVisibility(View.INVISIBLE);
                stage = 1;
        }
        if(v.getId() == R.id.radio_b3) {
            if(rb3.isChecked())
                var1.setVisibility(View.INVISIBLE);
                var2.setVisibility(View.VISIBLE);
                var3.setVisibility(View.INVISIBLE);
                stage = 2;
        }

        if(v.getId() == R.id.radio_b4) {
            if(rb4.isChecked())
                var1.setVisibility(View.INVISIBLE);
                var2.setVisibility(View.INVISIBLE);
                var3.setVisibility(View.VISIBLE);
                stage = 3;
        }

    }
    public void start(int hour, int minute) {
        android.icu.util.Calendar calendar = android.icu.util.Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(android.icu.util.Calendar.HOUR_OF_DAY, hour);
        calendar.set(android.icu.util.Calendar.MINUTE, minute);
        calendar.set(android.icu.util.Calendar.SECOND, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 0;



        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);
    }
}

