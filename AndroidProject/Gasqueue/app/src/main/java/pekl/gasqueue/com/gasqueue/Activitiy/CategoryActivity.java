package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pekl.gasqueue.com.gasqueue.R;

public class CategoryActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Button beerBtn = (Button) findViewById(R.id.beerBtn);
        Button drinkBtn= (Button) findViewById(R.id.drinksBtn);

    }
    public void buttonListener(View view){
        Button a = (Button) view;
        a.setText("Pripp");


    }

}
