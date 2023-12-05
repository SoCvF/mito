package com.mitocode.service3.impl4;

import com.mitocode.model1.Role;
import com.mitocode.repository2.IRoleRepo;
import com.mitocode.repository2.IGenericRepo;
import com.mitocode.service3.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRolService {

    @Autowired
    private IRoleRepo repo;                                                   //llamado al IRoleRepo de manera simplificada con anotacion @Autowired

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }
}
