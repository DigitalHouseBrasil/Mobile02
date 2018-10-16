package br.com.digitalhouse.digitalhouseapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digitalhouseapp.adapter.SectionsFragmentPageAdapter;
import br.com.digitalhouse.digitalhouseapp.fragments.CommentsFragment;
import br.com.digitalhouse.digitalhouseapp.fragments.PostsFragment;
import br.com.digitalhouse.digitalhouseapp.interfaces.FragmentClick;
import br.com.digitalhouse.digitalhouseapp.model.Post;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentClick {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Seta a toolbar na tela
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Pega a referencia do drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Configuta o togle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Inicializa o page adapter comuma lista de frgmentos
        SectionsFragmentPageAdapter fragmentPageAdapter = new SectionsFragmentPageAdapter(getSupportFragmentManager(), getFragmentList());

        // Seta o adapter no viewpager
        viewPager.setAdapter(fragmentPageAdapter);

        // adiciona os listeners no viewpager e no tablayout
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private List<Fragment> getFragmentList() {

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new PostsFragment());
        fragmentList.add(new CommentsFragment());
        fragmentList.add(new PostsFragment());

        return fragmentList;
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

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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
            // Chama o replace passando uma instancia do fragmento de posts
            viewPager.setCurrentItem(0);

        } else if (id == R.id.nav_comments) {
            // Chama o replace passando uma instancia do fragmento de commets
            viewPager.setCurrentItem(1);

        } else if (id == R.id.nav_events) {
            viewPager.setCurrentItem(2);

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
