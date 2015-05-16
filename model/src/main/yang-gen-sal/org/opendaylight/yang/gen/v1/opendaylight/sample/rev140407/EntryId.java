package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407;
import com.google.common.base.Preconditions;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev100924.Uri;
import java.io.Serializable;
import java.beans.ConstructorProperties;


/**
 * An identifier for app entry.
 *
 */
public class EntryId extends Uri
 implements Serializable {
    private static final long serialVersionUID = -5996479465606256162L;

    @ConstructorProperties("value")
    public EntryId(java.lang.String _value) {
        super(_value);
    
    
        Preconditions.checkNotNull(_value, "Supplied value may not be null");
    
    
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public EntryId(EntryId source) {
        super(source);
    }
    /**
     * Creates a new instance from Uri
     *
     * @param source Source object
     */
    public EntryId(Uri source) {
            super(source);
    }

    public static EntryId getDefaultInstance(String defaultValue) {
        return new EntryId(defaultValue);
    }








}

