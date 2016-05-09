package pekl.gasqueue.com.gasqueue.Activitiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.IllegalFormatException;

import pekl.gasqueue.com.gasqueue.Product;
import pekl.gasqueue.com.gasqueue.R;

public class ProductDetailActivity extends AppCompatActivity {
    private Product chosenProduct = new Product();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        chosenProduct = chosenProduct.getChosenProduct();
        TextView nameLabel = (TextView) findViewById(R.id.nameLabel);
        TextView descriptionLabel = (TextView) findViewById(R.id.descriptionLabel);
        TextView priceLabel = (TextView) findViewById(R.id.priceLabel);
        final TextView totalLabel = (TextView) findViewById(R.id.totalLabel);
        final EditText quantity = (EditText) findViewById(R.id.quantity);

        nameLabel.setText(chosenProduct.getName());
        priceLabel.setText("* " + chosenProduct.getPrice() + " kr");

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    totalLabel.setText(Integer.valueOf(quantity.getText().toString()) * chosenProduct.getPrice());
                }
                catch (IllegalFormatException l)
                {
                    System.out.println("ange ett jävla nummer tack");
                }
            }
        });
    }
}
