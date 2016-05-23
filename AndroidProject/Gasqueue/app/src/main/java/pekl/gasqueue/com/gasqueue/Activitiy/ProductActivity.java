package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Product> productsSameCategory;
    private Map<Button, Product> productButtonMap;

    private ShoppingController shoppingController = new ShoppingController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        productsSameCategory = new ArrayList<>();
        productsSameCategory = shoppingController.getProductSameCategory();
        productButtonMap = new HashMap<>();
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_product);

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
