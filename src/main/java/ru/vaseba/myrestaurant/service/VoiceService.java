package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.repository.MenuRepository;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.repository.VoiceRepository;
import ru.vaseba.myrestaurant.to.VoiceDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class VoiceService extends BaseEntityService<Voice, VoiceRepository> {

    public static final LocalTime VOICE_CHANGE_STOP_TIME = LocalTime.of(11, 0);

    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public VoiceService(VoiceRepository repository, UserRepository userRepository, MenuRepository menuRepository) {
        super(repository);
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    protected void prepareBeforeCreate(Voice entity) {
        entity.setDate(LocalDate.now());
    }

    @Override
    protected void prepareBeforeUpdate(Voice entity) {
    }

    public VoiceDto vote(UUID userId, UUID menuId) {
        Voice voice = new Voice();
        voice.setUser(userRepository.getOne(userId));
        voice.setMenu(menuRepository.getOne(menuId));
        return new VoiceDto(super.create(voice));
    }

    public VoiceDto changeVote(UUID id, UUID menuId) throws TimeoutException {
        if (LocalTime.now().isAfter(VOICE_CHANGE_STOP_TIME)) {
            throw new TimeoutException("Истекло время, до которого можно было изменить выбор ресторана");
        }
        Voice voice = get(id);
        if (voice.getId().equals(id)) {
            return new VoiceDto(voice);
        } else {
            voice.setMenu(menuRepository.getOne(menuId));
            return new VoiceDto(repository.save(voice));
        }
    }

}
