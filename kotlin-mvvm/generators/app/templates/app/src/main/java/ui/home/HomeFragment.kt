package <%= packageName %>.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import <%= packageName %>.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var model: HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        model?.homeState?.observe(this, Observer { state ->
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
