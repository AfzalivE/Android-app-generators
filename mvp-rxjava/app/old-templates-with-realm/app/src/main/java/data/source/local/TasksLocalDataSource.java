/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package <%= appPackage %>.data.source.local;

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;

import <%= appPackage %>.data.Task;
import <%= appPackage %>.data.source.TasksDataSource;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Concrete implementation of a data source as a db.
 */
public class TasksLocalDataSource implements TasksDataSource {

    private static TasksLocalDataSource INSTANCE;
    private final RealmConfiguration mConfig;

    private TasksLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        // TODO explore other config options
        mConfig = new RealmConfiguration.Builder(context).build();
    }

    private Realm getRealm() {return Realm.getInstance(mConfig);}

    public static TasksLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TasksLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<? extends List<Task>> getTasks() {
        return getRealm().where(Task.class).findAll().asObservable();
    }

    @Override
    public Observable<Task> getTask(@NonNull String taskId) {
        checkNotNull(taskId);
        return getRealm().where(Task.class).equalTo("mId", taskId).findFirst().asObservable();
    }

    @Override
    public void saveTask(@NonNull Task task) {
        checkNotNull(task);
        getRealm().beginTransaction();
        Task realmTask = getRealm().copyToRealm(task);
        getRealm().commitTransaction();
    }

    @Override
    public void completeTask(@NonNull Task task) {
        checkNotNull(task);
        completeTask(task.getId());
    }

    @Override
    public void completeTask(@NonNull String taskId) {
        checkNotNull(taskId);
        getTask(taskId).doOnNext(task -> {
            getRealm().beginTransaction();
            task.setCompleted(true);
            getRealm().commitTransaction();
        });
    }

    @Override
    public void activateTask(@NonNull Task task) {
        checkNotNull(task);
        activateTask(task.getId());
    }

    @Override
    public void activateTask(@NonNull String taskId) {
        checkNotNull(taskId);
        getTask(taskId).doOnNext(task -> {
            getRealm().beginTransaction();
            task.setCompleted(false);
            getRealm().commitTransaction();
        });
    }

    @Override
    public void clearCompletedTasks() {
        getRealm().where(Task.class).equalTo("mCompleted", true).findAll().deleteAllFromRealm();
    }

    @Override
    public void refreshTasks() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllTasks() {
        getRealm().beginTransaction();
        getRealm().where(Task.class).findAll().deleteAllFromRealm();
        getRealm().commitTransaction();
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        getRealm().beginTransaction();
        getRealm().where(Task.class).equalTo("mId", taskId).findAll().deleteAllFromRealm();
        getRealm().commitTransaction();
    }
}
