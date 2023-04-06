package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void ThenNumberOfVertices8() {
        Box box = new Box(8, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(8);
    }

    @Test
    void ThenNumberOfVertices4() {
        Box box = new Box(4, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(4);
    }

    @Test
    void ifVertex4ThenExist() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void ifVertex9ThenNotExist() {
        Box box = new Box(9, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void ifVertex4Edge4ThenArea27_7() {
        Box box = new Box(4, 4);
        Double area = box.getArea();
        assertThat(area).isEqualTo(27.712d, withPrecision(0.001));
    }

    @Test
    void ifVertex8Edge4ThenArea96_0() {
        Box box = new Box(8, 4);
        Double area = box.getArea();
        assertThat(area).isEqualTo(96.0d, withPrecision(0.001));
    }
}