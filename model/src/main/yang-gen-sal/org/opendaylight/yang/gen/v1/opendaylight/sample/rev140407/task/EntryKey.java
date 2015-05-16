package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.EntryId;
import org.opendaylight.yangtools.yang.binding.Identifier;


public class EntryKey
 implements Identifier<Entry> {
    private static final long serialVersionUID = -5979597325666883812L;
    private final EntryId _entryId;

    public EntryKey(EntryId _entryId) {
    
    
        this._entryId = _entryId;
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public EntryKey(EntryKey source) {
        this._entryId = source._entryId;
    }


    public EntryId getEntryId() {
        return _entryId;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_entryId == null) ? 0 : _entryId.hashCode());
        return result;
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EntryKey other = (EntryKey) obj;
        if (_entryId == null) {
            if (other._entryId != null) {
                return false;
            }
        } else if(!_entryId.equals(other._entryId)) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toString() {
        java.lang.StringBuilder builder = new java.lang.StringBuilder(org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.EntryKey.class.getSimpleName()).append(" [");
        boolean first = true;
    
        if (_entryId != null) {
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append("_entryId=");
            builder.append(_entryId);
         }
        return builder.append(']').toString();
    }



}

