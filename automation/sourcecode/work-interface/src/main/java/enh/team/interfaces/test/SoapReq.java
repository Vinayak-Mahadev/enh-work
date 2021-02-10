package enh.team.interfaces.test;

public class SoapReq 
{
	protected byte[] bytes;

	public SoapReq(byte[] bytes)
	{
		this.bytes = bytes;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
