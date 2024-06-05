package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;


class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource(Country.class)
    void locale(Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String expected = localizationService.locale(country);
        assertTrue(expected.equals("Welcome")
                   || expected.equals("Добро пожаловать"));
    }
}