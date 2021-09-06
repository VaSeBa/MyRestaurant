package ru.vaseba.myrestaurant.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeUtil {
    private static final LocalTime DEAD_LINE_FOR_VOICING = LocalTime.of(11, 0);

    public static LocalDate getDeadLineForVoice() {
        return LocalTime.now().isBefore(DEAD_LINE_FOR_VOICING) ? LocalDate.now() : LocalDate.now().plusDays(1);
    }
}
