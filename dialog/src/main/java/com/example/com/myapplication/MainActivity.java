package com.example.com.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText userName, userEmail,editUserName,editUserEmail;
    TextView toastText;
    Button popDialog;
    View dialogView, toastView;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.userName);
        userEmail = (EditText)findViewById(R.id.userEmail);
        popDialog = (Button)findViewById(R.id.popDialog);




        popDialog.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog, null);
                AlertDialog.Builder userInfo = new AlertDialog.Builder(MainActivity.this);
                userInfo.setTitle("사용자 정보 입력");
                userInfo.setView(dialogView);

                editUserName = (EditText) dialogView.findViewById(R.id.editUserName);
                editUserEmail = (EditText) dialogView.findViewById(R.id.editUserEmail);
                editUserName.setText(userName.getText().toString());
                editUserEmail.setText(userEmail.getText().toString());

                userInfo.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        userName.setText(editUserName.getText().toString());
                        userEmail.setText(editUserEmail.getText().toString());
                    }
                });
                userInfo.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Random r = new Random();
                        toastView = (View)View.inflate(MainActivity.this, R.layout.toast, null);
                        toastText = (TextView)toastView.findViewById(R.id.toastText);
                        toast = new Toast(MainActivity.this);
                        toastText.setText("취소했습니다");
                        toast.setView(toastView);
                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int x=(int)(Math.random()*display.getWidth()), y=(int)(Math.random()*display.getHeight());

                        toast.setGravity(Gravity.NO_GRAVITY, x, y);
                        userName.setText(Integer.toString(x));
                        userEmail.setText(Integer.toString(y));
                        toast.show();

                    }
                });

                userInfo.show();
            }
        });








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
