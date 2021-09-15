package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.meta.Meta;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"name"})
@ToString(callSuper = true)
public abstract class BaseNamedEntity extends BaseEntity {

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = Meta.BaseNamedEntity.NAME, nullable = false)
    private String name;

}
