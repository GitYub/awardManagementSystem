package ch.makery.sortfilter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Simple model class for the person table.
 *
 * @author Yu Xinyu
 */
public class Person {

    private final StringProperty major;
    private final StringProperty classNum;
    private final StringProperty name;
    private final StringProperty studentID;
    private final StringProperty compettition;
    private final StringProperty time;
    private final StringProperty level;
    private final StringProperty prize;
    private final StringProperty workName;
    private final StringProperty path;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String major, String classNum, String name, String studentID, String time, String compettition, String level, String prize, String workName, String path) {
        this.major = new SimpleStringProperty(major);
        this.classNum = new SimpleStringProperty(classNum);
        this.name = new SimpleStringProperty(name);
        this.studentID = new SimpleStringProperty(studentID);
        this.time = new SimpleStringProperty(time);
        this.compettition = new SimpleStringProperty(compettition);
        this.level = new SimpleStringProperty(level);
        this.prize = new SimpleStringProperty(prize);
        this.workName = new SimpleStringProperty(workName);
        this.path = new SimpleStringProperty(path);
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

    public String getClassNum() {
        return classNum.get();
    }

    public void setClassNum(String classNum) {
        this.classNum.set(classNum);
    }

    public StringProperty classNumProperty() {
        return classNum;
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

    public String getStudentID() {
        return studentID.get();
    }

    public void setStudentID(String studentID) {
        this.studentID.set(studentID);
    }

    public StringProperty studentIDProperty() {
        return studentID;
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

    public String getCompettition() {
        return compettition.get();
    }

    public void setCompettition(String compettition) {
        this.compettition.set(compettition);
    }

    public StringProperty compettitionProperty() {
        return compettition;
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public StringProperty timeProperty() {
        return time;
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