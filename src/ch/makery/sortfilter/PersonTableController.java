package ch.makery.sortfilter;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.makery.sortfilter.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


/**
 * View-Controller for the person table.
 *
 * @author Yu Xinyu
 */
public class PersonTableController {

	private final Desktop desktop = Desktop.getDesktop();

	@FXML
	private TextField filterField;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> majorColumn;
    @FXML
    private TableColumn<Person, String> classColumn;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, String> timeColumn;
    @FXML
    private TableColumn<Person, String> studentIDColumn;
    @FXML
    private TableColumn<Person, String> compettitionColumn;
    @FXML
    private TableColumn<Person, String> workNameColumn;
    @FXML
    private TableColumn<Person, String> levelColumn;
    @FXML
    private TableColumn<Person, String> prizeColumn;
    @FXML
    private TableColumn<Person, String> pathColumn;

	private ObservableList<Person> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public PersonTableController() {

		Sql mysql = new Sql();
		mysql.connect();
		ResultSet rs = mysql.inquire();
            // 展开结果集数据库
        try {
			while(rs.next()){
			    // 通过字段检索
				String major = rs.getString("major");
			    String classNum = rs.getString("classNum");
			    String name = rs.getString("name");
			    String studentID = rs.getString("studentID");
			    String time = rs.getString("time");
			    String compettition = rs.getString("compettition");
			    String level = rs.getString("level");
			    String prize = rs.getString("prize");
			    String workName = rs.getString("workname");
			    String path = rs.getString("path");

			    masterData.add(new Person(major, classNum, name, studentID, time, compettition, level, prize, workName, path));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       mysql.close();
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 *
	 * Initializes the table columns and sets up sorting and filtering.
	 */
	@FXML
	private void initialize() {
		// 0. Initialize the columns.
		majorColumn.setCellValueFactory(cellData -> cellData.getValue().majorProperty());
		classColumn.setCellValueFactory(cellData -> cellData.getValue().classNumProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		studentIDColumn.setCellValueFactory(cellData -> cellData.getValue().studentIDProperty());
		compettitionColumn.setCellValueFactory(cellData -> cellData.getValue().compettitionProperty());
		workNameColumn.setCellValueFactory(cellData -> cellData.getValue().workNameProperty());
		levelColumn.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
		prizeColumn.setCellValueFactory(cellData -> cellData.getValue().prizeProperty());

		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Person> filteredData = new FilteredList<>(masterData, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getMajor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (person.getClassNum().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Person> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(personTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		personTable.setItems(sortedData);
	}

	@FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	Alert alert = new Alert(AlertType.CONFIRMATION);

        	alert.setTitle("请确认");
        	alert.setHeaderText("这将彻底删除该数据");
        	alert.setContentText("是否继续执行?");

        	Optional<ButtonType> result = alert.showAndWait();
        	if (result.get() == ButtonType.OK){
        		Person	currenPerson = personTable.getItems().get(selectedIndex);
        		Sql mysql = new Sql();
        		mysql.connect();
        		mysql.delete(currenPerson.getName(), currenPerson.getWorkName());
        		mysql.close();
                masterData.remove(currenPerson);
        	}

        } else {
        	// Nothing selected.

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("未选择");
            alert.setHeaderText("请选择一项");
            alert.setContentText("请在表格中选择一项！");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = Main.showPersonEditDialog(tempPerson);
        if (okClicked) {
        	String[] arr = {tempPerson.getMajor(), tempPerson.getClassNum(), tempPerson.getName(), tempPerson.getStudentID(), tempPerson.getCompettition(), tempPerson.getTime(), tempPerson.getLevel(), tempPerson.getPrize(), tempPerson.getWorkName(), tempPerson.getPath()};//new String[10];
        	Sql mysql = new Sql();
        	mysql.connect();
        	mysql.add(arr);
        	masterData.add(tempPerson);
        	mysql.close();
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = Main.showPersonEditDialog(selectedPerson);
            if (okClicked) {
            	String[] arr = {selectedPerson.getMajor(), selectedPerson.getClassNum(), selectedPerson.getName(), selectedPerson.getStudentID(), selectedPerson.getCompettition(), selectedPerson.getTime(), selectedPerson.getLevel(), selectedPerson.getPrize(), selectedPerson.getWorkName(), selectedPerson.getPath()};//new String[10];
            	Sql mysql = new Sql();
            	mysql.connect();
            	mysql.alter(arr);
            	mysql.close();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("未选择");
            alert.setHeaderText("请选择一项");
            alert.setContentText("请在表格中选择一项！");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to see the picture
     * details for the selected person.
     */
    @FXML
    private void handlePic() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
        	if(selectedPerson.getPath() != null) {
        		openFile(new File(selectedPerson.getPath()));
        	}
        	else {
        		Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("无证书");
                alert.setHeaderText("请添加证书");
                alert.setContentText("请添加证书至该选项！");

                alert.showAndWait();
        	}

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("未选择");
            alert.setHeaderText("请选择一项");
            alert.setContentText("请在表格中选择一项！");

            alert.showAndWait();
        }
    }

    private void openFile(File file) {
        EventQueue.invokeLater(() -> {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(PersonEditDialogController.
                        class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        });
    }
}
