package com.gklijs.adventofcode.test;

import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

public class TestSchedulerExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback, ParameterResolver {

    private TestScheduler testScheduler = new TestScheduler();

    @Override
    public void beforeTestExecution(final ExtensionContext extensionContext) throws Exception {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.single());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.single());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.single());
    }

    @Override
    public void afterTestExecution(final ExtensionContext extensionContext) throws Exception {
        RxJavaPlugins.reset();
    }

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == TestScheduler.class;
    }

    @Override
    public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) {
        return testScheduler;
    }
}
