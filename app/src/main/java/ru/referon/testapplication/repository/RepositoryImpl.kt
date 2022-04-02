package ru.referon.testapplication.repository

import ru.referon.testapplication.model.MainModel
import ru.referon.testtask.PostsApi

class RepositoryImpl {

    suspend fun getLessons(): MainModel = PostsApi.retrofitService.getLessons()
}