package com.privatinfoapp.view.activity;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.privatinfoapp.R;
import com.privatinfoapp.view.fragment.MainFragment;

/**
 * Created by Vladimir on 28.03.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
    }

    /**
     * @param fragment
     *
     * replace fragment with back stack
     */
    public void replaceFragmentWithBackStack(Fragment fragment) {
        fragmentManager.beginTransaction()
                .addToBackStack(fragment.getClass().getName())
                .replace(R.id.fragmentContainer, fragment, fragment.getClass().getName())
                .commit();
    }

    /**
     * @param fragment
     *
     * replace fragment non back stack
     */
    public void replaceFragmentNonBackStack(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, fragment.getClass().getName())
                .commit();
    }

    /**
     * @param fragment
     * @param enter
     * @param exit
     *
     * Add fragment with back stack
     */
    public void addFragmentWithBackStack(Fragment fragment, @AnimRes int enter, @AnimRes int exit) {
        fragmentManager.beginTransaction()
                .addToBackStack(fragment.getClass().getName())
                .setCustomAnimations(enter, exit, enter, exit)
                .add(R.id.fragmentContainer, fragment, fragment.getClass().getName())
                .commit();
    }

    /**
     * @param fragment
     * @param enter
     * @param exit
     *
     * Add fragment non back stack
     */
    public void addFragmentNonBackStack(Fragment fragment, @AnimRes int enter, @AnimRes int exit) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(enter, exit, enter, exit)
                .add(R.id.fragmentContainer, fragment, fragment.getClass().getName())
                .commit();
    }

    /**
     * @param message
     *
     * Show message
     */
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        fragmentManager.popBackStack();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
    }

    protected abstract int getActivityLayout();
}
