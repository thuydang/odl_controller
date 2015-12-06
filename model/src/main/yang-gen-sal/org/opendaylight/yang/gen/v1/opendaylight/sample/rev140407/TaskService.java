package org.opendaylight.yang.gen.v1.opendaylight.sample.rev140407;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import java.util.concurrent.Future;


/**
 * Interface for implementing the following YANG RPCs defined in module &lt;b&gt;task&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/task.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * rpc saveEntry {
 *     " Method to add a new entry into datastore.";
 *     input {
 *         list entryField {
 *             key     leaf key {
 *                 type string;
 *             }
 *             leaf value {
 *                 type string;
 *             }
 *         }
 *         leaf entryId {
 *             type string;
 *         }
 *     }
 *     
 *     status CURRENT;
 * }
 * &lt;/pre&gt;
 *
 */
public interface TaskService
    extends
    RpcService
{




    /**
     * Method to add a new entry into datastore.
     *
     */
    Future<RpcResult<java.lang.Void>> saveEntry(SaveEntryInput input);

}

