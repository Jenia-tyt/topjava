package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.AbstractBaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends AbstractBaseEntity {
    private Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final String nameUser;

    public Meal(LocalDateTime dateTime, String description, int calories, String nameUser) {
        this(null, dateTime, description, calories, nameUser);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories, String nameUser) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.nameUser = nameUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
    }

    public String getNameUser() {
        return nameUser;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}