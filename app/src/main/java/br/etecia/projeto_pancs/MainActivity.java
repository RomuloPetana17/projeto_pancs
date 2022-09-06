package br.etecia.projeto_pancs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.idToolBar);
        drawerLayout = findViewById(R.id.idDrawerLayout);

        navigationView.setNavigationItemSelectedListener(this);

        //Clique no menu para abrir e fechar o NavigationView
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout,
                toolbar,
                R.string.opennav,
                R.string.closenav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.idContainerNav, new HomeFragment()).commit();

            navigationView.setCheckedItem(R.id.mHome);
        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mPlants:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.idContainerNav, new PlantsFragment()).commit();

                break;
            case R.id.mHome:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.idContainerNav, new HomeFragment()).commit();
                break;
            case R.id.mHelp:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.idContainerNav, new HelpFragment()).commit();
                break;
            case R.id.mShare:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.idContainerNav, new ShareFragment()).commit();
                break;
            case R.id.mSettings:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.idContainerNav, new SettingsFragment()).commit();
                break;
            default:

        }


        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        toggle.syncState();

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}