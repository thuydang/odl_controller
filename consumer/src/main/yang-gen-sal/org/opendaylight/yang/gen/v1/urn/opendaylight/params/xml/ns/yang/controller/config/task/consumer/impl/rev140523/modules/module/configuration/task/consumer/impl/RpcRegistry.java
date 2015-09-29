package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.task.consumer.impl;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.ServiceRef;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.rev130405.modules.Module;


/**
 * &lt;p&gt;This class represents the following YANG schema fragment defined in module &lt;b&gt;task-consumer-impl&lt;/b&gt;
 * &lt;br&gt;(Source path: &lt;i&gt;META-INF/yang/task-consumer-impl.yang&lt;/i&gt;):
 * &lt;pre&gt;
 * container rpc-registry {
 *     leaf type {
 *         type leafref;
 *     }
 *     leaf name {
 *         type leafref;
 *     }
 *     uses service-ref {
 *         refine (urn:opendaylight:params:xml:ns:yang:controller:config:task-consumer:impl?revision=2014-05-23)type {
 *             leaf type {
 *                 type leafref;
 *             }
 *         }
 *     }
 * }
 * &lt;/pre&gt;
 * The schema path to identify an instance is
 * &lt;i&gt;task-consumer-impl/modules/module/configuration/(urn:opendaylight:params:xml:ns:yang:controller:config:task-consumer:impl?revision=2014-05-23)task-consumer-impl/rpc-registry&lt;/i&gt;
 *
 * &lt;p&gt;To create instances of this class use {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.task.consumer.impl.RpcRegistryBuilder}.
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.task.consumer.impl.RpcRegistryBuilder
 *
 */
public interface RpcRegistry
    extends
    ChildOf<Module>,
    Augmentable<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.controller.config.task.consumer.impl.rev140523.modules.module.configuration.task.consumer.impl.RpcRegistry>,
    ServiceRef
{



    public static final QName QNAME = org.opendaylight.yangtools.yang.common.QName.cachedReference(org.opendaylight.yangtools.yang.common.QName.create("urn:opendaylight:params:xml:ns:yang:controller:config:task-consumer:impl","2014-05-23","rpc-registry"));


}

