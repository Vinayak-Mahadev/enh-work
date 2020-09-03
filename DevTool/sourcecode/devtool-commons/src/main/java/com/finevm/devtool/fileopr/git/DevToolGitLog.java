package com.finevm.devtool.fileopr.git;

public class DevToolGitLog
{
	private String _id;
	private String authName;
	private String authEmail;
	private String commitTime;
	private String commitMsg;
	
	public DevToolGitLog(String _id, String authName, String authEmail, String commitTime, String commitMsg) {
		super();
		this._id = _id;
		this.authName = authName;
		this.authEmail = authEmail;
		this.commitTime = commitTime;
		this.commitMsg = commitMsg;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthEmail() {
		return authEmail;
	}

	public void setAuthEmail(String authEmail) {
		this.authEmail = authEmail;
	}

	public String getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}

	public String getCommitMsg() {
		return commitMsg;
	}

	public void setCommitMsg(String commitMsg) {
		this.commitMsg = commitMsg;
	}

	@Override
	public String toString() {
		return "GitLog [_id=" + _id + ", authName=" + authName + ", authEmail=" + authEmail + ", commitTime="
				+ commitTime + ", commitMsg=" + commitMsg + "]";
	}
	

	

}
