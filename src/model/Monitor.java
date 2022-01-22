package model;

public class Monitor extends Follower {
	
	public Monitor(String name, int maxChannel) {
		super(name, maxChannel);
	}
	
	

	public void followChannel(Channel c) {
		this.followedChannels[this.folNum] = c;
		this.followedChannels[this.folNum].views = 0;
		this.followedChannels[this.folNum].watchTime = 0;
		this.followedChannels[this.folNum].maxWatchTime = 0;
		this.folNum++;
	}

	public String getName() {
		return "Monitor " + this.name;
	}

	public String toString() {
		String report = "";
		report += "Monitor " + this.name;

		if(this.folNum > 0) {
			report += " follows [";
			for(int i = 0 ; i < this.folNum ; i++) {
				if(i > 0) report += ", ";
				report +=  this.followedChannels[i].getName() 
						+ this.followedChannels[i].getChannelStatistics();
			}
			report += "].";
		}else {
			report +=  " follows no channels.";
		}
		return report;
	}
}