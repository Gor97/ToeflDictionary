package com.toefldictionary.activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.api.translate.Language;
import com.toefldictionary.R;
import com.toefldictionary.tools.Translate;
import com.toefldictionary.tools.service.Receiver;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {
    private EditText translateEdit;
    private TextView translationText;
    private String translatedText;
    private Button date, time, remind;
    private Calendar selectedCalendar, currentCalendar;
    private int selectedDay, selectedMonth, selectedYear, selectedHour, selectedMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        selectedCalendar = Calendar.getInstance();
        currentCalendar = Calendar.getInstance();
        date = (Button) findViewById(R.id.dateButton);
        time = (Button) findViewById(R.id.timeButton);
        remind = (Button) findViewById(R.id.remindButton);
        selectedDay = selectedCalendar.get(Calendar.DAY_OF_MONTH);
        selectedMonth = selectedCalendar.get(Calendar.MONTH) + 1;
        selectedYear = selectedCalendar.get(Calendar.YEAR);
        selectedHour = selectedCalendar.get(Calendar.HOUR_OF_DAY);
        selectedMinute = selectedCalendar.get(Calendar.MINUTE);
        translateEdit = (EditText) findViewById(R.id.translateEdit);
        translationText = (TextView) findViewById(R.id.translationText);
        date.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
        time.setText(selectedHour + ":" + String.format("%02d\n", selectedMinute));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d = new DatePickerDialog(SettingsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDay = dayOfMonth;
                        selectedMonth = monthOfYear + 1;
                        selectedYear = year;
                        date.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        selectedCalendar.set(Calendar.MONTH, monthOfYear);
                        selectedCalendar.set(Calendar.YEAR, year);
                    }
                }, selectedYear, selectedMonth - 1, selectedDay);
                d.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog d = new TimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedHour = hourOfDay;
                        selectedMinute = minute;
                        time.setText(selectedHour + ":" + String.format("%02d\n", selectedMinute));
                        selectedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedCalendar.set(Calendar.MINUTE, minute);
                    }
                }, selectedHour, selectedMinute, true);
                d.show();
            }
        });

        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("Reminder")
                        .setMessage("Remind you at that time?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (selectedCalendar.getTimeInMillis() < currentCalendar.getTimeInMillis())
                                    Toast.makeText(SettingsActivity.this, "Time must be future", Toast.LENGTH_SHORT).show();
                                else {
                                    Intent myIntent = new Intent(getApplication(), Receiver.class);
                                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplication(),  0, myIntent, PendingIntent.FLAG_ONE_SHOT);
                                    AlarmManager alarmManager = (AlarmManager)getApplication().getSystemService(Context.ALARM_SERVICE);
                                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, selectedCalendar.getTimeInMillis(), pendingIntent);
                                    Toast.makeText(SettingsActivity.this, "Reminder saved", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        Translate.setKey("trnsl.1.1.20160521T091147Z.41183c19259abd89.48573491ee4d6489543d0b128fb4b0c65935aa04");
        Button b = (Button) findViewById(R.id.button);
        assert b != null;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    public void run() {
                        try {
                            translatedText = Translate.execute(translateEdit.getText().toString(), Language.ENGLISH, Language.ARMENIAN);
                            translationText.setText(translatedText);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                Thread t = new Thread(runnable);
                t.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
