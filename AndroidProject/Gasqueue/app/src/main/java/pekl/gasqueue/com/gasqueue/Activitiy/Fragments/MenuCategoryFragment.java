package pekl.gasqueue.com.gasqueue.Activitiy.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import pekl.gasqueue.com.gasqueue.Activitiy.CartActivity;
import pekl.gasqueue.com.gasqueue.Activitiy.ProductActivity;
import pekl.gasqueue.com.gasqueue.R;
import pekl.gasqueue.com.gasqueue.control.ShoppingController;
import pekl.gasqueue.com.gasqueue.model.Authenticator;
import pekl.gasqueue.com.gasqueue.model.Menu;
import pekl.gasqueue.com.gasqueue.model.Product;
import pekl.gasqueue.com.gasqueue.service.FirebaseDatabaseManager;
import pekl.gasqueue.com.gasqueue.service.IDatabaseManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuCategoryFragment extends Fragment implements View.OnClickListener {
    private Button cartBtn ;
    private String barPassword;
    private String customerPassword;
    private boolean clientType;
    private Button createBarBtn;
    ShoppingController shoppingController;

    public MenuCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        shoppingController = ShoppingController.getInstance();
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
        cartBtn = (Button) view.findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getActivity(),  CartActivity.class);
                startActivity(nextActivity);

            }
        });
        try {
            shoppingController.setTypeOfUser(getArguments().getBoolean("clientType"));
        }
        catch(NullPointerException exception){
            shoppingController.setTypeOfUser(false);

        }
        //shit code ,just temporary to prevent merge conflicts and try stuff
        createBarBtn = (Button) view.findViewById(R.id.createBarBtn);
        createBarBtn.setVisibility(View.INVISIBLE);
        if(getArguments()!=null) {
           getData();
           createBarBtn.setVisibility(View.VISIBLE);
           createBarBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  shoppingController.sendMenu(barPassword,customerPassword);
               }
           });
       }
        return view;


    }
    public void onClick(View v) {

        Intent temp = new Intent(getActivity(),ProductActivity.class);
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
        }
        //check maybe move putextra.
        temp.putExtra("category",shoppingController.getChosenCategory());
        startActivity(temp);
    }
    private void getData(){
       barPassword = getArguments().getString("barPassword");
        customerPassword=getArguments().getString("customerPassword");
        clientType =getArguments().getBoolean("clientType");
    }

}
