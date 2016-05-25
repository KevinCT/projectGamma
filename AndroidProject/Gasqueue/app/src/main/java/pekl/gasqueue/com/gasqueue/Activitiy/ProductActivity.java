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
    private CustomerDBController customerDB = CustomerDBController.getInstance();
    private List<Product> productsSameCategory;
    private Map<Button, Product> productButtonMap;
    private ShoppingController shoppingController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Firebase.setAndroidContext(this);

        shoppingController=new ShoppingController();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Product.Category category = (Product.Category)extras.getSerializable("category");
            shoppingController.setChosenCategory(category);
        }
        productsSameCategory = new ArrayList<>();
        productsSameCategory = shoppingController.getProductSameCategory();
        productButtonMap = new HashMap<>();


        GridLayout listLayout = (GridLayout) findViewById(R.id.listLayout);
        listLayout.removeAllViews();
        if (productsSameCategory.size() > 0) {
            for (int i = 0; i < productsSameCategory.size(); i++) {
                Button button = new Button(this);
                button.setText(productsSameCategory.get(i).getName());
                productButtonMap.put(button, productsSameCategory.get(i));
                button.setOnClickListener(this);
                listLayout.addView(button);
            }
        }
    }

    @Override
    public void onClick(View view) {
        shoppingController.setChosenProduct(productButtonMap.get(view));
        Intent temp = new Intent(this, ProductDetailActivity.class);
        startActivity(temp);

    }
}
