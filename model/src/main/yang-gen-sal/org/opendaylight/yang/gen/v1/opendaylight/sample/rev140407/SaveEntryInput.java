package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import java.util.List;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;task&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/task.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container input {
 *     list entryField {
 *         key     leaf key {
 *             type string;
 *         }
 *         leaf value {
 *             type string;
 *         }
 *     }
 *     leaf entryId {
 *         type string;
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;task/saveEntry/input&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInputBuilder}.
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInputBuilder
 *
 */
public interface SaveEntryInput
    extends
    DataObject,
    Augmentable<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("opendaylight:sample","2014-04-07","input"));

    List<EntryField> getEntryField();
    
    /**
     * entry Identifier
     *
     */
    java.lang.String getEntryId();

}

