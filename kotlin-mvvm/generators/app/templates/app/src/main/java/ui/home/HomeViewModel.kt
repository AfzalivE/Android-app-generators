package <%= packageName %>.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = Job()

    /**
     * This is the scope for all coroutines launched by [HomeViewModel].
     *
     * Since we pass [viewModelJob], you can cancel all coroutines launched by [viewModelScope] by calling
     * viewModelJob.cancel().  This is called in [onCleared].
     */
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    internal val homeViewState = MutableLiveData<HomeViewState>()

    fun refreshState() {
      homeViewState.value = HomeViewState.Loading

      viewModelScope.launch {
          // some 2 second async task
          delay(2000)
          homeViewState.value = HomeViewState.Loaded
      }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

     sealed class HomeViewState {
        object Loading : HomeViewState()
        object Empty : HomeViewState()
        object Error : HomeViewState()
        object Loaded : HomeViewState()
    }
}
