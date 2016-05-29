package pekl.gasqueue.com.gasqueue.Activitiy;

import android.content.Intent;
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
    private Button removeBtn;
    private boolean isCreating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_product_detail);

        initializeViews();

        if (isCreating == true)
        {
            nameTextField.setText("Name of product");
            priceTextField.setText("99");
            saveBtn.setText("CREATE PRODUCT");
            removeBtn.setVisibility(View.INVISIBLE);
        }
        else{
            removeBtn.setVisibility(View.VISIBLE);
            for (int i =1; i<categories.size();i++)
            {
                if (shoppingController.getChosenProductCategory().equals(categories.get(i)))
                {
                    Product.Category temp = categories.get(0);
                    categories.set(0,shoppingController.getChosenProductCategory());
                    categories.set(i,temp);
                }
            }
            nameTextField.setText(shoppingController.getChosenProductName() + "");
            priceTextField.setText(shoppingController.getChosenProductPrice() + "");
        }

        initializeSpinner();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCreating == true)
                {
                    shoppingController.addProductToMenu(nameTextField.getText().toString(), category, Integer.parseInt(priceTextField.getText().toString()));
                }
                else {
                    shoppingController.setChosenProductChanges(nameTextField.getText().toString(), category, Integer.parseInt(priceTextField.getText().toString()));
                }
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingController.removeChosenProductFromMenu();
                finish();
            }
        });
    }

    private void initializeViews()
    {
        shoppingController = ShoppingController.getInstance();
        categories = new ArrayList<Product.Category>(EnumSet.allOf(Product.Category.class));
        nameTextField = (EditText) findViewById(R.id.nameTF);
        priceTextField = (EditText) findViewById(R.id.priceTF);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        isCreating = shoppingController.getIntention();
        removeBtn = (Button) findViewById(R.id.removeBTN);
    }

    private void initializeSpinner()
    {

        spinnerAdapter = new ArrayAdapter<Product.Category>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category =(Product.Category) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

