package org.example.crmbackinterntask.repository;

import org.example.crmbackinterntask.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
    Authority findByAuthority(String name);
}
