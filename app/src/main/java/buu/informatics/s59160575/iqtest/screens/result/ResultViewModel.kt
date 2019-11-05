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
    }

    val scoresString = Transformations.map(score) { score ->
        score.toString()
    }

    fun computeIQ(iq: Int) : Int {
        return when (iq) {
            in 0..2 -> 90
            in 3..4 -> 100
            in 5..6 -> 110
            in 7..8 -> 120
            in 9..10 -> 140
            else -> 0
        }
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

    fun insertScore(username: String, score: Int) {
        uiScope.launch {
            val newScore = GameScore()
            newScore.username = username
            newScore.score = score
            insert(newScore)
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



