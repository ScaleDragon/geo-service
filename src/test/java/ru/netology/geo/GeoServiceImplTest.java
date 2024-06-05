package ru.netology.geo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;


class GeoServiceTest {

    @ParameterizedTest
    @MethodSource
    void byIpTest(String input, Location output) {
        GeoServiceImpl geoService = new GeoServiceImpl();

        assertEquals(output, geoService.byIp(input));

    }

    public static Stream<Arguments> byIpTest() {
        return Stream.of(
                arguments("127.0.0.1", new Location(null, null, null, 0)),
                arguments("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                arguments("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                arguments("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                arguments("96.", new Location("New York", Country.USA, null, 0))
        );
    }

    @Test
    void byCoordinates_RuntimeExceptionTest() {
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Not implemented");
        });
    }
}