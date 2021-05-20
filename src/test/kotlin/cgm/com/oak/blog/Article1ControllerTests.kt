package cgm.com.oak.blog


import cgm.com.oak.blog.domain.models.Article1
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [BlogApplication::class])
@AutoConfigureMockMvc
class Article1ControllerTests(
    @Autowired private val mockMvc: MockMvc
) {

    private val mapper = jacksonObjectMapper()

    val articles  = listOf(
        Article1(1,"title x","body of article x"),
        Article1(2,"title y","body of article y")
    )
    @Test
    fun `can get all articles`() {

        mockMvc.get("/api/articles")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(mapper.writeValueAsString(articles))}
            }
    }

    @Test
    fun `can get one articles`() {

        val id = 1
        val expected = articles.first { it.id == id	 }
        mockMvc.get("/api/articles/1")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(mapper.writeValueAsString(expected))}
            }
    }

    @Test
    fun `not found article `() {
        mockMvc.get("/api/articles/99999")
            .andExpect {
                status { isNotFound() }
            }
    }
}