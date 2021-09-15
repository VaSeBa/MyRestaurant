package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoiceRequestDto {

    @ApiModelProperty(position = 1, required = true, example = "dd0e6ec6-1532-4219-bab4-2e04a31b6a01")
    private UUID menuId;

}
