package sport.quiz.skeleton.data.model

data class PrefixTopic(
    val id: Int,
    val name: String,
    val questions: List<PrefixQuestion>,
)