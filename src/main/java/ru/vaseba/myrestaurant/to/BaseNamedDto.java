package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.BaseNamedEntity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseNamedDto<E extends BaseNamedEntity> extends BaseDto<E> {

    @ApiModelProperty(position = 2, required = true, example = "name")
    private String name;

    public BaseNamedDto() {
        super();
    }

    public BaseNamedDto(E entity) {
        super(entity);
        this.name = entity.getName();
    }

}
