package game.bugtracker;

public class Game {
    private String gameName;
    private String genre;
    private String projectName;
    private String releaseDate;
    private String description;
    private String platform;

    public Game(String gameName, String genre, String projectName, String releaseDate, String description, String platform) {
        this.gameName = gameName;
        this.genre = genre;
        this.projectName = projectName;
        this.releaseDate = releaseDate;
        this.description = description;
        this.platform = platform;
    }

    // Getters
    public String getGameName() {
        return gameName;
    }

    public String getGenre() {
        return genre;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPlatform() {
        return platform;
    }

    // Setters
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
