package com.example.com.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
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

import java.io.File;
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
    int thisYear;
    int thisMonth;
    int thisDay;
    String strSDpath;
    String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        filePath = strSDpath + "/mydir";
        File myDir;
        String sysDir = Environment.getRootDirectory().getAbsolutePath();
        File[] sysFiles =  (new File(sysDir)).listFiles();

        String tmpFileName = filePath;
        for(int i=0; i<sysFiles.length; i++)
        {
            if(sysFiles[i].isDirectory() == true)
            {
                if(tmpFileName.equals(sysFiles.toString()))
                    break;
            }
            else if(i == sysFiles.length-1)
            {
                myDir = new File(filePath);
                myDir.mkdir();
            }
        }

        saveBtn = (Button)findViewById(R.id.saveBtn);
        diaryText = (EditText)findViewById(R.id.diaryText);
        dateText = (TextView)findViewById(R.id.dateText);
        dialogView = (View)View.inflate(MainActivity.this, R.layout.datepicker, null);
        datePicker = (DatePicker)findViewById(R.id.datePicker);

        Calendar cal = Calendar.getInstance();
        thisYear = cal.get(Calendar.YEAR);
        thisMonth = cal.get(Calendar.MONTH);
        thisDay = cal.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                String msg = String.format("%d / %d / %d", year, monthOfYear + 1, dayOfMonth);

                thisYear = year;
                thisMonth = monthOfYear;
                thisDay = dayOfMonth;
                dateText.setText(msg);

                fileName = Integer.toString(thisYear) + "_" + Integer.toString(thisMonth+1) + "_" + Integer.toString(thisDay) + ".txt";
                String str = "";
                str = readDiary(fileName);
                diaryText.setText(str);
                String tmp = filePath + "/" + fileName;
                Toast.makeText(getApplication(), tmp.substring(1,tmp.length()), Toast.LENGTH_SHORT).show();

            }
        };

        final DatePickerDialog dateDig = new DatePickerDialog(MainActivity.this, dateSetListener, thisYear, thisMonth, thisDay);

        saveBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                try {
                    String tmp = filePath +"/"+ fileName;
                    String tmp2 = tmp.substring(1,tmp.length());

                    FileOutputStream outPutStream = openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
                    String str = diaryText.getText().toString().trim();

                    outPutStream.write(str.getBytes());
                    outPutStream.close();
                    Toast.makeText(getApplication(), fileName + "이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                }
                catch(IOException e) {
                }
            }
        });

        dateText.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                dateDig.show();
            }
        });
    }

    String readDiary(String fileName)
    {
        String diaryStr = null;
        FileInputStream inputStream;
        try
        {
            String tmp = filePath +"/"+ fileName;
            String tmp2 = tmp.substring(1,tmp.length());
            inputStream = openFileInput(fileName);
            byte[] txt = new byte[inputStream.available()];
            inputStream.read(txt);
            diaryStr = (new String(txt)).trim();
            inputStream.close();
        }
        catch(IOException e)
        {
            diaryText.setHint("일기 없음");
        }
        return diaryStr;
    }

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
*/
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
