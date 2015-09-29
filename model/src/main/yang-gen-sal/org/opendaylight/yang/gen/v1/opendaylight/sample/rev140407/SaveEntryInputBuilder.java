package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput} instances.
 *
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput
 *
 */
public class SaveEntryInputBuilder implements Builder <org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput> {

    private List<EntryField> _entryField;
    private java.lang.String _entryId;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>> augmentation = new HashMap<>();

    public SaveEntryInputBuilder() {
    }

    public SaveEntryInputBuilder(SaveEntryInput base) {
        this._entryField = base.getEntryField();
        this._entryId = base.getEntryId();
        if (base instanceof SaveEntryInputImpl) {
            SaveEntryInputImpl impl = (SaveEntryInputImpl) base;
            this.augmentation = new HashMap<>(impl.augmentation);
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>) base;
            this.augmentation = new HashMap<>(casted.augmentations());
        }
    }


    public List<EntryField> getEntryField() {
        return _entryField;
    }
    
    public java.lang.String getEntryId() {
        return _entryId;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public SaveEntryInputBuilder setEntryField(List<EntryField> value) {
        this._entryField = value;
        return this;
    }
    
    public SaveEntryInputBuilder setEntryId(java.lang.String value) {
        this._entryId = value;
        return this;
    }
    
    public SaveEntryInputBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public SaveEntryInputBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>> augmentationType) {
        this.augmentation.remove(augmentationType);
        return this;
    }

    public SaveEntryInput build() {
        return new SaveEntryInputImpl(this);
    }

    private static final class SaveEntryInputImpl implements SaveEntryInput {

        public java.lang.Class<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput.class;
        }

        private final List<EntryField> _entryField;
        private final java.lang.String _entryId;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>> augmentation = new HashMap<>();

        private SaveEntryInputImpl(SaveEntryInputBuilder base) {
            this._entryField = base.getEntryField();
            this._entryId = base.getEntryId();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
                case 1:
                    final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>> e = base.augmentation.entrySet().iterator().next();
                    this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public List<EntryField> getEntryField() {
            return _entryField;
        }
        
        @Override
        public java.lang.String getEntryId() {
            return _entryId;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_entryField == null) ? 0 : _entryField.hashCode());
            result = prime * result + ((_entryId == null) ? 0 : _entryId.hashCode());
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
            if (!org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput other = (org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput)obj;
            if (_entryField == null) {
                if (other.getEntryField() != null) {
                    return false;
                }
            } else if(!_entryField.equals(other.getEntryField())) {
                return false;
            }
            if (_entryId == null) {
                if (other.getEntryId() != null) {
                    return false;
                }
            } else if(!_entryId.equals(other.getEntryId())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                SaveEntryInputImpl otherImpl = (SaveEntryInputImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("SaveEntryInput [");
            boolean first = true;
        
            if (_entryField != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_entryField=");
                builder.append(_entryField);
             }
            if (_entryId != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_entryId=");
                builder.append(_entryId);
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
