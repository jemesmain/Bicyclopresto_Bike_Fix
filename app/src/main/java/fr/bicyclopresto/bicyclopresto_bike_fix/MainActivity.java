package fr.bicyclopresto.bicyclopresto_bike_fix;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Terminer votre demande", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                        .setAction("Envoyer", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Start sending email" , Toast.LENGTH_LONG ).show();
                        sendEmail();
                    }
                }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //default fragment = fragment_home
        Fragment_home fragment_home = new Fragment_home();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, fragment_home).commit();





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
            //default fragment = fragment_home
            Fragment_home fragment_home = new Fragment_home();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_home).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
        SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final EditText profil_name = findViewById(R.id.edit_profil_nom);
        // default fragment = fragment home

        if (id == R.id.nav_profil) {
            // Handle the profil action
            //Context context = getApplicationContext();
            //CharSequence text = "Hello toast!";
            //int duration = Toast.LENGTH_SHORT;
            //Toast toast = Toast.makeText(context, text, duration);
            //toast.show();

            Fragment_profil fragment_profil = new Fragment_profil();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_profil).commit();


        } else if (id == R.id.nav_what) {
            Fragment_what fragment_what = new Fragment_what();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_what).commit();

        } else if (id == R.id.nav_when) {
            Fragment_when fragment_when = new Fragment_when();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_when).commit();

        } else if (id == R.id.nav_where) {
            Fragment_where fragment_where = new Fragment_where();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_where).commit();

        } else if (id == R.id.nav_fix) {
            Fragment_fix fragment_fix = new Fragment_fix();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_fix).commit();

        } else if (id == R.id.nav_share) {
            Fragment_share fragment_share = new Fragment_share();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_share).commit();

        }else if (id == R.id.nav_join) {
            Fragment_join fragment_join = new Fragment_join();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_join).commit();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        final SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        //choix du destinataire TO
        String magasin = settings.getString("fix_name", "").toString();
        String fix_mail ="";

        switch (magasin){
            case "Grenoble : Bicyclopresto":
                fix_mail="contact@bicyclopresto.fr";
                break;
            case "Grenoble : Bike Corner":
                fix_mail="clelia.mesmain@gmail.com";
                break;
            default:
                fix_mail="jeaneric.mesmain@gmail.com";
        }



        String[] TO = {fix_mail};
        String[] CC = {"app@bicyclopresto.fr"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);



        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Demande de réparation");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Name: " +settings.getString("profil_name", "").toString()
                + "\n Mail: " +settings.getString("profil_mail", "").toString()
                + "\n Phone: " +settings.getString("profil_phone", "").toString()
                + "\n Reparation: " +settings.getString("what_repair", "").toString()
                + "\n RDV souhaité: "+settings.getString("when_date", "").toString()+" "+ settings.getString("when_time", "").toString()
                + "\n Adresse: "+settings.getString("where_repair", "").toString()
                + "\n Magasin: "+settings.getString("fix_name", "").toString()
                + "\n \n Courriel généré l'application Google Play Store:" +
                        "\n Bike Repair by BicycloPresto \n\n Diffusez Largement \n \n"
                );

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
