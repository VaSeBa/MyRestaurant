package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.User;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDto extends BaseNamedDto<User> {

    @ApiModelProperty(position = 3, required = true, example = "new@foodvoice.ru")
    private String email;
    @ApiModelProperty(position = 4, required = true, example = "password")
    private String password;
    @ApiModelProperty(position = 5, required = true, example = "[{\"name\": \"USER\"},{\"name\": \"ADMIN\"}]")
    private Set<RoleDto> roles = Set.of();

    public UserDto() {
        super();
    }

    public UserDto(User entity) {
        super(entity);
        email = entity.getEmail();
        password = entity.getPassword();
        roles = entity.getRoles().stream().map(RoleDto::new).collect(toSet());
    }

}
