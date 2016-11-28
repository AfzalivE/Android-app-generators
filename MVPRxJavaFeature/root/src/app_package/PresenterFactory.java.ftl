package ${packageName}.${subpackageName};

import android.content.Context;

import ${packageName}.Injection;
import ${packageName}.util.PresenterFactory;

/**
 * Created by afzal on 2016-11-19.
 */
class ${className}PresenterFactory extends PresenterFactory<${className}Presenter> {
    @Override
    public ${className}Presenter create(Context context) {
        return new ${className}Presenter(
                Injection.provideDataRepository(context)
        );
    }
}
