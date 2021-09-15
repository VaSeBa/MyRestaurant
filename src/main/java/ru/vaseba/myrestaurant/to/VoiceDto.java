package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.Voice;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VoiceDto extends BaseDto<Voice> {

    private UUID userId;
    private LocalDate date;
    private UUID menuId;

    public VoiceDto() {
        super();
    }

    public VoiceDto(Voice entity) {
        super(entity);
        userId = entity.getUser().getId();
        date = entity.getDate();
        menuId = entity.getUser().getId();
    }

}
