package ru.vaseba.myrestaurant.to;

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

    private String email;
    private String password;
    private Set<RoleDto> roles;

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
