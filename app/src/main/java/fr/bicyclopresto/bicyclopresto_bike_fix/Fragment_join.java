package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_join extends Fragment {


    public Fragment_join() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_join, container, false);
        // DECLARATION DE VARIABLE
        // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_join, container, false);


        ImageButton btnJoinMail = (ImageButton) rootView.findViewById(R.id.btn_join_mail);
        final EditText join_town = rootView.findViewById(R.id.edit_join_town);
        final EditText join_mag = rootView.findViewById(R.id.edit_join_mag);
        final EditText join_type = rootView.findViewById(R.id.edit_join_type);
        final EditText join_qualif = rootView.findViewById(R.id.edit_join_qualif);
        final EditText join_phone = rootView.findViewById(R.id.edit_join_phone);

        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();


        join_town.setText(settings.getString("join_town", "").toString());
        join_mag.setText(settings.getString("join_mag", "").toString());
        join_type.setText(settings.getString("join_type", "").toString());
        join_qualif.setText(settings.getString("join_qualif", "").toString());
        join_phone.setText(settings.getString("join_phone", "").toString());

        btnJoinMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = settings.edit();
                editor.putString("join_town",join_town.getText().toString());
                editor.putString("join_mag",join_mag.getText().toString());
                editor.putString("join_type",join_type.getText().toString());
                editor.putString("join_qualif",join_qualif.getText().toString());
                editor.putString("join_phone",join_phone.getText().toString());
                editor.commit(); // indispensable pour valider les changement dans les shared pref ;-)
                Toast.makeText(getActivity().getApplicationContext(), "Ville: " +join_town.getText().toString()
                                + "\n Magasin: " +join_mag.getText().toString()
                                + "\n Type réparation: " +join_type.getText().toString()
                                + "\n Qualification cycle: " +join_qualif.getText().toString()
                                + "\n Telephone: " +join_phone.getText().toString()
                        , Toast.LENGTH_SHORT).show();

                Toast.makeText(getActivity(), "Start sending email" , Toast.LENGTH_SHORT ).show();
                //sendEmail();
                ((MainActivity) getActivity()).sendEmailJoin();

            }
        });
        return rootView;
    }

}
