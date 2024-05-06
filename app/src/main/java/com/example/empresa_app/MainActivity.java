package com.example.empresa_app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextDateTime;
    Button buttonBack, buttonAccept;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDateTime = findViewById(R.id.editText_date);
        buttonBack = findViewById(R.id.button_cancel);
        buttonAccept = findViewById(R.id.button_accept);

        calendar = Calendar.getInstance();

        // Manejar clic en el campo de fecha y hora
        editTextDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog();
            }
        });

        // Manejar clic en el botón de retroceder
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Manejar clic en el botón de aceptar
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAcceptClicked();
            }
        });
    }

    // Método para mostrar el selector de fecha y hora
    private void showDateTimePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        showTimePickerDialog(selectedDate);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    // Método para mostrar el selector de hora después de seleccionar la fecha
    private void showTimePickerDialog(final String selectedDate) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = selectedDate + " " + String.format("%02d:%02d", hourOfDay, minute);
                        editTextDateTime.setText(selectedTime);
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    // Método para manejar clic en el botón de retroceder
    public void onCancelClicked(View view) {
        // Implementa tu lógica aquí
    }

    // Método para manejar clic en el botón de aceptar
    public void onAcceptClicked(View view) {
        // Implementa tu lógica aquí
    }
}
