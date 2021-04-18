package person.hong.learn.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MathUtilsTest {

    @BeforeAll
    public static void begin() {
        System.out.println("九品芝麻官公堂之上 殴打状师");
    }

    @AfterAll
    public static void hitMe() {
        System.out.println("打我啦本 最后状师就被打了");
    }

    static List<Arguments> testCapitalize() {
        return Arrays.asList(Arguments.arguments("abc", "Abc"), //
                Arguments.arguments("APPLE", "Apple"), //
                Arguments.arguments("gooD", "Good"));
    }

    @BeforeEach
    public void in() {
        System.out.println("我进来了");
    }

    @AfterEach
    public void out(){
        System.out.println("我又出去了");
    }

    @Test
    void fibonacci() {
        int first = MathUtils.fibonacci(1);
        assert first == 1;

        int second = MathUtils.fibonacci(2);
        assertEquals(1, second);

        int third = MathUtils.fibonacci(3);
        assertEquals(2, third);

        assertEquals(3, MathUtils.fibonacci(4));
        assertEquals(5, MathUtils.fibonacci(5));
        assertEquals(8, MathUtils.fibonacci(6));
    }

    @Test
    void factorial() {
        Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                MathUtils.factorial(-1);
            }
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -6, -1321})
    public void testAbs(int num) {
        assertEquals(-num, Math.abs(num));
    }

    @ParameterizedTest
    @MethodSource
    void testCapitalize(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    @ParameterizedTest
    @CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
    public void testCapitalizeUsingCsv(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = { "/test-capitalize.csv" })
    void testCapitalizeUsingCsvFile(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }
}