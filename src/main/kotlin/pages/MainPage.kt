package pages

import Application
import currentDate
import data.Headline
import getDate
import getIdFromUrl
import getText
import getUrl
import org.jsoup.Jsoup
import org.jsoup.nodes.Element


class MainPage : Page<List<Headline>> {

    override val name = "Main_$currentDate.html"
    override val url = Application.mainUrl

    override fun get(): List<Headline> {
        val text = load()
        val body = Jsoup.parse(text).body()

        val top = getTopNews(body)
        val list = getNewsList(body)
        return list + top
    }

    fun getTopNews(body: Element): Headline {
        val topNews = body.getElementById("topnews")
        return topNews.extractHeadline(true)
    }

    fun getNewsList(body: Element): List<Headline> = body.getElementById("topnewslist")
            .getElementsByTag("ul").first()
            .getElementsByTag("li") // all list items
            .map {
                it.extractHeadline(false)
            }


    fun Element.extractHeadline(isTop: Boolean): Headline {
        val headingStyle = if (isTop) "h2" else "h3"
        val link = this
                .getElementsByTag(headingStyle).first()
                .getElementsByTag("a").first()
        val url = link.getUrl()
        val title = link.getText()
        val date = this.getDate()
        val id = getIdFromUrl(url)
        return Headline(id, title, date, url)
    }


}