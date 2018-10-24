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
        String code_repair = "0";
        //String mode_repair ="";

        ImageButton btnSaveFix = (ImageButton) rootView.findViewById(R.id.btn_fix_save);
        final TextView text_fix = rootView.findViewById(R.id.fragment_fix_save);
        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        // récupération des informations utilisateurs

        text_fix.setText(settings.getString("fix_name", ""));

        //Toast.makeText(getActivity().getApplicationContext(), "Magasin: " +settings.getString("fix_name", "").toString()

        //       , Toast.LENGTH_LONG).show();

        // test du code_code repair -> fragment what choix radio button
        code_repair = settings.getString("what_code_repair","");
        //Toast.makeText(getActivity().getApplicationContext(), "mode repair / ambulant: " + mode_repair +" / "+ ambulant

        //        , Toast.LENGTH_SHORT).show();
        if (code_repair.equals("0")){
           // ambulant = false;
            Toast.makeText(getActivity().getApplicationContext(), "aucun mode de réparation n'a été défini: " +  code_repair
                    , Toast.LENGTH_SHORT).show();
        }



        //@Override
        //protected void onCreate(Bundle savedInstanceState) {
            //super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_main);

            listView = (ExpandableListView)rootView.findViewById(R.id.lvExp);
            switch (code_repair) {
                case "1":
                    initData1();
                    break;
                case "2":
                    initData2();
                    break;
                case "3":
                    initData3();
                    break;
            }
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
                editor.apply(); // indispensable pour valider les changement dans les shared pref ;-)
                Toast.makeText(getActivity().getApplicationContext(), "Magasin: " +text_fix.getText().toString()

                        , Toast.LENGTH_SHORT).show();

                Fragment_recap fragment_recap = new Fragment_recap();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_recap).commit();


            }
        });


        return rootView;
    }


    private void initData1() {
            // on construit une liste de réparateur selon le code_repair
            listDataHeader = new ArrayList<>();
            listHash = new HashMap<>();

            //liste en ambulant

            //Ardeche : PMPV Bikeshop
            listDataHeader.add("Ardeche");
            List<String> Ardeche = new ArrayList<>();
            Ardeche.add("PMPV Bikeshop");

            listDataHeader.add("Grenoble");
            List<String> Grenoble = new ArrayList<>();
            Grenoble.add("Bicyclopresto");

            listDataHeader.add("Lyon");
            List<String> Lyon = new ArrayList<>();
            Lyon.add("CycloDoc");

            listDataHeader.add("La Riviere");
            List<String> LaRiviere = new ArrayList<>();
            LaRiviere.add("Raccoon Workshop");

            listDataHeader.add("Paris");
            List<String> Paris = new ArrayList<>();
            Paris.add("HelpMyBike");

            listDataHeader.add("Toulouse");
            List<String> Toulouse = new ArrayList<>();
            Toulouse.add("MecaniCycle");

            listHash.put(listDataHeader.get(0), Ardeche);
            listHash.put(listDataHeader.get(1), Grenoble);
            listHash.put(listDataHeader.get(2), Lyon);
            listHash.put(listDataHeader.get(3), LaRiviere);
            listHash.put(listDataHeader.get(4), Paris);
            listHash.put(listDataHeader.get(5), Toulouse);
        }

    private void initData2() {
        // on construit une liste de réparateur selon le code_repair
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        // liste en magasin
        //Ardeche : PMPV Bikeshop
        listDataHeader.add("Ardeche");
        List<String> Ardeche = new ArrayList<>();
        Ardeche.add("PMPV Bikeshop");

        listDataHeader.add("Grenoble");
        List<String> Grenoble = new ArrayList<>();
        Grenoble.add("Bicyclopresto");
        Grenoble.add("BikeCorner");

        listDataHeader.add("La Riviere");
        List<String> LaRiviere = new ArrayList<>();
        LaRiviere.add("Raccoon Workshop");

        listDataHeader.add("Toulouse");
        List<String> Toulouse = new ArrayList<>();
        Toulouse.add("MecaniCycle");

        listHash.put(listDataHeader.get(0), Ardeche);
        listHash.put(listDataHeader.get(1), Grenoble);
        listHash.put(listDataHeader.get(2), LaRiviere);
        listHash.put(listDataHeader.get(3), Toulouse);
    }
    private void initData3() {
        // on construit une liste de réparateur selon le code_repair
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        // reseau magasin flycat

        listDataHeader.add("MOUGINS_06250");
        List<String> MOUGINS_06250 = new ArrayList<>();
        MOUGINS_06250.add("MECAVELO");
        listDataHeader.add("GIGNAC_LA_NERTHE_13180");
        List<String> GIGNAC_LA_NERTHE_13180 = new ArrayList<>();
        GIGNAC_LA_NERTHE_13180.add("BATTI CYCLES");
        listDataHeader.add("DIJON_21000");
        List<String> DIJON_21000 = new ArrayList<>();
        DIJON_21000.add("SUN CITY");
        listDataHeader.add("YFFINIAC_22120");
        List<String> YFFINIAC_22120 = new ArrayList<>();
        YFFINIAC_22120.add("BRITWAYS-CAR YFFINIAC");
        listDataHeader.add("LANNION_22300");
        List<String> LANNION_22300 = new ArrayList<>();
        LANNION_22300.add("BRITWAYS-CAR LANNION");
        listDataHeader.add("PERIGUEUX_24000");
        List<String> PERIGUEUX_24000 = new ArrayList<>();
        PERIGUEUX_24000.add("REBOOT CYCLES");
        listDataHeader.add("ANGERVILLE_LA_CAMPAGNE_27930");
        List<String> ANGERVILLE_LA_CAMPAGNE_27930 = new ArrayList<>();
        ANGERVILLE_LA_CAMPAGNE_27930.add("REPAR VDL Normandie");
        listDataHeader.add("ST_POL_DE_LEON_29250");
        List<String> ST_POL_DE_LEON_29250 = new ArrayList<>();
        ST_POL_DE_LEON_29250.add("MARCO VELO");
        listDataHeader.add("TOULOUSE_31300");
        List<String> TOULOUSE_31300 = new ArrayList<>();
        TOULOUSE_31300.add("L'ECHAPPEE BELLE");
        listDataHeader.add("BORDEAUX_33000");
        List<String> BORDEAUX_33000 = new ArrayList<>();
        BORDEAUX_33000.add("Bicyclopresto");
        listDataHeader.add("MONTPELLIER_34000");
        List<String> MONTPELLIER_34000 = new ArrayList<>();
        MONTPELLIER_34000.add("W Ville et Velo");
        listDataHeader.add("MEZE_34140");
        List<String> MEZE_34140 = new ArrayList<>();
        MEZE_34140.add("JPA Sport Moto");
        listDataHeader.add("SETE_34200");
        List<String> SETE_34200 = new ArrayList<>();
        SETE_34200.add("FLYING CAT");
        listDataHeader.add("GRENOBLE_38000");
        List<String> GRENOBLE_38000 = new ArrayList<>();
        GRENOBLE_38000.add("Bicyclopresto");
        listDataHeader.add("SAINT_NIZIER_DU_MOUCHEROTTE_38250");
        List<String> SAINT_NIZIER_DU_MOUCHEROTTE_38250 = new ArrayList<>();
        SAINT_NIZIER_DU_MOUCHEROTTE_38250.add("GREEN E-BIKE COUNTRY");
        listDataHeader.add("LILLE_59000");
        List<String> LILLE_59000 = new ArrayList<>();
        LILLE_59000.add("Bicyclopresto");
        listDataHeader.add("LEMPDES_63370");
        List<String> LEMPDES_63370 = new ArrayList<>();
        LEMPDES_63370.add("ELECTRIC AUTO");
        listDataHeader.add("SERRES_CASTET_64121");
        List<String> SERRES_CASTET_64121 = new ArrayList<>();
        SERRES_CASTET_64121.add("ASSOCIATION MIKRO EKO");
        listDataHeader.add("SUNDHOUSE_67920");
        List<String> SUNDHOUSE_67920 = new ArrayList<>();
        SUNDHOUSE_67920.add("SAWIKO");
        listDataHeader.add("STRASBOURG_68000");
        List<String> STRASBOURG_68000 = new ArrayList<>();
        STRASBOURG_68000.add("Bicyclopresto");
        listDataHeader.add("LYON_69000");
        List<String> LYON_69000 = new ArrayList<>();
        LYON_69000.add("Bicyclopresto");
        listDataHeader.add("SAINT_LEGER_SUR_D_HEUNE_71510");
        List<String> SAINT_LEGER_SUR_D_HEUNE_71510 = new ArrayList<>();
        SAINT_LEGER_SUR_D_HEUNE_71510.add("NORD SUD CARAVANING");
        listDataHeader.add("MARIN_74200");
        List<String> MARIN_74200 = new ArrayList<>();
        MARIN_74200.add("GREEN 2 GO");
        listDataHeader.add("PERS_JUSSY_74930");
        List<String> PERS_JUSSY_74930 = new ArrayList<>();
        PERS_JUSSY_74930.add("CYCLES JACQUEMOUD");
        listDataHeader.add("PARIS_75000");
        List<String> PARIS_75000 = new ArrayList<>();
        PARIS_75000.add("Bicyclopresto");
        listDataHeader.add("DAMMARTIN_EN_GOELE_77230");
        List<String> DAMMARTIN_EN_GOELE_77230 = new ArrayList<>();
        DAMMARTIN_EN_GOELE_77230.add("ALEXIS DUBOURG");
        listDataHeader.add("AVIGNON_84000");
        List<String> AVIGNON_84000 = new ArrayList<>();
        AVIGNON_84000.add("AYME ROBERT et FILS");
        listDataHeader.add("BEDARRIDES_84370");
        List<String> BEDARRIDES_84370 = new ArrayList<>();
        BEDARRIDES_84370.add("POWER ENERGIE BATTERIES");
        listDataHeader.add("FONTAINE_LE_COMTE_86240");
        List<String> FONTAINE_LE_COMTE_86240 = new ArrayList<>();
        FONTAINE_LE_COMTE_86240.add("VIENNE PASSION");
        listDataHeader.add("ASNIERES_92600");
        List<String> ASNIERES_92600 = new ArrayList<>();
        ASNIERES_92600.add("US WAY");


        listDataHeader.add("Luxembourg_STEINFORT");
        List<String> Luxembourg_STEINFORT = new ArrayList<>();
        Luxembourg_STEINFORT.add("SDS SA");

        listHash.put(listDataHeader.get(0), MOUGINS_06250);
        listHash.put(listDataHeader.get(1), GIGNAC_LA_NERTHE_13180);
        listHash.put(listDataHeader.get(2), DIJON_21000);
        listHash.put(listDataHeader.get(3), YFFINIAC_22120);
        listHash.put(listDataHeader.get(4), LANNION_22300);
        listHash.put(listDataHeader.get(5), PERIGUEUX_24000);
        listHash.put(listDataHeader.get(6), ANGERVILLE_LA_CAMPAGNE_27930);
        listHash.put(listDataHeader.get(7), ST_POL_DE_LEON_29250);
        listHash.put(listDataHeader.get(8), TOULOUSE_31300);
        listHash.put(listDataHeader.get(9), BORDEAUX_33000);
        listHash.put(listDataHeader.get(10), MONTPELLIER_34000);
        listHash.put(listDataHeader.get(11), MEZE_34140);
        listHash.put(listDataHeader.get(12), SETE_34200);
        listHash.put(listDataHeader.get(13), GRENOBLE_38000);
        listHash.put(listDataHeader.get(14), SAINT_NIZIER_DU_MOUCHEROTTE_38250);
        listHash.put(listDataHeader.get(15), LILLE_59000);
        listHash.put(listDataHeader.get(16), LEMPDES_63370);
        listHash.put(listDataHeader.get(17), SERRES_CASTET_64121);
        listHash.put(listDataHeader.get(18), SUNDHOUSE_67920);
        listHash.put(listDataHeader.get(19), STRASBOURG_68000);
        listHash.put(listDataHeader.get(20), LYON_69000);
        listHash.put(listDataHeader.get(21), SAINT_LEGER_SUR_D_HEUNE_71510);
        listHash.put(listDataHeader.get(22), MARIN_74200);
        listHash.put(listDataHeader.get(23), PERS_JUSSY_74930);
        listHash.put(listDataHeader.get(24), PARIS_75000);
        listHash.put(listDataHeader.get(25), DAMMARTIN_EN_GOELE_77230);
        listHash.put(listDataHeader.get(26), AVIGNON_84000);
        listHash.put(listDataHeader.get(27), BEDARRIDES_84370);
        listHash.put(listDataHeader.get(28), FONTAINE_LE_COMTE_86240);
        listHash.put(listDataHeader.get(29), ASNIERES_92600);


        listHash.put(listDataHeader.get(30), Luxembourg_STEINFORT);


    }
















}





