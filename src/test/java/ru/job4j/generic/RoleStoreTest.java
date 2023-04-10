package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsDo() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Do");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsDo() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        store.add(new Role("1", "Go"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Do");
    }

    @Test
    void whenReplaceThenRolenameIsGo() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        store.replace("1", new Role("1", "Go"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Go");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        store.replace("10", new Role("10", "Go"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Do");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Do");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        boolean rsl = store.replace("1", new Role("1", "Go"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        boolean rsl = store.replace("10", new Role("10", "Go"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Do"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}