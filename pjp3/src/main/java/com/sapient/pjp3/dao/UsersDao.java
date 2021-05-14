package com.sapient.pjp3.dao;

import com.sapient.pjp3.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersDao extends CrudRepository <User,Integer> {
    Integer findById(String Id);
}
