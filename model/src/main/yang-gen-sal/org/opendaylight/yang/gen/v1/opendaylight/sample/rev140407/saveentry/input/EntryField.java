package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.SaveEntryInput;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;task&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/task.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * list entryField {
 *     key     leaf key {
 *         type string;
 *     }
 *     leaf value {
 *         type string;
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;task/saveEntry/input/entryField&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryFieldBuilder}.
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryFieldBuilder
 *
 *
 */
public interface EntryField
    extends
    ChildOf<SaveEntryInput>,
    Augmentable<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.saveentry.input.EntryField>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("opendaylight:sample","2014-04-07","entryField"));

    /**
     * name of the field
     *
     */
    java.lang.String getKey();
    
    /**
     * value of the field
     *
     */
    java.lang.String getValue();

}

