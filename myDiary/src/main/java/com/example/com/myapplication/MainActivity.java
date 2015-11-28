package com.example.com.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
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
    DatePicker datePicker;
    int thisYear;
    int thisMonth;
    int thisDay;
    String strSDpath;
    String filePath;
    Spannable fontSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        filePath = strSDpath + "/mydir";

        diaryText = (EditText)findViewById(R.id.diaryText);
        dateText = (TextView)findViewById(R.id.dateText);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        saveBtn = (Button)findViewById(R.id.saveBtn);

        Calendar cal = Calendar.getInstance(); // 오늘 날짜를가지고온다.
        thisYear = cal.get(Calendar.YEAR);
        thisMonth = cal.get(Calendar.MONTH)+1;
        thisDay = cal.get(Calendar.DAY_OF_MONTH);

        dateText.setText((thisYear + "년 " + thisMonth + "월 " + thisDay +"일"));
        fileName = Integer.toString(thisYear) + "_" + Integer.toString(thisMonth+1) + "_" + Integer.toString(thisDay) + ".txt";
        diaryText.setText(readDiary(fileName)); //해당하는 날짜에 일기가 있으면 가지고 온다.

        fileCheck();

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            { // datePickerDialog 리스너
                String msg = String.format("%d_%d_%d", year, monthOfYear + 1, dayOfMonth);

                thisYear = year;
                thisMonth = monthOfYear+1;
                thisDay = dayOfMonth;
                dateText.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth +"일"); // 선택한 날짜 textView,setText()

                fileName = Integer.toString(thisYear) + "_" + Integer.toString(thisMonth+1) + "_" + Integer.toString(thisDay) + ".txt";
                String str = readDiary(fileName); // 파일 이름을 통해 해당 파일이 있으면 파일의 내용을 str에 저장
                diaryText.setText(str); // editText.setText()
            }
        };

        final DatePickerDialog dateDig = new DatePickerDialog(MainActivity.this, dateSetListener, thisYear, thisMonth, thisDay);

        saveBtn.setOnClickListener(new OnClickListener() // 저장버튼 클릭리스너
        {
            public void onClick(View v)
            {
                saveDiary(fileName); // 저장할 파일이름을 넘겨주고 파일을 저장한다.
            }
        });

        dateText.setOnClickListener(new OnClickListener() // textView 클릭리스너
        {
            public void onClick(View v)
            {
                dateDig.show(); // 다이얼로그 show()매소드
            }
        });
    }

    void fileCheck()
    {
        strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        filePath = strSDpath + "/mydir";
        File myDir;
        String sysDir = Environment.getRootDirectory().getAbsolutePath();
        File[] sysFiles =  (new File(sysDir)).listFiles(); // sdcard저장장소의 파일 리스트

        String tmpFileName = filePath;
        for(int i=0; i<sysFiles.length; i++)
        {
            if(sysFiles[i].isDirectory() == true)
            {
                if(tmpFileName.equals(sysFiles.toString()))
                    break;
            }
            else if(i == sysFiles.length-1) // 마지막 까지 없다면
            {
                myDir = new File(filePath);
                myDir.mkdir();  // 파일을 생성한다.
            }
        }
    }

    void saveDiary(String fileName)
    {
        try {
            File file;
            String readFile = filePath +"/"+ fileName;
            file = new File(readFile); // sdcard 저장장소에 fileName에 대한 file객체
            FileOutputStream outPutStream = new FileOutputStream(file);
            String str = diaryText.getText().toString().trim(); // 일기 내용을 가지고 온다.

            outPutStream.write(str.getBytes()); // 일기 내용을 file에 쓴다
            outPutStream.close();
            Toast.makeText(getApplication(), fileName + "이 저장되었습니다.", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e) {
        }
    }


    String readDiary(String fileName)
    {
        String diaryStr = null; // 파일에 있는 일기 내용
        FileInputStream inputStream;
        try
        {
            String tmp = filePath +"/"+ fileName;
            inputStream = new FileInputStream(tmp);
            byte[] txt = new byte[inputStream.available()];
            inputStream.read(txt);
            diaryStr = (new String(txt)).trim();
            inputStream.close();
            Toast.makeText(getApplication(), fileName + "을 불러왔습니다.", Toast.LENGTH_SHORT).show(); // 일기가 불렸다는 토스트 메세지
        }
        catch(IOException e)
        {
            diaryText.setHint("일기 내용"); // 일기가 없을시에 일기 내용을 setHint()한다.
        }
        return diaryStr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        fontSize = diaryText.getText();

        switch ((item.getItemId())) {

            case R.id.deleteDairy:
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                dig.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String tmpFileName = dateText.getText().toString() + ".txt";
                        File file = new File(filePath + "/" + tmpFileName);
                        file.delete();
                        Toast.makeText(getApplication(), tmpFileName + "을 삭제하였습니다.", Toast.LENGTH_SHORT).show();
                        diaryText.setText("");
                    }
                });

                dig.setNegativeButton("취소", null);
                dig.setTitle(fileName + " 삭제 하시겠습니까?");
                dig.show();
                break;

            case R.id.reread:
                AlertDialog.Builder rereadDig = new AlertDialog.Builder(MainActivity.this);
                rereadDig.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String tmpFileName = dateText.getText().toString() + ".txt";
                        diaryText.setText(readDiary(fileName));
                        Toast.makeText(getApplication(), tmpFileName + "을 불러왔습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                rereadDig.setNegativeButton("취소", null);
                rereadDig.setTitle("현재 작성중인 일기가 사라집니다. 다시불러오시겠습니까?");
                rereadDig.show();
                break;

            case R.id.largeSize:
                fontSize.setSpan(new AbsoluteSizeSpan(120), 0, diaryText.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;

            case R.id.nomalSize:
                fontSize.setSpan(new AbsoluteSizeSpan(60), 0, diaryText.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;

            case R.id.smallSize:
                fontSize.setSpan(new AbsoluteSizeSpan(30), 0, diaryText.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
        }
        return true;
    }
}
