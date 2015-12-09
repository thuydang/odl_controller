from mininet.topo import Topo
from mininet.net import Mininet 
from mininet.link import Link 
from mininet.util import quietRun
from mininet.log import setLogLevel, info
from mininet.node import Controller, RemoteController, OVSController
from mininet.node import CPULimitedHost, Host, Node 
from mininet.node import OVSKernelSwitch, UserSwitch
from mininet.util import dumpNodeConnections
from mininet.cli import CLI 
from mininet.log import setLogLevel, info
from mininet.link import TCLink, Intf
from subprocess import call
setLogLevel('info')
net = Mininet(link=TCLink)

### Servers
Host1 = net.addHost('h1',cls=Host,ip="10.0.0.1")
Host2 = net.addHost('h2',cls=Host,ip="10.0.0.2")
Host3 = net.addHost('h3',cls=Host,ip="10.0.0.3")
Host4 = net.addHost('h4',cls=Host,ip="10.0.0.4")
Host5 = net.addHost('h5',cls=Host,ip="10.0.0.5")
Host6 = net.addHost('h6',cls=Host,ip="10.0.0.6")
Host7 = net.addHost('h7',cls=Host,ip="10.0.0.7")
Host8 = net.addHost('h8',cls=Host,ip="10.0.0.8")


#Clients

Host9 = net.addHost('h9',ip="10.0.0.9")



#Adding Switch

Switch1 = net.addSwitch('s1')
Switch2 = net.addSwitch('s2')
Switch3 = net.addSwitch('s3')
Switch4 = net.addSwitch('s4')
Switch5 = net.addSwitch('s5')
Switch6 = net.addSwitch('s6')
Switch7 = net.addSwitch('s7')

linksw400 = dict(bw=400)
linksw100 = dict(bw=100)
linkclients = dict(bw=100)
linkservers = dict(bw=100)

#Links between switches and hosts

net.addLink(Host1,Switch3,port1=13,port2=31, **linkservers)
net.addLink(Host5,Switch3,port1=53,port2=35, **linkservers)

net.addLink(Host2,Switch4,port1=24,port2=42, **linkservers)
net.addLink(Host6,Switch4,port1=64,port2=46, **linkservers)

net.addLink(Host3,Switch5,port1=35,port2=53, **linkservers)
net.addLink(Host7,Switch5,port1=75,port2=57, **linkservers)

net.addLink(Host4,Switch6,port1=46,port2=64, **linkservers)
net.addLink(Host5,Switch6,port1=56,port2=65, **linkservers)

net.addLink(Host9,Switch7,port1=97,port2=79, **linkclients)


# Links between switches

net.addLink(Switch2,Switch4, port1=240,port2=420,intf1Name='s24-eth240',intf2Name='s42-eth420', **linksw100)
net.addLink(Switch2,Switch5, port1=250,port2=520,intf1Name='s25-eth250',intf2Name='s52-eth520', **linksw100)
net.addLink(Switch2,Switch6, port1=260,port2=620,intf1Name='s26-eth260',intf2Name='s62-eth620', **linksw100)
net.addLink(Switch2,Switch3, port1=230,port2=320,intf1Name='s23-eth230',intf2Name='s32-eth320', **linksw100)

net.addLink(Switch1,Switch4, port1=140,port2=410,intf1Name='s14-eth140',intf2Name='s41-eth410', **linksw100)
net.addLink(Switch1,Switch5, port1=150,port2=510,intf1Name='s15-eth150',intf2Name='s51-eth510', **linksw100)
net.addLink(Switch1,Switch6, port1=160,port2=610,intf1Name='s16-eth160',intf2Name='s61=eth610', **linksw100)
net.addLink(Switch1,Switch3, port1=130,port2=310,intf1Name='s13-eth150',intf2Name='s51-eth510', **linksw100)
net.addLink(switch7,Switch1, port1=710,port2=170,intf1Name='s71-eth710',intf2Name='s17-eth170', **linksw400)
net.addLink(switch7,Switch2, port1=720,port2=270,intf1Name='s72-eth720',intf2Name='s27-eth270', **linksw400)

net.addController('c0', controller=RemoteController,ip='10.10.10.135',port=6633)

net.build()
net.start()

CLI(net)

net.stop()