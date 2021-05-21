package enh.team.interfaces.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class AvengersApp
{
	public static void main(String[] args) 
	{
		List<String> list = new ArrayList<String>();
		list.add("VINAY N");
		list.add("SENTHAMARAI M");
		list.add("KALAIVANAN S");
		list.add("SHANTHOSH S");
		list.add("RUPESHKUMAR K");
		list.add("SATHISH R");
		list.add("PRAKASHKUMAR S");
		list.add("MAHADENRAN");
		list.add("GNANASEKAR");

		System.out.println(list.get(getRandomNumber(0, list.size()-1)));

		
		System.out.println(Base64.encodeBase64String(("usrconsoadev:Koneksipidev2020!").getBytes()));
	}

	public static int getRandomNumber(int min, int max) 
	{
		return (int) ((Math.random() * (max - min)) + min);
	}
}
