package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407;
import org.opendaylight.yangtools.yang.binding.DataRoot;


/**
 * YANG version of the sample app models
 *
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;task&lt;/b&gt;
 * &lt;br&gt;Source path: &lt;i&gt;META-INF/yang/task.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * module task {
 *     yang-version 1;
 *     namespace "opendaylight:sample";
 *     prefix "task";
 *
 *     import ietf-inet-types { prefix "inet"; }
 *     revision 2014-04-07 {
 *         description "YANG version of the sample app models
 *         ";
 *     }
 *
 *     container task {
 *         list entry {
 *             key "entry-id"
 *             leaf entry-id {
 *                 type entry-id;
 *             }
 *             leaf title {
 *                 type string;
 *             }
 *             leaf desc {
 *                 type string;
 *             }
 *         }
 *     }
 *
 *     rpc saveEntry {
 *         " Method to add a new entry into datastore.";
 *         input {
 *             list entryField {
 *                 key     leaf key {
 *                     type string;
 *                 }
 *                 leaf value {
 *                     type string;
 *                 }
 *             }
 *             leaf entryId {
 *                 type string;
 *             }
 *         }
 *         
 *         status CURRENT;
 *     }
 * }
 * &lt;/pre&gt;
 *
 */
public interface TaskData
    extends
    DataRoot
{




    /**
     * Top-level container for all application database objects.
     *
     */
    Task getTask();

}

