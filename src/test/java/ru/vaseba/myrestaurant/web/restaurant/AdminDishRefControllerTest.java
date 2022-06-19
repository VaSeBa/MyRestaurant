package ru.vaseba.myrestaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vaseba.myrestaurant.model.DishRef;
import ru.vaseba.myrestaurant.repository.DishRefRepository;
import ru.vaseba.myrestaurant.util.JsonUtil;
import ru.vaseba.myrestaurant.util.validation.AdminRestaurantsUtil;
import ru.vaseba.myrestaurant.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vaseba.myrestaurant.web.restaurant.RestaurantTestData.*;
import static ru.vaseba.myrestaurant.web.user.UserTestData.R_ADMIN_MAIL;
import static ru.vaseba.myrestaurant.web.user.UserTestData.USER_MAIL;

class AdminDishRefControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantsUtil.REST_URL + '/';

    @Autowired
    private DishRefRepository dishRefRepository;

    private String getUrl(int restaurantId) {
        return REST_URL + restaurantId + "/dish-ref/";
    }

    private String getUrl(int restaurantId, int id) {
        return getUrl(restaurantId) + id;
    }

    @Test
    @WithUserDetails(value = R_ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(MAC_ID, mac_fof.id())))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_REF_MATCHER.contentJson(mac_fof));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(MAC_ID, mac_fof.id())))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = R_ADMIN_MAIL)
    void getNotBelong() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(WASABI_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = R_ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(MAC_ID, wasabi_rsh.id())))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = R_ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(getUrl(MAC_ID, mac_fof.id())))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(dishRefRepository.findById(mac_fof.id()).isPresent());
    }

    @Test
    @WithUserDetails(value = R_ADMIN_MAIL)
    void update() throws Exception {
        DishRef updated = getUpdatedDish();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(getUrl(MAC_ID, mac_fof.id()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        DISH_REF_MATCHER.assertMatch(dishRefRepository.getById(mac_fof.id()), getUpdatedDish());
    }

    @Test
    @WithUserDetails(value = R_ADMIN_MAIL)
    void createWithLocation() throws Exception {
        DishRef newDishRef = getNewDish();
        ResultActions action = perform(MockMvcRequestBuilders.post(getUrl(MAC_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDishRef)))
                .andExpect(status().isCreated());

        DishRef created = DISH_REF_MATCHER.readFromJson(action);
        int newId = created.id();
        newDishRef.setId(newId);
        DISH_REF_MATCHER.assertMatch(created, newDishRef);
        DISH_REF_MATCHER.assertMatch(dishRefRepository.getById(newId), newDishRef);
    }

    @Test
    @WithUserDetails(value = R_ADMIN_MAIL)
    void getByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(getUrl(MAC_ID)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_REF_MATCHER.contentJson(mac_fof, mac_chm20, mac_chb));
    }

    @Test
    @WithUserDetails(value = R_ADMIN_MAIL)
    void enable() throws Exception {
        perform(MockMvcRequestBuilders.patch(getUrl(MAC_ID, mac_fof.id()))
                .param("enabled", "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(dishRefRepository.getById(mac_fof.id()).isEnabled());
    }
}