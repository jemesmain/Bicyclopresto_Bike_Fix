package fr.bicyclopresto.bicyclopresto_bike_fix;


import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_when_alt extends Fragment {


    public Fragment_when_alt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_when, container, false);

        // rootview permet d'accéder aux différents éléments du fragment
        View rootView = inflater.inflate(R.layout.fragment_when_alt, container, false);

        // return inflater.inflate(R.layout.fragment_profil, container, false);

        //DECLARATION DES VARIABLES

        ImageButton btnSaveWhenAlt = (ImageButton) rootView.findViewById(R.id.btn_when_alt_save);
        //final DatePicker edit_when_date = rootView.findViewById(R.id.edit_when_DatePicker);
        //final TimePicker edit_when_time = rootView.findViewById(R.id.edit_when_TimePicker);
        final TextView tvWhenDate = rootView.findViewById(R.id.edit_when_alt_date);
        final TextView tvWhenTime = rootView.findViewById(R.id.edit_when_alt_time);
        final SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        //affichage au format 24h du time picker
        //edit_when_time.setIs24HourView(true);

        // récupération des informations utilisateurs

        tvWhenDate.setText(settings.getString("when_date", "").toString());
        tvWhenTime.setText(settings.getString("when_time", "").toString());

        //Toast.makeText(getActivity().getApplicationContext(), "RDV souhaité le: " +settings.getString("when_date", "").toString()
        //        + " " +settings.getString("when_time", "").toString()
        //        , Toast.LENGTH_LONG).show();

        btnSaveWhenAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", 0);
                SharedPreferences settings = getActivity().getSharedPreferences("Bicyclopresto_bike_fix_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();

                //recupération de la date
                //int   day  = edit_when_date.getDayOfMonth();
                //int   month= edit_when_date.getMonth();
                //int   year = edit_when_date.getYear();
                //Calendar calendar = Calendar.getInstance();
                //calendar.set(year, month, day);

                //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                //String formatedDate = sdf.format(calendar.getTime());

                //recupération de la date et l'heure
                String date = tvWhenDate.getText().toString();
                String time = tvWhenTime.getText().toString();
                //String time = hour +":"+ minute;

                //sauvegarde dans les préférences
                editor.putString("when_date",date);
                editor.putString("when_time",time);
                editor.apply(); // indispensable pour valider les changement dans les shared pref ;-)
                Toast.makeText(getActivity().getApplicationContext(), "RDV souhaité le: " + date
                                +" " + time

                        , Toast.LENGTH_SHORT).show();

                Fragment_where fragment_where = new Fragment_where();
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment, fragment_where).commit();


            }
        });









        return rootView;
    }

}











/**
package fr.bicyclopresto.bicyclopresto_bike_fix;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_when_alt.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_when_alt#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
public class Fragment_when_alt extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_when_alt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_when_alt.
     */
/**
    // TODO: Rename and change types and number of parameters
    public static Fragment_when_alt newInstance(String param1, String param2) {
        Fragment_when_alt fragment = new Fragment_when_alt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_when_alt, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/**
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
 */