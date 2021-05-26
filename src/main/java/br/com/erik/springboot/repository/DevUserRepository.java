package br.com.erik.springboot.repository;

import br.com.erik.springboot.domain.DevUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevUserRepository extends JpaRepository<DevUser, Long> {

    DevUser findByUsername(String username);

}
