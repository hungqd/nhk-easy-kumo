package me.avo.kumo.lingq

import org.junit.jupiter.api.Test
import me.avo.kumo.util.loadResource
import me.avo.kumo.util.print

/**
 * Created by Av on 4/22/2017.
 */
internal class ConversionsTest {

    @Test
    fun extractTitle() {
        val json = this::class.loadResource("CourseResponse.json")
        val titles = resToTitle(json)
        titles.print(5)

    }


}