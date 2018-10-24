package br.com.digitalhouse.retofitpagination.interfaces;

//integrar as camadas (entrega a resposta do controller p/ view)
public interface WebServiceInterface {
	void success(Object obj);
	void erro(Throwable throwable);
}
