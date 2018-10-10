package br.com.digitalhouse.digitalhouseapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.digitalhouse.digitalhouseapp.fragments.CommentsFragment;
import br.com.digitalhouse.digitalhouseapp.fragments.PostsFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void changeFragmentOne(View view) {
        // Instacia o fragmento de Posts
        Fragment fragment = new PostsFragment();

        // Chama o replace passando uma instancia do fragmento de posts
        replaceFragment(fragment, R.id.content_one, "POSTS");
    }

    public void changeFragmentTwo(View view) {

        // Chama o replace passando uma instancia do fragmento de comentários
        replaceFragment(new CommentsFragment(), R.id.content_two, "COMMENTS");
    }

    // Substitui o conteiner com o fragment passado e adiciona a pilha
    public void replaceFragment(Fragment fragment, int container, String stack) {
        //Inicia uma transação
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Substitu o container com o fragmento
        transaction.replace(container, fragment);

        // Adiciona a pilha de fragmentos
        transaction.addToBackStack(stack);

        // Comita/Finaliza a transação
        transaction.commit();
    }
}
