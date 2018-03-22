package fr.bicyclopresto.bicyclopresto_bike_fix;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_recap extends Fragment {
    // declaration variable

    //Button btnSaveProfil;
    //EditText profil_name;
    //SharedPreferences sharedpreferences;


    public Fragment_recap() {
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
        View rootView = inflater.inflate(R.layout.fragment_recap, container, false);

        // return inflater.inflate(R.layout.fragment_profil, container, false);

        //DECLARATION DES VARIABLES

        //ImageButton btnSaveProfil = (ImageButton) rootView.findViewById(R.id.btn_profil_save);
        final TextView recap_name = rootView.findViewById(R.id.fragment_recap_name);
        final TextView recap_mail = rootView.findViewById(R.id.fragment_recap_mail);
        final TextView recap_phone = rootView.findViewById(R.id.fragment_recap_phone);

        final TextView recap_ambulant = rootView.findViewById(R.id.fragment_recap_ambulant);
        final TextView recap_repair = rootView.findViewById(R.id.fragment_recap_repair);

        final TextView recap_date = rootView.findViewById(R.id.fragment_recap_date);
        final TextView recap_time = rootView.findViewById(R.id.fragment_recap_time);

        final TextView recap_where = rootView.findViewById(R.id.fragment_recap_time);

        final TextView recap_fix = rootView.findViewById(R.id.fragment_recap_fix);

        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        // récupération des informations utilisateurs

        recap_name.setText(settings.getString("profil_name", "").toString());
        recap_mail.setText(settings.getString("profil_mail", "").toString());
        recap_phone.setText(settings.getString("profil_phone", "").toString());

        recap_ambulant.setText(settings.getString("what_mode_repair", "").toString());
        recap_repair.setText(settings.getString("what_repair", "").toString());

        recap_date.setText(settings.getString("when_date", "").toString());
        recap_time.setText(settings.getString("when_time", "").toString());

        recap_where.setText(settings.getString("where_repair", "").toString());


        recap_fix.setText(settings.getString("fix_name", "").toString());



        //Toast.makeText(getActivity().getApplicationContext(), "Name: " +settings.getString("profil_name", "").toString()
        //        + "\n Mail: " +settings.getString("profil_mail", "").toString()
        //        + "\n Phone: " +settings.getString("profil_phone", "").toString()
        //        , Toast.LENGTH_LONG).show();
        //profil_name.setText("toto");


        return rootView;
    }

}
