package com.example.nutricare.Dieta2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutricare.Dieta.Alimento;
import com.example.nutricare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Dieta2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Dieta2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dieta2Fragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private LinearLayout linearLayout_checkboxes;
    private final ArrayList<CheckBox> allCb = new ArrayList<>();
    private Button bEnviarDatos;
    private Button bIngresarAlimento;
    private TextView tvFecha;

    private int nService = 0;
    private List<Integer> listOfIds = new ArrayList<>();

    RecyclerView recyclerView;
    ArrayList<Alimento> alimentos;

    ProgressDialog progressDialog;

    RequestQueue request;


    int nAlimentos = 0;

    public Dieta2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dieta2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Dieta2Fragment newInstance(String param1, String param2) {
        Dieta2Fragment fragment = new Dieta2Fragment();
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
        View vista =  inflater.inflate(R.layout.fragment_dieta2, container, false);

        alimentos = new ArrayList<>();
        linearLayout_checkboxes = (LinearLayout) vista.findViewById(R.id.linearLayout_checkboxes);
        bEnviarDatos = vista.findViewById(R.id.bSaveData);
        bIngresarAlimento = vista.findViewById(R.id.bIngresarAlimento);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        final String fecha = dateFormat.format(date);

        tvFecha = vista.findViewById(R.id.tvFecha);
        tvFecha.setText(fecha);

        bEnviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                for(int i = 0; i < allCb.size(); i++)
                {
                    if(allCb.get(i).isChecked())
                    {
                       cargarWebService2(fecha, 2, allCb.get(i).getId());
                    }
                }
            }
        });

        bIngresarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    agregarAlimento();
            }
        });


        request = Volley.newRequestQueue(getContext());

        cargarWebService();



        return vista;
    }

    private void cargarWebService()
    {
        nService = 1;

        String url = "https://nutricareapp.000webhostapp.com/consultarAlimentos.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }



    private void cargarWebService2(String fecha, int idUsuario, int idAlimento)
    {
        nService = 2;

        /*for(int i = 0; i < list.size(); i++)
        {
            String url = "https://nutricareapp.000webhostapp.com/insertarDieta.php?fecha=" + fecha + "&idUsuario=" + idUsuario + "&idAlimento=" +  list.get(i) ;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            request.add(jsonObjectRequest);
        }*/


        String url = "https://nutricareapp.000webhostapp.com/insertarDieta.php?fecha=" + fecha + "&idUsuario=" + idUsuario + "&idAlimento=" +  idAlimento ;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
        //on response yeeeei
        Toast.makeText(getContext(), "Se ha registrado la dieta correctamente", Toast.LENGTH_SHORT).show();


    }

    private void cargarWebService3(String nombre, Integer tipo, String info, int calorias, int carbohidratos,
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

    }

    @Override
    public void onResponse(JSONObject response) {


        if(nService==1)
        {
            Alimento alimento = null;

            JSONArray json = response.optJSONArray("Alimento");

            try{
                for(int i = 0; i < json.length(); i++)
                {
                    alimento = new Alimento();
                    JSONObject jsonObject = null;
                    jsonObject = json.getJSONObject(i);

                    alimento.setIdAlimento(jsonObject.optInt("idAlimento"));
                    alimento.setNombre(jsonObject.optString("nombre"));
                    alimento.setInfo(jsonObject.optString("info"));
                    alimento.setTipo(jsonObject.optInt("tipo"));
                    alimento.setCarbohidratos(jsonObject.optInt("carbohidratos"));
                    alimento.setCalorias(jsonObject.optInt("calorias"));
                    alimento.setGrasas(jsonObject.optInt("grasas"));
                    alimento.setProteinas(jsonObject.optInt("proteinas"));

                    CheckBox cb = new CheckBox(getActivity());
                    cb.setText(alimento.getNombre());
                    cb.setId(alimento.getIdAlimento());
                    linearLayout_checkboxes.addView(cb);
                    allCb.add(cb);

                    alimentos.add(alimento);
                    listOfIds.add(alimento.getIdAlimento());

                }

                // nAlumnos = alumnos.size();

            /*AlumnoAdapter adapter = new AlumnoAdapter(alumnos);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(adapter);*/

            }catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(getContext(), "No se ha podido conectar con el servidor" +
                        response, Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }

        }
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
                    cargarWebService3(aNombre.getText().toString(),1 ,aInfo.getText().toString(),
                            Integer.parseInt(cal), Integer.parseInt(carb), Integer.parseInt(grasas),
                            Integer.parseInt(prot));

                else if(rbVerdura.isChecked())
                    cargarWebService3(aNombre.getText().toString(),2 ,aInfo.getText().toString(),
                            Integer.parseInt(cal), Integer.parseInt(carb), Integer.parseInt(grasas),
                            Integer.parseInt(prot));

                dialog.dismiss();
            }
        });
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
