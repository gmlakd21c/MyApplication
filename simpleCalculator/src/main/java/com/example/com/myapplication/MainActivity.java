package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText1,editText2;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        resultText = (TextView)findViewById(R.id.resultText);

        Button btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                double num1 = Integer.parseInt(editText1.getText().toString());
                double num2 = Integer.parseInt(editText1.getText().toString());
                double result = num1 + num2;
                resultText.setText("계산된 결과는 " + result + " 입니다.");
            }

        });


        Button btnSub = (Button)findViewById(R.id.btnSub);

        btnSub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                double num1 = Integer.parseInt(editText1.getText().toString());
                double num2 = Integer.parseInt(editText1.getText().toString());
                double result = num1 - num2;
                resultText.setText("계산된 결과는 " + result + " 입니다.");
            }

        });
        Button btnMul = (Button)findViewById(R.id.btnMul);

        btnMul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                double num1 = Integer.parseInt(editText1.getText().toString());
                double num2 = Integer.parseInt(editText1.getText().toString());

                resultText.setText("계산된 결과는 " + num1*num2 + " 입니다.");
            }

        });
        Button btnDiv = (Button)findViewById(R.id.btnDiv);

        btnDiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                double num1 = Integer.parseInt(editText1.getText().toString());
                double num2 = Integer.parseInt(editText1.getText().toString());

                resultText.setText("계산된 결과는 " + num1/num2 + " 입니다.");
            }

        });
        Button btnShowRemainder = (Button)findViewById(R.id.btnShowRemainder);

        btnShowRemainder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                double num1 = Integer.parseInt(editText1.getText().toString());
                double num2 = Integer.parseInt(editText1.getText().toString());

                resultText.setText("계산된 결과의 나머지는 " + num1%num2 + " 입니다.");
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
