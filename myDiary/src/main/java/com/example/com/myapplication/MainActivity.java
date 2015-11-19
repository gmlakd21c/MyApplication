package com.example.com.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button saveBtn;
    EditText diaryText;
    TextView dateText;
    String fileName;
    View dialogView;
    DatePicker datePicker;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = (Button)findViewById(R.id.saveBtn);
        diaryText = (EditText)findViewById(R.id.diaryText);
        dateText = (TextView)findViewById(R.id.dateText);
        dialogView = (View)View.inflate(MainActivity.this, R.layout.datepicker, null);
        datePicker = (DatePicker)findViewById(R.id.datePicker);


        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                String msg = String.format("%d / %d / %d", year,monthOfYear+1, dayOfMonth);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };

        saveBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                try {
                    FileOutputStream outPutStream = openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                    String str = diaryText.getText().toString();
                    outPutStream.write(str.getBytes());
                    outPutStream.close();
                    Toast.makeText(getApplication(), fileName + "이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                }
                catch(IOException e)
                {

                }
            }
        });

        dateText.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                new DatePickerDialog(MainActivity.this, dateSetListener, year, month, day).show();

                dateText.setText(year + "년 " + month + "월 " + day + "일");
                /*
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                dig.setPositiveButton("선택", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(getApplication(),datePicker.getYear() +"년 " + datePicker.getMonth() + "월 " + datePicker.getDayOfMonth() + "일",Toast.LENGTH_SHORT).show();
                    }
                });

                dig.setTitle("날짜 정해주세요");
                dig.setView(dialogView);
                dig.show();
                */
            }
        });
    }

    public AlertDialog createDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("날짜를 정해주세요");

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker;

        datePicker = new DatePicker(this);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener()
        {
            public void onDateChanged(DatePicker view, int year, int month, int day)
            {
                fileName = Integer.toString(year) + "_" + Integer.toString(month+1) + "_" + Integer.toString(day) + ".txt";
                String str = readDiary(fileName);
                diaryText.setText(str);
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }

    String readDiary(String fileName)
    {
        String diaryStr = null;
        FileInputStream inputStream;
        try
        {
            inputStream = openFileInput(fileName);
            byte[] txt = new byte[500];
            inputStream.read(txt);
            inputStream.close();
            diaryStr = (new String(txt)).trim();
        }
        catch(IOException e)
        {
            diaryText.setHint("일기 없음");
        }
        return diaryStr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
