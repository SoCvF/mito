package com.mitocode.repository2;

import com.mitocode.model1.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepo extends IGenericRepo<Category, Integer> {

    //Query derivado
    //Cada que se use el findBy, este retornara una lista
    //Name es el nombre de mi atributo de mi clase Category
    // si en todo caso se quiere buscar por otro atributo se debe de poner el nombre de ese atributo

    List<Category> findByName(String name);

    List<Category> findByNameLike(String name);

    //Otra manera de utilizar el findByNameLike pero queriendo los % al inicio y final
    List<Category> findByNameContains(String name);

    List<Category>findByNameContainsAndEnabled(String name, boolean enabled);

//    List<Category>findTop3ByNameContains(String name);
//
//    List<Category>findByNameContainsOrderByNameDesc(String name);
//
//    List<Category>findByNameIs(String name);
//
//    List<Category>findByNameIsNot(String name);
//
//    List<Category>findByNameIsNull(String name);
//
//    List<Category>findByNameIsNotNull(String name)
//    List<Category>findByIdCategoryLessThan(Integer id);



    //JPQL Java Persistence Query Language


    //Método 1
    @Query("FROM Category c WHERE c.name=?1 AND c.description LIKE %?2%" )
    List<Category> getByNameAndAndDescription1(String name, String description);

    //Método 2
    @Query("FROM Category c WHERE c.name= :name AND c.description LIKE %:description%" )
    List<Category> getByNameAndAndDescription2(@Param("name") String name,@Param("description") String description);

    @Query("SELECT new Category (c.name, c.description) FROM Category c")
    List<Category>getByNameAndAndDescription3();


    //Query nativo




}
