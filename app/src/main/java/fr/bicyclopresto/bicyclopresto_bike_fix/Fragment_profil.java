package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_profil extends Fragment {


    public Fragment_profil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
        btnSaveProfil = findViewById(R.id.profil_save);
        profil_name = findViewById(R.id.edit_profil_nom);

        btnSaveProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                SharedPreferences settings = getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("profil_name",profil_name.getText().toString());
                Toast.makeText(getApplicationContext(), "Profil saved", Toast.LENGTH_LONG).show();


            }
        });
    }

}
