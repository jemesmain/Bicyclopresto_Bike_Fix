package fr.bicyclopresto.bicyclopresto_bike_fix;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.Gravity;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient mGoogleApiClient;

    // pour createVCard
    private static final String VCF_DIRECTORY = "/bikemeeting_vcard/";
    private static final String VCF_NAME = "bikemeeting_customer_vcard.vcf";
    private File vcfFile;
    //private String str_chemin = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "fichier.txt";

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

    public void ouvrirDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.START);
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

        } else if (id == R.id.nav_recap) {
            Fragment_recap fragment_recap = new Fragment_recap();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_recap).commit();





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

    protected void createVCard () {

//essai en internal storage au final gmail n'a pas les droits pour l'attacher au mail
/*
        String dirName = "vcard"
        public File getPublicStorageDir(String dirName) {
            // Get the directory for the user's public pictures directory.
            File fileDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), dirName);
            if (!fileDir.mkdirs()) {
                Log.e(LOG_TAG, "Directory not created");
            }
            return fileDir;
        }




        String filename = "customer.vcf";
        String fileContents = "Hello world!";
        FileOutputStream outputStream;
        File file = new File(getFilesDir(), filename);

        try {
            //outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
            Toast.makeText(MainActivity.this, "VCard created!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Exception VCard", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
*/


//essai en external storage
        //acces au settings
        final SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        File vdfdirectory = null;
        try {
            // File vcfFile = new File(this.getExternalFilesDir(null), "generated.vcf");
            vdfdirectory = new File(
                    getExternalStorageDirectory() + VCF_DIRECTORY);
            //avec cela ca fonctionne pas Environment.getExternalStorageDirectory() /storage emulated O
            // have the object build the directory structure, if needed.
            if (!vdfdirectory.exists()) {
                vdfdirectory.mkdirs();
            }

            //vcfFile = new File(vdfdirectory, "android_"+ Calendar.getInstance().getTimeInMillis() + ".vcf");
            vcfFile = new File(vdfdirectory, VCF_NAME);

            File chemin = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

            File fichier = new File(chemin,VCF_NAME);
            fichier.createNewFile();
            FileWriter fw = null;
            fw = new FileWriter(fichier);
            //fw.write("hello Clelia world!");
            fw.write("BEGIN:VCARD\r\n");
            fw.write("VERSION:3.0\r\n");
            fw.write("FN:" + settings.getString("profil_name", "").toString() + "\r\n");
            fw.write("TEL;TYPE=WORK,VOICE:" + settings.getString("profil_phone", "").toString() + "\r\n");
            fw.write("ADR;TYPE=WORK:" + settings.getString("where_repair", "").toString() + "\r\n");
            fw.write("EMAIL;TYPE=PREF,INTERNET:" + settings.getString("profil_mail", "").toString() + "\r\n");
            fw.write("END:VCARD\r\n");
            fw.close();

            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("/storage/emulated/0/Android/data/fr.bicyclopresto.bicyclopresto_bike_fix/files/Download/fichier.txt", Context.MODE_APPEND));
            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fichier);
            //outputStreamWriter.write("hello world");
            //outputStreamWriter.close();
