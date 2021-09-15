package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import ru.vaseba.myrestaurant.model.BaseEntity;
import ru.vaseba.myrestaurant.repository.BaseEntityJpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static io.jsonwebtoken.lang.Assert.isNull;
import static org.springframework.util.Assert.notNull;

@Slf4j
public abstract class BaseEntityService<E extends BaseEntity, R extends BaseEntityJpaRepository<E>> {
    protected final R repository;

    protected BaseEntityService(R repository) {
        this.repository = repository;
    }

    //region Сервисные методы

    public E get(UUID id) {
        checkId(id);
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Сущность c id [" + id + "] не найдена"));
    }

    protected abstract void prepareBeforeCreate(E entity);

    public E create(E entity) {
        checkBeforeCreate(entity);
        prepareBeforeCreate(entity);
        entity = repository.save(entity);
        log.info("Сохранена новая сущность: тип {}, id {}", entity.getClass().getName(), entity.getId());
        return entity;
    }

    protected abstract void prepareBeforeUpdate(E entity);

    public E update(E entity) {
        checkBeforeUpdate(entity);
        prepareBeforeUpdate(entity);
        entity = repository.save(entity);
        log.info("Обновлена сущность: тип [{}], id [{}]", entity.getClass().getName(), entity.getId());
        return entity;
    }

    public void delete(UUID id) {
        checkId(id);
        repository.deleteById(id);
        log.info("Произведено мягкое удаление сущности с id [{}]", id);
    }

    //endregion

    //region Методы проверки

    private void checkId(UUID id) {
        notNull(id, "Параметр \"id\" не может быть пустым (null)");
    }

    private void checkIsPresent(E entity) {
        notNull(entity, "Сущность не может быть пустой (null)");
    }

    private void checkBeforeCreate(E entity) {
        checkIsPresent(entity);
        isNull(entity.getId(), String.format(
                "У новой сущности не может быть id, тип [%s], id [%s]", entity.getClass().getName(), entity.getId()
        ));
    }

    private void checkBeforeUpdate(E entity) {
        checkIsPresent(entity);
        checkId(entity.getId());
    }

    //endregion

}
