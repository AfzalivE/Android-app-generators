package ${packageName}.ui.${subpackageName}

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ${className}ViewModel : ViewModel() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = Job()

    /**
     * This is the scope for all coroutines launched by [${className}ViewModel].
     *
     * Since we pass [viewModelJob], you can cancel all coroutines
     * launched by [viewModelScope] by calling viewModelJob.cancel().
     * This is called in [onCleared].
     */
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    internal val ${subpackageName}ViewState = MutableLiveData<${className}ViewState>()

    fun refreshState() {
      ${subpackageName}ViewState.value = ${className}ViewState.Loading

      viewModelScope.launch {
          // some 2 second async task
          delay(2000)
          ${subpackageName}ViewState.value = ${className}ViewState.Loaded
      }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

     sealed class ${className}ViewState {
        object Loading : ${className}ViewState()
        object Empty : ${className}ViewState()
        object Error : ${className}ViewState()
        object Loaded : ${className}ViewState()
    }
}