/*
            //Vcard example
            FileWriter fw = null;
            fw = new FileWriter(vcfFile);
            fw.write("BEGIN:VCARD\r\n");
            fw.write("VERSION:3.0\r\n");
            // fw.write("N:" + p.getSurname() + ";" + p.getFirstName() + "\r\n");
            //fw.write("FN:" + etname.getText().toString() + "\r\n");
            fw.write("FN:" + settings.getString("profil_name", "").toString() + "\r\n");
            //  fw.write("ORG:" + p.getCompanyName() + "\r\n");
            //  fw.write("TITLE:" + p.getTitle() + "\r\n");
            //fw.write("TEL;TYPE=WORK,VOICE:" + etphon.getText().toString() + "\r\n");
            fw.write("TEL;TYPE=WORK,VOICE:" + settings.getString("profil_phone", "").toString() + "\r\n");
            //   fw.write("TEL;TYPE=HOME,VOICE:" + p.getHomePhone() + "\r\n");
            fw.write("ADR;TYPE=WORK:;;" + p.getStreet() + ";" + p.getCity() + ";" + p.getState() + ";" + p.getPostcode() + ";" + p.getCountry() + "\r\n");
            //fw.write("EMAIL;TYPE=PREF,INTERNET:" + etmail.getText().toString() + "\r\n");
            fw.write("EMAIL;TYPE=PREF,INTERNET:" + settings.getString("profil_mail", "").toString() + "\r\n");
            fw.write("END:VCARD\r\n");
            fw.close();
*/
                   /* Intent i = new Intent(); //this will import vcf in contact list
                    i.setAction(android.content.Intent.ACTION_VIEW);
                    i.setDataAndType(Uri.fromFile(vcfFile), "text/x-vcard");
                    startActivity(i);*/

            Toast.makeText(MainActivity.this, "VCard created!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Exception VCard \r\n"
                            + "directory: " + this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "\r\n"
                            + "name: " + VCF_NAME + "\r\n"
                    , Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


    protected void sendEmail() {
        Log.i("Send email", "");
        final SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        //choix du destinataire TO
        String magasin = settings.getString("fix_name", "").toString();
        String fix_mail ="";

        switch (magasin){
            case "Ardeche : PMPV Bikeshop":
                fix_mail="contact@pmpv-ardeche.com";
                break;
            case "Grenoble : Bicyclopresto":
                fix_mail="contact@bicyclopresto.fr";
                break;
            case "Grenoble : BikeCorner":
                fix_mail="bikecornergrenoble@gmail.com";
                break;
            case "Lyon : CycloDoc":
                fix_mail="cyclodoc.contact@gmail.com";
                break;
            case "Paris : HelpMyBike":
                fix_mail="serviceclients@helpmybike.fr";
                break;
            case "Toulouse : MecaniCycle":
                fix_mail="mecanicycle1@gmail.com";
                break;
            default:
                fix_mail="jeaneric.mesmain@gmail.com";
        }

        //creation de la vcard
        createVCard();

        String[] TO = {fix_mail};
        String[] CC = {"app@bicyclopresto.fr"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String url = "https://play.google.com/store/apps/details?id=fr.bicyclopresto.bicyclopresto_bike_fix";

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("BikeMeeting by BicycloPresto ");
        int start = builder.length();
        builder.append(url);
        int end = builder.length();

        builder.setSpan(new URLSpan(url), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);

        //joindre la Vcard
        //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse( "file://"+filelocation));
        //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+"Android/data/fr.bicyclopresto.bicyclopresto_bike_fix/files/Download"  +"/"+ "fichier.txt"));
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///"+ this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) +"/"+ VCF_NAME));
        //emailIntent.putExtra(Intent.EXTRA_STREAM, "/"+"storage/emulated/0/Android/data/fr.bicyclopresto.bicyclopresto_bike_fix/files/Download"  +"/"+ "fichier.txt");
        //Toast.makeText(MainActivity.this, "Attachment path: "+ this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() +"/"+"fichier.txt", Toast.LENGTH_LONG).show();
        //Toast.makeText(MainActivity.this, "Vcard Attachment path: " + this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) +"\n Vcard filename: "+ VCF_NAME, Toast.LENGTH_LONG).show();

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Demande de réparation de la part de "+settings.getString("profil_name", "").toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Name: " +settings.getString("profil_name", "").toString()
                + "\n Mail: " +settings.getString("profil_mail", "").toString()
                + "\n Phone: " +settings.getString("profil_phone", "").toString()
                + "\n Mode Reparation: " +settings.getString("what_mode_repair", "").toString()
                + "\n * si le mode de réparation est magasin ne pas tenir compte de l'adresse"
                + "\n Reparation: " +settings.getString("what_repair", "").toString()
                + "\n RDV souhaité: "+settings.getString("when_date", "").toString()+" "+ settings.getString("when_time", "").toString()
                + "\n Adresse: "+settings.getString("where_repair", "").toString()
                + "\n Magasin: "+settings.getString("fix_name", "").toString()
                + "\n \n Courriel généré l'application Google Play Store: \n\n"
                + builder
                + " \n\n Diffusez Largement! \n \n"
                );

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    protected void sendEmailJoin() {
        Log.i("Send email", "");
        final SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        //choix du destinataire TO
        String magasin = settings.getString("fix_name", "").toString();
        String fix_mail ="";





        String[] TO = {"contact@bicyclopresto.fr"};
        String[] CC = {"app@bicyclopresto.fr"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);



        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Demande d'utilisation de l'application par: "+settings.getString("join_mag", "").toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Ville: " +settings.getString("join_town", "").toString()
                + "\n Magasin: " +settings.getString("join_mag", "").toString()
                + "\n Type de réparation: " +settings.getString("join_type", "").toString()
                + "\n Qualification cycle: " +settings.getString("join_qualif", "").toString()
                + "\n Telephone: " +settings.getString("join_phone", "").toString()

                +"\n\n BikeMeeting by BicycloPresto sur google play \n\n https://play.google.com/store/apps/details?id=fr.bicyclopresto.bicyclopresto_bike_fix \n\n Diffusez Largement \n \n"
        );

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    public static void shareApp(Context context)
    {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Je vous invite à utiliser BikeMeeting, l'application de rencontre entre votre vélo et son réparateur: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

}
