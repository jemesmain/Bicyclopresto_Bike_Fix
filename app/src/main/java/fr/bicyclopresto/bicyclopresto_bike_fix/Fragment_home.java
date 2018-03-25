package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_home extends Fragment {


    public Fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);

        // DECLARATION DE VARIABLE
        // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //final DrawerLayout drawer = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);


        // return inflater.inflate(R.layout.fragment_profil, container, false);

        //DECLARATION DES VARIABLES

        ImageButton btnHomeMenu = (ImageButton) rootView.findViewById(R.id.btn_home_menu);

        ImageButton btnHomeFingerprint = (ImageButton) rootView.findViewById(R.id.btn_home_fingerprint);
        ImageButton btnHomeMotorcycle = (ImageButton) rootView.findViewById(R.id.btn_home_motorcycle);
        ImageButton btnHomeToday = (ImageButton) rootView.findViewById(R.id.btn_home_today);
        ImageButton btnHomeLocation = (ImageButton) rootView.findViewById(R.id.btn_home_location);
        ImageButton btnHomeManage = (ImageButton) rootView.findViewById(R.id.btn_home_manage);
        ImageButton btnHomeReceipt = (ImageButton) rootView.findViewById(R.id.btn_home_receipt);

        ImageButton btnHomeShare = (ImageButton) rootView.findViewById(R.id.btn_home_share);
        ImageButton btnHomeJoin = (ImageButton) rootView.findViewById(R.id.btn_home_join);



        btnHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open drawer
                //drawer.openDrawer(GravityCompat.START);
                //@Override
                //public void onClick(View v) {
                    ((MainActivity) getActivity()).ouvrirDrawer();
                //}
                //Toast.makeText(getActivity().getApplicationContext(), "Merci d'appuyer sur le bouton en haut à gauche "
                //                , Toast.LENGTH_SHORT).show();


            }
        });

        btnHomeFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_profil fragment_profil = new Fragment_profil();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_profil).commit();


            }
        });

        btnHomeMotorcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_what fragment_what = new Fragment_what();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_what).commit();


            }
        });

        btnHomeToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_when fragment_when = new Fragment_when();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_when).commit();


            }
        });

        btnHomeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_where fragment_where = new Fragment_where();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_where).commit();


            }
        });

        btnHomeManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_fix fragment_fix = new Fragment_fix();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_fix).commit();


            }
        });

        btnHomeReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_recap fragment_recap = new Fragment_recap();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_recap).commit();


            }
        });

        btnHomeShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_share fragment_share = new Fragment_share();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_share).commit();


            }
        });

        btnHomeJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_join fragment_join = new Fragment_join();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_join).commit();


            }
        });



        return rootView;





    }

}
