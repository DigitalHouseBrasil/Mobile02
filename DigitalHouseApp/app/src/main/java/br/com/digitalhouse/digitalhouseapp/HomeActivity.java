package br.com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.digitalhouse.digitalhouseapp.fragments.CommentsFragment;
import br.com.digitalhouse.digitalhouseapp.fragments.PostsFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void changeFragmentOne(View view) {

//Instancia o fragment de Posts
        Fragment fragment = new PostsFragment();
        // chama o replace passando uma instancia de fragmento de posts
        replaceFragment(fragment, R.id.content_one, "POSTS");
    }

    public void changeFragmentTwo(View view) {
//// chama o replace passando uma instancia de fragmento de comentarios
        replaceFragment(new CommentsFragment(), R.id.content_two, "COMMENTS");

    }

    // substitui o container com o fragment passado e adiciona a pilha
    public void replaceFragment(Fragment fragment, int container, String stack) {
// inicia a transação
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//substitui container com fragmento
        transaction.replace(container, fragment);
//adiciona a pilha de fragmentos
        transaction.addToBackStack(stack);
//comita/finaliza a transação
        transaction.commit();
    }
}
