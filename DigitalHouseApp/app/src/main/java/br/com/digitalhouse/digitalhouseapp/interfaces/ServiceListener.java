package br.com.digitalhouse.digitalhouseapp.interfaces;

public interface ServiceListener {
    void onError(Throwable throwable);
    void onSuccess(Object obj);
}
