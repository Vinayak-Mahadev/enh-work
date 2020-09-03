package com.finevm.devtool.fileopr.git;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.lib.IndexDiff.StageState;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.json.JSONObject;

/**
 * <p>This is  main  class for git operations..</p>
 * @author Vinayak Mahadev
 *
 */
public class DevToolGit {
	private final static Logger logger = Logger.getLogger(DevToolGit.class);
	private final DevToolGitSupport gitSupport;
	private Git  git;
	private File dir;

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
	public DevToolGit(JSONObject gitConfig){

		gitSupport  = new DevToolGitSupport(gitConfig);
		logger.info("GitOperations init ...");

	}



	/**
	 * <p>This method pull your git code from remote to local ...</p>
	 * @return status;
	 * @throws Exception
	 * @author Vinayak Mahadev
	 */
	public boolean gitPull() throws Exception {
		boolean status = false;
		try 
		{
			dir      = new File(gitSupport.getDirName());
			git      = Git.open(dir);

			logger.info("gitPull invoked with :: "+git);
			status   = git.pull()
					.setCredentialsProvider(gitSupport.getCredentialsProvider())
					.setTransportConfigCallback(gitSupport.getTransportConfigCallback())
					.call()
					.isSuccessful();
			logger.info("gitPull done..");
		} 
		catch(Exception e){
			status =  false;
			logger.error(e.getMessage()+ " "+e);
			throw e;
		}
		finally 
		{
			if(git!=null)
				git.close();
			git    = null;
			dir    = null;
		}
		return status;
	}



	/**
	 * <p>This method Revert your git code from remote to local ...</p>
	 * @return status;
	 * @throws Exception
	 * @author Vinayak Mahadev
	 */
	public boolean gitRevert() throws Exception {

		boolean responce = false;
		try 
		{
			dir      = new File(gitSupport.getDirName());
			git      = Git.open(dir);


			Status status = git.status().call();
			System.out.println("hasUncommittedChanges    :    "+status.hasUncommittedChanges());
		} 
		catch(Exception e){
			responce =  false;
			logger.error(e.getMessage()+ " "+e);
			throw e;
		}
		finally 
		{
			if(git!=null)
				git.close();
			git    = null;
			dir    = null;
		}
		return responce;

	}
	
	public boolean gitMsg() throws Exception
	{

		boolean responce = false;
		try 
		{
			dir      = new File(gitSupport.getDirName());
			git      = Git.open(dir);


			/*System.out.println();
			Set<String> set = git.getRepository().getAllRefs().keySet();
			RevertCommand revCommand = null;
			for (String string : set) {
				revCommand = git.revert().include(git.getRepository().getAllRefs().get(string));
			}

			revCommand.call();
			 */


			Status status = git.status().call();
			System.out.println("hasUncommittedChanges    :    "+status.hasUncommittedChanges());
			Set<String> conflicting = status.getConflicting();
			for(String conflict : conflicting) {
				System.out.println("Conflicting: " + conflict);
			}

			Set<String> added = status.getAdded();
			for(String add : added) {
				System.out.println("Added: " + add);
			}

			Set<String> changed = status.getChanged();
			for(String change : changed) {
				System.out.println("Change: " + change);
			}

			Set<String> missing = status.getMissing();
			for(String miss : missing) {
				System.out.println("Missing: " + miss);
			}

			Set<String> modified = status.getModified();
			for(String modify : modified) {
				System.out.println("Modification: " + modify);
			}

			Set<String> removed = status.getRemoved();
			for(String remove : removed) {
				System.out.println("Removed: " + remove);
			}

			Set<String> uncommittedChanges = status.getUncommittedChanges();
			for(String uncommitted : uncommittedChanges) {
				System.out.println("Uncommitted: " + uncommitted);
			}

			Set<String> untracked = status.getUntracked();
			for(String untrack : untracked) {
				System.out.println("Untracked: " + untrack);
			}

			Set<String> untrackedFolders = status.getUntrackedFolders();
			for(String untrack : untrackedFolders) {
				System.out.println("Untracked Folder: " + untrack);
			}

			Map<String, StageState> conflictingStageState = status.getConflictingStageState();
			for(Map.Entry<String, StageState> entry : conflictingStageState.entrySet()) {
				System.out.println("ConflictingState: " + entry);
			}

			responce = true;
			//			status   = git.pull()
			//					.setCredentialsProvider(gitSupport.getCredentialsProvider())
			//					.setTransportConfigCallback(gitSupport.getTransportConfigCallback())
			//					.call()
			//					.isSuccessful();
		} 
		catch(Exception e){
			responce =  false;
			logger.error(e.getMessage()+ " "+e);
			throw e;
		}
		finally 
		{
			if(git!=null)
				git.close();
			git    = null;
			dir    = null;
		}
		return responce;

	}

