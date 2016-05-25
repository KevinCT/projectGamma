package pekl.gasqueue.com.gasqueue.Activitiy.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;
import pekl.gasqueue.com.gasqueue.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeProductDetailFragment extends Fragment{
    public ChangeProductDetailFragment() {
        // Required empty public constructor
    }
    private View view;
    private ShoppingController shoppingController;
    private List<Product.Category> categories;
    private ArrayAdapter<Product.Category> spinnerAdapter;

    private EditText nameTextField;
    private EditText priceTextField;
    private Spinner categorySpinner;
    private Button saveBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initializeViews();


        view = inflater.inflate(R.layout.fragment_change_product_detail, container, false);
/*        nameTextField = (EditText) view.findViewById(R.id.nameTF);
        priceTextField = (EditText) view.findViewById(R.id.priceTF);
        categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
        saveBtn = (Button) view.findViewById(R.id.saveBtn);
//        nameTextField.setText(shoppingController.getChosenProduct().getName() + "");
//        priceTextField.setText(shoppingController.getChosenProduct().getPrice() + "");
        spinnerAdapter = new ArrayAdapter<Product.Category>(view.getContext(), android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        if (shoppingController.getIntention() == true)
        {
            nameTextField.setText("Name of product");
            priceTextField.setText("Price of product in kr");
        }
        categorySpinner.setAdapter(spinnerAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                shoppingController.getChosenProduct().setChanges(shoppingController.getChosenProduct().getName(), (Product.Category) adapterView.getItemAtPosition(i), shoppingController.getChosenProduct().getPrice());
                System.out.println(shoppingController.getChosenProduct().getCategory().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingController.getChosenProduct().setChanges(nameTextField.getText().toString(), shoppingController.getChosenProduct().getCategory(), Integer.parseInt(priceTextField.getText().toString()));
            }
        });
*/
        return view;
    }

    private void initializeViews()
    {
        shoppingController = new ShoppingController();
        categories = new ArrayList<Product.Category>(EnumSet.allOf(Product.Category.class));
    }
}
