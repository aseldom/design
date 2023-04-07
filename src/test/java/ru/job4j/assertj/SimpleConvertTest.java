package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .doesNotContain("first", Index.atIndex(3))
                .containsSequence("second", "three", "four")
                .last().isEqualTo("five");
        assertThat(list).filteredOn(e -> e.length() == 4)
                .hasSize(2);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "second");
        assertThat(set).hasSize(5)
                .contains("first", "second", "three")
                .containsExactlyInAnyOrder("three", "four", "five", "first", "second");
        assertThat(set).filteredOn(e -> e.contains("second"))
                .hasSize(1);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first",
                "second", "three", "four", "five", "second");
        assertThat(map).hasSize(5)
                .containsKey("five")
                .containsEntry("second", 1);
    }

}