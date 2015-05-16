package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.List;
import org.opendaylight.yangtools.yang.binding.Augmentation;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task} instances.
 *
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task
 *
 */
public class TaskBuilder implements Builder <org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task> {

    private List<Entry> _entry;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>> augmentation = new HashMap<>();

    public TaskBuilder() {
    }

    public TaskBuilder(Task base) {
        this._entry = base.getEntry();
        if (base instanceof TaskImpl) {
            TaskImpl impl = (TaskImpl) base;
            this.augmentation = new HashMap<>(impl.augmentation);
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>) base;
            this.augmentation = new HashMap<>(casted.augmentations());
        }
    }


    public List<Entry> getEntry() {
        return _entry;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public TaskBuilder setEntry(List<Entry> value) {
        this._entry = value;
        return this;
    }
    
    public TaskBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public TaskBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>> augmentationType) {
        this.augmentation.remove(augmentationType);
        return this;
    }

    public Task build() {
        return new TaskImpl(this);
    }

    private static final class TaskImpl implements Task {

        public java.lang.Class<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task.class;
        }

        private final List<Entry> _entry;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>> augmentation = new HashMap<>();

        private TaskImpl(TaskBuilder base) {
            this._entry = base.getEntry();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
                case 1:
                    final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>> e = base.augmentation.entrySet().iterator().next();
                    this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public List<Entry> getEntry() {
            return _entry;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_entry == null) ? 0 : _entry.hashCode());
            result = prime * result + ((augmentation == null) ? 0 : augmentation.hashCode());
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
            if (!org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task other = (org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task)obj;
            if (_entry == null) {
                if (other.getEntry() != null) {
                    return false;
                }
            } else if(!_entry.equals(other.getEntry())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                TaskImpl otherImpl = (TaskImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("Task [");
            boolean first = true;
        
            if (_entry != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_entry=");
                builder.append(_entry);
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
