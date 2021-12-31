package com.leightek.user.repository;

import com.leightek.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, String> {
}
