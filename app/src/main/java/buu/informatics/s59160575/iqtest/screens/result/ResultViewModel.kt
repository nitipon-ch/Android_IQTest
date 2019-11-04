package buu.informatics.s59160575.iqtest.screens.result

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import buu.informatics.s59160575.iqtest.database.GameScoreDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ResultViewModel( val database: GameScoreDatabaseDao, application: Application) : AndroidViewModel(application){

}



