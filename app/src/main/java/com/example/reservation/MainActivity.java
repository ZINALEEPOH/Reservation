package com.example.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText etFN;
    EditText etPN;
    EditText etGS;
    DatePicker dp;
    TimePicker tp;
    CheckBox cbSmoke;
    Button btnSub;
    Button btnRes;

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFN=findViewById(R.id.editTextFN);
        etPN=findViewById(R.id.editTextPhone);
        etGS=findViewById(R.id.editTextSize);
        dp=findViewById(R.id.datePicker);
        tp=findViewById(R.id.timePicker);
        cbSmoke=findViewById(R.id.checkBoxSmoke);
        btnSub=findViewById(R.id.buttonSubmit);
        btnRes=findViewById(R.id.buttonReset);
        txt=findViewById(R.id.textView);

        tp.setIs24HourView(true);
        dp.updateDate(2023, 5, 1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etFN.getText().toString().matches("")||etPN.getText().toString().matches("")||etGS.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,"invalid empty field(s)", Toast.LENGTH_SHORT).show();
                }else{
                    String output=String.format("Full Name: %s\nPhone Number: %s\nGroup Size: %s\nDate: %d/%d/%d\nTime: %d:%d\nSmoking Table: %s", etFN.getText(), etPN.getText(), etGS.getText(), dp.getDayOfMonth(), dp.getMonth()+1, dp.getYear(), tp.getCurrentHour(), tp.getCurrentMinute(), cbSmoke.isChecked());
                    new CountDownTimer(3000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            txt.setText(output);
                        }
                        public void onFinish() {
                            txt.setText("");
                        }
                    }.start();
                }
            }
        });
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etFN.setText("");
                etPN.setText("");
                etGS.setText("");
                dp.updateDate(2023, 5, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                cbSmoke.setChecked(false);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(tp.getCurrentHour()<8){
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                }else if(tp.getCurrentHour()>20){
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(0);
                }
            }
        });

    }
}