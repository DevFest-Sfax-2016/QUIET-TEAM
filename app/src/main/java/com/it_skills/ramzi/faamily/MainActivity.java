package com.it_skills.ramzi.faamily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.it_skills.ramzi.faamily.adapters.PagerAdapter;
import com.it_skills.ramzi.faamily.fragments.SplashFragment1;
import com.it_skills.ramzi.faamily.fragments.SplashFragment2;
import com.it_skills.ramzi.faamily.fragments.SplashFragment3;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private static final int PICK_NAVIGATION_REQUEST = 10;
    private SplashFragment1 splachFragment1;
    private SplashFragment2 splachFragment2;
    private SplashFragment3 splachFragment3;

    private FragmentManager mFragmentManager;
    private PagerAdapter adapter;
    private ViewPager viewPager;

    RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private Button mSkiptext, mNextDoneText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        rb1 = (RadioButton) findViewById(R.id.radio_1);
        rb2 = (RadioButton) findViewById(R.id.radio_2);
        rb3 = (RadioButton) findViewById(R.id.radio_3);

        mSkiptext = (Button) findViewById(R.id.text_left);
        mSkiptext.setText(getResources().getString(R.string.title_activity_intro_skip));
        mSkiptext.setOnClickListener(this);
        mNextDoneText = (Button) findViewById(R.id.text_right);
        mNextDoneText.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radio_1) {
//                    Toast.makeText(getApplicationContext(), "choice: radio 1",
//                            Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(0);
                    mNextDoneText.setText(getResources().getString(R.string.title_activity_intro_next));

                } else if(checkedId == R.id.radio_2) {
//                    Toast.makeText(getApplicationContext(), "choice: radio 2",
//                            Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(1);
                    mNextDoneText.setText(getResources().getString(R.string.title_activity_intro_next));
                } else if(checkedId == R.id.radio_3) {
//                    Toast.makeText(getApplicationContext(), "choice: radio 3",
//                            Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(2);

                    mNextDoneText.setText(getResources().getString(R.string.title_activity_intro_done));


                }
            }

        });


        //radioGroup.check(R.id.radio_1);

        initViewPager();

        radioGroup.check(R.id.radio_1);
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);


        splachFragment1 = SplashFragment1.newInstance();
        splachFragment2 = SplashFragment2.newInstance();
        splachFragment3 = SplashFragment3.newInstance();

        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(splachFragment1, getResources().getString(R.string.title_activity_intro_1));
        adapter.addFragment(splachFragment2, getResources().getString(R.string.title_activity_intro_2));
        adapter.addFragment(splachFragment3, getResources().getString(R.string.title_activity_intro_3));

        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    radioGroup.check(R.id.radio_1);
                } else if (position == 1){
                    radioGroup.check(R.id.radio_2);
                } else if (position == 2){
                    radioGroup.check(R.id.radio_3);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.text_left :
                performNavigationDrawer();
                break;
            case R.id.text_right :
                updateNext();
                break;
        }

    }

    private void updateNext() {
        if (viewPager.getCurrentItem() == 2){
//            Toast.makeText(getApplicationContext(), "Done pressed",
//                            Toast.LENGTH_SHORT).show();
            performNavigationDrawer();

        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
//            Toast.makeText(getApplicationContext(), "Next pressed",
//                    Toast.LENGTH_SHORT).show();
        }
    }

    private void performNavigationDrawer() {

       Intent intent = new Intent(this, login.class);
        startActivityForResult(intent, PICK_NAVIGATION_REQUEST);
       finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
