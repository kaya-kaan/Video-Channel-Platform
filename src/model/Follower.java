package model;

public class Follower {

	protected String name;
	protected Channel[] followedChannels;

	protected int folNum;
	
	public Follower(String name, int maxChannel) {
		this.name = name;
		this.followedChannels = new Channel[maxChannel];
		this.folNum = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void followChannel(Channel c) {
		this.followedChannels[this.folNum] = c;
		this.folNum++;
	}
	
	public void sync(String n, int wt) {
		
	}
	
	public void unfollowChannel(Channel c) {
		for(int i = 0 ; i < this.folNum ; i++) {
			if(c.equals(this.followedChannels[i])) {
				this.followedChannels[i] = null;
			}
		}

			Channel[] tempChann = new Channel[this.followedChannels.length];
			int temp = 0;

			for(int i = 0 ; i < this.folNum ; i++) {
				if(this.followedChannels[i] != null) {
					tempChann[temp] = this.followedChannels[i];
					temp++;
				}
			}
			
			this.folNum--;
			this.followedChannels = tempChann;	
	}
	
	public void addRecommendedVideo(String v) {
		
	}
	
	
	
}
