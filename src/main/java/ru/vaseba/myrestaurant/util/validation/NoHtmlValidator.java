package ru.vaseba.myrestaurant.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        return value == null || Jsoup.clean(value, Safelist.none()).equals(value);
    }
}
