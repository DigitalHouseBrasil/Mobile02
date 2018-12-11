package br.com.digitalhouse.app.mob2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.app.mob2.R;
import br.com.digitalhouse.app.mob2.adapter.SectionsPageAdapter;
import br.com.digitalhouse.app.mob2.fragments.CommentsFragment;
import br.com.digitalhouse.app.mob2.fragments.PostsFragment;
import br.com.digitalhouse.app.mob2.interfaces.FragmentClick;
import br.com.digitalhouse.app.mob2.model.Post;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentClick {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setSupportActionBar(toolbar);
        configureDrawerLayout();
        configureViewPager();
    }

    private void configureViewPager() {
        // adiciona os listeners no viewpager e no tablayout
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        // Crio um instancia do pageadapter com uma lista de fragmentos
        SectionsPageAdapter pageAdapter = new SectionsPageAdapter(getSupportFragmentManager(), getFragmentList());

        // Seto o adapter no viewpager
        viewPager.setAdapter(pageAdapter);
    }

    private void configureDrawerLayout() {
        //Configuta o togle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        // Seta a toolbar na tela
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Pega a referencia do drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.container);
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new PostsFragment());
        fragments.add(new CommentsFragment());
        fragments.add(new PostsFragment());

        return fragments;
    }

    @Override
    public void onBackPressed() {
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

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("LOG", "query: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("LOG", "Novo texto: " + newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return super.onOptionsItemSelected(item);
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
            // Chama uma instancia do fragmento de posts
            viewPager.setCurrentItem(0);

        } else if (id == R.id.nav_events) {
            // Chama uma instancia do fragmento de eventos
            viewPager.setCurrentItem(1);

        } else if (id == R.id.nav_colearning) {
            // Chama uma instancia do fragmento de co-learning
            viewPager.setCurrentItem(2);
        }

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
