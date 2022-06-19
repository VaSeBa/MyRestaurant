package ru.vaseba.myrestaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vaseba.myrestaurant.mapper.RestaurantMapper;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vaseba.myrestaurant.web.restaurant.RestaurantTestData.*;

class RestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantController.REST_URL + '/';

    @Autowired
    private RestaurantRepository repository;
    @Autowired
    private RestaurantMapper mapper;

    @Test
    void getWithMenuForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "menu_today"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER_WITH_MENU.contentJson(mapper.toTo(wasabi), mapper.toTo(mac)));
    }

    @Test
    void getWithMenuByRestaurantForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MAC_ID + "/menu_today"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER_WITH_MENU.contentJson(mapper.toTo(mac)));
    }

    @Test
    void getDisabled() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + SHALYPIN_ID + "/menu_today"))
                .andExpect(status().isConflict());
    }

    @Test
    void getAllEnabled() throws Exception {
        repository.save(mac);
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(wasabi, mac));
    }
}