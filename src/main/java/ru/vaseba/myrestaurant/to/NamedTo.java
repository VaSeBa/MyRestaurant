package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NamedTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    protected String name;

    public NamedTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

}
