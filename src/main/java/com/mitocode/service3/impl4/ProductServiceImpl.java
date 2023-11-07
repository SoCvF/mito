package com.mitocode.service3.impl4;

import com.mitocode.model1.Product;
import com.mitocode.repository2.IProductRepo;
import com.mitocode.repository2.IGenericRepo;
import com.mitocode.service3.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

    @Autowired
    private IProductRepo repo; //llamado al IProductRepo de manera simplificada con anotacion @Autowired

    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }

  /*  @Override
    public Product save(Product Product) throws Exception {
        return repo.save(Product);
    }

    @Override
    public Product update(Product Product) throws Exception {
        return repo.save(Product);
    }

    @Override
    public List<Product> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Product readById(Integer id) throws Exception {
        //Optional<Product>op = repo.findById(id);
       // return op.isPresent() ? op.get() : new Product();
       // return op.orElse(new Product());
       return repo.findById(id).orElse(new Product());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }*/
}
