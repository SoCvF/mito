package com.mitocode.service3.impl4;

import com.mitocode.model1.Sale;
import com.mitocode.repository2.ISaleRepo;
import com.mitocode.repository2.IGenericRepo;
import com.mitocode.service3.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    @Autowired
    private ISaleRepo repo;                                                   //llamado al ISaleRepo de manera simplificada con anotacion @Autowired

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }
}
