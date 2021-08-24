package ru.vaseba.myrestaurant.to;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString(callSuper = true)
public class BaseTo {
    protected Integer id;

}
