package ru.netology.sender;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class MessageSenderImplTest {


    @ParameterizedTest
    @MethodSource("sendTestParams")
    void sendTest(String ip, Location localeTest) {

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(localeTest);

        String str = String.valueOf(localeTest.getCountry());

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(localeTest.getCountry()))
                .thenReturn(str);

        String expected = localizationService.locale(localeTest.getCountry());

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        assertEquals(expected, messageSender.send(headers));
    }

    public static Stream<Arguments> sendTestParams() {
        Map<String, String> headers = new HashMap<>();
        return Stream.of(
                arguments("172.123.12.19", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                arguments("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                arguments("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                arguments("96.", new Location("New York", Country.USA, null, 0))
        );
    }
}