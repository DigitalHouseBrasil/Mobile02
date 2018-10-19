package br.com.digitalhouse.digitalhouseapp;

import java.util.HashMap;
import java.util.Map;

public class LoginManager {

    Map<String, String> usuarios = new HashMap<>();

    public LoginManager() {
        usuarios.put("fabio@digital.com", "senha");
        usuarios.put("alexandre@digital.com", "senha123");
        usuarios.put("joao@digital.com", "senha321");
        usuarios.put("jose@digital.com", "senha543");
        usuarios.put("teste@digital.com", "teste");
        usuarios.put("admin@digital.com", "admin");
    }

    public String getSenhaPorUsuario(String usuario) {
        return usuarios.get(usuario);
    }
}
