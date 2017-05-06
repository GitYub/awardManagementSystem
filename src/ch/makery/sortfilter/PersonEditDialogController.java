package ch.makery.sortfilter;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * Dialog to edit details of a person.
 *
 * @author Yu Xinyu
 */
public class PersonEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField workNameField;
    @FXML
    ChoiceBox<Object> cbTimeYear = new ChoiceBox<>();
    @FXML
    ChoiceBox<Object> cbTimeMonth = new ChoiceBox<>();
    @FXML
    ChoiceBox<Object> cbLevel = new ChoiceBox<>();
    @FXML
    ChoiceBox<Object> cbPrize= new ChoiceBox<>();
    @FXML
    ChoiceBox<Object> cbMajor = new ChoiceBox<>();
    @FXML
    ChoiceBox<Object> cbGrade = new ChoiceBox<>();
/**
    @FXML
	ImageView aa = new ImageView();*/

    private Stage dialogStage;
    private Person person;
    private String fileName;
    private String fileOldPath;
    private String[] pathArr;

    //FileInputStream input = null;

    private boolean fileSel = false;
    private boolean pathField = false;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	cbTimeYear.setItems(FXCollections.observableArrayList(
                "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020")
            );
    	cbTimeMonth.setItems(FXCollections.observableArrayList(
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
            );
    	cbLevel.setItems(FXCollections.observableArrayList(
    			"J", "G", "S", "X")
            );
    	cbPrize.setItems(FXCollections.observableArrayList(
    			"T", "1", "2", "3")
            );
    	cbMajor.setItems(FXCollections.observableArrayList(
                "R", "X")
            );
    	cbGrade.setItems(FXCollections.observableArrayList(
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20")
            );
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
        	cbTimeYear.setValue(person.getTimeYear());
        	cbTimeMonth.setValue(person.getTimeMonth());
        	cbLevel.setValue(person.getLevel());
        	cbPrize.setValue(person.getPrize());
        	cbMajor.setValue(person.getMajor());
        	cbGrade.setValue(person.getGrade());
            workNameField.setText(person.getWorkName());
            nameField.setText(person.getName());
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

        	/**
			try {
				input = new FileInputStream(file.getPath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        	Image image = new Image(input);
			aa.setImage(image);*/
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
        	/**
        	try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        	String oldName = person.getTimeAll()+person.getLevelAll()+person.getMajorAll()+"_"+person.getWorkName()+"_"+person.getName();
            person.setTimeYear((String)cbTimeYear.getValue());
            person.setTimeMonth((String)cbTimeMonth.getValue());
            person.setTimeAll(person.getTimeYear()+person.getTimeMonth());
            person.setLevel((String)cbLevel.getValue());
            person.setPrize((String)cbPrize.getValue());
            person.setLevelAll(person.getLevel()+person.getPrize());
            person.setMajor((String)cbMajor.getValue());
            person.setGrade((String)cbGrade.getValue());
            person.setMajorAll(person.getMajor()+person.getGrade());
            person.setName(nameField.getText());
            person.setWorkName(workNameField.getText());
            String newName = person.getTimeAll()+person.getLevelAll()+person.getMajorAll()+"_"+person.getWorkName()+"_"+person.getName();

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

            		File oldFile2 = new File(fileOldPath);
            		myStr += person.getTimeAll()+person.getLevelAll()+person.getMajorAll()+"_"+person.getWorkName()+"_"+person.getName() + ".jpg";

                    oldFile2.renameTo(new File(myStr));

            		myStr = "";
                    int i = 0;
                    do {
                    	myStr += pathArr[i];
                    	myStr += "\\\\";
                    }while(++i < pathArr.length);
                    myStr += person.getTimeAll()+person.getLevelAll()+person.getMajorAll()+"_"+person.getWorkName()+"_"+person.getName()+".jpg";
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
            	myStr += person.getTimeAll()+person.getLevelAll()+person.getMajorAll()+"_"+person.getWorkName()+"_"+person.getName()+".jpg";
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

        if (cbTimeYear.getValue() == null) {
            errorMessage += "年份为空!\n";
        }

        if (cbTimeMonth.getValue() == null) {
            errorMessage += "月份为空!\n";
        }

        if (cbLevel.getValue() == null) {
            errorMessage += "等级为空!\n";
        }

        if (cbPrize.getValue() == null) {
            errorMessage += "奖次为空!\n";
        }

        if (cbMajor.getValue() == null) {
            errorMessage += "专业为空!\n";
        }

        if (cbGrade.getValue() == null) {
            errorMessage += "年级为空!\n";
        }

        if (nameField.getText() == null) {
            errorMessage += "姓名为空!\n";
        }

        if (workNameField.getText() == null || workNameField.getText().length() == 0) {
            errorMessage += "奖项名称为空!\n";
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