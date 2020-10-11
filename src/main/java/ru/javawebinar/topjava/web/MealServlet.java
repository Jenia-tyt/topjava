package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.to.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UserUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;


public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryMealRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")),
                request.getParameter("userName"));

        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        repository.save(meal);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action==null && request.getParameter("withDate")!=null && request.getParameter("byDate")!=null) {
            LocalDate withDate = request.getParameter("withDate").equals("") ? LocalDate.MIN : LocalDate.parse(request.getParameter("withDate"));
            LocalDate byDate = request.getParameter("byDate").equals("") ? LocalDate.MAX : LocalDate.parse(request.getParameter("byDate"));
            List<MealTo> mealToListFilterDate = MealsUtil.getFilteredTosDay(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY, withDate, byDate);
            request.setAttribute("meals", mealToListFilterDate);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else if (action==null && request.getParameter("withTime")!=null && request.getParameter("byTime")!=null) {
            LocalTime withTime = request.getParameter("withTime").equals("") ? LocalTime.MIN :LocalTime.parse(request.getParameter("withTime"));
            LocalTime byTime = request.getParameter("byTime").equals("") ? LocalTime.MAX :LocalTime.parse(request.getParameter("byTime"));
            List<MealTo> mealToListFilterTime = MealsUtil.getFilteredTosTime(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY, withTime, byTime);
            request.setAttribute("meals", mealToListFilterTime);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                repository.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, "userName") :
                        repository.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "toUser":
                String userName = request.getParameter("userName");
                List<MealTo> mealToList = MealsUtil.byFilterUserName(repository.getAll(), userName, MealsUtil.DEFAULT_CALORIES_PER_DAY);
                request.setAttribute("meals", mealToList);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        MealsUtil.getTos(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
