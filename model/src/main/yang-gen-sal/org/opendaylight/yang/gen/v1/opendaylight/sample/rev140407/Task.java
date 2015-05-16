package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407;
import org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.task.Entry;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import java.util.List;


/**
 * Top-level container for all application database objects.
 *
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;task&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/task.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container task {
 *     list entry {
 *         key "entry-id"
 *         leaf entry-id {
 *             type entry-id;
 *         }
 *         leaf title {
 *             type string;
 *         }
 *         leaf desc {
 *             type string;
 *         }
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;task/task&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.TaskBuilder}.
 * @see org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.TaskBuilder
 *
 */
public interface Task
    extends
    ChildOf<TaskData>,
    Augmentable<org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407.Task>
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("opendaylight:sample","2014-04-07","task"));

    List<Entry> getEntry();

}

