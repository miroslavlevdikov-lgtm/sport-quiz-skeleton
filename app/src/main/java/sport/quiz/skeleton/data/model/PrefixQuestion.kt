package sport.quiz.skeleton.data.model

data class PrefixQuestion(
    val id: Int,
    val text: String,
    val imageUrl: String? = null,
    val rightAnswerId: Int,
    val answers: List<PrefixAnswer>,
)