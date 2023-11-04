package com.mitocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//Se está diciendo que la interfaz será para cualquier tipo de clase y  su valor de llave primaria
//que tenga
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository <T,ID>{
}
