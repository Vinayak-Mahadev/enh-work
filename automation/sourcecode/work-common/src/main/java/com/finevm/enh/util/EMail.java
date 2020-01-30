package com.finevm.enh.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.JSONObject;

/**
 * <p>You can get this Email class object from method as setFromUser(final Properties properties) only. This class is used to send email with to, cc, bcc and attachment</p>
 * 
 * @see EmailMessage
 * @author VINAY N <br>
 * vinay.nagaraj@enhancesys.com
 *
 */
public class EMail {

	private  Session session;

	private static EMail eMail;

	public static JSONObject user = null;

	/**
	 * <p>	This method accept properties and It create Email object with session 
	 * and returns Email reference. That reference can used for 
	 * to call method is  Email(EmailMessage emailMessage).<li><b>
	 * Note : </b>The Properties example <br>
	 * mail.username  = vinayakmahadev.nm@gmail.com  <br>             
	 * mail.password  = xxxxxxxxx   <br>
	 * mail.smtp.port = 465    <br>
	 * mail.smtp.auth = true  <br>
	 * mail.smtp.host = smtp.gmail.com <br>
	 * mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory <br><br></li></p>
	 * 
	 * @param properties
	 * @return Email
	 */

	public  static EMail setUser(JSONObject userList, String senduser) {
		if(EMail.user == null)
			EMail.user = userList; 

		if(user != null){
			final String username  = user.getJSONObject(senduser.toString()).getString("mail.username");
			final String password  = user.getJSONObject(senduser.toString()).getString("mail.password");

			Properties properties = new Properties();

			properties.put("mail.username",  user.getJSONObject(senduser.toString()).getString("mail.username"));
			properties.put("mail.password",  user.getJSONObject(senduser.toString()).getString("mail.password"));
			properties.put("mail.smtp.port",  user.getJSONObject(senduser.toString()).getString("mail.smtp.port"));
			properties.put("mail.smtp.auth",  user.getJSONObject(senduser.toString()).getString("mail.smtp.auth"));
			properties.put("mail.smtp.socketFactory.class",  user.getJSONObject(senduser.toString()).getString("mail.smtp.socketFactory.class"));
			properties.put("mail.smtp.host",  user.getJSONObject(senduser.toString()).getString("mail.smtp.host"));


			if(eMail==null){
				eMail = new EMail();
				eMail.session = Session.getDefaultInstance(properties, 
						new javax.mail.Authenticator() { 

					protected PasswordAuthentication  
					getPasswordAuthentication() { 
						return new PasswordAuthentication(username,	password); 
					} 
				}); 
				properties = null;			
			}
			return eMail;
		}
		return eMail;
	}

