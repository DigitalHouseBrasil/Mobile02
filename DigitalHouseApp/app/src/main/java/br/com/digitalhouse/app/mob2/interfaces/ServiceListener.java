package br.com.digitalhouse.app.mob2.interfaces;

public interface ServiceListener {
    void onSuccess(Object object);
    void onError(Throwable throwable);
}
