package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pekl.gasqueue.com.gasqueue.R;

public class CategoryActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Button beerBtn = (Button) findViewById(R.id.beerBtn);
        beerBtn.setOnClickListener(this);
        Button drinkBtn= (Button) findViewById(R.id.drinksBtn);
        drinkBtn.setOnClickListener(this);
        Button ciderBtn= (Button) findViewById(R.id.ciderBtn);
        ciderBtn.setOnClickListener(this);
        Button alkfriaBtn= (Button) findViewById(R.id.alkfriaBtn);
        alkfriaBtn.setOnClickListener(this);
        Button matBtn = (Button) findViewById(R.id.matBtn);
        matBtn.setOnClickListener(this);
    }
    public void buttonListener(View view){
        Button a = (Button) view;
        a.setText("Pripp");
    }

    @Override
    public void onClick(View v) {
        Intent temp;
        switch (v.getId())
        {
            case R.id.beerBtn:
                temp = new Intent(this,ProductActivity.class);
                startActivity(temp);
                break;
            case R.id.drinksBtn:
                temp = new Intent(this,ProductActivity.class);
                startActivity(temp);
                break;
            case R.id.ciderBtn:
                temp = new Intent(this,ProductActivity.class);
                startActivity(temp);
                break;
            case R.id.alkfriaBtn:
                temp = new Intent(this,ProductActivity.class);
                startActivity(temp);
                break;
            case R.id.matBtn:
                temp = new Intent(this,ProductActivity.class);
                startActivity(temp);
                break;
        }
    }
}
