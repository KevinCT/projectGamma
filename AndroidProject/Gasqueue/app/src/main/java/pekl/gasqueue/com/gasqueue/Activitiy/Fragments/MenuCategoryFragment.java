package pekl.gasqueue.com.gasqueue.Activitiy.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pekl.gasqueue.com.gasqueue.Activitiy.ProductActivity;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.model.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuCategoryFragment extends Fragment implements View.OnClickListener {


    public MenuCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_category, container, false);
        Button beerBtn = (Button) view.findViewById(R.id.beerBtn);
        beerBtn.setOnClickListener(this);
        Button drinkBtn= (Button) view.findViewById(R.id.drinksBtn);
        drinkBtn.setOnClickListener(this);
        Button ciderBtn= (Button) view.findViewById(R.id.ciderBtn);
        ciderBtn.setOnClickListener(this);
        Button alkfriaBtn= (Button) view.findViewById(R.id.alkfriaBtn);
        alkfriaBtn.setOnClickListener(this);
        Button matBtn = (Button) view.findViewById(R.id.matBtn);
        matBtn.setOnClickListener(this);
        return view;


    }
    public void onClick(View v) {
        Product.Category category= Product.Category.BEER;
        Intent temp = new Intent(getActivity(),ProductActivity.class);
        switch (v.getId())
        {
            case R.id.beerBtn:
                category= Product.Category.BEER;
                break;
            case R.id.drinksBtn:
                category=Product.Category.DRINK;
                break;
            case R.id.ciderBtn:
                category=Product.Category.CIDER;
                break;
            case R.id.alkfriaBtn:
                category=Product.Category.NON_ALCOHOLIC;
                break;
            case R.id.matBtn:
               category=Product.Category.FOOD;
                break;
        }
        temp.putExtra("category",category);
        startActivity(temp);
    }

}
