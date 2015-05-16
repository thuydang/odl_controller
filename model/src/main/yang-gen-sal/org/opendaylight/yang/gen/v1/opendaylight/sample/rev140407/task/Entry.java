package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.EntryId;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.binding.Augmentable;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;task&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/task.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * list entry {
 *     key "entry-id"
 *     leaf entry-id {
 *         type entry-id;
 *     }
 *     leaf title {
 *         type string;
 *     }
 *     leaf desc {
 *         type string;
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;task/task/entry&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.EntryBuilder}.
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.EntryBuilder
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.EntryKey
 *
 */
public interface Entry
    extends
    ChildOf<Task>,
    Augmentable<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry>,
    Identifiable<EntryKey>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("opendaylight:sample","2014-04-07","entry"));

    /**
     * identifier of single list of entries.
     *
     */
    EntryId getEntryId();
    
    java.lang.String getTitle();
    
    java.lang.String getDesc();
    
    /**
     * Returns Primary Key of Yang List Type
     *
     */
    EntryKey getKey();

}

