package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.control.CustomerDBController;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    private CustomerDBController customerDB;
    private List<Product> productsSameCategory;
    private Map<Button, Product> productButtonMap;
    private ShoppingController shoppingController;
    private GridLayout listLayout;
    private Button createProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Firebase.setAndroidContext(this);
        customerDB = CustomerDBController.getInstance();

        initializeViews();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shoppingController.setChosenCategory((Product.Category)extras.getSerializable("category"));
        }

        createProductButton();

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        createProductButton();
    }

    @Override
    public void onClick(View view) {
        Intent temp;
        if (view.equals(createProductBtn))
        {
            shoppingController.setIntention(true);
            temp = new Intent(this,ChangeProductDetailActivity.class);
        }
        else {
            if (shoppingController.getTypeOfUser() == true)
            {
                shoppingController.setIntention(false);
                shoppingController.setChosenProduct(productButtonMap.get(view));
                temp = new Intent(this,ChangeProductDetailActivity.class);
            }
            else {
                shoppingController.setChosenProduct(productButtonMap.get(view));
                shoppingController.setChosenProduct(productButtonMap.get(view));
                temp = new Intent(this, ProductDetailActivity.class);
            }
        }
        startActivity(temp);
    }

    private void initializeViews()
    {
        shoppingController = new ShoppingController();
        productButtonMap = new HashMap<>();
        listLayout = (GridLayout) findViewById(R.id.listLayout);
        productsSameCategory = shoppingController.getProductSameCategory();
        createProductBtn = new Button(this);
    }

    private void createProductButton(){
        listLayout.removeAllViews();
        if (productsSameCategory.size() > 0) {
            for (int i = 0; i < productsSameCategory.size(); i++) {
                Button button = new Button(this);
                button.setText(shoppingController.getProductName(productsSameCategory.get(i)));
                button.setWidth(500);
                button.setHeight(400);
                productButtonMap.put(button, productsSameCategory.get(i));
                button.setOnClickListener(this);
                listLayout.addView(button);
            }
        }

        if (shoppingController.getTypeOfUser() == true)
        {
            createProductBtn.setText("CREATE NEW PRODUCT");
            createProductBtn.setWidth(500);
            createProductBtn.setHeight(400);
            createProductBtn.setOnClickListener(this);
            listLayout.addView(createProductBtn);
        }
    }
}
