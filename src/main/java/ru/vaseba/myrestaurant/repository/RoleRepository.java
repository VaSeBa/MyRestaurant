package ru.vaseba.myrestaurant.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Role;

@Transactional(readOnly = true)
public interface RoleRepository extends BaseNamedEntityJpaRepository<Role> {
}
