package sport.quiz.skeleton.data.repository

import sport.quiz.skeleton.data.model.PrefixTopic

class PrefixTopicRepository {
    private val topics = listOf<PrefixTopic>(

    )

    fun getTopics(): List<PrefixTopic> = topics
}