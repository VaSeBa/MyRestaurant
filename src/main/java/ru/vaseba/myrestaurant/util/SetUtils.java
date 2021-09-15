package ru.vaseba.myrestaurant.util;

import lombok.NoArgsConstructor;
import ru.vaseba.myrestaurant.model.BaseEntity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class SetUtils {

    public static <E extends BaseEntity> Optional<E> getById(@NotEmpty Set<E> set, @NotNull UUID id) {
        for (E entity : set) {
            if (id.equals(entity.getId())) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

}
