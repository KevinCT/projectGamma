package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Menu;
import pekl.gasqueue.com.gasqueue.Product;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;

public class CategoryActivity extends AppCompatActivity  implements View.OnClickListener {
    private ShoppingController shoppingController = new ShoppingController();
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
        Button toCart = (Button) findViewById(R.id.cartBTn);
        toCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent temp = new Intent(this,ProductActivity.class);
        switch (v.getId())
        {
            case R.id.beerBtn:
                shoppingController.setChosenCategory(Product.Category.BEER);
                break;
            case R.id.drinksBtn:
                shoppingController.setChosenCategory(Product.Category.DRINK);
                break;
            case R.id.ciderBtn:
                shoppingController.setChosenCategory(Product.Category.CIDER);
                break;
            case R.id.alkfriaBtn:
                shoppingController.setChosenCategory(Product.Category.NON_ALCOHOLIC);
                break;
            case R.id.matBtn:
                shoppingController.setChosenCategory(Product.Category.FOOD);
                break;
            case R.id.cartBTn:
                temp = new Intent(this,CartActivity.class);
                break;
        }
        startActivity(temp);
    }
}
