package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.model.User;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VoiceTO extends BaseTo{

    private LocalDate timeOfVoicing;
    private User user;
    private Restaurant restaurant;

    public VoiceTO(Integer id) {
        super(id);
    }

    public VoiceTO() {
    }

    public VoiceTO(Integer id, LocalDate timeOfVoicing, User user, Restaurant restaurant) {
        super(id);
        this.timeOfVoicing = timeOfVoicing;
        this.user = user;
        this.restaurant = restaurant;
    }

    public VoiceTO(LocalDate timeOfVoicing, User user, Restaurant restaurant) {
        this.timeOfVoicing = timeOfVoicing;
        this.user = user;
        this.restaurant = restaurant;
    }
}
