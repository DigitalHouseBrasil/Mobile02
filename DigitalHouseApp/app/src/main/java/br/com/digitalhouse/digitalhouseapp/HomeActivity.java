package br.com.digitalhouse.digitalhouseapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.digitalhouse.digitalhouseapp.fragments.CommentsFragment;
import br.com.digitalhouse.digitalhouseapp.fragments.PostsFragment;
import br.com.digitalhouse.digitalhouseapp.interfaces.FragmentClick;
import br.com.digitalhouse.digitalhouseapp.model.Post;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Chama o replace passando uma instancia do fragmento de posts
        replaceFragment(new PostsFragment(), R.id.container);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.nav_posts) {
            // Handle the camera action
            // Chama o replace passando uma instancia do fragmento de posts
            replaceFragment(new PostsFragment(), R.id.container);

        } else if (id == R.id.nav_comments) {
            // Chama o replace passando uma instancia do fragmento de posts
            replaceFragment(new CommentsFragment(), R.id.container);

        } else if (id == R.id.nav_events) {

        } else if (id == R.id.nav_colearning) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Substitui o conteiner com o fragment passado e adiciona a pilha
    public void replaceFragment(Fragment fragment, int container) {
        //Inicia uma transação
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Substitu o container com o fragmento
        transaction.replace(container, fragment);


        // Comita/Finaliza a transação
        transaction.commit();
    }

    @Override
    public void onItemClick(Post post) {
        // Chama o replace passando uma instancia do fragmento de comentários
        Fragment fragment = new CommentsFragment();

        Bundle bundle = new Bundle();
        bundle.putString("TEXT", post.getDescription());

        fragment.setArguments(bundle);

        replaceFragment(fragment, R.id.container);
    }
}
