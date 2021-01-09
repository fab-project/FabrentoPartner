package com.app.fabrentopartner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.app.fabrentopartner.fragment.DashboardFragment;
import com.app.fabrentopartner.fragment.LeadFragment;
import com.app.fabrentopartner.fragment.ProfileFragment;
import com.app.fabrentopartner.fragment.RevenueFragment;
import com.app.fabrentopartner.fragment.ShareProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navView;
    private HashMap<String, Stack<Fragment>> mStacks;
    public static final String TAB_HOME = "tab_home";
    public static final String TAB_LEAD = "tab_lead";
    public static final String TAB_REVENUE = "tab_revenue";
    public static final String TAB_PRODUCT = "tab_product";
    public static final String TAB_PROFILE = "tab_profile";
    String mCurrentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_Lead, R.id.navigation_product, R.id.navigation_package, R.id.navigation_me).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put(TAB_HOME, new Stack<Fragment>());
        mStacks.put(TAB_LEAD, new Stack<Fragment>());
        mStacks.put(TAB_REVENUE, new Stack<Fragment>());
        mStacks.put(TAB_PRODUCT, new Stack<Fragment>());
        mStacks.put(TAB_PROFILE, new Stack<Fragment>());

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedTab(TAB_HOME);
                        return true;
                    case R.id.navigation_Lead:
                        selectedTab(TAB_LEAD);
                        return true;
                    case R.id.navigation_product:
                        selectedTab(TAB_REVENUE);
                        return true;
                    case R.id.navigation_package:
                        selectedTab(TAB_PRODUCT);
                        return true;
                    case R.id.navigation_me:
                        selectedTab(TAB_PROFILE);
                        return true;
                }
                return false;
            }

            private void selectedTab(String tabId) {
                mCurrentTab = tabId;
                if (mStacks.get(tabId).size() == 0) {
                    /*
                     *    First time this tab is selected. So add first fragment of that tab.
                     *    Don't need animation, so that argument is false.
                     *    We are adding a new fragment which is not present in stack. So add to stack is true.
                     */
                    if (tabId.equals(TAB_HOME)) {
                        pushFragments(tabId, new DashboardFragment(), true);
                    } else if (tabId.equals(TAB_LEAD)) {
                        pushFragments(tabId, new LeadFragment(), true);
                    } else if (tabId.equals(TAB_REVENUE)) {
                        pushFragments(tabId, new RevenueFragment(), true);
                    } else if (tabId.equals(TAB_PRODUCT)) {
                        pushFragments(tabId, new ShareProductFragment(), true);
                    } else if (tabId.equals(TAB_PROFILE)) {
                        pushFragments(tabId, new ProfileFragment(), true);
                    }
                } else {
                    /*
                     *    We are switching tabs, and target tab is already has at least one fragment.
                     *    No need of animation, no need of stack pushing. Just show the target fragment
                     */
                    pushFragments(tabId, mStacks.get(tabId).lastElement(), false);
                }
            }

            public void pushFragments(String tag, Fragment fragment, boolean shouldAdd) {
                if (shouldAdd) mStacks.get(tag).push(fragment);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        };
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
