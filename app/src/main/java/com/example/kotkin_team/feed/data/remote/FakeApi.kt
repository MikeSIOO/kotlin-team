package com.example.kotkin_team.feed.data.remote

import com.example.kotkin_team.feed.data.Ingredient
import com.example.kotkin_team.feed.data.Recipe
import com.example.kotkin_team.feed.data.Step
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeApi @Inject constructor() {
    fun createFakeRecipes(): List<Recipe> {
        val recipes = mutableListOf<Recipe>()
        val ingredients: List<Ingredient> = listOf(
            Ingredient("лук-шалот", "100 г"),
            Ingredient("белые грибы свежие", "400 г"),
            Ingredient("рис круглозерный", "400 г"),
            Ingredient("сосика вареная", "1 шт"),
            Ingredient("бульон овощной", "300-400 мл"),
            Ingredient("оливковое масло `экстра вирджин` для подачи", null),
        )
        val steps: List<Step> = listOf(
            Step(
                1,
                "Мелко порубить лук шалот и чеснок. Белые грибы почистить, тонко нарезать, обжарить в оливковом масле на сковороде с двумя зубчиками рубленого чеснока и луком. Отложить в сторону несколько кусочков белых грибов для украшения блюда."
            ),
            Step(
                2,
                "Через 2 минуты добавить сырой рис, жарить на сковороде около 2 минут. После этого начать порционно добавлять к рису овощной бульон, по 100 мл за раз. Каждый раз добавлять жидкость, когда она полностью впитается."
            ),
            Step(
                3,
                "Через 15 минут, когда рис готов и жидкости не осталось, добавить в ризотто сливочное масло и тертый пармезан (если блюдо готовится не для поста), перемешать деревянной ложкой. Если у ризотто слишком плотная консистенция, добавить еще немного овощного бульона."
            ),
            Step(
                4,
                "Выложить ризотто в тарелку, украсить кусочками белых грибов и петрушкой. Сбрызнуть оливковым маслом \"экстра вирджин\". Посолить и поперчить. Сразу подавать."
            ),
        )
        for (i in 1..30) {
            recipes.add(
                Recipe(
                    i,
                    "Ризотто с белыми грибами",
                    "${30 + i}",
                    "средняя",
                    "итальянская",
                    ingredients,
                    steps
                )
            )
        }
        return recipes
    }
}
