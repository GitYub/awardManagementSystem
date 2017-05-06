package ch.makery.sortfilter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Simple model class for the person table.
 *
 * @author Yu Xinyu
 */
public class Person {

    private final StringProperty timeYear;
    private final StringProperty timeMonth;
    private final StringProperty timeAll;
    private final StringProperty level;
    private final StringProperty prize;
    private final StringProperty levelAll;
    private final StringProperty major;
    private final StringProperty grade;
    private final StringProperty majorAll;
    private final StringProperty workName;
    private final StringProperty name;
    private final StringProperty path;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null, null, null, null, null, null, null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String timeYear, String timeMonth, String level, String prize, String major, String grade, String workName, String name, String path) {
    	this.timeYear = new SimpleStringProperty(timeYear);
    	this.timeMonth = new SimpleStringProperty(timeMonth);
    	this.level = new SimpleStringProperty(level);
        this.prize = new SimpleStringProperty(prize);
    	this.major = new SimpleStringProperty(major);
        this.grade = new SimpleStringProperty(grade);
        this.workName = new SimpleStringProperty(workName);
        this.name = new SimpleStringProperty(name);
        this.path = new SimpleStringProperty(path);
        this.timeAll = new SimpleStringProperty(timeYear+timeMonth);
        this.levelAll = new SimpleStringProperty(level+prize);
        this.majorAll = new SimpleStringProperty(major+grade);
    }

    public String getTimeAll() {
        return timeAll.get();
    }

    public void setTimeAll(String timeAll) {
        this.timeAll.set(timeAll);
    }

    public StringProperty timeAllProperty() {
        return timeAll;
    }

    public String getLevelAll() {
        return levelAll.get();
    }

    public void setLevelAll(String levelAll) {
        this.levelAll.set(levelAll);
    }

    public StringProperty levelAllProperty() {
        return levelAll;
    }

    public String getMajorAll() {
        return majorAll.get();
    }

    public void setMajorAll(String majorAll) {
        this.majorAll.set(majorAll);
    }

    public StringProperty majorAllProperty() {
        return majorAll;
    }

    public String getMajor() {
        return major.get();
    }

    public void setMajor(String major) {
        this.major.set(major);
    }

    public StringProperty majorProperty() {
        return major;
    }

    public String getTimeYear() {
        return timeYear.get();
    }

    public void setTimeYear(String timeYear) {
        this.timeYear.set(timeYear);
    }

    public StringProperty timeYearProperty() {
        return timeYear;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getTimeMonth() {
        return timeMonth.get();
    }

    public void setTimeMonth(String timeMonth) {
        this.timeMonth.set(timeMonth);
    }

    public StringProperty TimeMonthProperty() {
        return timeMonth;
    }

    public String getLevel() {
        return level.get();
    }

    public void setLevel(String level) {
        this.level.set(level);
    }

    public StringProperty levelProperty() {
        return level;
    }

    public String getPrize() {
        return prize.get();
    }

    public void setPrize(String prize) {
        this.prize.set(prize);
    }

    public StringProperty prizeProperty() {
        return prize;
    }

    public String getWorkName() {
        return workName.get();
    }

    public void setWorkName(String workName) {
        this.workName.set(workName);
    }

    public StringProperty workNameProperty() {
        return workName;
    }

    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    public StringProperty gradeProperty() {
        return grade;
    }

    public String getPath() {
        return path.get();
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public StringProperty pathProperty() {
        return path;
    }
}