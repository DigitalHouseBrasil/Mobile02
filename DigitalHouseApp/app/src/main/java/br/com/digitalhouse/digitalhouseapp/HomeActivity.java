package br.com.digitalhouse.digitalhouseapp;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digitalhouseapp.adapter.SectionsPageAdapter;
import br.com.digitalhouse.digitalhouseapp.fragments.CommentsFragment;
import br.com.digitalhouse.digitalhouseapp.fragments.PostsFragment;
import br.com.digitalhouse.digitalhouseapp.interfaces.FragmentClick;
import br.com.digitalhouse.digitalhouseapp.model.Post;

public class HomeActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener, FragmentClick {

	private ViewPager viewPager;
	private TabLayout tabLayout;
	private Toolbar toolbar;
	private DrawerLayout drawer;
	private ActionBarDrawerToggle toggle;
	private NavigationView navigationView;
	private SectionsPageAdapter pageAdapter;
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
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

		pageAdapter = new SectionsPageAdapter(getSupportFragmentManager(), getFragmentList());

		viewPager.setAdapter(pageAdapter);
	}

	private void configureDrawerLayout() {
		//Configuta o togle
		toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		navigationView.setNavigationItemSelectedListener(this);
	}

	private void initViews() {
		// findViewById
		toolbar = findViewById(R.id.toolbar);
		drawer = findViewById(R.id.drawer_layout);
		navigationView = findViewById(R.id.nav_view);
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
				Toast.makeText(HomeActivity.this, "Pesquisando... "+query, Toast.LENGTH_SHORT).show();
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
			//nada acontece
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
		} else if (id == R.id.nav_events) {
			viewPager.setCurrentItem(1);
		} else if (id == R.id.nav_colearning) {
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
