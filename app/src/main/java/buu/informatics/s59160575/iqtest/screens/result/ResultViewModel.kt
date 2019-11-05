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

    private var tonight = MutableLiveData<GameScore?>()

    init {
        initializeGameScore()
    }

    private fun initializeGameScore() {
        uiScope.launch {
            tonight.value = getGameScoreFromDatabase()
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
}



