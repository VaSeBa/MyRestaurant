package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    @ApiModelProperty(position = 1, required = true, example = "admin@foodvoice.ru")
    private String email;
    @ApiModelProperty(position = 2, required = true, example = "password")
    private String password;

}
