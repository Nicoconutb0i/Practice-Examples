package PE;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        tabPane.getTabs().add(createHealthProfileTab());
        tabPane.getTabs().add(createRectangleTab());
        tabPane.getTabs().add(createSavingsAccountTab());
        tabPane.getTabs().add(createDateTab());

        VBox root = new VBox(tabPane);
        Scene scene = new Scene(root, 500, 450);

        primaryStage.setTitle("PE Assignment Classes Tester");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab createHealthProfileTab() {
        Tab tab = new Tab("Health Profile");
        tab.setClosable(false);

        GridPane grid = createStandardGrid();

        TextField txtFirst = new TextField();
        TextField txtLast = new TextField();
        TextField txtGender = new TextField();
        TextField txtMonth = new TextField();
        TextField txtDay = new TextField();
        TextField txtYear = new TextField();
        TextField txtHeight = new TextField();
        TextField txtWeight = new TextField();

        grid.add(new Label("First Name:"), 0, 0); 
        grid.add(new Label("Last Name:"), 0, 1); 
        grid.add(new Label("Gender:"), 0, 2); 
        grid.add(new Label("Birth Month (1-12):"), 0, 3); 
        grid.add(new Label("Birth Day (1-31):"), 0, 4); 
        grid.add(new Label("Birth Year:"), 0, 5); 
        grid.add(new Label("Height (inches):"), 0, 6); 
        grid.add(new Label("Weight (pounds):"), 0, 7);

        grid.add(txtFirst, 1, 0);
        grid.add(txtLast, 1, 1);
        grid.add(txtGender, 1, 2);
        grid.add(txtMonth, 1, 3);
        grid.add(txtDay, 1, 4);
        grid.add(txtYear, 1, 5);
        grid.add(txtHeight, 1, 6);
        grid.add(txtWeight, 1, 7);
        
        Button btnCalc = new Button("Calculate Health Profile");
        Label lblResult = new Label();
        lblResult.setWrapText(true);
        grid.add(btnCalc, 0, 8);
        grid.add(lblResult, 1, 8);

        btnCalc.setOnAction(e -> {
            try {
                HealthProfile person = new HealthProfile(
                    txtFirst.getText(), txtLast.getText(), txtGender.getText(),
                    Integer.parseInt(txtMonth.getText()), Integer.parseInt(txtDay.getText()), Integer.parseInt(txtYear.getText()),
                    Double.parseDouble(txtHeight.getText()), Double.parseDouble(txtWeight.getText())
                );
                lblResult.setText(String.format("Age: %d yrs\nMax HR: %d bpm\nTarget: %s\nBMI: %.2f",
                    person.getAge(), person.getMaxHeartRate(), person.getTargetHeartRateRange(), person.getBMI()));
            } catch (NumberFormatException ex) {
                lblResult.setText("Error: Please fill all fields with valid numbers.");
            }
        });

        tab.setContent(grid);
        return tab;
    }

    private Tab createRectangleTab() {
        Tab tab = new Tab("Rectangle");
        tab.setClosable(false);

        GridPane grid = createStandardGrid();

        TextField txtLength = new TextField();
        TextField txtWidth = new TextField();

        grid.add(new Label("Length (0.0 - 20.0):"), 0, 0); grid.add(txtLength, 1, 0);
        grid.add(new Label("Width (0.0 - 20.0):"), 0, 1); grid.add(txtWidth, 1, 1);

        Button btnCalc = new Button("Calculate Dimensions");
        Label lblResult = new Label();
        grid.add(btnCalc, 0, 2);
        grid.add(lblResult, 1, 2);

        btnCalc.setOnAction(e -> {
            try {
                Rectangle rect = new Rectangle();
                rect.setLength(Double.parseDouble(txtLength.getText()));
                rect.setWidth(Double.parseDouble(txtWidth.getText()));
                lblResult.setText(String.format("Perimeter: %.2f\nArea: %.2f", 
                    rect.calculatePerimeter(), rect.calculateArea()));
            } catch (NumberFormatException ex) {
                lblResult.setText("Error: Enter valid floating-point values.");
            }
        });

        tab.setContent(grid);
        return tab;
    }

    private Tab createSavingsAccountTab() {
        Tab tab = new Tab("Savings Account");
        tab.setClosable(false);

        GridPane grid = createStandardGrid();

        TextField txtRate = new TextField();
        TextField txtBalance = new TextField();

        grid.add(new Label("Annual Rate (e.g. 0.04):"), 0, 0); grid.add(txtRate, 1, 0);
        grid.add(new Label("Starting Balance ($):"), 0, 1); grid.add(txtBalance, 1, 1);

        Button btnCalc = new Button("Apply 1-Month Interest");
        Label lblResult = new Label();
        grid.add(btnCalc, 0, 2);
        grid.add(lblResult, 1, 2);

        btnCalc.setOnAction(e -> {
            try {
                SavingsAccount.modifyInterestRate(Double.parseDouble(txtRate.getText()));
                SavingsAccount account = new SavingsAccount(Double.parseDouble(txtBalance.getText()));
                account.calculateMonthlyInterest();
                lblResult.setText(String.format("New Balance: $%.2f", account.getSavingsBalance()));
            } catch (NumberFormatException ex) {
                lblResult.setText("Error: Enter valid values.");
            }
        });

        tab.setContent(grid);
        return tab;
    }

    private Tab createDateTab() {
        Tab tab = new Tab("Date");
        tab.setClosable(false);

        GridPane grid = createStandardGrid();

        TextField txtMonth = new TextField();
        TextField txtDay = new TextField();
        TextField txtYear = new TextField();

        grid.add(new Label("Month (1-12):"), 0, 0); grid.add(txtMonth, 1, 0);
        grid.add(new Label("Day:"), 0, 1); grid.add(txtDay, 1, 1);
        grid.add(new Label("Year:"), 0, 2); grid.add(txtYear, 1, 2);

        Button btnCalc = new Button("Roll to Next Day");
        Label lblResult = new Label();
        grid.add(btnCalc, 0, 3);
        grid.add(lblResult, 1, 3);

        btnCalc.setOnAction(e -> {
            try {
                Date dateObj = new Date(
                    Integer.parseInt(txtMonth.getText()),
                    Integer.parseInt(txtDay.getText()),
                    Integer.parseInt(txtYear.getText())
                );
                dateObj.nextDay();
                lblResult.setText("The next day is: " + dateObj);
            } catch (NumberFormatException ex) {
                lblResult.setText("Error: Enter valid integers.");
            }
        });

        tab.setContent(grid);
        return tab;
    }

    private GridPane createStandardGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(20, 20, 20, 20));
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
