package com.mitocode.service3.impl4;

import com.mitocode.model1.Provider;
import com.mitocode.repository2.IProviderRepo;
import com.mitocode.repository2.IGenericRepo;
import com.mitocode.service3.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {

    @Autowired
    private IProviderRepo repo;                                                   //llamado al IProviderRepo de manera simplificada con anotacion @Autowired

    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return repo;
    }
}
