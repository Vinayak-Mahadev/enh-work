package com.finevm.enh.util;

import java.util.Set;

/**<p>
 * This class can you get from Builder class.
 * This class mainly used for to set email body part
 * using fields as to*,from*,cc*,ccList*,bcc*,bccList*,text, subject, fileLocation and fileLocationList. Fields had Setter and Getter.
 * <br><b>Note : </b> * Fields any one you should set the value otherwise mail will not send.
 * </p>
 * @author VINAY N<br>
 * vinay.nagaraj@enhancesys.com
 */

public class EmailMessage {

	private String to;
	private String cc;
	private String bcc;
	private Set<String> toSet;
	private Set<String> ccSet;
	private Set<String> bccSet;
	private String text;
	private String subject;
	private String fileLocation;
	private Set<String> fileLocationSet;

	private EmailMessage(BuildEmail buildEmail){
		if((buildEmail.to == null) && (buildEmail.bccSet == null) && (buildEmail.ccSet == null) && (buildEmail.toSet == null)){

			if((buildEmail.to == null ))
				System.out.print("Your 'TO' is empty..");
			else if (buildEmail.bccSet == null) 
				System.out.print("Your 'BCC' is empty..");
			else if (buildEmail.toSet == null) 
				System.out.print("Your 'To Set' is empty..");
			else if( buildEmail.ccSet == null)
				System.out.print("Your 'CC' is empty..");
			System.out.println(" So you cann't send the mail.. :-(");
		}
		else{
			to = 		buildEmail.to;
			cc = 		buildEmail.cc;
			bcc= 		buildEmail.bcc;
			toSet = 	buildEmail.toSet;
			ccSet = 	buildEmail.ccSet;
			bccSet = 	buildEmail.bccSet;
			text = 		buildEmail.text;
			subject = 	buildEmail.subject;
			fileLocation =  buildEmail.fileLocation;
			fileLocationSet =   buildEmail.fileLocationSet;
			buildEmail = null;
		}

	}


	public static class BuildEmail{

		private String to;
		private String cc;
		private String bcc;
		private Set<String> toSet;
		private Set<String> ccSet;
		private Set<String> bccSet;
		private String text;
		private String subject;
		private String fileLocation;
		private Set<String> fileLocationSet;

		public EmailMessage build() {
			return new EmailMessage(this);
		}

		public BuildEmail setTo(String to) {
			this.to = to;
			return this;
		}

		public BuildEmail setCc(String cc) {
			this.cc = cc;
			return this;
		}

		public BuildEmail setBcc(String bcc) {
			this.bcc = bcc;
			return this;
		}

		public BuildEmail setToSet(Set<String> toSet) {
			this.toSet = toSet;
			return this;
		}

		public BuildEmail setCcSet(Set<String> ccSet) {
			this.ccSet = ccSet;
			return this;
		}

		public BuildEmail setBccSet(Set<String> bccSet) {
			this.bccSet = bccSet;
			return this;
		}

		public BuildEmail setText(String text) {
			this.text = text;
			return this;
		}

		public BuildEmail setSubject(String subject) {
			this.subject = subject;
			return this;
		}

		public BuildEmail setFileLocation(String fileLocation) {
			this.fileLocation = fileLocation;
			return this;
		}

		public BuildEmail setFileLocationSet(Set<String> fileLocationSet) {
			this.fileLocationSet = fileLocationSet;
			return this;
		}



	}




	public String getTo() {
		return to;
	}


	public String getCc() {
		return cc;
	}


	public String getBcc() {
		return bcc;
	}


	public Set<String> getToSet() {
		return toSet;
	}


	public Set<String> getCcSet() {
		return ccSet;
	}


	public Set<String> getBccSet() {
		return bccSet;
	}


	public String getText() {
		return text;
	}


	public String getSubject() {
		return subject;
	}


	public String getFileLocation() {
		return fileLocation;
	}


	public Set<String> getFileLocationSet() {
		return fileLocationSet;
	}


	@Override
	public String toString() {
		return "EmailMessage [to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", toSet=" + toSet + ", ccSet=" + ccSet
				+ ", bccSet=" + bccSet + ", text=" + text + ", subject=" + subject + ", fileLocation=" + fileLocation
				+ ", fileLocationSet=" + fileLocationSet + "]";
	}



}
