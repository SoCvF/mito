package com.mitocode.service3;

import java.util.List;


/*Se está creando esta INTERFAZ GENERICA  en donde se establecerá el nombre del METODO y que RECIBIRA
* En este caso recibe una T de tipo de objeto y un ID
* */
public interface ICRUD<T,ID> {
    T save(T t) throws Exception;
    T update(T t) throws Exception;
    List<T> readAll() throws Exception;
    T readById(ID id) throws Exception;
    void delete(ID id)throws Exception;

}
