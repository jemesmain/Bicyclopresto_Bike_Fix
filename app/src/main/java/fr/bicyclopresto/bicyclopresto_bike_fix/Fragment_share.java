package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_share extends Fragment {


    public Fragment_share() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_share, container, false);
        // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_share, container, false);



        //DECLARATION DES VARIABLES

        ImageButton btnShare = (ImageButton) rootView.findViewById(R.id.btn_share);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open drawer
                //drawer.openDrawer(GravityCompat.START);
                //@Override
                //public void onClick(View v) {
                ((MainActivity) getActivity()).shareApp(getContext());
                //}
                //Toast.makeText(getActivity().getApplicationContext(), "Merci d'appuyer sur le bouton en haut à gauche "
                //                , Toast.LENGTH_SHORT).show();


            }
        });






        return rootView;
    }

}
