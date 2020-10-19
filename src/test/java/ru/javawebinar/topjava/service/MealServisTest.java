package ru.javawebinar.topjava.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTetstData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.UserTestData.assertMatch;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServisTest {
    static
    {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() throws Exception{
        Meal newMeal = MealTetstData.getNew();
        Meal created = service.create(newMeal, 100001);
        int idCreateMeal = created.getId();
        newMeal.setId(idCreateMeal);
        assertThat(created).isEqualTo(newMeal);
    }

//    @Test
//    public void delete() throws Exception{
//        Meal newMeal = MealTetstData.getNew();
//        newMeal.setId(666);
//        int idMeal = newMeal.getId();
//        mealService.create(newMeal, 100001);
//        mealService.delete(idMeal, 100001);
//        assertThrows(NotFoundException.class, ()-> mealService.get(idMeal, 100001));
////        assertThat(newMeal).isEqualTo(mealService.get(idMeal, 100001));
//    }

    @Test
    public void delete() throws Exception{
        Meal meal = MealTetstData.getNew();
        service.create(meal, 100001);
        service.delete(meal.getId(), 100001);
        assertThrows(NotFoundException.class, ()-> service.get(meal.getId(), 100001));
    }

    @Test
    public void get()throws Exception{
        Meal meal = MealTetstData.getNew();
        service.create(meal, 100000);
        Meal meal2 = service.get(meal.getId(), 100000);
        assertThat(meal).isEqualTo(meal2);
    }

    @Test
    public void getAll() throws Exception{
        List<Meal> mealList1 = service.getAll();
        mealList1.sort(Comparator.comparing(Meal::getId));
        List <Meal> mealList2 = new ArrayList<>();
        mealList2.addAll(service.getAll(100000));
        mealList2.addAll(service.getAll(100001));
        mealList2.sort(Comparator.comparing(Meal::getId));
        assertThat(mealList1).isEqualTo(mealList2);
    }
}
