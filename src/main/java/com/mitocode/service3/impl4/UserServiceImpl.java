package com.mitocode.service3.impl4;

import com.mitocode.model1.User;
import com.mitocode.repository2.IGenericRepo;
import com.mitocode.repository2.IUserRepo;
import com.mitocode.service3.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    @Autowired
    private IUserRepo repo;                                                   //llamado al IUserRepo de manera simplificada con anotacion @Autowired

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }
}
