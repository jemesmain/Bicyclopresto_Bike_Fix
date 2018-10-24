package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_when extends Fragment {


    public Fragment_when() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_when, container, false);
        // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_when, container, false);

        // assure la retrocompatibilite en dessous de la version 23 car les datePicker et TimPicker ne sont pas compatible
        if (Build.VERSION.SDK_INT<23){
            Fragment_when_alt fragment_when_alt = new Fragment_when_alt();
            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment_when_alt).commit();
            return rootView;



        } else {
            //Fragment_when fragment_when = new Fragment_when();
            //android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.fragment, fragment_when).commit();





        // return inflater.inflate(R.layout.fragment_profil, container, false);

        //DECLARATION DES VARIABLES

        ImageButton btnSaveWhen = (ImageButton) rootView.findViewById(R.id.btn_when_save);
        final DatePicker edit_when_date = rootView.findViewById(R.id.edit_when_DatePicker);
        final TimePicker edit_when_time = rootView.findViewById(R.id.edit_when_TimePicker);
        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        //affichage au format 24h du time picker
        edit_when_time.setIs24HourView(true);

        // récupération des informations utilisateurs

        //edit_when_date.setText(settings.getString("when_date", "").toString());
        //edit_when_time.setText(settings.getString("when_time", "").toString());

        //Toast.makeText(getActivity().getApplicationContext(), "RDV souhaité le: " +settings.getString("when_date", "").toString()
        //        + " " +settings.getString("when_time", "").toString()
        //        , Toast.LENGTH_LONG).show();

        btnSaveWhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = settings.edit();

                //recupération de la date
                int   day  = edit_when_date.getDayOfMonth();
                int   month= edit_when_date.getMonth();
                int   year = edit_when_date.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String formatedDate = sdf.format(calendar.getTime());

                //recupération de l'heure
                int hour = edit_when_time.getHour();
                int minute = edit_when_time.getMinute();
                String time = hour +":"+ minute;

                //sauvegarde dans les préférences
                editor.putString("when_date",formatedDate);
                editor.putString("when_time",time);
                editor.apply(); // indispensable pour valider les changement dans les shared pref ;-)
                Toast.makeText(getActivity().getApplicationContext(), "RDV souhaité le: " +formatedDate
                        +" " + time

                        , Toast.LENGTH_SHORT).show();

                Fragment_where fragment_where = new Fragment_where();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_where).commit();


            }
        });









        return rootView;
        }
    }

}
