package com.mitocode.repository2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*Esta interfaz está extrayendo metodos de una Clase JpaRepository en donde están metodos, El motivo de tener una clase GENERAL O GENERICO
 es para que esta sea PARA que esta pueda implementarse con otros REPOSITORY y después sea utilizado por un ServiceGenerico */
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository <T,ID>{
}
