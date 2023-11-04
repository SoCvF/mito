package com.mitocode.service.impl;

import com.mitocode.model.Category;
import com.mitocode.repository.ICategoryRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.ICRUD;
import com.mitocode.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    @Autowired
    private ICategoryRepo repo; //llamado al ICategoryRepo de manera simplificada con anotacion @Autowired

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

  /*  @Override
    public Category save(Category category) throws Exception {
        return repo.save(category);
    }

    @Override
    public Category update(Category category) throws Exception {
        return repo.save(category);
    }

    @Override
    public List<Category> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Category readById(Integer id) throws Exception {
        //Optional<Category>op = repo.findById(id);
       // return op.isPresent() ? op.get() : new Category();
       // return op.orElse(new Category());
       return repo.findById(id).orElse(new Category());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }*/
}
