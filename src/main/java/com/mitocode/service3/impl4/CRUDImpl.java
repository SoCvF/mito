package com.mitocode.service3.impl4;


import com.mitocode.repository2.IGenericRepo;
import com.mitocode.service3.ICRUD;

import java.util.List;


/*En esta CLASE GENERICA CRUDIMPL se IMPLEMENTARA los metodos de la INTERFAZ GENERICA ICRUD
* IMPLEMENTANDO SUS METODOS Y ELABORANDO LA LOGICA sobreescribiendo los metodos creado en
* la interfaz ICRUD
**/
public abstract class CRUDImpl<T, ID> implements ICRUD<T,ID> {

    protected abstract IGenericRepo<T,ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);

    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id);
    }
}
