package ch.makery.sortfilter;

import java.io.File;
import ch.makery.sortfilter.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a person.
 *
 * @author Yu Xinyu
 */
public class PersonEditDialogController {

    @FXML
    private TextField majorField;
    @FXML
    private TextField classField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField studentIDField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField compettitionField;
    @FXML
    private TextField levelField;
    @FXML
    private TextField prizeField;
    @FXML
    private TextField workNameField;

    private Stage dialogStage;
    private Person person;
    private String fileName;
    private String fileOldPath;
    private String[] pathArr;
    private boolean fileSel = false;
    private boolean pathField = false;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        if(person != null) {
        	majorField.setText(person.getMajor());
            classField.setText(person.getClassNum());
            nameField.setText(person.getName());
            studentIDField.setText(person.getStudentID());
            compettitionField.setText(person.getCompettition());
            levelField.setText(person.getLevel());
            prizeField.setText(person.getPrize());
            workNameField.setText(person.getWorkName());
            timeField.setText(person.getTime());
            if(person.getPath() != null) {
            	pathField = true;
            }
        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks Choose a picture.
     */
    @FXML
    private void handleChoose() {
    	final FileChooser fileChooser = new FileChooser();
    	configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
        fileSel = false;
        if (file != null) {

        	fileSel = true;
        	fileName = file.getName();
        	fileOldPath = file.getPath();
        	String b = (fileName.split("\\."))[0];
        	String a = (fileOldPath.split(b))[0];
        	a = a.substring(0, a.length()-1);
        	pathArr = a.split("\\\\");
        }
        else {
        	fileSel = false;
        }
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	String oldName = person.getClassNum()+person.getName()+person.getWorkName();
            person.setMajor(majorField.getText());
            person.setClassNum(classField.getText());
            person.setName(nameField.getText());
            person.setStudentID(studentIDField.getText());
            person.setCompettition(compettitionField.getText());
            person.setLevel(levelField.getText());
            person.setPrize(prizeField.getText());
            person.setWorkName(workNameField.getText());
            person.setTime(timeField.getText());
            String newName = person.getClassNum()+person.getName()+person.getWorkName();

            if(pathField == false && fileSel == true) {
            	String newStr = "";
                int i = 0;
                do {
                	newStr += pathArr[i];
                	newStr += "\\";
                }while(++i < pathArr.length);

                newStr += newName +".jpg";
                File oldFile = new File(fileOldPath);
                oldFile.renameTo(new File(newStr));

                newStr = "";
                i = 0;
                do {
                	newStr += pathArr[i];
                	newStr += "\\\\";
                }while(++i < pathArr.length);
                newStr += newName +".jpg";
                person.setPath(newStr);
                fileSel = false;
            }
            else if(pathField == true && fileSel == true) {
            	String[] pathNew = person.getPath().split(oldName);
            	String[] pathArr1 = pathNew[0].split("\\\\\\\\");
            	String myStr = "";
            	for(String tmp : pathArr1) {
            		myStr += tmp;
            		myStr += "\\";
            	}
            	String comStr = myStr + oldName+".jpg";

            	//文件相同
            	if(fileOldPath.equals(comStr)) {

	    			myStr += newName +".jpg";
	    			File oldFile = new File(fileOldPath);
	                oldFile.renameTo(new File(myStr));

	                myStr = "";
	                int i = 0;
	                do {
	                	myStr += pathArr[i];
	                	myStr += "\\\\";
	                }while(++i < pathArr.length);
	                myStr += newName +".jpg";
	                person.setPath(myStr);
            	}

            	//文件不同
            	else {
            		//名字相同
            		if(oldName.equals(newName)) {
            			String tmp = myStr + oldName+"_old.jpg";
            			File oldFile = new File(comStr);
                        boolean succ = oldFile.renameTo(new File(tmp));
                        if(!succ) {
                        	String tmp1 = myStr + oldName+"_old1.jpg";
                            oldFile.renameTo(new File(tmp1));
                        }
            		}

            		System.out.println(myStr);
            		File oldFile2 = new File(fileOldPath);
            		myStr += person.getClassNum()+person.getName()+person.getWorkName()+".jpg";

                    oldFile2.renameTo(new File(myStr));

            		myStr = "";
                    int i = 0;
                    do {
                    	myStr += pathArr[i];
                    	myStr += "\\\\";
                    }while(++i < pathArr.length);
                    myStr += person.getClassNum()+person.getName()+person.getWorkName()+".jpg";
                    person.setPath(myStr);
            	}
            }
            else if(pathField == true && fileSel == false){
            	String[] pathNew = person.getPath().split(oldName);
            	String[] pathArr1 = pathNew[0].split("\\\\\\\\");
            	String myStr = "";
            	for(String tmp : pathArr1) {
            		myStr += tmp;
            		myStr += "\\\\";
            	}
            	myStr += person.getClassNum()+person.getName()+person.getWorkName()+".jpg";
            	String oldPath = person.getPath();
            	File oldFile = new File(oldPath);
                oldFile.renameTo(new File(myStr));
            	person.setPath(myStr);
            }

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (majorField.getText() == null || majorField.getText().length() == 0) {
            errorMessage += "专业为空!\n";
        }

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "姓名为空!\n";
        }

        if (studentIDField.getText() == null || studentIDField.getText().length() == 0) {
            errorMessage += "学号为空!\n";
        }

        if (compettitionField.getText() == null || compettitionField.getText().length() == 0) {
            errorMessage += "比赛为空!\n";
        }

        if (levelField.getText() == null || levelField.getText().length() == 0) {
            errorMessage += "等级为空!\n";
        }

        if (prizeField.getText() == null || prizeField.getText().length() == 0) {
            errorMessage += "奖次为空!\n";
        }

        if (workNameField.getText() == null || workNameField.getText().length() == 0) {
            errorMessage += "作品名称为空!\n";
        }

        if (classField.getText() == null || classField.getText().length() == 0) {
            errorMessage += "班级为空!\n";
        }

        if (timeField.getText() == null || timeField.getText().length() == 0) {
            errorMessage += "时间为空!\n";
        }

        if (fileSel == false && pathField == false) {
            errorMessage += "未选择证书!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("失败");
            alert.setHeaderText(errorMessage);
            alert.setContentText("请填写完整!");

            alert.showAndWait();
            return false;
        }
    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
                fileChooser.setTitle("选择证书");
                fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
                );
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg")
                );
    }
}