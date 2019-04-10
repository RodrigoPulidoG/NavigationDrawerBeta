package com.example.rodpro.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;

import static com.example.rodpro.navigationdrawer.R.id.drawer_layout;
import static com.example.rodpro.navigationdrawer.R.id.sScooter;
import static com.example.rodpro.navigationdrawer.R.id.spacer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Switch sA,sP,sLB,sM,sT,sC,ssA,ssP,ssLB,ssM,ssT,ssC;
    private ProgressBar pb_A, pb_B, pb_C, pb_D, pb_E;
    private NavigationView navigationView;
    private Button boton;
    private DrawerLayout midrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        boton = findViewById(R.id.boton);
        midrawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        pb_A = findViewById(R.id.login_spin_kitA);
        pb_B = findViewById(R.id.login_spin_kitB);
        pb_C = findViewById(R.id.login_spin_kitC);
        pb_D = findViewById(R.id.login_spin_kitD);
        pb_E = findViewById(R.id.login_spin_kitE);

        sA = findViewById(R.id.switchScooter);
        sP = findViewById(R.id.switchPersonal);
        sLB = findViewById(R.id.switchLowBat);
        sM = findViewById(R.id.switchMantenimiento);
        sT = findViewById(R.id.switchTransporte);
        sC = findViewById(R.id.switchClear);

        ssA = navigationView.getHeaderView(0).findViewById(R.id.sScooter);
        ssP = navigationView.getHeaderView(0).findViewById(R.id.sPersonal);
        ssLB = navigationView.getHeaderView(0).findViewById(R.id.sLowBat);
        ssM = navigationView.getHeaderView(0).findViewById(R.id.sMantenimiento);
        ssT = navigationView.getHeaderView(0).findViewById(R.id.sTransporte);
        ssC = navigationView.getHeaderView(0).findViewById(R.id.sClear);

        ssA.setOnClickListener(this);
        ssP.setOnClickListener(this);
        ssLB.setOnClickListener(this);
        ssM.setOnClickListener(this);
        ssT.setOnClickListener(this);
        ssC.setOnClickListener(this);

        loadPreferences();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_googleMaps) {
            Intent intentMap = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intentMap);
        } else if (id == R.id.nav_slideshow) {
            Intent intentMap = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intentMap);
        } else if (id == R.id.nav_gridView) {
            Intent intentGrid = new Intent(getApplicationContext(), GridActivity.class);
            startActivity(intentGrid);
        } else if (id == R.id.nav_recycler) {
            Intent intentRecycler = new Intent(getApplicationContext(), RecyclerActivity.class);
            startActivity(intentRecycler);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()){

            case (R.id.boton):
                midrawer.openDrawer(GravityCompat.START);
                break;

            case (R.id.sScooter):
                visualProgresBar(ssA.isChecked(),pb_A);
                break;
            case (R.id.sPersonal):
                visualProgresBar(ssP.isChecked(),pb_B);
                break;
            case (R.id.sLowBat):
                visualProgresBar(ssLB.isChecked(),pb_C);
                break;
            case (R.id.sMantenimiento):
                visualProgresBar(ssM.isChecked(),pb_D);
                break;
            case (R.id.sTransporte):
                visualProgresBar(ssT.isChecked(),pb_E);
                break;
            case (R.id.sClear):
                clearAllSwitches(ssC.isChecked());
                break;

            case R.id.switchScooter:
                savePreferences("stateA",sA.isChecked());
                visualProgresBar(sA.isChecked(),pb_A);
                break;
            case R.id.switchPersonal:
                savePreferences("stateP",sP.isChecked());
                visualProgresBar(sP.isChecked(),pb_B);
                break;
            case R.id.switchLowBat:
                savePreferences("stateLB",sLB.isChecked());
                visualProgresBar(sLB.isChecked(),pb_C);
                break;
            case R.id.switchMantenimiento:
                savePreferences("stateM",sM.isChecked());
                visualProgresBar(sM.isChecked(),pb_D);
                break;
            case R.id.switchTransporte:
                savePreferences("stateT",sT.isChecked());
                visualProgresBar(sT.isChecked(),pb_E);
                break;

            case R.id.switchClear:
                savePreferences("stateC",sC.isChecked());
                clearAllSwitches(sC.isChecked());
                break;

                default:
                break;
        }
    }

    private void clearAllSwitches(boolean stado) {
        if (stado){
            ssA.setChecked(false);
            ssP.setChecked(false);
            ssLB.setChecked(false);
            ssM.setChecked(false);
            ssT.setChecked(false);
            sA.setChecked(false);
            sP.setChecked(false);
            sLB.setChecked(false);
            sM.setChecked(false);
            sT.setChecked(false);
        }
        ssC.setChecked(false);
        sC.setChecked(false);

        visualProgresBar(ssA.isChecked(),pb_A);
        visualProgresBar(ssP.isChecked(),pb_B);
        visualProgresBar(ssLB.isChecked(),pb_C);
        visualProgresBar(ssM.isChecked(),pb_D);
        visualProgresBar(ssT.isChecked(),pb_E);
        visualProgresBar(sA.isChecked(),pb_A);
        visualProgresBar(sP.isChecked(),pb_B);
        visualProgresBar(sLB.isChecked(),pb_C);
        visualProgresBar(sM.isChecked(),pb_D);
        visualProgresBar(sT.isChecked(),pb_E);
    }

    private void visualProgresBar(boolean state, ProgressBar p_bar) {
        Log.i("state", "state");
        if (state){
            p_bar.setVisibility(View.VISIBLE);
        }else {
            p_bar.setVisibility(View.INVISIBLE);
        }
    }

    private void savePreferences(String eti,Boolean stat) {
        SharedPreferences preferences = getSharedPreferences("Estados", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(eti,stat);
        editor.commit();

    }

    private void loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("Estados", Context.MODE_PRIVATE);
        boolean estadoA = preferences.getBoolean("stateA",false); sA.setChecked(estadoA);
        boolean estadoP = preferences.getBoolean("stateP",false); sP.setChecked(estadoP);
        boolean estadoLB = preferences.getBoolean("stateLB",false); sLB.setChecked(estadoLB);
        boolean estadoM = preferences.getBoolean("stateM",false); sM.setChecked(estadoM);
        boolean estadoT = preferences.getBoolean("stateT",false); sT.setChecked(estadoT);
        boolean estadoC = preferences.getBoolean("stateC",false); sC.setChecked(estadoC);
    }
}
