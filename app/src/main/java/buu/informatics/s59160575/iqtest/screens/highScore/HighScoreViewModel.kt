package buu.informatics.s59160575.iqtest.screens.highScore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import buu.informatics.s59160575.iqtest.database.GameScore
import buu.informatics.s59160575.iqtest.database.GameScoreDatabaseDao
import buu.informatics.s59160575.iqtest.formatScore
import kotlinx.coroutines.*

class HighScoreViewModel (val database: GameScoreDatabaseDao, application: Application) : AndroidViewModel(application){
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var gameScore = MutableLiveData<GameScore?>()

    val score = database.getAllGameScore()


    private val _name = MutableLiveData<List<GameScore>>()
    val name: LiveData<List<GameScore>>
        get() = _name


    init {
        initializeGameScore()
    }

    val scoresString = Transformations.map(score) { score ->
        formatScore(score)
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
