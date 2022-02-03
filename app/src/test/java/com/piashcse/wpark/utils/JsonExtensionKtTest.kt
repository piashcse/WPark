package com.piashcse.wpark.utils

import com.piashcse.wpark.data.model.CityItem
import com.piashcse.wpark.data.model.FoodItem
import org.junit.Assert
import org.junit.Test

class JsonExtensionKtTest {
    @Test
    fun `object to json test`() {
        val cityItem =
            CityItem(id = 1, name = "Wpart", description = "Tokyo city", image = "www.google.com")
        println(cityItem.toPrettyJson())
        val testingJson =
            "{\"id\":1,\"description\":\"Tokyo city\",\"image\":\"www.google.com\",\"name\":\"Wpart\"}"
        Assert.assertEquals(cityItem.toPrettyJson(), testingJson)
    }

    @Test
    fun `json to object test`() {
        val cityItem =
            CityItem(name = "Wpart", description = "Tokyo city", image = "www.google.com")
        val testingJson =
            "{\"description\":\"Tokyo city\",\"image\":\"www.google.com\",\"name\":\"Wpart\"}"
        Assert.assertEquals(testingJson.fromPrettyJson<CityItem>(), cityItem)
    }

    @Test
    fun `json to object list test`() {
        val foodItemsListJson = "[{\n" +
                "name: \"Sushi\",\n" +
                "image: \"https://robata-kaba.jp/wp-content/uploads/2020/02/susi.jpg\"\n" +
                "},\n" +
                "{\n" +
                "name: \"Ramen\",\n" +
                "image: \"https://glebekitchen.com/wp-content/uploads/2017/04/tonkotsuramenfront.jpg\"\n" +
                "}]"
        val foodItemList = arrayListOf(
            FoodItem(
                name = "Sushi",
                image = "https://robata-kaba.jp/wp-content/uploads/2020/02/susi.jpg"
            ),
            FoodItem(
                name = "Ramen",
                image = "https://glebekitchen.com/wp-content/uploads/2017/04/tonkotsuramenfront.jpg"
            )
        )
        Assert.assertEquals(foodItemList, foodItemsListJson.fromPrettyJsonList<FoodItem>())
    }
}
