package com.salvador.devworms.hurryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView name;
   public TextView saldo;
    ProfilePictureView fotoper;
   public String inifbnombre;
   public String inifbfoto;
    String pantall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        saldo=(TextView)findViewById(R.id.saldo);
        name=(TextView)findViewById(R.id.nombreperfil);
        fotoper=(ProfilePictureView)findViewById(R.id.profilePicture);
       // Bundle args = getIntent().getExtras();

       // name= args.getString("nombre");
       // fotoper= args.getString("foto");

        ///***************Barra***************************************************
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        ///***************Barra***************************************************

        ///***************Menu***************************************************
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ///***************Menu***************************************************

        ///***************Fragment***************************************************
        getFragmentManager().beginTransaction()
                .replace(R.id.actividad, new Tiendas()).commit();
        getFragmentManager().beginTransaction().addToBackStack(null);
        ///***************Fragment***************************************************

        try{

            Intent i=getIntent();


                inifbnombre = i.getExtras().getString("nombrefb");
                inifbfoto = i.getExtras().getString("foto");

                if (inifbnombre != null || inifbnombre != "")
                    name.setText(inifbnombre);
                if (inifbfoto != null || inifbfoto != "")
                    fotoper.setProfileId(inifbfoto);

        }catch (Exception e){
           // Toast.makeText(this, conta, Toast.LENGTH_SHORT).show();

             finish();

        }
    }

    @Override
    protected void onStart(){
        super.onStart();

    }
    public void onResume(){
        super.onResume();

    }

    @Override
    protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        if (inifbnombre != null || inifbnombre != "")
        guardarEstado.putString("nomfb", inifbnombre);
        if (inifbfoto != null || inifbfoto != "")
        guardarEstado.putString("fotofb", inifbfoto);
    }

    @Override
    protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        if (inifbnombre == null || inifbnombre == "")
            inifbnombre = recEstado.getString("variable");
        if (inifbfoto == null || inifbfoto == "")
            inifbfoto = recEstado.getString("posicion");
    }
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {
            /*if (getFragmentManager().getBackStackEntryCount() > 0) {
                FragmentManager.BackStackEntry first = getFragmentManager().getBackStackEntryAt(0);
                getFragmentManager().popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }*/
            getFragmentManager().popBackStack();
        }

/*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);

       // MenuItem item = menu.getItem(0);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recarga) {
            item.setTitle("Recarga");
             getFragmentManager().beginTransaction()
                    .replace(R.id.actividad, new Recarga()).commit();
        } else if (id == R.id.nav_imprime) {
            item.setTitle("Imprimir");
            getFragmentManager().beginTransaction()
                    .replace(R.id.actividad, new Tiendas()).commit();

        } else if (id == R.id.nav_cuenta) {
            item.setTitle("Perfil");
            getFragmentManager().beginTransaction()
                    .replace(R.id.actividad, new Cuenta()).commit();

        }  else if (id == R.id.nav_creditos) {
            //getFragmentManager().beginTransaction()
            //        .replace(R.id.actividad, new CreditosFragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
