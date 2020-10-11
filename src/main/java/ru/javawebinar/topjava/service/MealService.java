package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.Meal;

import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal creat (Meal meal){
        return repository.save(meal);
    }

    public void delete(int id){
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get (int id){
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Meal> getAll(){
       return new ArrayList<>(repository.getAll());
    }

    public void upDate(Meal meal){
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

}