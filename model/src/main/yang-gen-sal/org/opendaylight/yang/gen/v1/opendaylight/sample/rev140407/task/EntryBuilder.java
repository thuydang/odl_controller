package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.EntryId;
import java.util.Collections;
import java.util.Map;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.Augmentation;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry} instances.
 *
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry
 *
 */
public class EntryBuilder implements Builder <org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry> {

    private java.lang.String _desc;
    private EntryId _entryId;
    private EntryKey _key;
    private java.lang.String _title;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>> augmentation = Collections.emptyMap();

    public EntryBuilder() {
    }

    public EntryBuilder(Entry base) {
        if (base.getKey() == null) {
            this._key = new EntryKey(
                base.getEntryId()
            );
            this._entryId = base.getEntryId();
        } else {
            this._key = base.getKey();
            this._entryId = _key.getEntryId();
        }
        this._desc = base.getDesc();
        this._title = base.getTitle();
        if (base instanceof EntryImpl) {
            EntryImpl impl = (EntryImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getDesc() {
        return _desc;
    }
    
    public EntryId getEntryId() {
        return _entryId;
    }
    
    public EntryKey getKey() {
        return _key;
    }
    
    public java.lang.String getTitle() {
        return _title;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public EntryBuilder setDesc(java.lang.String value) {
        this._desc = value;
        return this;
    }
    
    public EntryBuilder setEntryId(EntryId value) {
        if (value != null) {
        }
        this._entryId = value;
        return this;
    }
    
    public EntryBuilder setKey(EntryKey value) {
        this._key = value;
        return this;
    }
    
    public EntryBuilder setTitle(java.lang.String value) {
        this._title = value;
        return this;
    }
    
    public EntryBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public EntryBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public Entry build() {
        return new EntryImpl(this);
    }

    private static final class EntryImpl implements Entry {

        public java.lang.Class<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry.class;
        }

        private final java.lang.String _desc;
        private final EntryId _entryId;
        private final EntryKey _key;
        private final java.lang.String _title;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>> augmentation = Collections.emptyMap();

        private EntryImpl(EntryBuilder base) {
            if (base.getKey() == null) {
                this._key = new EntryKey(
                    base.getEntryId()
                );
                this._entryId = base.getEntryId();
            } else {
                this._key = base.getKey();
                this._entryId = _key.getEntryId();
            }
            this._desc = base.getDesc();
            this._title = base.getTitle();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.String getDesc() {
            return _desc;
        }
        
        @Override
        public EntryId getEntryId() {
            return _entryId;
        }
        
        @Override
        public EntryKey getKey() {
            return _key;
        }
        
        @Override
        public java.lang.String getTitle() {
            return _title;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>> E getAugmentation(java.lang.Class<E> augmentationType) {
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
            result = prime * result + ((_desc == null) ? 0 : _desc.hashCode());
            result = prime * result + ((_entryId == null) ? 0 : _entryId.hashCode());
            result = prime * result + ((_key == null) ? 0 : _key.hashCode());
            result = prime * result + ((_title == null) ? 0 : _title.hashCode());
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
            if (!org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry other = (org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry)obj;
            if (_desc == null) {
                if (other.getDesc() != null) {
                    return false;
                }
            } else if(!_desc.equals(other.getDesc())) {
                return false;
            }
            if (_entryId == null) {
                if (other.getEntryId() != null) {
                    return false;
                }
            } else if(!_entryId.equals(other.getEntryId())) {
                return false;
            }
            if (_key == null) {
                if (other.getKey() != null) {
                    return false;
                }
            } else if(!_key.equals(other.getKey())) {
                return false;
            }
            if (_title == null) {
                if (other.getTitle() != null) {
                    return false;
                }
            } else if(!_title.equals(other.getTitle())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                EntryImpl otherImpl = (EntryImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>>, Augmentation<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>> e : augmentation.entrySet()) {
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
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("Entry [");
            boolean first = true;
        
            if (_desc != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_desc=");
                builder.append(_desc);
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
            if (_key != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_key=");
                builder.append(_key);
             }
            if (_title != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_title=");
                builder.append(_title);
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
