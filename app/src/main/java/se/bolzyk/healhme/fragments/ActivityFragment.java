package se.bolzyk.healhme.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import se.bolzyk.healhme.R;

/**
 * Created by andreasbolzyk on 2016-02-24.
 */
public class ActivityFragment extends Fragment implements View.OnClickListener {


    private Spinner spinner;
    private Button btnDatePicker, btnTimePicker;
    private EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    private EditText dateText;
    private Set<String> listArray;

    public ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_activity, container, false);

        dateText=(EditText) rootView.findViewById(R.id.in_date);

        /*
        activity
         */
        spinner = (Spinner) rootView.findViewById(R.id.activity_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.activity_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        /*
        date
         */
        btnDatePicker=(Button)rootView.findViewById(R.id.btn_date);
        //btnTimePicker=(Button)rootView.findViewById(R.id.btn_time);
        txtDate=(EditText)rootView.findViewById(R.id.in_date);
        //txtTime=(EditText)rootView.findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        //btnTimePicker.setOnClickListener(this);


        /*
        save data
         */
        final Button button1 = (Button) rootView.findViewById(R.id.update_data);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData();
            }
        });


        /*
        load data
         */
        final Button button2 = (Button) rootView.findViewById(R.id.read_data);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadData();
            }
        });

        return rootView;
    }



    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }



    private void saveData() {

        SharedPreferences ss = getContext().getSharedPreferences("db", 0);
        Set<String> hs = ss.getStringSet("set", new HashSet<String>());
        Set<String> in = new HashSet<String>(hs);

        //in.add(String.valueOf(hs.size() + 1));
        in.add(dateText.getText().toString()+ "," + spinner.getSelectedItem().toString());


        ss.edit().putStringSet("set", in).commit(); // brevity
        // SharedPreferences sss = getSharedPreferences("db", 0); // not needed


        //Log.i("chauster", "2.set = " + ss.getStringSet("set", new HashSet<String>()));
        Log.d("ALL", ss.getAll().toString());
    }



    private void loadData() {

        SharedPreferences sp = getContext().getSharedPreferences("db", 0);
        listArray = sp.getStringSet("set", new HashSet<String>());


        Log.d("Set",listArray.toString());
        //Log.d("ALL", sp.getAll().toString());
    }



    @Override
    public void onPause() {
        super.onPause();
        //saveData();
    }



    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

}
