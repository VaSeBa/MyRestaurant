package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vaseba.myrestaurant.model.BaseEntity;
import ru.vaseba.myrestaurant.model.Status;

import java.io.Serializable;
import java.util.UUID;

import static ru.vaseba.myrestaurant.model.Status.ACTIVE;


@NoArgsConstructor
@Data
public abstract class BaseDto<E extends BaseEntity> implements Serializable {

    private UUID id;
    @ApiModelProperty(position = 2, required = true, example = "1", value = "sdfsdfs")
    private Status status = ACTIVE;

    public BaseDto(E entity) {
        this.id = entity.getId();
        this.status = entity.getStatus();
    }

    @ApiModelProperty(position = 1, hidden = true)
    public void setId(UUID id) {
        this.id = id;
    }

}
