package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkNameContainsEqual() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"s"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name:")
                .hasMessageContaining(names[0])
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void checkNameContainsKey() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=s"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name:")
                .hasMessageContaining(names[0])
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkNameContainsValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"s="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name:")
                .hasMessageContaining(names[0])
                .hasMessageContaining("does not contain a value");
    }
}