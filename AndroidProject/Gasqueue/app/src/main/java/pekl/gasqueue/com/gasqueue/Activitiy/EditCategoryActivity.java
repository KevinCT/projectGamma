package pekl.gasqueue.com.gasqueue.Activitiy;



import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pekl.gasqueue.com.gasqueue.Activitiy.Fragments.MenuCategoryFragment;
import pekl.gasqueue.com.gasqueue.R;

public class EditCategoryActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MenuCategoryFragment fragment;
    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
        initFragment();
        handleData();



    }
    private void initFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new MenuCategoryFragment();
        fragmentTransaction.add(R.id.relativeLayout, fragment);
        fragmentTransaction.commit();

    }
    private void handleData(){
        data=getIntent().getExtras();
        fragment.setArguments(data);

    }
}
