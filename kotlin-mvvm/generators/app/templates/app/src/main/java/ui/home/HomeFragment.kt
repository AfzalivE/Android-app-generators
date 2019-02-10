package <%= packageName %>.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import com.afzaln.test.R
import com.afzaln.test.ui.home.HomeViewModel.HomeViewState
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    private var model: HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        model?.homeViewState?.observe(this, Observer { state ->
            render(state)
        })
    }

    override fun onResume() {
        super.onResume()
        model?.refreshState()
    }

    private fun render(state: HomeViewState?) {
        when (state) {
            is HomeViewState.Loading -> renderLoading()
            is HomeViewState.Empty   -> renderEmpty()
            is HomeViewState.Error   -> renderError()
            is HomeViewState.Loaded  -> renderLoaded(state)
        }
    }

    private fun renderLoading() {
        TransitionManager.beginDelayedTransition(container)
        progress.visibility = View.VISIBLE
        loaded_container.visibility = View.GONE
        empty.visibility = View.GONE
        error.visibility = View.GONE
    }

    private fun renderEmpty() {
        TransitionManager.beginDelayedTransition(container)
        progress.visibility = View.GONE
        loaded_container.visibility = View.GONE
        empty.visibility = View.VISIBLE
        error.visibility = View.GONE
    }

    private fun renderError() {
        TransitionManager.beginDelayedTransition(container)
        progress.visibility = View.GONE
        loaded_container.visibility = View.GONE
        empty.visibility = View.GONE
        error.visibility = View.VISIBLE
    }

    private fun renderLoaded(state: HomeViewState.Loaded) {
        TransitionManager.beginDelayedTransition(container)
        progress.visibility = View.GONE
        loaded_container.visibility = View.VISIBLE
        empty.visibility = View.GONE
        error.visibility = View.GONE
    }
}
