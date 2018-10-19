package br.com.digitalhouse.digitalhouseapp.model.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.digitalhouse.digitalhouseapp.model.Post;

public class PostDAO {

    public List<Post> getPosts(Context context) {

        List<Post> postList = new ArrayList<>();

        try {
            //Criar um stream para ler o arquivo JSON.
            AssetManager manager = context.getAssets();
            InputStream newsJson = manager.open("posts.json");
            BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(newsJson));

            //Criar um objeto da classe Gson que permita analisar o JSON de forma simples.
            Gson gson = new Gson();
            //Utilizando o objeto gson e o método fromJson, fazer a análise do arquivo que temos no
            // bufferReaderIn, usando como “molde” a classe News.

            Post[] postArray = gson.fromJson(bufferReaderIn, Post[].class);

            //Transforma Array[] em ArrayList
            postList = Arrays.asList(postArray);
        }catch (IOException exception){
            Log.e("JSON","erro ao ler arquivo posts.json");
        }

        return postList;
    }
}

