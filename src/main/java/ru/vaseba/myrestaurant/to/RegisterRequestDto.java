package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @ApiModelProperty(position = 1, required = true, example = "New User")
    private String name;
    @ApiModelProperty(position = 2, required = true, example = "new_user@foodvoice.ru")
    private String email;
    @ApiModelProperty(position = 3, required = true, example = "password")
    private String password;

}
