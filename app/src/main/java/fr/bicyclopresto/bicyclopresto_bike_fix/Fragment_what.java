package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_what extends Fragment {


    public Fragment_what() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_what, container, false);
        // DECLARATION DE VARIABLE
        // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_what, container, false);

        // return inflater.inflate(R.layout.fragment_profil, container, false);

        //DECLARATION DES VARIABLES
        String code_repair ="";

        ImageButton btnSaveWhat = (ImageButton) rootView.findViewById(R.id.btn_what_save);
        final EditText edit_what = rootView.findViewById(R.id.edit_what_repair);

        final RadioButton ambulant = rootView.findViewById(R.id.what_ambulant);
        final RadioButton magasin = rootView.findViewById(R.id.what_magasin);
        final RadioButton flycat = rootView.findViewById(R.id.what_flycat);

        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        //repositionnement eventuel du choix fonction du code repair
        code_repair = settings.getString("what_code_repair","").toString();
        switch (code_repair){
            case "1":
                ambulant.setChecked(true);
                break;
            case "2":
                magasin.setChecked(true);
                break;
            case "3":
                flycat.setChecked(true);
                break;
            default:
                ambulant.setChecked(true);

        }



        // récupération des informations utilisateurs

        edit_what.setText(settings.getString("what_repair", "").toString());

        //Toast.makeText(getActivity().getApplicationContext(), "Reparation: " +settings.getString("what_repair", "").toString()

         //       , Toast.LENGTH_LONG).show();

        btnSaveWhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = settings.edit();
                // le choix du mode de réparation est sauvegardé lors de la manipulation des radiobutton
                //editor.putString("what_mode_repair",mode_repair);
                editor.putString("what_repair",edit_what.getText().toString());

                editor.apply(); // indispensable pour valider les changement dans les shared pref ;-)
                Toast.makeText(getActivity().getApplicationContext(),
                        "Mode reparation: " +settings.getString("what_mode_repair","").toString()
                        +"\n Reparation: " +edit_what.getText().toString()
                        , Toast.LENGTH_SHORT).show();


                Fragment_when fragment_when = new Fragment_when();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_when).commit();




            }
        });

        ambulant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("what_mode_repair", "A domicile / En entreprise");
                editor.putString("what_code_repair","1");
                        editor.commit(); // indispensable pour valider les changement dans les shared pref ;-)

                Toast.makeText(getActivity().getApplicationContext(),
                        "Mode reparation: " +settings.getString("what_mode_repair","").toString()
                        , Toast.LENGTH_SHORT).show();

            }


        });

        magasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("what_mode_repair", "En magasin");
                editor.putString("what_code_repair","2");
                editor.commit(); // indispensable pour valider les changement dans les shared pref ;-)

                Toast.makeText(getActivity().getApplicationContext(),
                        "Mode reparation: " +settings.getString("what_mode_repair","").toString()
                        , Toast.LENGTH_SHORT).show();

            }


        });
        flycat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("what_mode_repair", "Reseau partenaire FlyCat");
                editor.putString("what_code_repair","3");
                editor.commit(); // indispensable pour valider les changement dans les shared pref ;-)

                Toast.makeText(getActivity().getApplicationContext(),
                        "Mode reparation: " +settings.getString("what_mode_repair","").toString()
                        , Toast.LENGTH_SHORT).show();

            }


        });


        return rootView;
    }

}
