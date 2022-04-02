package ru.referon.testapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.referon.testapplication.FeedModel
import ru.referon.testapplication.model.*
import ru.referon.testapplication.repository.RepositoryImpl
import java.text.SimpleDateFormat
import java.util.*

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl()
    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data


    fun loadLessons() {

        _data.value = FeedModel(loading = true)
        viewModelScope.launch {
            try {
                val allLessons = repository.getLessons()
                val traners = allLessons.trainers
                val lessons = mutableListOf<Result>()

                for (i in allLessons.lessons.indices) {
                    var tren = ""
                    var timeStart = ""
                    var timeEnd = ""
                    var title = ""
                    var date = ""
                    var local = ""

                    for (j in allLessons.trainers.indices) {

                        if (allLessons.lessons[i].coach_id == traners[j].id) {
                            tren = allLessons.trainers[j].name
                            timeStart = allLessons.lessons[i].startTime
                            timeEnd = allLessons.lessons[i].endTime
                            title = allLessons.lessons[i].tab
                            date = allLessons.lessons[i].date
                            local = allLessons.lessons[i].place

                        } else {
                            timeStart = allLessons.lessons[i].startTime
                            timeEnd = allLessons.lessons[i].endTime
                            title = allLessons.lessons[i].tab
                            date = allLessons.lessons[i].date
                            local = allLessons.lessons[i].place
                        }
                    }
                    lessons.add(Result(title, timeStart, timeEnd, tren, local, date))
                }
                val lessonsSorted = lessons.sortedBy { it.date }
                val pullDate = lessonsSorted.distinctBy { it.date }.map { it.date }

                val listsByDate = mutableListOf<List<Result>>()
                for (i in pullDate.indices) {
                    val b = lessonsSorted.filter { it.date == pullDate[i] }
                    listsByDate.add(b)
                }

                val listsByStartTime = mutableListOf<List<Result>>()
                for (i in listsByDate.indices) {
                    val resultList = listsByDate[i].sortedBy { it.startTime }
                    listsByStartTime.add(resultList)
                }
                val parserDate = SimpleDateFormat("yyyy-MM-dd")
                val formatterDate = SimpleDateFormat("d MMMM", Locale("ru"))
                val dateInFormat = pullDate.map { formatterDate.format(parserDate.parse(it)) }

                _data.value = FeedModel(result = listsByStartTime, date = dateInFormat)
            } catch (e: Exception) {
                _data.value = FeedModel(error = true)
            }
        }
    }
}