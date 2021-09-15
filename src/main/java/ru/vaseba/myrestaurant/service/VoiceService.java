package ru.vaseba.myrestaurant.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.repository.MenuRepository;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.repository.VoiceRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class VoiceService extends BaseEntityService<Voice, VoiceRepository> {

    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    @Value("${food-voice.voice.change-stop-time}")
    private String voiceChangeStopTime;
    @Getter
    private LocalTime voiceChangeStopLocalTime;

    @Autowired
    public VoiceService(VoiceRepository repository, UserRepository userRepository, MenuRepository menuRepository) {
        super(repository);
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @PostConstruct
    protected void init() {
        voiceChangeStopLocalTime = LocalTime.parse(voiceChangeStopTime);
    }

    @Override
    protected void prepareBeforeCreate(Voice entity) {
        super.prepareBeforeCreate(entity);
        entity.setDate(LocalDate.now());
    }

    public Voice vote(UUID userId, UUID menuId) {
        Voice voice = new Voice();
        voice.setUser(userRepository.getOne(userId));
        voice.setMenu(menuRepository.getOne(menuId));
        return super.create(voice);
    }

    public Voice changeVote(UUID id, UUID userId, UUID newMenuId) throws TimeoutException {
        Voice voice = get(id, userId);
        if (LocalDate.now().equals(voice.getCreated().toLocalDate())
                && LocalTime.now().isAfter(voiceChangeStopLocalTime)) {
            throw new TimeoutException("Истекло время, до которого можно было изменить выбор ресторана");
        }
        if (voice.getId().equals(id)) {
            return voice;
        } else {
            voice.setMenu(menuRepository.getOne(newMenuId));
            return repository.save(voice);
        }
    }

    public Page<Voice> getAllAllByUserId(UUID userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }

    private Voice get(UUID id, UUID userId) {
        return repository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Голос c id [%s] не найден для пользователя с id [%s]", id, userId)
                ));
    }

}
