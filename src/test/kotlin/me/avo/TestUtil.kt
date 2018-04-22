package me.avo

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import kotlin.reflect.KClass

object TestUtil

fun parseHtml(html: String): Document = Jsoup.parse(html)

fun <T: Any> KClass<T>.loadResource(name: String) = java.classLoader.getResource(name).readText()

fun getBodyFromHtmlFile(name: String): Element = TestUtil::class.loadResource(name).let(::parseHtml).body()


