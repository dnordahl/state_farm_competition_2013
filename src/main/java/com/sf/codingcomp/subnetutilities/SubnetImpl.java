package com.sf.codingcomp.subnetutilities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class SubnetImpl implements Subnet {
	
	public static void main(String[] args) throws UnknownHostException, InvalidMaskException {
		new SubnetImpl(InetAddress.getByName("10.0.0.0"), 24);
	}
	
	private byte[] subnet;
	private int mask;
	private InetAddress original;
	
	public SubnetImpl(InetAddress subnet, int mask) throws InvalidMaskException {
		if (mask < 1 || mask > 32) // bro are you even trying? 1-32 or no networking for you.
			throw new InvalidMaskException();
		
		this.original = subnet;
		this.subnet = subnet.getAddress();
		this.mask = mask;
		
		int maskdiff = 32 - mask;
		
		// adjust for subnet
		for (int i = 3; i >= 0 && maskdiff > 0; i--)
		{
			int thisiter = maskdiff % 9;
			this.subnet[i] -= Math.pow(2,thisiter);
			maskdiff -= 8;
		}
		
		
	}
	
	public boolean isIPInRange(InetAddress ipAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	public InetAddress getNetworkAddress() {
		// TODO Auto-generated method stub
		try {
			return InetAddress.getByAddress(subnet);
		} catch (UnknownHostException e) {
			return original;
		}
	}

	public InetAddress getBroadcastAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<InetAddress> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getAddressCount() {
		return (int)Math.pow(2, 32 - mask);
	}	
}
