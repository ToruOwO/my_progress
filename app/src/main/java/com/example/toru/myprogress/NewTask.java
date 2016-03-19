package com.example.toru.myprogress;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class NewTask extends AppCompatActivity implements View.OnClickListener {

    ChipsMultiAutoCompleteTextview mu;

    private EditText StartDate;
    private EditText EndDate;

    private DatePickerDialog StartDatePickerDialog;
    private DatePickerDialog EndDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.JAPAN);

        findViewById();

        setDateTimeField();

        mu = (ChipsMultiAutoCompleteTextview) findViewById(R.id.multiAutoCompleteTextView1);

        String[] item = getResources().getStringArray(R.array.tags);

        Log.i("", "Tag Count : " + item.length);
        mu.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, item));
        mu.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }


    private void findViewById(){
        StartDate = (EditText) findViewById(R.id.start_date);
        StartDate.setInputType(InputType.TYPE_NULL);
        StartDate.requestFocus();

        EndDate = (EditText) findViewById(R.id.end_date);
        EndDate.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        StartDate.setOnClickListener(this);
        EndDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        StartDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,monthOfYear,dayOfMonth);
                StartDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        EndDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                EndDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_task, menu);
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

    @Override
    public void onClick(View view) {
        if(view == StartDate) {
            StartDatePickerDialog.show();
        } else if (view == EndDate) {
            EndDatePickerDialog.show();
        }

    }
}
