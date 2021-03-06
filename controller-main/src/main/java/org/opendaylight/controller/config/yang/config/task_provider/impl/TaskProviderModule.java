package org.opendaylight.controller.config.yang.config.task_provider.impl;

import de.dailab.nemo.ima.controller.app.ImaController;
import org.opendaylight.controller.config.spi.Module;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker;
import org.opendaylight.controller.sal.binding.api.NotificationProviderService;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskProviderModule extends org.opendaylight.controller.config.yang.config.task_provider.impl.AbstractTaskProviderModule {
    private static final Logger log = LoggerFactory.getLogger(TaskProviderModule.class);

    public TaskProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver) {
        super(identifier, dependencyResolver);
    }

    public TaskProviderModule(org.opendaylight.controller.config.api.ModuleIdentifier identifier, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.controller.config.yang.config.task_provider.impl.TaskProviderModule oldModule, AutoCloseable oldInstance) {
        super(identifier, dependencyResolver, oldModule, oldInstance);
    }

    @Override
    public void customValidation() {
        // add custom validation form module attributes here.
    }

    @Override
    public AutoCloseable createInstance() {
        final ImaController appProvider = new ImaController();

        DataBroker dataBrokerService = getDataBrokerDependency();
        appProvider.setDataService(dataBrokerService);

        RpcProviderRegistry rpcRegistryDependency = getRpcRegistryDependency();
				appProvider.setRpcProviderRegistry(rpcRegistryDependency);
				// As provider registration:
        final BindingAwareBroker.RpcRegistration<TaskService> rpcRegistration =
                                rpcRegistryDependency
                                    .addRpcImplementation(TaskService.class, appProvider);
				// Get services to consume from registry:
        //TaskService service = getRpcRegistryDependency().getRpcService(TaskService.class);

        //retrieves the notification service for publishing notifications
        NotificationProviderService notificationService = getNotificationServiceDependency();
				appProvider.setNotificationProviderService(notificationService);

				appProvider.start();

        // Wrap toaster as AutoCloseable and close registrations to md-sal at
        // close()
        final class CloseResources implements AutoCloseable {

            @Override
            public void close() throws Exception {
                //rpcRegistration.close();
                appProvider.close();
                log.info("ImaController (instance {}) torn down.", this);
            }
        }

        AutoCloseable ret = new CloseResources();
        log.info("ImaController (instance {}) initialized.", ret);
        return ret;
    }

		/**
    @Override
    public boolean canReuse(Module m){
        return true;
    }
		*/

}
