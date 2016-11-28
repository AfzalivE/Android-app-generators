package ${packageName}.${subpackageName};

import android.support.annotation.NonNull;

import ${packageName}.data.source.DataRepository;
import ${packageName}.${subpackageName}.${className}Contract.View;
import ${packageName}.util.BasePresenter;

import rx.Observable;
import rx.Observer;

import static ${packageName}.util.RxUtils.applySchedulers;

/**
 * Created by afzal on 2016-11-19.
 */
class ${className}Presenter implements BasePresenter<View> {
    private final DataRepository dataRepository;
    private View view;

    ${className}Presenter(@NonNull DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    void doSomething() {
        showProgressBar(true);
        // dataRepository.getPosts()
        Observable.just("call repository method here")
                .compose(applySchedulers())
                .subscribe(updateViewObserver);
    }

    // internal methods

    private void showMessage(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    private void showError(Throwable throwable) {
        if (view != null) {
            view.showError(throwable.getMessage());
        }
    }


    private void showProgressBar(boolean show) {
        if (view != null) {
            view.showProgressBar(show);
        }
    }

    /**
     * The observer that updates the view based on the view state, or shows an error
     */
    private Observer<String> updateViewObserver = new Observer<String>() {
        @Override
        public void onCompleted() {
            // onCompleted

        }

        @Override
        public void onError(Throwable throwable) {
            // onError
            showError(throwable);
        }

        @Override
        public void onNext(String message) {
            // onNext
            showMessage(message);
            showProgressBar(false);
        }
    };


    // Overridden base methods

    @Override
    public void onViewAttached(View view, boolean autoLoad) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void onDestroyed() {
        // clean up listeners, unsubscribe from data here
    }
}
