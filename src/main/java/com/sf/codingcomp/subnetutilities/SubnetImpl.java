package com.sf.codingcomp.subnetutilities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class SubnetImpl implements Subnet {
	
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
			// couldn't figure out the math in time, so ugly bitwise operations it is!
			switch (maskdiff) {
			case 0:
				break;
			case 1:
				this.subnet[i] = (byte) (this.subnet[i] & 254);
				maskdiff -= 1;
				break;
			case 2:
				this.subnet[i] = (byte) (this.subnet[i] & 252);
				maskdiff -= 2;
				break;
			case 3:
				this.subnet[i] = (byte) (this.subnet[i] & 248);
				maskdiff -= 3;
				break;
			case 4:
				this.subnet[i] = (byte) (this.subnet[i] & 240);
				maskdiff -= 4;
				break;
			case 5:
				this.subnet[i] = (byte) (this.subnet[i] & 224);
				maskdiff -= 5;
				break;
			case 6:
				this.subnet[i] = (byte) (this.subnet[i] & 192);
				maskdiff -= 6;
				break;
			case 7:
				this.subnet[i] = (byte) (this.subnet[i] & 128);
				maskdiff -= 7;
				break;
			default:
				this.subnet[i] = 0;
				maskdiff -= 8;
				break;
			}
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
