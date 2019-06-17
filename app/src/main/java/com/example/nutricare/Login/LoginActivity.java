package com.example.nutricare.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    int idUsuario = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasenia = findViewById(R.id.etContrasenia);
        bLogin = findViewById(R.id.bLogin);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void cargarWebService(View v)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        String url = "https://nutricareapp.000webhostapp.com/loginUsuario.php?username=" + etUsuario.getText().toString() +
                "&pass=" + etContrasenia.getText().toString();

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
        progressDialog.hide();
        //Toast.makeText(getContext(),"Mensaje: " + response, Toast.LENGTH_SHORT).show();

        Usuario usuario = new Usuario();

        JSONArray json = response.optJSONArray("Usuario");
        JSONObject jsonObject=null;

        try {
            jsonObject=json.getJSONObject(0);
            usuario.setIdUsuario(jsonObject.optInt("idUsuario"));
            usuario.setNombre(jsonObject.optString("nombre"));
            usuario.setApellido(jsonObject.optString("apellido"));
            usuario.setEmail(jsonObject.optString("email"));
            usuario.setUsername(jsonObject.optString("username"));
            usuario.setPassword(jsonObject.optString("pass"));
            usuario.setTipo(jsonObject.optInt("tipo"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(!(usuario.getUsername().isEmpty()) && !(usuario.getPassword().isEmpty()))
        {
            Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show();
            goToActivity(usuario.getIdUsuario(), usuario.getTipo());
        }
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

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
