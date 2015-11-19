package com.example.com.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    Button returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        returnBtn = (Button)findViewById(R.id.returnBtn);

        Intent inIntent = getIntent();
        String operation = inIntent.getStringExtra("Operation");
        int total=0;
        switch(operation)
        {
            case "더하기":
                total = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0);
                break;
            case "빼기":
                total = inIntent.getIntExtra("Num1", 0) - inIntent.getIntExtra("Num2", 0);
                break;
            case "곱하기":
                total = inIntent.getIntExtra("Num1", 0) * inIntent.getIntExtra("Num2", 0);
                break;
            case "나누기":
                total = inIntent.getIntExtra("Num1", 0) / inIntent.getIntExtra("Num2", 0);
                break;
        }

        final int result = total;

        returnBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("Rusult", result);
                setResult(RESULT_OK,outIntent);
                finish();
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