	/**
	 * <pre>EmailMessage must contain fields as from*, (to* or toList*)</pre>
	 * <pre>Optional fields as subject, text, cc,bcc,ccList, bccList and (fileLocationSet or fileLocation)</pre>
	 * <p><b>Note : </b> Invalid email id or ids are removed then only the email msg process  will be done then it returns true. If Exception or  after email validation  (to,cc,bcc,toList,ccList,bccList) are all empty then method will return false.</p>
	 * @param emailMessage
	 * @see EmailMessage
	 */
	public boolean sendMail(EmailMessage emailMessage){
		Set<String> toSet  = new HashSet<String>();
		Set<String> ccSet  = new HashSet<String>();
		Set<String> bccSet = new HashSet<String>();
		Set<String> fileLocationSet = new HashSet<String>();


		//to List
		if(emailMessage.getTo() != null)
			toSet.add(emailMessage.getTo());

		if(emailMessage.getToSet() != null)
			toSet.addAll(emailMessage.getToSet());

		//to cc
		if(emailMessage.getCc() != null)
			ccSet.add(emailMessage.getCc());

		if(emailMessage.getCcSet() != null)
			ccSet.addAll(emailMessage.getCcSet());


		//to bcc
		if(emailMessage.getBcc() != null)
			bccSet.add(emailMessage.getBcc());

		if(emailMessage.getBccSet() != null)
			bccSet.addAll(emailMessage.getBccSet());

		//to fileLocationSet
		if(emailMessage.getFileLocation() != null)
			fileLocationSet.add(emailMessage.getFileLocation());

		if(emailMessage.getFileLocationSet() != null)
			fileLocationSet.addAll(emailMessage.getFileLocationSet());

		toSet = isValidEmailAddress(toSet);
		ccSet = isValidEmailAddress(ccSet);
		bccSet = isValidEmailAddress(bccSet);

		if(fileLocationSet!= null)
			fileLocationSet = isValidFileLocation(fileLocationSet);

		if(!toSet.isEmpty() || !ccSet.isEmpty() || !bccSet.isEmpty()){

			try { 
				MimeMessage message = new MimeMessage(eMail.session); 
				Multipart multipart = new MimeMultipart();

				DataSource source = null;
				BodyPart messageBodyPart = null;


				System.out.println(toSet);
				//to
				if(!toSet.isEmpty())
					for(String to : toSet)
						message.addRecipient(Message.RecipientType.TO,  
								new InternetAddress(to)); 				
				//cc
				if(! ccSet.isEmpty())
					for (String cc : ccSet) 
						message.addRecipient(Message.RecipientType.CC,  
								new InternetAddress(cc));
				//bcc
				if(! bccSet.isEmpty())
					for (String bcc : bccSet) 
						message.addRecipient(Message.RecipientType.BCC,  
								new InternetAddress(bcc));
				//subject
				if(emailMessage.getSubject() != null)
					message.setSubject(emailMessage.getSubject()); 

				//text
				if(emailMessage.getText() != null){

					messageBodyPart = new MimeBodyPart();
					// Now set the actual message
					messageBodyPart.setText(emailMessage.getText());

					// Set text message part
					multipart.addBodyPart(messageBodyPart);
				}
				if(!fileLocationSet.isEmpty())
					for(String filename : fileLocationSet){

						messageBodyPart = new MimeBodyPart();
						source = new FileDataSource(filename);
						messageBodyPart.setDataHandler(new DataHandler(source));
						//set Attachment name
						messageBodyPart.setFileName(filename.substring(filename.lastIndexOf("/")+1, filename.length()));
						multipart.addBodyPart(messageBodyPart);
						//add with body
						multipart.addBodyPart(messageBodyPart);
					}

				// Send the complete message parts
				message.setContent(multipart);

				// Send message 
				Transport.send(message); 
				return true;
			} 
			catch (MessagingException mex) { 
				mex.printStackTrace(); 
				return false;
			} 
		}

		else{
			System.out.println("Invaild email has given..");
			return false;
		} 
	}


	/**
	 * <p>This method accepts Set of emailId in form of String format. It check one by one
	 * with regular expression like <br><br>^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$<br><br>
	 * If it's matches that mailId added to new Set<String>. Then finally the valid emailIds returns in form of Set<String> <br>
	 * <br>
	 * <b>Note: </b>This method works like the invalid emailId are removed from Set. So you didn't get invalid emailIds details and This method only works with in the class because It is private method</p> 
	 * 
	 * @param emailSet
	 * @return Set<String>
	 * 
	 * 
	 */
	private  Set<String> isValidEmailAddress(final Set<String> emailSet) {

		Set<String> emailSetOrginalCopy = new HashSet<>();
		boolean result = true;
		for(String email : emailSet){
			try {
				result = true;
				String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
				java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
				java.util.regex.Matcher m = p.matcher(email);
				result =  m.matches();
			} catch (ConcurrentModificationException ex) {
				result = false;

			}
			if(result==true){
				emailSetOrginalCopy.add(email);
			}
			else{
				System.out.println(email + " deleted..");
			}
		}
		return emailSetOrginalCopy;
	}

	/**
	 * <p>The @param Set contains file location's paths. In this method file path check by
	 *  {@code new FileInputStream(new File(fileLocation))} If given 
	 * path file isn't  there it throw Exception. If it didn't throw Exception then that
	 *  path added with {@code Set<String> filelocationOrginalCopy = new HashSet<>();}
	 * then it returns filelocationOrginalCopy.</p>
	 * @param fileLocationSet
	 * @return Set<String>
	 */
	@SuppressWarnings("resource")
	private   Set<String> isValidFileLocation(final Set<String> fileLocationSet) {
		Set<String> filelocationOrginalCopy = new HashSet<>();
		for (String fileLocation : fileLocationSet) {
			try {
				new FileInputStream( new File(fileLocation));
				filelocationOrginalCopy.add(fileLocation);					
			} catch (Exception e) {
				System.out.println(fileLocation +" is deleted");
			}
		}

		return filelocationOrginalCopy;
	}
}