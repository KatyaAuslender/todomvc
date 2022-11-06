import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.Test;

public class TodoMvc {
    @Test
    public void completesTask() {
        // 1) открыть сайт (open TodoMVC page)
        open("https://todomvc.com/examples/emberjs/");
        // 2) добавить задачи "a", "b", "c"  (add tasks: "a", "b", "c")
        // find new task edit box; set value to "a"; press Enter
        element(byId("new-todo")).setValue("a").pressEnter();
        element(byId("new-todo")).setValue("b").pressEnter();
        element(byId("new-todo")).setValue("c").pressEnter();
        // 3) убедиться, что задачи отображаются в списке корректно (tasks should be "a", "b", "c")
        System.out.println( elements("#todo-list>li"));
        elements("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));
        // 4) отметить задачу b как сделанную (toggle b)
        // 1.among all tasks 2.find the one with "b" text 3.find its toggle checkbox 4.click it
        elements("#todo-list>li").findBy(exactText("b"))
                .find(".toggle").click();
        // 5) проверить статус отмеченной задачи, что она "сделанная" (complete tasks should be b)
        elements("#todo-list>li").filterBy(cssClass("completed"))
                .shouldHave(exactTexts("b"));
        // 6) задачи a и c должны быть со статусом "не сделаны" (active tasks should be a, c)
        elements("#todo-list>li").filterBy(not(cssClass("completed")))
                .shouldHave(exactTexts("a", "c"));
    }
}
