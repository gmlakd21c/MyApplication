package com.example.com.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.*;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    Button button;
    DatePicker datePicker;
    EditText editText;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        editText = (EditText)findViewById(R.id.editText);
        setTitle("간단한 일기장");


        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener()
        {
           public void onDateChanged(DatePicker view, int year, int month, int day)
           {
               fileName = Integer.toString(year) + "_" + Integer.toString(month+1) + "_" + Integer.toString(day) + ".txt";
               String str = readDiary(fileName);
               editText.setText(str);
               button.setEnabled(true);
           }
        });

        button.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                try {
                    FileOutputStream outPutStream = openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                    String str = editText.getText().toString();
                    outPutStream.write(str.getBytes());
                    outPutStream.close();
                    Toast.makeText(getApplication(), fileName + "이 저장되었습니다.", Toast.LENGTH_SHORT).show();

                }
                catch(IOException e)
                {

                }
            }
        });
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
            button.setText("수정 하기");
        }
        catch(IOException e)
        {
            editText.setHint("일기 없음");
            button.setText("새로 저장");
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
