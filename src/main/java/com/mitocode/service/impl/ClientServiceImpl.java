package com.mitocode.service.impl;

import com.mitocode.model.Cliente;
import com.mitocode.repository.IClientRepo;
import com.mitocode.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClientService {

    @Autowired
    private IClientRepo repo; //llamado al IClientRepo de manera simplificada con anotacion @Autowired

    @Override
    public Cliente save(Cliente category) throws Exception {
        return repo.save(category);
    }

    @Override
    public Cliente update(Cliente category) throws Exception {
        return repo.save(category);
    }

    @Override
    public List<Cliente> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Cliente readById(Integer id) throws Exception {
        //Optional<Client>op = repo.findById(id);
       // return op.isPresent() ? op.get() : new Client();
       // return op.orElse(new Client());
       return repo.findById(id).orElse(new Cliente());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
