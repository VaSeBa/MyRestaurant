package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vaseba.myrestaurant.model.BaseEntity;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class BaseDto<E extends BaseEntity> implements Serializable {

    private UUID id;

    public BaseDto(E entity) {
        this.id = entity.getId();
    }

}
