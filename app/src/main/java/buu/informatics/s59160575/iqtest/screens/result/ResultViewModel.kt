package buu.informatics.s59160575.iqtest.screens.result

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import buu.informatics.s59160575.iqtest.database.GameScore
import buu.informatics.s59160575.iqtest.database.GameScoreDatabaseDao
import kotlinx.coroutines.*

class ResultViewModel( val database: GameScoreDatabaseDao, application: Application) : AndroidViewModel(application){
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var gameScore = MutableLiveData<GameScore?>()

    private val score = database.getAllGameScore()

    init {
        initializeGameScore()
        onStartTracking()
    }

    val scoresString = Transformations.map(score) { score ->
        score.toString()
    }

    private fun initializeGameScore() {
        uiScope.launch {
            gameScore.value = getGameScoreFromDatabase()
        }
    }

    private suspend fun getGameScoreFromDatabase(): GameScore? {
        return withContext(Dispatchers.IO) {
            var score = database.getGameScore()
            score
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onStartTracking() {
        uiScope.launch {
            val newNight = GameScore()
//            newNight.username = "What da duck"
            insert(newNight)
            gameScore.value = getGameScoreFromDatabase()
        }
    }

    fun clearDatabase(){
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun insert(score: GameScore) {
        withContext(Dispatchers.IO) {
            database.insert(score)
        }
    }


}



