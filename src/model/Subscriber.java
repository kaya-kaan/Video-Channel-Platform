package model;

public class Subscriber extends Follower {

	protected String[] videosRecomended;
	protected int vidNum;

	public Subscriber(String name, int maxChannel, int maxVid) {
		super(name, maxChannel);
		this.videosRecomended = new String[maxVid];
		this.vidNum = 0;
	}

	public void watch(String v, int wt) {

		for(int i = 0 ; i < this.folNum ; i++) {
			for(int j = 0 ; j < this.followedChannels[i].vidNum ; j++) {
				if(v.equals(this.followedChannels[i].videos[j])) {
					this.followedChannels[i].updateChannelStatistics(wt);
					break;
				}
			}
		}
	}

	public String getName() {
		return "Subscriber " + this.name;
	}

	public void addRecommendedVideo(String v) {
		this.videosRecomended[this.vidNum] = v;
		this.vidNum++;
	}

	public String toString() {
		String report = "";
		report += "Subscriber " + this.name;

		if(this.folNum > 0) {
			report += " follows [";
			for(int i = 0 ; i < this.folNum ; i++) {
				if(i > 0) report += ", ";
				report +=  this.followedChannels[i].getName();
			}
			report += "] and ";
		}else {
			report +=  " follows no channels and ";
		}

		if(this.vidNum > 0) {
			report += "is recommended <";
			for(int i = 0 ; i < this.vidNum ; i++) {
				if(i > 0) report += ", ";
				report +=  this.videosRecomended[i];
			}
			report += ">.";
		}else {
			report +=  "has no recommended videos.";
		}
		return report;
	}
}
