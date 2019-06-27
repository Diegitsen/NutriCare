package com.example.nutricare.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.example.nutricare.MainActivity;
import com.example.nutricare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    private EditText etUsuario, etContrasenia;
    Button bLogin;
    RadioButton rbPaciente, rbDoctor;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    int idUsuario = 0;
    int nservice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasenia = findViewById(R.id.etContrasenia);
        bLogin = findViewById(R.id.bLogin);
        rbDoctor = findViewById(R.id.rbDoctor);
        rbPaciente = findViewById(R.id.rbPaciente);


        rbPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbDoctor.setChecked(false);
            }
        });

        rbDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbPaciente.setChecked(false);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbPaciente.isChecked())
                {
                    cargarWebService();
                }
                else if(rbDoctor.isChecked())
                {
                    cargarWebService2();
                }
            }
        });

        requestQueue = Volley.newRequestQueue(this);
    }

    public void cargarWebService()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        nservice = 1;

        String url = "https://nutricareapp.000webhostapp.com/loginPaciente.php?usuario=" + etUsuario.getText().toString() +
                "&password=" + etContrasenia.getText().toString();

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    public void cargarWebService2()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        nservice = 2;

        String url = "https://nutricareapp.000webhostapp.com/loginDoctor.php?usuario=" + etUsuario.getText().toString() +
                "&password=" + etContrasenia.getText().toString();

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    public void goToMain(View v)
    {
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response)
    {
        if(nservice==1)
        {
            progressDialog.hide();
            //Toast.makeText(getContext(),"Mensaje: " + response, Toast.LENGTH_SHORT).show();

            Paciente paciente = new Paciente();

            JSONArray json = response.optJSONArray("paciente");
            JSONObject jsonObject=null;

            try {
                jsonObject=json.getJSONObject(0);
                paciente.setIdPaciente(jsonObject.optInt("idPaciente"));
                paciente.setNombre(jsonObject.optString("nombre"));
                paciente.setApellido(jsonObject.optString("apellido"));
                paciente.setEmail(jsonObject.optString("email"));
                paciente.setUsuario(jsonObject.optString("usuario"));
                paciente.setPassword(jsonObject.optString("password"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(!(paciente.getUsuario().isEmpty()) && !(paciente.getPassword().isEmpty()))
            {
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show();
                goToActivity(paciente.getIdPaciente(), 2);
            }
            else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        else if(nservice==2)
        {
            progressDialog.hide();

            Doctor doctor = new Doctor();

            JSONArray json = response.optJSONArray("doctor");
            JSONObject jsonObject=null;

            try {
                jsonObject=json.getJSONObject(0);
                doctor.setidDoctor(jsonObject.optInt("idDoctor"));
                doctor.setNombre(jsonObject.optString("nombre"));
                doctor.setApellido(jsonObject.optString("apellido"));
                doctor.setEmail(jsonObject.optString("email"));
                doctor.setUsuario(jsonObject.optString("usuario"));
                doctor.setPassword(jsonObject.optString("password"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(!(doctor.getUsuario().isEmpty()) && !(doctor.getPassword().isEmpty()))
            {
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show();
                goToActivity(doctor.getidDoctor(), 1);
            }
            else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }


    }


    public void goToActivity(int id, int tipo)
    {
        Intent i = new Intent(this, MainActivity.class);

        Bundle b = new Bundle();
        b.putInt("ID_USUARIO", id);
        i.putExtras(b);

        Bundle b2 = new Bundle();
        b2.putInt("ID_TIPO", tipo);
        i.putExtras(b2);

        startActivity(i);
    }
}
