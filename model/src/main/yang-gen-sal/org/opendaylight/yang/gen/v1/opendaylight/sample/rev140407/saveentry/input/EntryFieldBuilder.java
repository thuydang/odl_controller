package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField} instances.
 *
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField
 *
 */
public class EntryFieldBuilder implements Builder <org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField> {

    private java.lang.String _key;
    private java.lang.String _value;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>> augmentation = Collections.emptyMap();

    public EntryFieldBuilder() {
    }

    public EntryFieldBuilder(EntryField base) {
        this._key = base.getKey();
        this._value = base.getValue();
        if (base instanceof EntryFieldImpl) {
            EntryFieldImpl impl = (EntryFieldImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getKey() {
        return _key;
    }
    
    public java.lang.String getValue() {
        return _value;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public EntryFieldBuilder setKey(java.lang.String value) {
        this._key = value;
        return this;
    }
    
    public EntryFieldBuilder setValue(java.lang.String value) {
        this._value = value;
        return this;
    }
    
    public EntryFieldBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public EntryFieldBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public EntryField build() {
        return new EntryFieldImpl(this);
    }

    private static final class EntryFieldImpl implements EntryField {

        public java.lang.Class<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField.class;
        }

        private final java.lang.String _key;
        private final java.lang.String _value;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>> augmentation = Collections.emptyMap();

        private EntryFieldImpl(EntryFieldBuilder base) {
            this._key = base.getKey();
            this._value = base.getValue();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.String getKey() {
            return _key;
        }
        
        @Override
        public java.lang.String getValue() {
            return _value;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + ((_key == null) ? 0 : _key.hashCode());
            result = prime * result + ((_value == null) ? 0 : _value.hashCode());
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
            if (!org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField other = (org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField)obj;
            if (_key == null) {
                if (other.getKey() != null) {
                    return false;
                }
            } else if(!_key.equals(other.getKey())) {
                return false;
            }
            if (_value == null) {
                if (other.getValue() != null) {
                    return false;
                }
            } else if(!_value.equals(other.getValue())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                EntryFieldImpl otherImpl = (EntryFieldImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("EntryField [");
            boolean first = true;
        
            if (_key != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_key=");
                builder.append(_key);
             }
            if (_value != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_value=");
                builder.append(_value);
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
