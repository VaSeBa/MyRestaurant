package ru.vaseba.myrestaurant.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Menu;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseNamedEntityJpaRepository<Menu> {
}
