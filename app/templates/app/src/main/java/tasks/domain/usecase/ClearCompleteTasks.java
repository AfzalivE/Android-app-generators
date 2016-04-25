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

package <%= appPackage %>.tasks.domain.usecase;

import <%= appPackage %>.UseCase;
import <%= appPackage %>.data.source.TasksRepository;

/**
 * Deletes tasks marked as completed.
 */
public class ClearCompleteTasks
        extends UseCase<ClearCompleteTasks.RequestValues, ClearCompleteTasks.ResponseValue> {

    private final TasksRepository mTasksRepository;

    public ClearCompleteTasks(TasksRepository tasksRepository) {
        mTasksRepository = tasksRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        mTasksRepository.clearCompletedTasks();
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static class RequestValues extends UseCase.RequestValues { }

    public class ResponseValue extends UseCase.ResponseValue { }
}
