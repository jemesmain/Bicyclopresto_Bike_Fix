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
       View rootView = inflater.inflate(R.layout.fragment_profil, container, false);

       // return inflater.inflate(R.layout.fragment_profil, container, false);

        Button btnSaveProfil = (Button) rootView.findViewById(R.id.btn_profil_save);
        //profil_name = rootView.findViewById(R.id.edit_profil_nom);

        btnSaveProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = settings.edit();
                //editor.putString("profil_name",profil_name.getText().toString());
                Toast.makeText(getActivity().getApplicationContext(), "Button pressed", Toast.LENGTH_LONG).show();


            }
        });
        return rootView;
    }

}
