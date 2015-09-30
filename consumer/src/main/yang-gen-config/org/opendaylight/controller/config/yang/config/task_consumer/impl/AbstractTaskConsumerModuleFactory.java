/*
* Generated file
*
* Generated from: yang module name: task-consumer-impl yang module local name: task-consumer-impl
* Generated by: org.opendaylight.controller.config.yangjmxgenerator.plugin.JMXGenerator
* Generated at: Tue Sep 29 18:43:10 EDT 2015
*
* Do not modify this file unless it is present under src/main directory
*/
package org.opendaylight.controller.config.yang.config.task_consumer.impl;
@org.opendaylight.yangtools.yang.binding.annotations.ModuleQName(revision = "2014-05-23", name = "task-consumer-impl", namespace = "urn:opendaylight:params:xml:ns:yang:controller:config:task-consumer:impl")

public abstract class AbstractTaskConsumerModuleFactory implements org.opendaylight.controller.config.spi.ModuleFactory {
    public static final java.lang.String NAME = "task-consumer-impl";

    private static final java.util.Set<Class<? extends org.opendaylight.controller.config.api.annotations.AbstractServiceInterface>> serviceIfcs;

    @Override
    public final String getImplementationName() {
        return NAME;
    }

    static {
        java.util.Set<Class<? extends org.opendaylight.controller.config.api.annotations.AbstractServiceInterface>> serviceIfcs2 = new java.util.HashSet<Class<? extends org.opendaylight.controller.config.api.annotations.AbstractServiceInterface>>();
        serviceIfcs2.add(org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerServiceServiceInterface.class);
        serviceIfcs = java.util.Collections.unmodifiableSet(serviceIfcs2);
    }

    @Override
    public final boolean isModuleImplementingServiceInterface(Class<? extends org.opendaylight.controller.config.api.annotations.AbstractServiceInterface> serviceInterface) {
        for (Class<?> ifc: serviceIfcs) {
            if (serviceInterface.isAssignableFrom(ifc)){
                return true;
            }
        }
        return false;
    }

    @Override
    public java.util.Set<Class<? extends org.opendaylight.controller.config.api.annotations.AbstractServiceInterface>> getImplementedServiceIntefaces() {
        return serviceIfcs;
    }

    @Override
    public org.opendaylight.controller.config.spi.Module createModule(String instanceName, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.osgi.framework.BundleContext bundleContext) {
        return instantiateModule(instanceName, dependencyResolver, bundleContext);
    }

    @Override
    public org.opendaylight.controller.config.spi.Module createModule(String instanceName, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.controller.config.api.DynamicMBeanWithInstance old, org.osgi.framework.BundleContext bundleContext) throws Exception {
        org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule oldModule = null;
        try {
            oldModule = (org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule) old.getModule();
        } catch(Exception e) {
            return handleChangedClass(old);
        }
        org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule module = instantiateModule(instanceName, dependencyResolver, oldModule, old.getInstance(), bundleContext);
        module.setRpcRegistry(oldModule.getRpcRegistry());

        return module;
    }

    public org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule instantiateModule(String instanceName, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule oldModule, java.lang.AutoCloseable oldInstance, org.osgi.framework.BundleContext bundleContext) {
        return new org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule(new org.opendaylight.controller.config.api.ModuleIdentifier(NAME, instanceName), dependencyResolver, oldModule, oldInstance);
    }

    public org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule instantiateModule(String instanceName, org.opendaylight.controller.config.api.DependencyResolver dependencyResolver, org.osgi.framework.BundleContext bundleContext) {
        return new org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule(new org.opendaylight.controller.config.api.ModuleIdentifier(NAME, instanceName), dependencyResolver);
    }

    public org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule handleChangedClass(org.opendaylight.controller.config.api.DynamicMBeanWithInstance old) throws Exception {
        throw new UnsupportedOperationException("Class reloading is not supported");
    }

    @Override
    public java.util.Set<org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule> getDefaultModules(org.opendaylight.controller.config.api.DependencyResolverFactory dependencyResolverFactory, org.osgi.framework.BundleContext bundleContext) {
        return new java.util.HashSet<org.opendaylight.controller.config.yang.config.task_consumer.impl.TaskConsumerModule>();
    }

}
