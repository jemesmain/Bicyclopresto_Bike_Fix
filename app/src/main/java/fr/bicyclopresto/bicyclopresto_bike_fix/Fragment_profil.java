package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_profil extends Fragment {
    // declaration variable

    //Button btnSaveProfil;
    //EditText profil_name;
    //SharedPreferences sharedpreferences;


    public Fragment_profil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // permet de construire le contenu de l'onglet adequat du fragment
        //final View rootView = inflater.inflate(R.layout.fragment_tab_setting, container, false);

        // DECLARATION DE VARIABLE
       // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);

       // return inflater.inflate(R.layout.fragment_profil, container, false);

        //DECLARATION DES VARIABLES

        ImageButton btnSaveProfil = (ImageButton) rootView.findViewById(R.id.btn_profil_save);
        final EditText profil_name = rootView.findViewById(R.id.edit_profil_nom);
        final EditText profil_mail = rootView.findViewById(R.id.edit_profil_mail);
        final EditText profil_phone = rootView.findViewById(R.id.edit_profil_phone);
        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        // récupération des informations utilisateurs

        profil_name.setText(settings.getString("profil_name", "").toString());
        profil_mail.setText(settings.getString("profil_mail", "").toString());
        profil_phone.setText(settings.getString("profil_phone", "").toString());
        //Toast.makeText(getActivity().getApplicationContext(), "Name: " +settings.getString("profil_name", "").toString()
        //        + "\n Mail: " +settings.getString("profil_mail", "").toString()
        //        + "\n Phone: " +settings.getString("profil_phone", "").toString()
        //        , Toast.LENGTH_LONG).show();
        //profil_name.setText("toto");

        btnSaveProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = settings.edit();
                editor.putString("profil_name",profil_name.getText().toString());
                editor.putString("profil_mail",profil_mail.getText().toString());
                editor.putString("profil_phone",profil_phone.getText().toString());
                editor.commit(); // indispensable pour valider les changement dans les shared pref ;-)
                Toast.makeText(getActivity().getApplicationContext(), "Name: " +profil_name.getText().toString()
                        + "\n Mail: " +profil_mail.getText().toString()
                        + "\n Phone: " +profil_phone.getText().toString()
                        , Toast.LENGTH_SHORT).show();

                Fragment_what fragment_what = new Fragment_what();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_what).commit();


            }
        });
        return rootView;
    }

}
