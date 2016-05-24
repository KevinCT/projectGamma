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
import pekl.gasqueue.com.gasqueue.control.EditorController;
import pekl.gasqueue.com.gasqueue.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeProductDetailFragment extends Fragment{
    public ChangeProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        EditorController editorController = new EditorController();
        View view = inflater.inflate(R.layout.fragment_change_product_detail, container, false);
        List<Product.Category> categories = new ArrayList<Product.Category>(EnumSet.allOf(Product.Category.class));
        final ArrayAdapter<Product.Category> spinnerAdapter = new ArrayAdapter<Product.Category>(view.getContext(), android.R.layout.simple_spinner_item,categories);

        final Product chosenProduct;
        final EditText nameTextField = (EditText) view.findViewById(R.id.nameTF) ;
        final EditText priceTextField = (EditText) view.findViewById(R.id.priceTF);
        final Spinner categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
        Button saveBtn = (Button) view.findViewById(R.id.saveBtn);

        chosenProduct = editorController.getChosenProduct();
       // nameTextField.setText(chosenProduct.getName().toString(), TextView.BufferType.EDITABLE);
        priceTextField.setText(chosenProduct.getPrice() + " kr", TextView.BufferType.EDITABLE);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chosenProduct.setChanges(chosenProduct.getName(),(Product.Category)adapterView.getItemAtPosition(i),chosenProduct.getPrice());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenProduct.setChanges(nameTextField.getText().toString(),chosenProduct.getCategory(),Integer.parseInt(priceTextField.getText().toString()));
            }
        });

        return view;
    }

}
