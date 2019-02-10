package ${packageName}.ui.${subpackageName};

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import ${packageName}.R
import ${packageName}.ui.${subpackageName}.${className}ViewModel.${className}ViewState
import kotlinx.android.synthetic.main.${subpackageName}_fragment.*

class ${className}Fragment : Fragment() {

    private var model: ${className}ViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.${subpackageName}_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(this).get(${className}ViewModel::class.java)
        model?.${subpackageName}ViewState?.observe(this, Observer { state ->
            render(state)
        })
    }

    override fun onResume() {
        super.onResume()
        model?.refreshState()
    }

    private fun render(state: ${className}ViewState?) {
        when (state) {
            is ${className}ViewState.Loading -> renderLoading()
            is ${className}ViewState.Empty   -> renderEmpty()
            is ${className}ViewState.Error   -> renderError()
            is ${className}ViewState.Loaded  -> renderLoaded(state)
        }
    }

    private fun renderLoading() {
        TransitionManager.beginDelayedTransition(loaded_container)
        progress.visibility = View.VISIBLE
        loaded_container.visibility = View.GONE
        empty.visibility = View.GONE
        error.visibility = View.GONE
    }

    private fun renderEmpty() {
        TransitionManager.beginDelayedTransition(loaded_container)
        progress.visibility = View.GONE
        loaded_container.visibility = View.GONE
        empty.visibility = View.VISIBLE
        error.visibility = View.GONE
    }

    private fun renderError() {
        TransitionManager.beginDelayedTransition(loaded_container)
        progress.visibility = View.GONE
        loaded_container.visibility = View.GONE
        empty.visibility = View.GONE
        error.visibility = View.VISIBLE
    }

    private fun renderLoaded(state: ${className}ViewState.Loaded) {
        TransitionManager.beginDelayedTransition(loaded_container)
        progress.visibility = View.GONE
        loaded_container.visibility = View.VISIBLE
        empty.visibility = View.GONE
        error.visibility = View.GONE
    }
}
