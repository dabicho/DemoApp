package com.code3e.demoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.code3e.demoapp.adapters.MenuListViewAdapter;
import com.code3e.demoapp.models.MenuItem;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ListView menuListView;
    private MenuListViewAdapter menuListViewAdapter;
    private static final String TAG="MenuActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_menu);
        menuListView = (ListView) findViewById(R.id.menuListView);

        MenuItem menuItem0 = new MenuItem("Restaurantes", getResources().getColor(R.color.colorClearBlue));
        MenuItem menuItem1 = new MenuItem("Sucursales", getResources().getColor(R.color.colorClearGreen));
        MenuItem menuItem2 = new MenuItem("Personal", getResources().getColor(R.color.colorDarkGreen));
        MenuItem menuItem3 = new MenuItem("Contacto", getResources().getColor(R.color.colorOrange));
        MenuItem menuItem4 = new MenuItem("Acerca de", getResources().getColor(R.color.colorYellow));

        List<MenuItem> menu = new ArrayList<>();
        menu.add(menuItem0);
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);

        menuListViewAdapter = new MenuListViewAdapter(MenuActivity.this, R.layout.menu_item_layout, menu);
        menuListView.setAdapter(menuListViewAdapter);
        setUpListeners();

        Button salirButton = (Button)findViewById(R.id.salirButton);
        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                finish();
                //TODO Iniciar intent de login.
            }
        });
    }

    private void setUpListeners(){
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: Se ha seleccionado la opcion"+position);
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(MenuActivity.this, RestaurantsActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MenuActivity.this, MapsActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        break;
                    case 3:

                        intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Contacto desde app");
                        startActivity(Intent.createChooser(intent, "Seleccione una opción"));

                        /*
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:23344123"));
                        intent.putExtra("sms_body", "Hola!!!");
                        startActivity(Intent.createChooser(intent, "Seleccione una opción"));
                        */
                        
                        // Validacion de permiso con cierta version de android
                        /*
                        int currentVersion = Build.VERSION.SDK_INT;
                        if( currentVersion >= Build.VERSION_CODES.M) {
                            if(ContextCompat.checkSelfPermission(MenuActivity.this,
                                    Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                                Log.d(TAG, "onItemClick: Solicitando permiso");
                                ActivityCompat.requestPermissions(MenuActivity.this,
                                        new String[]{Manifest.permission.SEND_SMS}, 1);
                            } else {
                                Log.d(TAG, "onItemClick: Permission already granted");
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage("34222343",null,"HOLAS",null, null);
                            }
                        } else {
                            Log.d(TAG, "onItemClick: No es posible solicitar permisos: "+currentVersion);
                        }
                        Log.d(TAG, "onItemClick: ENVIANDO MENSAJE");

                        */
                        break;
                    case 4:
                        intent = new Intent(MenuActivity.this, WebActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1) {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Log.d(TAG, "onRequestPermissionsResult: Permiso autorizado");
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("34222343",null,"HOLAS",null, null);
            }
        }
    }
}
