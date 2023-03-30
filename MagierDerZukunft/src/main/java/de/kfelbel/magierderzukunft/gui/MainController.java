package de.kfelbel.magierderzukunft.gui;

import de.kfelbel.magierderzukunft.gui.listview.CoinCellFactory;
import de.kfelbel.magierderzukunft.gui.listview.TransactionCellFactory;
import de.kfelbel.magierderzukunft.logic.CoinHolder;
import de.kfelbel.magierderzukunft.logic.TransactionHolder;
import de.kfelbel.magierderzukunft.model.Coin;
import de.kfelbel.magierderzukunft.model.Transaction;
import de.kfelbel.magierderzukunft.settings.AppTexts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

/**
 * Implementiert die Steuerlogik für die Main-Szene. Es gibt drei Regionen leftView, middleView und
 * rightView. Jeder Teil hat seine eigenen Funktionen, die Daten, die aus diesen erstellt werden,
 * interagieren in den weiteren Bereichen.
 */
public class MainController implements Initializable {
    //region Konstanten
    //endregion Konstanten

    //region Attribute


    //region leftView Attributes
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSymbol;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtFounder;
    @FXML
    private ListView<Coin> coinListView;


    private Coin selectedCoin;
    //endregion
    //region MiddleView
    @FXML
    private ListView<Transaction> transactionListView;
    @FXML
    private PieChart pieChart;

    //endregion

    //region RightView

    @FXML
    private Label maxAmountUSDT;
    @FXML
    private TextField usdtTextField;
    @FXML
    private ComboBox<Coin> buyChoiceBox;
    @FXML
    private ComboBox<Coin> sellChoiceBox;
    @FXML
    private Button sellButton;

    @FXML
    private Label maxAmountCoin;
    @FXML
    private Label sellCoinPriceLabel;

    @FXML
    private Label sellAmountUSDTLabel;

    @FXML
    private TextField coinAmountTextField;
    @FXML
    private TextField euroTextField;
    @FXML
    private Label nameUSDTLabel;

    @FXML
    private Label changedUSDTLabel;
    @FXML
    private Label pricePerCoinUSDTLabel;

    @FXML
    private Label setCoinPriceLabel;

    @FXML
    private Label amountCoin;

    private Coin coinToBuy;



    //endregion



    //endregion

    //region Konstruktoren

    //endregion

    //region Methoden

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        CoinCellFactory coinCellFactory = new CoinCellFactory();
        coinListView.setCellFactory(coinCellFactory);

        coinListView.setItems(CoinHolder.getInstance().getCoins());

        coinListView.setOnMouseClicked(event -> {
            Coin selectedCoin = coinListView.getSelectionModel().getSelectedItem();
            setSelectedCoin(selectedCoin);
        });

        TransactionCellFactory transactionCellFactory = new TransactionCellFactory();
        transactionListView.setCellFactory(transactionCellFactory);

        transactionListView.setItems(TransactionHolder.getInstance().getTransactions());


        buyChoiceBox.setItems(CoinHolder.getInstance().getCoins());
        sellChoiceBox.setItems(CoinHolder.getInstance().getCoins());

        sellChoiceBox.setValue(CoinHolder.getInstance().getCoins().get(0));
        buyChoiceBox.setValue(CoinHolder.getInstance().getCoins().get(0));

        sellChoiceBox.setOnAction(event -> {
            String coinToSell = sellChoiceBox.getValue().toString();
            calculateCoinTotal(transactionListView, maxAmountCoin, coinToSell);
            setSellCoinPriceLabel();
            });


