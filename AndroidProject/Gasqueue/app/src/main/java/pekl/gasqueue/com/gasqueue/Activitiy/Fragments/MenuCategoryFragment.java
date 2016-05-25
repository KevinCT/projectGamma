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
    private Product.Category category;
    private String barPW;
    private String customerPW;
    private boolean clientType;
    ShoppingController shoppingController = new ShoppingController();

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
        cartBtn = (Button) view.findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActiity = new Intent(getActivity(),  CartActivity.class);
                startActivity(nextActiity);

            }
        });
        try {
            shoppingController.setTypeOfUser(getArguments().getBoolean("clientType"));
        }
        catch(NullPointerException exception){

        }
        //shit code ,just temporary to prevent merge conflicts and try stuff
        Button createBarBtn = (Button) view.findViewById(R.id.createBarBtn);

        createBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Authenticator authenticator;
                Gson gson = new Gson();
                String object = getArguments().getString("authenticator");
                authenticator=gson.fromJson(object,Authenticator.class);
                Map map1 = new HashMap();
                Map map2 = new HashMap();
                map1.put(authenticator.getBarPassword(),shoppingController.getMenu());
                map2.put(authenticator.getCustomerPassword(), shoppingController.getMenu());

                IDatabaseManager<Firebase> db = new FirebaseDatabaseManager(new Firebase("https://dazzling-torch-9680.firebaseio.com/"));
                db.createChildReference("Menus").push().setValue(map1);
                db.createChildReference("Menus").push().setValue(map2);


            }
        });
        return view;


    }
    public void onClick(View v) {

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
        shoppingController.setChosenCategory(category);
        //check maybe move putextra.
        temp.putExtra("category",category);
        startActivity(temp);
    }
    private void getData(){
        barPW = getArguments().getString("barPassword");
        customerPW=getArguments().getString("customerPassword");
        clientType =getArguments().getBoolean("clientType");
    }

}
