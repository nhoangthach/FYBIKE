package com.android.app.fybike;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.maps.GoogleMap;

public class AddNewShopActivity extends AppCompatActivity implements View.OnClickListener{

    AddShopMapFragment m_map = new AddShopMapFragment();
    Button btnTimeOpen, btnTimeClose;
    EditText txtTimeOpen, txtTimeClose, txtPrice;

    final static int OPEN_DIALOG_ID = 0;
    final static int CLOSE_DIALOG_ID = 1;
    int m_hours;
    int m_minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_shop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnTimeOpen = (Button) findViewById(R.id.btnTimeOpen);
        btnTimeClose = (Button) findViewById(R.id.btnTimeClose);
        txtTimeOpen = (EditText) findViewById(R.id.txtTImeOpen);
        txtTimeClose = (EditText) findViewById(R.id.txtTimeClose);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        btnTimeOpen.setOnClickListener(this);
        btnTimeClose.setOnClickListener(this);
        txtPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                    v.clearFocus();
                }
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if(id == OPEN_DIALOG_ID){
            return new TimePickerDialog(AddNewShopActivity.this, openTimePickerListener, m_hours, m_minutes, false);
        }
        if(id == CLOSE_DIALOG_ID){
            return new TimePickerDialog(AddNewShopActivity.this, closeTimePickerListener, m_hours, m_minutes, false);
        }
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener openTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            m_hours = hourOfDay;
            m_minutes = minute;
            txtTimeOpen.setText(formatTime(m_hours, m_minutes));
        }
    };

    protected TimePickerDialog.OnTimeSetListener closeTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            m_hours = hourOfDay;
            m_minutes = minute;
            txtTimeClose.setText(formatTime(m_hours, m_minutes));
        }
    };
    @Override
    public void onClick(View v) {
        if(v == btnTimeOpen){
            showDialog(OPEN_DIALOG_ID);
        }
        if(v == btnTimeClose){
            showDialog(CLOSE_DIALOG_ID);
        }
    }

    private String formatTime(int hours, int mins){
        String result = "";
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        result = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        return result;
    }

    public void hideKeyboard(View v) {
        InputMethodManager imm =(InputMethodManager )this.getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
