package io.hotkey.rpi_monitorwatcher.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.orm.SugarContext;

import io.hotkey.rpi_monitorwatcher.R;
import io.hotkey.rpi_monitorwatcher.model.Raspberry;
import io.hotkey.rpi_monitorwatcher.ui.fragment.StatisticsFragment;
import io.hotkey.rpi_monitorwatcher.ui.fragment.StatusFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Create a new Fragment to be placed in the activity layout
        StatusFragment statusFragment = new StatusFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        statusFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, statusFragment).commit();

        SugarContext.init(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_main, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_status) {

            // Handle the status action
            Fragment statusFragment = new StatusFragment();
            exchangeFragment(statusFragment, true);

        } else if (id == R.id.nav_statistics) {

            // Handle the statistics action
            Fragment statisticsFragment = new StatisticsFragment();
            exchangeFragment(statisticsFragment, true);

        } else if (id == R.id.nav_share) {

            // Handle the share action

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.menu_share_content));
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.menu_share)));

        } else if (id == R.id.nav_donate) {

            // Handle the donate action
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/cyberto"));
            startActivity(browserIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     *
     * @param fragment  The fragment we want to change to
     * @param history   true, if the transaction should be navigatable
     */
    public void exchangeFragment(Fragment fragment, boolean history){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        if(history) transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
