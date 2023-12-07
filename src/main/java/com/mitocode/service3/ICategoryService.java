package com.mitocode.service3;

import com.mitocode.model1.Category;

import java.util.List;

public interface ICategoryService extends ICRUD<Category, Integer>{


    List<Category> findByName(String name);

    List<Category>findByNameContainsAndEnabled(String name, boolean enabled);

    List<Category>getByNameAndAndDescription3();
}
