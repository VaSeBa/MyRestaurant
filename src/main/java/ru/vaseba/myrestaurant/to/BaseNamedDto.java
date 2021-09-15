package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.BaseNamedEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseNamedDto<E extends BaseNamedEntity> extends BaseDto<E> {

    private String name;

    public BaseNamedDto() {
        super();
    }

    public BaseNamedDto(E entity) {
        super(entity);
        this.name = entity.getName();
    }

}
