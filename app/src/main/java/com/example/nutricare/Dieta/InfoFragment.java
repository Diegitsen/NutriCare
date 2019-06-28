package com.example.nutricare.Dieta;

import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutricare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DietaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DietaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FloatingActionButton floatingActionButton;

    private OnFragmentInteractionListener mListener;


    RecyclerView recyclerView;
    ArrayList<Alimento> listaAlimentos;

    ProgressDialog progressDialog;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    FloatingActionButton bAgregar;

    private int nService = 0;


    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DietaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DietaFragment newInstance(String param1, String param2) {
        DietaFragment fragment = new DietaFragment();
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

        View v = inflater.inflate(R.layout.fragment_dieta, container, false);

        Bundle bundle = getArguments();
        final int id_usuario = bundle.getInt("ID_USUARIO");

        listaAlimentos = new ArrayList<>();

        recyclerView = v.findViewById(R.id.idRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);
        bAgregar = v.findViewById(R.id.add_fab);


        request = Volley.newRequestQueue(getContext());

        cargarWebService(id_usuario);

        bAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarAlimento();
            }
        });

        return v;
    }


    private void agregarAlimento()
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View mView = getLayoutInflater().inflate(R.layout.dialog_agregar_alimento, null);
        final EditText aNombre = (EditText) mView.findViewById(R.id.etNombre);
        final EditText aInfo = (EditText) mView.findViewById(R.id.etInfo);
        final EditText aCarbohidratos = (EditText) mView.findViewById(R.id.etCarbohidratos );
        final EditText aCalorias = (EditText) mView.findViewById(R.id.etCalorias);
        final EditText aGrasas = (EditText) mView.findViewById(R.id.etGrasas);
        final EditText aProteinas = (EditText) mView.findViewById(R.id.etProteinas);
        final RadioButton rbFruta = mView.findViewById(R.id.rbFruta);
        final RadioButton rbVerdura = mView.findViewById(R.id.rbVerdura);
        Button aAgregar = (Button) mView.findViewById(R.id.bAgregar);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        aAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cal = aCalorias.getText().toString();
                String carb = aCarbohidratos.getText().toString();
                String grasas = aGrasas.getText().toString();
                String prot = aProteinas.getText().toString();
                if(rbFruta.isChecked())
                    cargarWebService2(aNombre.getText().toString(),1 ,aInfo.getText().toString(),
                            Integer.parseInt(cal), Integer.parseInt(carb), Integer.parseInt(grasas),
                            Integer.parseInt(prot));

                else if(rbVerdura.isChecked())
                    cargarWebService2(aNombre.getText().toString(),2 ,aInfo.getText().toString(),
                            Integer.parseInt(cal), Integer.parseInt(carb), Integer.parseInt(grasas),
                            Integer.parseInt(prot));

                dialog.dismiss();
            }
        });
    }

    private void cargarWebService(int idPaciente)
    {
        nService = 1;

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado...");
        progressDialog.show();

        String url = "https://nutricareapp.000webhostapp.com/consultarAlimentosPorIdPaciente.php?idPaciente=" + idPaciente;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    private void cargarWebService2(String nombre, Integer tipo, String info, int calorias, int carbohidratos,
                                   int grasas, int proteinas)
    {
        nService = 2;
        String url = "https://nutricareapp.000webhostapp.com/agregarAlimento.php?nombre=" + nombre
                + "&tipo=" + tipo + "&info=" + info + "&calorias=" + calorias + "&carbohidratos=" + carbohidratos
                + "&grasas=" + grasas + "&proteinas=" + proteinas;

        url = url.replace(" ", "%20");

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

        if(nService==1)
        {
            progressDialog.hide();
            Toast.makeText(getContext(), "No se pudo conectar" , Toast.LENGTH_SHORT).show();
        }
        else if(nService == 2)
        {
            progressDialog.hide();
            Toast.makeText(getContext(), "No se pudo agregar el alimento" , Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResponse(JSONObject response)
    {
        if(nService==1)
        {
            Alimento alimento = null;

            JSONArray json = response.optJSONArray("alimento");

            try{
                for(int i = 0; i < json.length(); i++)
                {
                    alimento = new Alimento();
                    JSONObject jsonObject = null;
                    jsonObject = json.getJSONObject(i);

                    alimento.setNombre(jsonObject.optString("nombre"));
                    alimento.setInfo(jsonObject.optString("info"));
                    alimento.setTipo(jsonObject.optInt("tipo"));
                    alimento.setCarbohidratos(jsonObject.optInt("carbohidratos"));
                    alimento.setCalorias(jsonObject.optInt("calorias"));
                    alimento.setGrasas(jsonObject.optInt("grasas"));
                    alimento.setProteinas(jsonObject.optInt("proteinas"));

                    listaAlimentos.add(alimento);

                }
                progressDialog.hide();

                AlimentoAdapter adapter = new AlimentoAdapter(listaAlimentos);

                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

                recyclerView.setAdapter(adapter);

            }catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(getContext(), "No se ha podido conectar con el servidor", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }
        else if(nService==2)
        {
            progressDialog.hide();
            Toast.makeText(getActivity(), "Se ha agregado exitosamente", Toast.LENGTH_SHORT).show();
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
