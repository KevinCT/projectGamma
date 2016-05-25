package pekl.gasqueue.com.gasqueue.Activitiy;



import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pekl.gasqueue.com.gasqueue.Activitiy.Fragments.MenuCategoryFragment;
import pekl.gasqueue.com.gasqueue.R;

public class EditCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_category);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MenuCategoryFragment fragment = new MenuCategoryFragment();
        fragmentTransaction.add(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
        Bundle data=getIntent().getExtras();

        Bundle bundle = new Bundle();
        bundle.putAll(data);
        fragment.setArguments(bundle);

    }
}