	/**
	 * <p>This method Revert your git code from remote to local ...</p>
	 * @return status;
	 * @throws Exception
	 * @author Vinayak Mahadev
	 */
	public boolean gitUntracked() throws Exception {
		boolean responce = false;
		Status status = null;
		List<String> untrack = null;
		try 
		{
			dir      = new File(gitSupport.getDirName());
			git      = Git.open(dir);


			status = git.status().call();
			untrack = new ArrayList<>();
			untrack.addAll(status.getUntracked());
			untrack.addAll(status.getUntrackedFolders());

			for(String track : untrack) {
				System.out.println("Untracked    : " + track);
			}



			responce = true;
			//			status   = git.pull()
			//					.setCredentialsProvider(gitSupport.getCredentialsProvider())
			//					.setTransportConfigCallback(gitSupport.getTransportConfigCallback())
			//					.call()
			//					.isSuccessful();
		} 
		catch(Exception e){
			responce =  false;
			logger.error(e.getMessage()+ " "+e);
			throw e;
		}
		finally 
		{

			if(git!=null)
				git.close();
			git    = null;
			dir    = null;
			status = null;
			untrack       = null;

		}
		return responce;
	}




	/**
	 * <p>This method get recent logs...</p>
	 * `
	 * @param limit
	 * @return list
	 * @throws Exception
	 * @author Vinayak Mahadev
	 */
	public List<DevToolGitLog> showLogs(int limit) throws Exception{

		DevToolGitLog              gLog      = null;
		RevCommit           rev       = null;
		PersonIdent         person    = null;
		List<DevToolGitLog>        list      = null;
		Iterator<RevCommit> iterator  = null;
		int i = 0;

		try 
		{
			list   = new ArrayList<DevToolGitLog>();
			dir    = new File(gitSupport.getDirName());
			git    = Git.open(dir);

			iterator = git.log()
					.call().iterator();

			if(iterator!=null){
				while(iterator.hasNext()) {
					i++;
					if(i==limit){
						i=0;
						break;
					}
					rev   = iterator.next();
					person = rev.getAuthorIdent();

					gLog  = new DevToolGitLog(
							rev.toObjectId().getName(), 
							person.getName(), 
							person.getEmailAddress(), 
							person.getWhen().toString(), 
							rev.getShortMessage()
							);
					list.add(gLog);
				}
			}
		} 
		catch (Exception e)
		{
			logger.error(e.getMessage()+ " "+e);
			throw e;
		}
		finally 
		{
			if(git!=null)
				git.close();

			git   = null;
			dir   = null;
			gLog  = null;
			rev   = null;             
			person= null;             
			iterator  = null;  
		}
		return list;
	}



	/**
	 * <p>This method give log record for perticular given hashID,
	 * If hashID not there it returns msg like No data found for this hashID</p>
	 * @param hashID
	 * @return
	 * @throws Exception
	 */
	public String getLogDetails(String hashID) throws Exception{
		String     data    = null;
		try 
		{
			dir     = new File(gitSupport.getDirName());
			git     = Git.open(dir);

			data    = gitSupport.diffCommit(git.getRepository(), hashID);
			data = data +"\n-----------------------------------------------------------------------------------------------------------------------------------------";
		}
		catch (Exception e) 
		{
			data = "No data found for this hashID  :::  "+hashID ;
			logger.error(e.getMessage()+ " "+e);
			throw e;
		}
		finally 
		{
			if(git!=null)
				git.close();
			git     = null;
			dir     = null;
		}
		return data;
	}



}
