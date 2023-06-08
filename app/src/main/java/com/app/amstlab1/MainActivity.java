package com.app.amstlab1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);

        // Habilitar el ícono del menú en la barra de acción
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Configurar el listener para abrir/cerrar el menú lateral
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_calendar_view) {
                    // Lógica para el clic en el elemento de inicio de sesión
                    agregarEventoCalendario();
                    return true;
                } else if(itemId == R.id.menu_google_maps){
                    irAGoogleMaps();
                    return true;
                } else if (itemId == R.id.menu_video_view) {
                    irAYoutube();
                    return true;
                    
                } else if (itemId == R.id.menu_line_chart) {
                    return true;
                }

                // Otros elementos del menú

                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void agregarEventoCalendario() {
        // Obtener el calendario
        Calendar calendar = Calendar.getInstance();

        // Configurar la fecha y hora del evento
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);

        // Crear un Intent para agregar un evento al calendario
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendar.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calendar.getTimeInMillis() + 60 * 60 * 1000) // Duración del evento: 1 hora
                .putExtra(CalendarContract.Events.TITLE, "Mi evento")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Descripción del evento")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Ubicación del evento");

        // Iniciar la actividad del calendario
        startActivity(intent);
    }

    private void irAYoutube() {
        String videoUrl = "https://www.youtube.com/watch?v=X5fsU0Oxljg&list=PLIygiKpYTC_6XbaH_E39-cBEfqIosadAq";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(intent);
    }

    private void irAGoogleMaps() {
        String location = "latitude,longitude";
        String mapUrl = "http://maps.google.com/maps?q=" + location;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
        startActivity(intent);
    }
}