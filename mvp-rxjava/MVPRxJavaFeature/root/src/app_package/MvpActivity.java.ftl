package ${packageName}.${subpackageName};

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import ${packageName}.Injection;
import ${packageName}.R;
import ${packageName}.data.source.preference.PreferenceProvider;
import ${packageName}.util.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by afzal on 2016-11-19.
 */

public class ${className}Activity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ${className}Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        ButterKnife.bind(this);

        // do things with preferences
        PreferenceProvider preferenceProvider = Injection.providePreferenceProvider(this);

        fragment = (${className}Fragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        showFragment();
    }

    private void showFragment() {
        if (fragment == null) {
            fragment = ${className}Fragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }
    }
}
