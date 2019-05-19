package com.mytutorials.rest.webservices.Repository;

import com.mytutorials.rest.webservices.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUserName(String userName);
}
