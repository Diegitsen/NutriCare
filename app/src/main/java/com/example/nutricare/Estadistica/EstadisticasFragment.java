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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


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

    private TextView tvGrasas, tvProteinas, tvCalorias, tvCarbohidratos, tvText;

    ProgressDialog progressDialog;

    Button bDiario, bMensual;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

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

        Bundle bundle = getArguments();
        final int id_usuario = bundle.getInt("ID_USUARIO");


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        final String fecha = dateFormat.format(date);

        listaAlimentos = new ArrayList<>();

        tvCalorias = vista.findViewById(R.id.tvCalorias);
        tvGrasas = vista.findViewById(R.id.tvGrasas);
        tvCarbohidratos = vista.findViewById(R.id.tvCarbohidratos);
        tvProteinas = vista.findViewById(R.id.tvProteinas);
        tvText = vista.findViewById(R.id.tvText);

        bDiario = vista.findViewById(R.id.bDiario);
        bMensual = vista.findViewById(R.id.bMensual);

        bDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvText.setText("Las estadisticas de los alimentos que comiste hoy son:");
                cargarWebService(fecha, id_usuario);
            }
        });

        bMensual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvText.setText("Las estadisticas de los alimentos que comiste en este mes son:");
                cargarWebService2(fecha, id_usuario);

            }
        });

        request = Volley.newRequestQueue(getContext());

        cargarWebService(fecha, id_usuario);



        return  vista;
    }


    private void cargarWebService(String fecha, int idPaciente)
    {
        nService = 1;

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado...");
        progressDialog.show();

        Log.e("id", idPaciente+"");

        String url = "https://nutricareapp.000webhostapp.com/consultarEstadisticaDiaria.php?fecha=" + fecha + "&idPaciente=" + idPaciente;

        Log.e("url", url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    private void cargarWebService2(String fecha, int idPaciente)
    {
        nService = 2;

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado...");
        progressDialog.show();

        Log.e("id", idPaciente+"");

        String url = "https://nutricareapp.000webhostapp.com/consultarEstadisticaMensual.php?fecha=" + fecha + "&idPaciente=" + idPaciente;

        Log.e("url", url);

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

        Estadistica estadistica = null;

        JSONArray json = response.optJSONArray("dieta");

        try{
              estadistica = new Estadistica();
              JSONObject jsonObject = null;
              jsonObject = json.getJSONObject(0);

              estadistica.setCalorias(jsonObject.optInt("calorias"));
              estadistica.setCarbohidratos(jsonObject.optInt("carbohidratos"));
              estadistica.setProteinas(jsonObject.optInt("proteinas"));
              estadistica.setGrasas(jsonObject.optInt("grasas"));

              int suma = estadistica.getCarbohidratos() + estadistica.getCalorias() + estadistica.getProteinas()
                      + estadistica.getGrasas();

              Log.e("calorias", estadistica.getCalorias()+"");

              tvCarbohidratos.setText("Carbohidratos: " + estadistica.getCarbohidratos() + " - " + ((float)estadistica.getCarbohidratos()/(float)suma)*100 + "%");
              tvCalorias.setText("Calorias: " + estadistica.getCalorias() + " - " + ((float)estadistica.getCalorias()/(float)suma)*100 + "%");
              tvProteinas.setText("Proteinas: " + estadistica.getProteinas() + " - " + ((float)estadistica.getProteinas()/(float)suma)*100 + "%");
              tvGrasas.setText("Grasas: " + estadistica.getGrasas() + " - " + ((float)estadistica.getGrasas()/(float)suma)*100 + "%");

            progressDialog.hide();


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
