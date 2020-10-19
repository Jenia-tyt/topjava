package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public class MealTetstData {
    public static final int MEAL_ID = 1;

    public static int getMealId() {
        return MEAL_ID;
    }

    private static final Meal meal = new Meal(LocalDateTime.now(), "TESTMEAL", 2000);

    public static Meal getNew (){
        return new Meal(LocalDateTime.now(), "NewMeal", 3000);
    }

    public static Meal getNewWhitID (){
        return new Meal(5, LocalDateTime.now(), "NewMeal", 3000);
    }

    public static Meal getUpdated(){
        Meal mealUpDatd = new Meal(meal);
        mealUpDatd.setDescription("Updated");
        mealUpDatd.setCalories(6666);
        return mealUpDatd;
    }
}
