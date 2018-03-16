package br.com.renato.skla;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

//private DatePicker dp_cal;
private CalendarView v_calendario;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendario);

        //dp_cal = findViewById(R.id.dp_cal);

        v_calendario = findViewById(R.id.v_calendario);
    }


    @Override
    public void onClick(View v) {

    }
}
