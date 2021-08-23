package ru.vaseba.myrestaurant.to;

import lombok.*;
import ru.vaseba.myrestaurant.HasId;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString(callSuper = true)
public class BaseTo implements HasId {
    protected Integer id;

}
