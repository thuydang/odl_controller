package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import java.util.HashMap;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.task.consumer.impl.RpcRegistry;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.Augmentation;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl
 *
 */
public class TaskConsumerImplBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl> {

    private RpcRegistry _rpcRegistry;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>> augmentation = Collections.emptyMap();

    public TaskConsumerImplBuilder() {
    }

    public TaskConsumerImplBuilder(TaskConsumerImpl base) {
        this._rpcRegistry = base.getRpcRegistry();
        if (base instanceof TaskConsumerImplImpl) {
            TaskConsumerImplImpl impl = (TaskConsumerImplImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public RpcRegistry getRpcRegistry() {
        return _rpcRegistry;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public TaskConsumerImplBuilder setRpcRegistry(RpcRegistry value) {
        this._rpcRegistry = value;
        return this;
    }
    
    public TaskConsumerImplBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public TaskConsumerImplBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public TaskConsumerImpl build() {
        return new TaskConsumerImplImpl(this);
    }

    private static final class TaskConsumerImplImpl implements TaskConsumerImpl {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl.class;
        }

        private final RpcRegistry _rpcRegistry;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>> augmentation = Collections.emptyMap();

        private TaskConsumerImplImpl(TaskConsumerImplBuilder base) {
            this._rpcRegistry = base.getRpcRegistry();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public RpcRegistry getRpcRegistry() {
            return _rpcRegistry;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_rpcRegistry == null) ? 0 : _rpcRegistry.hashCode());
            result = prime * result + ((augmentation == null) ? 0 : augmentation.hashCode());
        
            hash = result;
            hashValid = true;
            return result;
        }

        @Override
        public boolean equals(java.lang.Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DataObject)) {
                return false;
            }
            if (!org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl other = (org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl)obj;
            if (_rpcRegistry == null) {
                if (other.getRpcRegistry() != null) {
                    return false;
                }
            } else if(!_rpcRegistry.equals(other.getRpcRegistry())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                TaskConsumerImplImpl otherImpl = (TaskConsumerImplImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.TaskConsumerImpl>> e : augmentation.entrySet()) {
                    if (!e.getValue().equals(other.getAugmentation(e.getKey()))) {
                        return false;
                    }
                }
                // .. and give the other one the chance to do the same
                if (!obj.equals(this)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public java.lang.String toString() {
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("TaskConsumerImpl [");
            boolean first = true;
        
            if (_rpcRegistry != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_rpcRegistry=");
                builder.append(_rpcRegistry);
             }
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append("augmentation=");
            builder.append(augmentation.values());
            return builder.append(']').toString();
        }
    }

}
