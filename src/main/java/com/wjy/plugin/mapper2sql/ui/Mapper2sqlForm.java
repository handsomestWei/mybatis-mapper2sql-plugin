package com.wjy.plugin.mapper2sql.ui;

import javax.swing.*;

/**
 * use vertical spacer auto compress horizontal spacing
 * 
 * @author weijiayu
 * @date 2024/10/16 10:44
 */
public class Mapper2sqlForm {

    private JPanel jdbcConfigMainPanel;

    private JTextField driverNameText;
    private JTextField jdbcUrlText;
    private JTextField dbUserNameText;
    private JTextField dbPasswordText;
    private JCheckBox enableMockSqlParamCheckBox;
    private JLabel driverNameLabel;
    private JLabel jdbcUrlLabel;
    private JLabel dbUserNameLabel;
    private JLabel dbPasswordLabel;
    private JCheckBox enableRunJdbcTestCheckBox;
    private JLabel dbTypeLabel;
    private JTextField dbTypeText;
    private JLabel sqlAutoTestWithLabel;

    public JPanel getPanel() {
        return jdbcConfigMainPanel;
    }

    public JCheckBox getEnableMockSqlParamJCheckBox() {
        return enableMockSqlParamCheckBox;
    }

    public JCheckBox getEnableRunJdbcTestCheckBox() {
        return enableRunJdbcTestCheckBox;
    }

    public String getDbTypeText() {
        return dbTypeText.getText();
    }

    public void setDbTypeText(String dbType) {
        this.dbTypeText.setText(dbType);
    }

    public String getDriverNameText() {
        return driverNameText.getText();
    }

    public void setDriverNameText(String driverName) {
        this.driverNameText.setText(driverName);
    }

    public String getJdbcUrlText() {
        return jdbcUrlText.getText();
    }

    public void setJdbcUrlText(String jdbcUrl) {
        this.jdbcUrlText.setText(jdbcUrl);
    }

    public String getDbUserNameText() {
        return dbUserNameText.getText();
    }

    public void setDbUserNameText(String dbUserName) {
        this.dbUserNameText.setText(dbUserName);
    }

    public String getDbPasswordText() {
        return dbPasswordText.getText();
    }

    public void setDbPasswordText(String dbPassword) {
        this.dbPasswordText.setText(dbPassword);
    }

    public Boolean getEnableMockSqlParamFlag() {
        return enableMockSqlParamCheckBox.isSelected();
    }

    public void setEnableMockSqlParamFlag(Boolean flag) {
        this.enableMockSqlParamCheckBox.setSelected(flag);
    }

    public Boolean getEnableRunJdbcTestFlag() {
        return enableRunJdbcTestCheckBox.isSelected();
    }

    public void setEnableRunJdbcTestFlag(Boolean flag) {
        this.enableRunJdbcTestCheckBox.setSelected(flag);
    }

    public JTextField getDriverNameJTextField() {
        return driverNameText;
    }

    public JTextField getJdbcUrlTextJTextField() {
        return jdbcUrlText;
    }

    public JTextField getDbUserNameTextJTextField() {
        return dbUserNameText;
    }

    public JTextField getDbPasswordTextJTextField() {
        return dbPasswordText;
    }
}
