package model;

public class Channel {

	protected String name;
	protected Follower[] followers;
	protected String[] videos;
	protected int vidNum;
	protected int folNum;
	
	protected int views= 0;
	protected int watchTime= 0;
	protected int maxWatchTime= 0;

	public Channel(String name, int maxFol, int maxVid) {
		this.name = name;
		this.followers = new Follower[maxFol];
		this.videos = new String[maxVid];
		this.vidNum = 0;
		this.folNum = 0;
		this.views = 0;
		this.watchTime = 0;
		this.maxWatchTime = 0;
	}
	
	public void updateChannelStatistics(int wt) {
		this.views++;
		this.watchTime += wt;
		if(this.maxWatchTime < wt) {
			this.maxWatchTime = wt;
		}	
	}
	
	public void resetStats() {
		this.views = 0;
		this.watchTime = 0;
		this.maxWatchTime = 0;
	}

	public void releaseANewVideo(String vidName) {
		this.videos[this.vidNum] = vidName;
		this.vidNum++;
		
		for(int i = 0 ; i < this.folNum ; i++) {
			this.followers[i].addRecommendedVideo(vidName);
			
		}
	}

	public void follow(Follower f) {
		this.followers[this.folNum] = f;
		this.folNum++;
		f.followChannel(this);
	}

	public void unfollow(Follower f) {
		boolean unfollowed = false;
		for(int i = 0 ; i < this.folNum ; i++) {
			if(f.equals(this.followers[i])) {
				this.followers[i] = null;
				unfollowed = true;
			}
		}

		if(unfollowed) {
			Follower[] tempFoll = new Follower[this.followers.length];
			int temp = 0;

			for(int i = 0 ; i < this.folNum ; i++) {
				if(this.followers[i] != null) {
					tempFoll[temp] = this.followers[i];
					temp++;
				}
			}
			
			this.folNum--;
			this.followers = tempFoll;
			f.unfollowChannel(this);
		}
	}

	public String getName() {
		return this.name;
	}
	
	public Follower[] getFollowers() {
		return this.followers;
	}
	
	public String[] getVideos() {
		return this.videos;
	}
	
	public int getVideoNum() {
		return this.vidNum;
	}
	
	public int getFolNum() {
		return this.folNum;
	}
	
	public int getViews() {
		return this.views;
	}
	
	public int getWatchTime() {
		return this.watchTime;
	}
	
	public int getMaxWatchTime() {
		return this.maxWatchTime;
	}

	
	public String getChannelStatistics() {
		String report = "";
		
		if(this.views > 0) {
			 report += " {#views: " + this.views + 
					 ", max watch time: " + this.maxWatchTime + 
					 ", avg watch time: " + 
					 String.format("%.2f", (double)this.watchTime / (double)this.views) + "}";
		}		
		return report;
	}

	public String toString() {
		String report = "";
		report += this.name + " released";

		if(this.vidNum > 0) {
			report += " <";
			for(int i = 0 ; i < this.vidNum ; i++) {
				if(i > 0) report += ", ";
				report +=  this.videos[i];
			}
			report += ">";
		}else {
			report +=  " no videos";
		}

		if(this.folNum > 0) {
			report += " and is followed by [";
			for(int i = 0 ; i < this.folNum ; i++) {
				if(i > 0) report += ", ";
				report +=  this.followers[i].getName();
			}
			report += "].";
		}else {
			report +=  " and has no followers.";
		}
		return report;
	}

}