package ${packageName}.${subpackageName};

/**
 * Created by afzal on 2016-11-19.
 */
public interface ${className}Contract {
    interface View {
        void showError(String errorMsg);
        void showMessage(String message);
        void showProgressBar(boolean show);
    }
}
