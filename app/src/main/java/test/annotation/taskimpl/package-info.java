@CreateService(name = BuildConfig.MAIN_SERVICE_NAME, taskimpl = "test.annotation.taskimpl.Task", tag = BuildConfig.MAIN_SERVICE_TAG)
package test.annotation.taskimpl;

import tools.android.taskimpl.annotation.CreateService;