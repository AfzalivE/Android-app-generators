package ${packageName}.${subpackageName};

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import ${packageName}.R;
import ${packageName}.util.BaseFragment;
import ${packageName}.util.PresenterFactory;

import butterknife.BindView;

/**
 * Created by afzal on 2016-11-19.
 */
public class ${className}Fragment extends BaseFragment<${className}Presenter, ${className}Contract.View> implements ${className}Contract.View {
    private ${className}Presenter presenter;

    @BindView(R.id.message)
    TextView messageV;

    // Initializer methods

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get any intent extras here ...
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize adapters etc here ...
    }

    // View Contract methods

    @Override
    public void showMessage(String message) {
        messageV.setText(message);
    }

    @Override
    public void showError(String errorMsg) {
        Snackbar.make(getView(), errorMsg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(boolean show) {
        // show progress bar here ...
    }

    // Internal methods

    // Overridden base methods

    @Override
    protected PresenterFactory<${className}Presenter> getPresenterFactory() {
        return new ${className}PresenterFactory();
    }

    @Override
    protected void onPresenterPrepared(${className}Presenter presenter) {
        this.presenter = presenter;
        // load initial data from presenter here ...
        presenter.doSomething();
    }

    // static creation method with layout

    public static ${className}Fragment newInstance() {
        ${className}Fragment fragment = new ${className}Fragment();
        // This layout is inflated in BaseFragment
        fragment.setLayout(R.layout.${subpackageName}_fragment);

        return fragment;
    }
}
