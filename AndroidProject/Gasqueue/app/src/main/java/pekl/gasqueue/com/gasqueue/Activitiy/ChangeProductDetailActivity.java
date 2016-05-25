package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;
import pekl.gasqueue.com.gasqueue.model.Product;

public class ChangeProductDetailActivity extends AppCompatActivity {
    private ShoppingController shoppingController;
    private List<Product.Category> categories;
    private ArrayAdapter<Product.Category> spinnerAdapter;
    private Product.Category category;

    private EditText nameTextField;
    private EditText priceTextField;
    private Spinner categorySpinner;
    private Button saveBtn;
    private boolean isCreating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_product_detail);
        initializeViews();
        categorySpinner.setAdapter(spinnerAdapter);

        if (isCreating == true)
        {
            nameTextField.setText("Name of product");
            priceTextField.setText("Price of product in kr");
            saveBtn.setText("CREATE PRODUCT");
        }
        else{
            nameTextField.setText(shoppingController.getChosenProduct().getName() + "");
            priceTextField.setText(shoppingController.getChosenProduct().getPrice() + "");
        }

        spinnerAdapter = new ArrayAdapter<Product.Category>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category =(Product.Category) adapterView.getItemAtPosition(i);
                System.out.println(shoppingController.getChosenProduct().getCategory().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCreating == true)
                {
                    Product newProduct = new Product(nameTextField.getText().toString(), category, Integer.parseInt(priceTextField.getText().toString()));
                    shoppingController.addProductToMenu(newProduct);
                }
                else {
                    shoppingController.getChosenProduct().setChanges(nameTextField.getText().toString(), category, Integer.parseInt(priceTextField.getText().toString()));
                }
            }
        });

    }

    private void initializeViews()
    {
        shoppingController = new ShoppingController();
        categories = new ArrayList<Product.Category>(EnumSet.allOf(Product.Category.class));
        nameTextField = (EditText) findViewById(R.id.nameTF);
        priceTextField = (EditText) findViewById(R.id.priceTF);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        isCreating = shoppingController.getIntention();
    }
}

