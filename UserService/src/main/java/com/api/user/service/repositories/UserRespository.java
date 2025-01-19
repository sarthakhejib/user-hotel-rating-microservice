package com.api.user.service.repositories;

import com.api.user.service.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<MyUser,String> {
}