        if (usdtTextField != null) {
            usdtTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            convertUSDTToCoin(newValue);

//            double enteredValue = Double.parseDouble(newValue);
//            double maxAmount = Double.parseDouble(String.valueOf(maxAmountUSDT));
//            if ( maxAmount < enteredValue) {
//                buyButton.setVisible(false);
//                usdtTextField.setStyle("-fx-text-fill: red");
//            } else {
//                buyButton.setVisible(true);
//                usdtTextField.setStyle("-fx-text-fill: black");
//            }
            });

        }

        coinAmountTextField.textProperty().addListener((observable, oldValue, newValue) ->{
            convertCoinInUSDT(newValue);
        });

        calculateUSDTTotal(transactionListView, maxAmountUSDT);

        updatePieChart();
    }
    //region LeftViewMethods
    private void setSelectedCoin(Coin coin) {
        selectedCoin = coin;

        if (selectedCoin != null) {
            txtName.setText(selectedCoin.getName());
            txtSymbol.setText(selectedCoin.getSymbol());
            txtPrice.setText(String.valueOf(selectedCoin.getPrice()));
            txtFounder.setText(selectedCoin.getFounder());
        }
    }

    @FXML
    private void saveCoin(){
        try {
            if (selectedCoin != null) {
                editSelectedCoin();
            } else {
                createNewCoin();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println(AppTexts.TXT_FAILED_CONVERSION);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(AppTexts.TXT_SAVE_ERROR);
            alert.setHeaderText(AppTexts.TXT_NUMBER_CONVERSION);
            alert.setContentText(String.format(AppTexts.TXT_PRICE_CONVERSION, txtPrice.getText()));
            alert.showAndWait();
        }
        clearFields();

    }

    private boolean fieldIsNotEqualBlankOrEmpty(TextField textField, String property) {
        return !textField.getText().equals(property) && !textField.getText().isEmpty() && !textField.getText().isBlank();
    }
    @FXML
    private void selectNewCoin() {
        clearFields();
    }

    private void clearFields() {
        txtName.setText("");
        txtSymbol.setText("");
        txtPrice.setText("");
        txtFounder.setText("");
    }

    private void editSelectedCoin() throws NumberFormatException {
        if (fieldIsNotEqualBlankOrEmpty(txtName, selectedCoin.getName())) {
            selectedCoin.setName(txtName.getText());
        }

        if (fieldIsNotEqualBlankOrEmpty(txtSymbol, selectedCoin.getSymbol())) {
            selectedCoin.setSymbol(txtSymbol.getText());
        }

        if (fieldIsNotEqualBlankOrEmpty(txtPrice, String.valueOf(selectedCoin.getPrice()))) {
            selectedCoin.setPrice(Double.parseDouble(txtPrice.getText()));
        }

        if (fieldIsNotEqualBlankOrEmpty(txtFounder, selectedCoin.getName())) {
            selectedCoin.setFounder(txtFounder.getText());
        }

        CoinHolder.getInstance().getCoins().set(
                CoinHolder.getInstance().getCoins().indexOf(selectedCoin), selectedCoin);
    }

    private void createNewCoin() throws NumberFormatException {

        Coin coin = new Coin(
                txtName.getText(),
                txtSymbol.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtFounder.getText()
        );

        CoinHolder.getInstance().getCoins().add(coin);
    }

    @FXML
    private void deleteCoin() {
        if (selectedCoin != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle(AppTexts.TXT_DELETE_COIN);
            deleteAlert.setHeaderText(AppTexts.TXT_DELETE_CONFIRMATION);
            deleteAlert.setContentText(selectedCoin.toString());
            Optional<ButtonType> result = deleteAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                CoinHolder.getInstance().getCoins().remove(selectedCoin);
                selectNewCoin();
            }
        }
    }
    //endregion
    //region middleViewMethods
    @FXML
    private void updatePieChart() {
        Map<String, Double> coinAmountsInUSDT = new HashMap<>();
        Map<String, Double> coinAmounts = new HashMap<>();

        for (Transaction transaction : TransactionHolder.getInstance().getTransactions()) {
            String coinName = transaction.getCoinToBuy();
            double amountUSDT = transaction.getAmountUSDT();
            double amountCoin = transaction.getAmountCoin();

            if (coinAmountsInUSDT.containsKey(coinName)) {
                coinAmountsInUSDT.put(coinName, coinAmountsInUSDT.get(coinName) + amountUSDT);
            } else {
                coinAmountsInUSDT.put(coinName, amountUSDT);
            }

            if (coinAmounts.containsKey(coinName)) {
                coinAmounts.put(coinName, coinAmounts.get(coinName) + amountCoin);
            } else {
                coinAmounts.put(coinName, amountCoin);
            }
        }

        double totalAmountUSDT = coinAmountsInUSDT.values().stream().mapToDouble(Double::doubleValue).sum();


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String coinName : coinAmountsInUSDT.keySet()) {
            double amountUSDT = coinAmountsInUSDT.get(coinName);
            double percentage = amountUSDT / totalAmountUSDT;
            pieChartData.add(new PieChart.Data(coinName , percentage));

        }


        pieChart.setData(pieChartData);
        pieChart.setLabelLineLength(10);


    }
    //endregion
    //region rightViewMethods
    public void calculateUSDTTotal(ListView<Transaction> listView, Label label) {
        List<Transaction> usdtTransactions = new ArrayList<>();
        for (Transaction transaction : listView.getItems()) {
            if (transaction.getCoinToBuy().equals(AppTexts.TXT_USDT_TETHER)) {
                usdtTransactions.add(transaction);
            }
        }

        double sum = 0.0;
        for (Transaction transaction : usdtTransactions) {
            sum += transaction.getAmountCoin();
        }

        label.setText(String.valueOf(sum));
    }

    @FXML
    private void saveTransaction() throws NumberFormatException {

        double usdtAmount = Double.parseDouble(usdtTextField.getText());
        double negativeUSDTAmount = -usdtAmount;

        String negativeUSDT = String.valueOf(AppTexts.TXT_USDT_TETHER);
        double price = Double.parseDouble(AppTexts.TXT_PRICE_USDT);
        double amount = Double.parseDouble(String.valueOf(Transaction.DEF_DOUBLE_VALUE));
        double amountNegativeUSDT = Double.parseDouble(String.valueOf(negativeUSDTAmount));

        Transaction transactionNegative = new Transaction(negativeUSDT, price, amount, amountNegativeUSDT);
        TransactionHolder.getInstance().getTransactions().add(transactionNegative);

        String coinToBuy = String.valueOf(buyChoiceBox.getValue());
        double pricePerCoin = Double.parseDouble(setCoinPriceLabel.getText());
        double amountUSDT = Double.parseDouble(usdtTextField.getText());
        double coinAmount = Double.parseDouble(amountCoin.getText());

        Transaction transaction = new Transaction(coinToBuy, pricePerCoin, amountUSDT, coinAmount);
        TransactionHolder.getInstance().getTransactions().add(transaction);

//        buyChoiceBox.getSelectionModel().clearSelection();
        setCoinPriceLabel.setText("");
        usdtTextField.clear();
        amountCoin.setText("");

        calculateUSDTTotal(transactionListView, maxAmountUSDT);

        updatePieChart();
    }

    @FXML
    private void loadUSDTByEuro() throws NumberFormatException{

        String coinToBuy = String.valueOf(nameUSDTLabel.getText());
        double pricePerCoin = Double.parseDouble(pricePerCoinUSDTLabel.getText());
        double amountEuro = Double.parseDouble(euroTextField.getText());
        double amountCoin = Double.parseDouble(changedUSDTLabel.getText());

        Transaction transaction = new Transaction(coinToBuy, pricePerCoin, amountEuro, amountCoin);
        TransactionHolder.getInstance().getTransactions().add(transaction);

        euroTextField.clear();
        changedUSDTLabel.setText("");


        updatePieChart();
        calculateUSDTTotal(transactionListView, maxAmountUSDT);
    }


    @FXML
    private void setSetCoinPrice() {
        Coin selectedCoin = buyChoiceBox.getValue();
        if (selectedCoin != null) {
            setCoinPriceLabel.setText(String.valueOf(selectedCoin.getPrice()));
        }

    }

    @FXML
    private void setSellCoinPriceLabel() {
        Coin selectedCoin = sellChoiceBox.getValue();
        if (selectedCoin != null) {
            sellCoinPriceLabel.setText(String.valueOf(selectedCoin.getPrice()));
        }

    }

    public void convertToUSDT(ActionEvent actionEvent) {
        String euroString = euroTextField.getText();
        double euro = Double.parseDouble(euroString);
        double usdt = euro * 1.0684;
        changedUSDTLabel.setText(String.valueOf(usdt));
    }

    private void convertUSDTToCoin(String value) {

        if (usdtTextField != null && setCoinPriceLabel != null && !setCoinPriceLabel.getText().isEmpty()) {
            try {
                double amount = Double.parseDouble(value);
                double coinPrice = Double.parseDouble(setCoinPriceLabel.getText());
                double result = amount / coinPrice;
                amountCoin.setText(String.valueOf(result));

            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        } else {
            amountCoin.setText("");
        }

    }

    private void convertCoinInUSDT(String value) {
        if (coinAmountTextField != null && sellCoinPriceLabel != null && !sellCoinPriceLabel.getText().isEmpty()) {
            try {
                double coinAmount = Double.parseDouble(value);
                double coinPrice = Double.parseDouble(sellCoinPriceLabel.getText());
                double result = coinAmount * coinPrice;
                sellAmountUSDTLabel.setText(String.valueOf(result));

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else sellAmountUSDTLabel.setText("");
    }

    @FXML
    private void sellCoin() throws NumberFormatException {
        double coinAmount = Double.parseDouble(coinAmountTextField.getText());
        double negativeCoinAmount = -coinAmount;

        String coinToSell = String.valueOf(sellChoiceBox.getValue());
        double pricePerCoin = Double.parseDouble(sellCoinPriceLabel.getText());
        double amountUSDT = Double.parseDouble(String.valueOf(Transaction.DEF_DOUBLE_VALUE));
        double negativeCoin = Double.parseDouble(String.valueOf(negativeCoinAmount));

        Transaction sellCoinTransaction = new Transaction(coinToSell, pricePerCoin, amountUSDT, negativeCoin);
        TransactionHolder.getInstance().getTransactions().add(sellCoinTransaction);

        String Tether = String.valueOf(AppTexts.TXT_USDT_TETHER);
        double price = Double.parseDouble(AppTexts.TXT_PRICE_USDT);
        double amount = Double.parseDouble(String.valueOf(Transaction.DEF_DOUBLE_VALUE));
        double sellAmount = Double.parseDouble(sellAmountUSDTLabel.getText());

        Transaction getUSDTTransaction = new Transaction(Tether, price, amount, sellAmount);
        TransactionHolder.getInstance().getTransactions().add(getUSDTTransaction);

        String sellCoin = sellChoiceBox.getValue().toString();
        calculateCoinTotal(transactionListView, maxAmountCoin, sellCoin);

        calculateUSDTTotal(transactionListView, maxAmountUSDT);

        updatePieChart();

 //       sellChoiceBox.getSelectionModel().clearSelection();
        sellCoinPriceLabel.setText("");
        coinAmountTextField.clear();
        sellAmountUSDTLabel.setText("");

    }

    public void calculateCoinTotal(ListView<Transaction> listView, Label label, String coinName) {
        List<Transaction> coinTransactions = new ArrayList<>();
        for (Transaction transaction : listView.getItems()) {
            if (transaction.getCoinToBuy().equals(coinName)) {
                coinTransactions.add(transaction);
            }
        }

        double sum = 0.0;
        for (Transaction transaction : coinTransactions) {
            sum += transaction.getAmountCoin();
        }

        label.setText(String.valueOf(sum));
    }









    //endregion
}