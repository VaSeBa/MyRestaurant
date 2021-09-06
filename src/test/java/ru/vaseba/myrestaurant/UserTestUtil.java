package ru.vaseba.myrestaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.vaseba.myrestaurant.model.Role;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestUtil {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 3;
    public static final String USER_MAIL = "first_user@gmail.com";
    public static final String ADMIN_MAIL = "admin@javaops.ru";
    public static final User user = new User(USER_ID, "User_Name", USER_MAIL,"password1", List.of(Role.ROLE_USER));
    public static final User admin = new User(ADMIN_ID, "Admin_Name", ADMIN_MAIL,"admin", List.of(Role.ROLE_ADMIN, Role.ROLE_USER));

    public static User getNew() {
        return new User(null, "New_Name", "new@gmail.com", "newpass", List.of(Role.ROLE_USER));
    }

    public static User getUpdated() {
        return new User(USER_ID,"User_Name_Update","user_update@gmail.com","password_update", List.of(Role.ROLE_USER));
    }

    public static void assertEquals(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id", "password").isEqualTo(expected);
    }

    // No id in HATEOAS answer
    public static void assertNoIdEquals(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id", "password").isEqualTo(expected);
    }

    public static User asUser(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, User.class);
    }

    public static ResultMatcher jsonMatcher(User expected, BiConsumer<User, User> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asUser(mvcResult), expected);
    }
}

