package cgm.com.oak.blog

import cgm.com.oak.blog.domain.models.Article
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class ArticlesController{
    private val articles = listOf(
        Article(1, "title x", "body of article x"),
        Article(2, "title y", "body of article y")
    )

    @GetMapping("articles")
    fun getAll(): List<Article> = articles

    //	@GetMapping("articles/{id}")
//	fun getOne(@PathVariable("id") idArticle: Int): ResponseEntity<Article> {
//
//	}
    @GetMapping("articles/{id}")
    fun getOne(@PathVariable id: Int): ResponseEntity<Article> {
        return articles
            .firstOrNull { it.id == id }
            ?.run { ResponseEntity.ok(this) }
            ?: ResponseEntity.notFound().build()
    }
}


