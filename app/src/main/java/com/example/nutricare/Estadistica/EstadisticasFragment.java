package com.example.nutricare.Estadistica;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutricare.Dieta.Alimento;
import com.example.nutricare.Dieta.AlimentoAdapter;
import com.example.nutricare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EstadisticasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EstadisticasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstadisticasFragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    ArrayList<Alimento> listaAlimentos;

    private TextView tvGrasas, tvProteinas, tvCalorias, tvCarbohidratos;

    ProgressDialog progressDialog;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    int sGrasas=0;
    int sProteinas=0;
    int sCarbohidratos=0;
    int sCalorias=0;

    private int nService = 0;

    public EstadisticasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstadisticasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstadisticasFragment newInstance(String param1, String param2) {
        EstadisticasFragment fragment = new EstadisticasFragment();
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
        View vista = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        listaAlimentos = new ArrayList<>();

        tvCarbohidratos = vista.findViewById(R.id.tvCarbohidratos);
        /*tvGrasas = vista.findViewById(R.id.tvGrasas);
        tvCarbohidratos = vista.findViewById(R.id.tvCarbohidratos);
        tvProteinas = vista.findViewById(R.id.tvProteinas);*/

        tvCarbohidratos.setText("Usted ha consumido: " + listaAlimentos.size() + " alimentos");
        /*tvGrasas.setText(sGrasas);
        tvCarbohidratos.setText(sCarbohidratos);
        tvProteinas.setText(sProteinas);*/

        request = Volley.newRequestQueue(getContext());

        cargarWebService();

        return  vista;
    }


    private void cargarWebService()
    {
        nService = 1;

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado...");
        progressDialog.show();

        String url = "https://nutricareapp.000webhostapp.com/consultarDietaHoy.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
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

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {


        Alimento alimento = null;

        JSONArray json = response.optJSONArray("Alimento");

        try{
            for(int i = 0; i < json.length(); i++)
            {
                alimento = new Alimento();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                alimento.setIdAlimento(jsonObject.optInt("idAlimento"));

                /*sCalorias += alimento.getCalorias();
                sCarbohidratos += alimento.getCarbohidratos();
                sProteinas += alimento.getProteinas();
                sGrasas += alimento.getGrasas();
*/
                listaAlimentos.add(alimento);

            }
            progressDialog.hide();
            tvCarbohidratos.setText("Usted ha consumido: " + listaAlimentos.size() + " alimentos");


            /*AlimentoAdapter adapter = new AlimentoAdapter(listaAlimentos);

            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

            recyclerView.setAdapter(adapter);*/

        }catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido conectar con el servidor", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
        }
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
