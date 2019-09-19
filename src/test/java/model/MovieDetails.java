package model;

public class MovieDetails {
	
	String movieName="";
	String youtubeDirectorName="";
	String moviePrice="";
	String imdbDirectorName=null;
	String imdbRating="";
	public String getMovieName() {
		return movieName;
	}
	
	public String getMoviePrice() {
		return moviePrice;
	}
	public void setMoviePrice(String moviePrice) {
		this.moviePrice = moviePrice;
	}
		
	public String getYoutubeDirectorName() {
		return youtubeDirectorName;
	}
	public String getImdbDirectorName() {
		return imdbDirectorName;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public void setYoutubeDirectorName(String youtubeDirectorName) {
		this.youtubeDirectorName = youtubeDirectorName;
	}
	public void setImdbDirectorName(String imdbDirectorName) {
		this.imdbDirectorName = imdbDirectorName;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	
	

}
