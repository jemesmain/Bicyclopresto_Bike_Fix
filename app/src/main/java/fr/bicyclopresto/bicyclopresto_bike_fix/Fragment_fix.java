package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_fix extends Fragment {


    public Fragment_fix() {
        // Required empty public constructor
    }


    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fix, container, false);
        // DECLARATION DE VARIABLE
        // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_fix, container, false);
        //DECLARATION DES VARIABLES
        Boolean ambulant = true;
        String mode_repair ="";

        ImageButton btnSaveFix = (ImageButton) rootView.findViewById(R.id.btn_fix_save);
        final TextView text_fix = rootView.findViewById(R.id.fragment_fix_save);
        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        // récupération des informations utilisateurs

        text_fix.setText(settings.getString("fix_name", "").toString());

        //Toast.makeText(getActivity().getApplicationContext(), "Magasin: " +settings.getString("fix_name", "").toString()

        //       , Toast.LENGTH_LONG).show();

        // test de en magasin ou a domicile / en entreprise
        mode_repair = settings.getString("what_mode_repair","").toString();
        //Toast.makeText(getActivity().getApplicationContext(), "mode repair / ambulant: " + mode_repair +" / "+ ambulant

        //        , Toast.LENGTH_SHORT).show();
        if (mode_repair.equals("En magasin")){
            ambulant = false;
        //    Toast.makeText(getActivity().getApplicationContext(), "ambulant is set: " +  ambulant

        //            , Toast.LENGTH_SHORT).show();
        }



        //@Override
        //protected void onCreate(Bundle savedInstanceState) {
            //super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_main);

            listView = (ExpandableListView)rootView.findViewById(R.id.lvExp);
            initData(ambulant);
            listAdapter = new ExpandableListAdapter(getActivity(),listDataHeader,listHash);
            listView.setAdapter(listAdapter);

        listView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                //Toast.makeText(
                //        getContext(),
                //        listDataHeader.get(groupPosition)
                //                + " : "
                //                + listHash.get(
                //                listDataHeader.get(groupPosition)).get(
                //                childPosition), Toast.LENGTH_SHORT)
                //        .show();
                text_fix.setText(listDataHeader.get(groupPosition)
                        + " : "
                        + listHash.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition));


                return false;
            }
        });

        btnSaveFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = settings.edit();
                editor.putString("fix_name",text_fix.getText().toString());
                editor.commit(); // indispensable pour valider les changement dans les shared pref ;-)
                Toast.makeText(getActivity().getApplicationContext(), "Magasin: " +text_fix.getText().toString()

                        , Toast.LENGTH_SHORT).show();

                Fragment_recap fragment_recap = new Fragment_recap();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_recap).commit();


            }
        });


        return rootView;
    }


    private void initData(boolean ambulant)  {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        // si ambulant = true alors on construit une liste de réparateur ambulant
        if (ambulant==true){

            listDataHeader.add("Grenoble");
            List<String> Grenoble = new ArrayList<>();
            Grenoble.add("Bicyclopresto");

            listDataHeader.add("Paris");
            List<String> Paris = new ArrayList<>();
            Paris.add("HelpMyBike");

            listDataHeader.add("Toulouse");
            List<String> Toulouse = new ArrayList<>();
            Toulouse.add("MecaniCycle");

            listHash.put(listDataHeader.get(0),Grenoble);
            listHash.put(listDataHeader.get(1),Paris);
            listHash.put(listDataHeader.get(2),Toulouse);

        } else {

            listDataHeader.add("Grenoble");
            List<String> Grenoble = new ArrayList<>();
            Grenoble.add("Bicyclopresto");
            Grenoble.add("BikeCorner");

            listDataHeader.add("Toulouse");
            List<String> Toulouse = new ArrayList<>();
            Toulouse.add("MecaniCycle");

            listHash.put(listDataHeader.get(0),Grenoble);
            listHash.put(listDataHeader.get(1),Toulouse);


        }











    }




}
