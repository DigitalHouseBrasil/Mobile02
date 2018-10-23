package br.com.digitalhouse.digitalhouseapp.interfaces;

public interface ServiceListener {

    void onSucess(Object object);
    void onError (Throwable throwable);
}
