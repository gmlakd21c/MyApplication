package com.example.com.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editName, editNumber, groupNameResult, groupNumberResult;
    Button init,input,delete,modify,inquire;
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        init = (Button)findViewById(R.id.init);
        input = (Button)findViewById(R.id.input);
        delete = (Button)findViewById(R.id.delete);
        modify = (Button)findViewById(R.id.modify);
        inquire = (Button)findViewById(R.id.inquire);

        editName = (EditText)findViewById(R.id.editName);
        editNumber = (EditText)findViewById(R.id.editNumber);
        groupNameResult = (EditText)findViewById(R.id.groupNameResult);
        groupNumberResult = (EditText)findViewById(R.id.groupNumberResult);

        myHelper = new myDBHelper(this);
        init.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        inquire.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String groupName = "그룹 이름" + "\r\n" + "--------------" + "\r\n";
                String groupNumber = "인원" + "\r\n" + "-------" + "\r\n";

                while( cursor.moveToNext())
                {
                    groupName += cursor.getString(0) + "\r\n";
                    groupNumber += cursor.getString(1) + "\r\n";
                }

                groupNameResult.setText(groupName);
                groupNumberResult.setText(groupNumber);

                cursor.close();
                sqlDB.close();

            }
        });

        input.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '" + editName.getText().toString() + "' , " + editNumber.getText().toString() + ");");
                sqlDB.close();
                Toast.makeText(getApplication(), "입력됨", Toast.LENGTH_SHORT).show();
                inquire.callOnClick();
            }
        });



        modify.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET groupNumber = "+editNumber.getText().toString()
                        +" WHERE groupName='" + editName.getText().toString() + "';");
                sqlDB.close();
                Toast.makeText(getApplication(), "수정됨", Toast.LENGTH_SHORT).show();
                inquire.callOnClick();
            }
        });

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE groupName= '" + editName.getText().toString() + "';");
                sqlDB.close();
                Toast.makeText(getApplication(), "삭제됨", Toast.LENGTH_SHORT).show();
                inquire.callOnClick();
            }
        });

    }

    public class myDBHelper extends SQLiteOpenHelper
    {
        public myDBHelper(Context context)
        {
            super(context,"groupDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE groupTBL ( groupName CHAR(20) PRIMARY KEY, groupNumber INTEGER);");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
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
