package ru.referon.testapplication

import ru.referon.testapplication.model.MainModel
import ru.referon.testapplication.model.Result

data class FeedModel(
    val fit: MainModel? = null,
    val result: MutableList<List<Result>>? = null,
    val date: List<String>? = null,
    val error: Boolean = false,
    val loading: Boolean = false
)
