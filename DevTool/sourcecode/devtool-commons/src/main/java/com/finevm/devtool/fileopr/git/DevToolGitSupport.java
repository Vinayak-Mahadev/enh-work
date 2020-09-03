package com.finevm.devtool.fileopr.git;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.UnsupportedCredentialItem;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.CredentialItem;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.TransportHttp;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.json.JSONObject;

import com.jcraft.jsch.Session;

class DevToolGitSupport
{

	private Git git;
	private String log;
	private final String url      ;
	private final String dirName  ;
	private final String username ;
	private final String password ;
	
	
	
	public String getUrl() {
		return url;
	}
	public String getDirName() {
		return dirName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	

	/**
	 * <pre>Provide the git JSONObject like this <br/>
	 *       {
	 *        "username": "vinay.nagaraj@enhancesys.com",
	 *        "password": "*****",
	 *        "url": "appuser@192.168.2.227:/home/appuser/snoc/snd-integration",
	 *        "localDir": "D:/interface/backend/d4s3/snd-integration"
	 *        } 
    </pre>	
	 * @param gitConfig
	 * @author Vinayak Mahadev
	 */
	public  DevToolGitSupport(JSONObject gitConfig)
	{
		url      = gitConfig.getString("url");
		dirName  = gitConfig.getString("localDir");
		username = gitConfig.getString("username");
		password = gitConfig.getString("password");

		
	}
	public  DevToolGitSupport()
	{
		url      = null;
		dirName  = null;
		username = null;
		password = null;
	}
	
	public String  diffCommit(Repository repo, String hashID) throws IOException {

		RevCommit newCommit = null;
		ObjectId  oId       = null;;
		String    data      = null;
		try
		{
			//Initialize repositories.
			git = new Git(repo);

			//Get the commit you are looking for.
			try (RevWalk walk = new RevWalk(repo)) {
				oId = repo.resolve(hashID);
				newCommit = walk.parseCommit(oId);
			}
			log = "LogCommit: " + newCommit+"\n";

			log = log+newCommit.getFullMessage()+"\n";

			//Print diff of the commit with the previous one.
			log = log+getDiffOfCommit(repo , newCommit)+"\n";

			data = log;
		} 
		catch (Exception e) 
		{
			data = "No data found for this hashID  :::  "+hashID ;
			throw e;
		}
		finally 
		{
			if(git!=null){
				git.close();
			}
			newCommit = null;   
			oId       = null;
			log       = null;   
			git       = null;
			repo      = null;
			hashID    = null;
		}
		return data;

	}
	//Helper gets the diff as a string.
	public String getDiffOfCommit(Repository repo, RevCommit newCommit) throws IOException {

		//Get commit that is previous to the current one.
		RevCommit oldCommit = getPrevHash(repo, newCommit);
		if(oldCommit == null){
			return "Start of repo";
		}
		//Use treeIterator to diff.
		AbstractTreeIterator oldTreeIterator = getCanonicalTreeParser(oldCommit);
		AbstractTreeIterator newTreeIterator = getCanonicalTreeParser(newCommit);
		OutputStream outputStream = new ByteArrayOutputStream();
		try (DiffFormatter formatter = new DiffFormatter(outputStream)) {
			formatter.setRepository(git.getRepository());
			formatter.format(oldTreeIterator, newTreeIterator);
		}
		String diff = outputStream.toString();
		return diff;
	}
	//Helper function to get the previous commit.
	public RevCommit getPrevHash(Repository repo , RevCommit commit)  throws  IOException {

		try (RevWalk walk = new RevWalk(repo)) {
			// Starting point
			walk.markStart(commit);
			int count = 0;
			for (RevCommit rev : walk) {
				// got the previous commit.
				if (count == 1) {
					return rev;
				}
				count++;
			}
			walk.dispose();
		}
		//Reached end and no previous commits.
		return null;
	}
	//Helper function to get the tree of the changes in a commit. Written by Rüdiger Herrmann
	public AbstractTreeIterator getCanonicalTreeParser(ObjectId commitId) throws IOException {
		try (RevWalk walk = new RevWalk(git.getRepository())) {
			RevCommit commit = walk.parseCommit(commitId);
			ObjectId treeId = commit.getTree().getId();
			try (ObjectReader reader = git.getRepository().newObjectReader()) {
				return new CanonicalTreeParser(null, reader, treeId);
			}
		}
	}
	
	
	
	
	
	public TransportConfigCallback getTransportConfigCallback()throws Exception {
		final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
			@Override
			protected void configure(OpenSshConfig.Host host, Session session) {
				session.setPassword(password);
			}
		};
		return new TransportConfigCallback() {

			public void configure(Transport transport) {
				if (transport instanceof TransportHttp)
					return;
				SshTransport sshTransport = (SshTransport) transport;
				sshTransport.setSshSessionFactory(sshSessionFactory);
			}
		};
	}

	public CredentialsProvider   getCredentialsProvider()throws Exception{

		final char[] pass = password.toCharArray();

		return new CredentialsProvider() {

			@Override
			public boolean supports(CredentialItem... items) {
				return true;
			}

			@Override
			public boolean isInteractive() {
				return true;
			}

			@Override
			public boolean get(URIish uri, CredentialItem... items)
					throws UnsupportedCredentialItem {

				for (CredentialItem item : items) {
					if (item instanceof CredentialItem.Password) {
						((CredentialItem.Password) item).setValue(pass);
						continue;
					}

					if (item instanceof CredentialItem.Username) {
						((CredentialItem.Username) item).
						setValue(new String(username));
						continue;
					}

					if (item instanceof CredentialItem.StringType) {
						((CredentialItem.StringType) item).
						setValue(new String("YOUR_PASSPHRASE"));
						continue;
					}
					if (item instanceof CredentialItem.YesNoType) {
						((CredentialItem.YesNoType) item).
						setValue(true);
						continue;
					}
				}
				return true;
			}
		};


	}
	
	
}
