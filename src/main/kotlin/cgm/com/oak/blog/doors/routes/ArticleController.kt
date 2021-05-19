package cgm.com.oak.blog.doors.routes

import cgm.com.oak.blog.domain.models.Article1
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class ArticlesController{
    private val articles = listOf(
        Article1(1, "title x", "body of article x"),
        Article1(2, "title y", "body of article y")
    )

    @GetMapping("articles")
    fun getAll(): List<Article1> = articles

    //	@GetMapping("articles/{id}")
//	fun getOne(@PathVariable("id") idArticle: Int): ResponseEntity<cgm.com.oak.blog.domain.models.Article> {
//
//	}
    @GetMapping("articles/{id}")
    fun getOne(@PathVariable id: Int): ResponseEntity<Article1> {
        return articles
            .firstOrNull { it.id == id }
            ?.run { ResponseEntity.ok(this) }
            ?: ResponseEntity.notFound().build()
    }
}


