package com.mitocode.service3.impl4;

import com.mitocode.model1.Category;
import com.mitocode.repository2.ICategoryRepo;
import com.mitocode.repository2.IGenericRepo;
import com.mitocode.service3.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    @Autowired
    private ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByName(String name) {
        return repo.findByNameContains(name);
    }

    @Override
    public List<Category> findByNameContainsAndEnabled(String name, boolean enabled) {
        return repo.findByNameContainsAndEnabled(name, enabled);
    }

    @Override
    public List<Category> getByNameAndAndDescription3() {
        return repo.getByNameAndAndDescription3();
    }


}
