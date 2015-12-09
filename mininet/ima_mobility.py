#!/usr/bin/python

"""
Simple example of Mobility with Mininet
(aka enough rope to hang yourself.)

We move a host from s1 to s2, s2 to s3, and then back to s1.

Gotchas:

The reference controller doesn't support mobility, so we need to
manually flush the switch flow tables!

Good luck!

to-do:

- think about wifi/hub behavior
- think about clearing last hop - why doesn't that work?
"""

from mininet.net import Mininet
from mininet.node import OVSSwitch
from mininet.topo import Topo, LinearTopo
from mininet.log import output, warn
from mininet.node import Controller, OVSKernelSwitch, RemoteController
from mininet.cli import CLI
from mininet.log import setLogLevel, info

from random import randint
import time

class MobilitySwitch( OVSSwitch ):
    "Switch that can reattach and rename interfaces"

    def delIntf( self, intf ):
        "Remove (and detach) an interface"
        port = self.ports[ intf ]
        del self.ports[ intf ]
        del self.intfs[ port ]
        del self.nameToIntf[ intf.name ]

    def addIntf( self, intf, rename=False, **kwargs ):
        "Add (and reparent) an interface"
        OVSSwitch.addIntf( self, intf, **kwargs )
        intf.node = self
        if rename:
            self.renameIntf( intf )

    def attach( self, intf ):
        "Attach an interface and set its port"
        port = self.ports[ intf ]
        if port:
            if self.isOldOVS():
                self.cmd( 'ovs-vsctl add-port', self, intf )
            else:
                self.cmd( 'ovs-vsctl add-port', self, intf,
                          '-- set Interface', intf,
                          'ofport_request=%s' % port )
            self.validatePort( intf )

    def validatePort( self, intf ):
        "Validate intf's OF port number"
        ofport = int( self.cmd( 'ovs-vsctl get Interface', intf,
                                'ofport' ) )
        if ofport != self.ports[ intf ]:
            warn( 'WARNING: ofport for', intf, 'is actually', ofport,
                  '\n' )

    def renameIntf( self, intf, newname='' ):
        "Rename an interface (to its canonical name)"
        intf.ifconfig( 'down' )
        if not newname:
            newname = '%s-eth%d' % ( self.name, self.ports[ intf ] )
        intf.cmd( 'ip link set', intf, 'name', newname )
        del self.nameToIntf[ intf.name ]
        intf.name = newname
        self.nameToIntf[ intf.name ] = intf
        intf.ifconfig( 'up' )

    def moveIntf( self, intf, switch, port=None, rename=True ):
        "Move one of our interfaces to another switch"
        self.detach( intf )
        self.delIntf( intf )
        switch.addIntf( intf, port=port, rename=rename )
        switch.attach( intf )


def printConnections( switches ):
    "Compactly print connected nodes to each switch"
    for sw in switches:
        output( '%s: ' % sw )
        for intf in sw.intfList():
            link = intf.link
            if link:
                intf1, intf2 = link.intf1, link.intf2
                remote = intf1 if intf1.node != sw else intf2
                output( '%s(%s) ' % ( remote.node, sw.ports[ intf ] ) )
        output( '\n' )


def moveHost( host, oldSwitch, newSwitch, newPort=None ):
    "Move a host from old switch to new switch"
    hintf, sintf = host.connectionsTo( oldSwitch )[ 0 ]
    oldSwitch.moveIntf( sintf, newSwitch, port=newPort )
    return hintf, sintf

class MyTopo( Topo ):
    "Simple topology example."

    def __init__( self ):
        "Create custom topo."

        # Initialize topology
        Topo.__init__( self )

        # Add hosts and switches
        h1 = self.addHost( 'h1', ip='10.1/24', mac='00:00:00:00:00:01')
        h2 = self.addHost( 'h2', ip='10.2/24', mac='00:00:00:00:00:02')
        h3 = self.addHost( 'h3', ip='10.3/24', mac='00:00:00:00:00:03')
        h4 = self.addHost( 'h4', ip='10.4/24', mac='00:00:00:00:00:04')
        h5 = self.addHost( 'h5', ip='10.5/24', mac='00:00:00:00:00:05')
        h6 = self.addHost( 'h6', ip='10.6/24', mac='00:00:00:00:00:06')
	# server
        h9 = self.addHost( 'h9', ip='10.9/24', mac='00:00:00:00:00:09')

        s1 = self.addSwitch( 's1' )
        s2 = self.addSwitch( 's2' )
        s3 = self.addSwitch( 's3' )
        s4 = self.addSwitch( 's4' )
        s5 = self.addSwitch( 's5' )
        s6 = self.addSwitch( 's6' )
        s7 = self.addSwitch( 's7' )
        s8 = self.addSwitch( 's8' )
        s9 = self.addSwitch( 's9' )

        # Add links
	# root - cache
        self.addLink( s1, s2 )
        self.addLink( s1, s3 )
	# cache - ap
        self.addLink( s2, s4 )
        self.addLink( s2, s5 )
        self.addLink( s3, s6 )
        self.addLink( s3, s5 )
        self.addLink( s3, s7 )
        self.addLink( s3, s8 )
        self.addLink( s3, s9 )
	# AP - Host
        self.addLink( s1, h9 )
        self.addLink( s4, h1 )
        self.addLink( s5, h2 )
        self.addLink( s6, h3 )
        self.addLink( s7, h4 )
        self.addLink( s8, h5 )
        self.addLink( s9, h6 )


#topos = { 'mytopo': ( lambda: MyTopo() ) }



def mobilityTest():
    "A simple test of mobility"
    print '* Simple mobility test'
    #net = Mininet( topo=LinearTopo( 3 ), switch=MobilitySwitch )
    net = Mininet( topo=MyTopo(), controller=RemoteController, switch=MobilitySwitch )
    c1 = net.addController('c1', controller=RemoteController, ip="10.10.11.44", port=6633)
    #c2 = net.addController('c2', controller=RemoteController, ip="127.0.0.1", port=6633)

    print '* Starting network:'
    net.start()
    printConnections( net.switches )
    print '* Testing network'
    for i in range(3):
        net.pingAll()
    print '* Identifying switch interface for h1'
    h1, old = net.get( 'h1', 's1' )
    #for s in 2, 3, 1:
    ##for s in [2]:
    #    new = net[ 's%d' % s ]
    #    port = randint( 10, 20 )
    #    print '* Moving', h1, 'from', old, 'to', new, 'port', port
    #    hintf, sintf = moveHost( h1, old, new, newPort=port )
    #    print '*', hintf, 'is now connected to', sintf
    #    print '* Clearing out old flows'
    #    #for sw in net.switches:
    #    #    sw.dpctl( 'del-flows' )
    #    print '* New network:'
    #    printConnections( net.switches )
    #    print '* Testing connectivity:'
    #    #time.sleep(1)
    #    for i in range(3):
    #        net.pingAll()
    #    old = new
    #	time.sleep(5)
    CLI(net)
    net.stop()

if __name__ == '__main__':
    mobilityTest()
