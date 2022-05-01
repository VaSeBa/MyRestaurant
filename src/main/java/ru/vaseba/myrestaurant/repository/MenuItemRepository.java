package ru.vaseba.myrestaurant.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.entity.MenuItem;

@Transactional(readOnly = true)
public interface MenuItemRepository extends BaseRepository<MenuItem> {
}