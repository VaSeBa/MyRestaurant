package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {

    @ApiModelProperty(position = 1, required = true)
    private String email;
    @ApiModelProperty(position = 2, required = true)
    private String token;

}
