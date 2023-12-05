package com.mitocode.service3.impl4;

import com.mitocode.model1.Client;
import com.mitocode.repository2.IClientRepo;
import com.mitocode.repository2.IGenericRepo;
import com.mitocode.service3.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    @Autowired
    private IClientRepo repo;                                                   //llamado al IClientRepo de manera simplificada con anotacion @Autowired

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }
}
