package org.rail.repository;

import org.rail.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String > {
}
