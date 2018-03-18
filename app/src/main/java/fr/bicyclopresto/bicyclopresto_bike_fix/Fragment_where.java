package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_where extends Fragment {


    public Fragment_where() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_where, container, false);
        // DECLARATION DE VARIABLE
        // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_where, container, false);



        //SupportPlaceAutocompleteFragment places= (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        SupportPlaceAutocompleteFragment places = (SupportPlaceAutocompleteFragment)
        getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        //DECLARATION DES VARIABLES

        ImageButton btnSaveWhere = (ImageButton) rootView.findViewById(R.id.btn_where_save);
        final TextView text_where = rootView.findViewById(R.id.fragment_where_save);
        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        // récupération des informations utilisateurs

        text_where.setText(settings.getString("where_repair", "").toString());

        Toast.makeText(getActivity().getApplicationContext(), "Adresse: " +settings.getString("where_repair", "").toString()

                , Toast.LENGTH_LONG).show();

        //You don't add a fragment into other fragment by the
        //property "name" in your Layout. You must use:
        places = new SupportPlaceAutocompleteFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.place_autocomplete_fragment,places);
        transaction.commit();
        //filtre pour la france
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("Fr")
                .build();
        places.setFilter(typeFilter);
        //places.setHint("ville puis adresse");

        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

// TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                Toast.makeText(getActivity().getApplicationContext(),place.getAddress(),Toast.LENGTH_SHORT).show();
                text_where.setText(place.getAddress());
            }

            @Override
            public void onError(Status status) {
// TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(getActivity().getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        btnSaveWhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = settings.edit();
                editor.putString("where_repair",text_where.getText().toString());
                editor.commit(); // indispensable pour valider les changement dans les shared pref ;-)
                Toast.makeText(getActivity().getApplicationContext(), "Adresse: " +text_where.getText().toString()

                        , Toast.LENGTH_LONG).show();


            }
        });


        return rootView;
    }



}

/*
PlaceAutocompleteFragment places= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Toast.makeText(getActivity().getApplicationContext(),place.getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getActivity().getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });
 */