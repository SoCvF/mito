package com.mitocode.service3.impl4;

import com.mitocode.model1.Category;
import com.mitocode.repository2.ICategoryRepo;
import com.mitocode.repository2.IGenericRepo;
import com.mitocode.service3.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
