package com.mitocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//Esta interfaz está extrayendo metodos de una Clase JpaRepository en donde están metodos 
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository <T,ID>{
}
