package com.wjy.plugin.mapper2sql.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.options.Configurable;

/**
 * @author weijiayu
 * @date 2024/10/16 10:57
 */
public class Mapper2sqlConfigurable implements Configurable {

    private Mapper2sqlForm mapper2sqlForm;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "mybatis-mapper2sql Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mapper2sqlForm.getEnableMockSqlParamJCheckBox();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mapper2sqlForm = new Mapper2sqlForm();
        mapper2sqlForm.getEnableRunJdbcTestCheckBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    setJdbcConfigEditable(true);
                } else {
                    setJdbcConfigEditable(false);
                }
            }
        });
        wrapperForm();
        return mapper2sqlForm.getPanel();
    }

    @Override
    public boolean isModified() {
        Mapper2sqlSetting settings = Mapper2sqlSetting.getInstance();
        boolean modified = !settings.getEnableMockSqlParamFlag().equals(mapper2sqlForm.getEnableMockSqlParamFlag());
        modified |= !settings.getEnableRunJdbcTestFlag().equals(mapper2sqlForm.getEnableRunJdbcTestFlag());
        modified |= !settings.getDbType().equals(mapper2sqlForm.getDbTypeText());
        modified |= !settings.getDriverName().equals(mapper2sqlForm.getDriverNameText());
        modified |= !settings.getJdbcUrl().equals(mapper2sqlForm.getJdbcUrlText());
        modified |= !settings.getDbUserName().equals(mapper2sqlForm.getDbUserNameText());
        modified |= !settings.getDbPassword().equals(mapper2sqlForm.getDbPasswordText());
        return modified;
    }

    @Override
    public void apply() {
        Mapper2sqlSetting settings = Mapper2sqlSetting.getInstance();
        if (mapper2sqlForm.getDbTypeText() == null || "".equals(mapper2sqlForm.getDbTypeText().trim())) {
            JOptionPane.showMessageDialog(null, "dbType cannot empty", "warn", JOptionPane.WARNING_MESSAGE);
            return;
        }
        settings.setDbType(mapper2sqlForm.getDbTypeText());
        settings.setEnableMockSqlParamFlag(mapper2sqlForm.getEnableMockSqlParamFlag());
        settings.setDriverName(mapper2sqlForm.getDriverNameText());
        settings.setJdbcUrl(mapper2sqlForm.getJdbcUrlText());
        settings.setDbUserName(mapper2sqlForm.getDbUserNameText());
        settings.setDbPassword(mapper2sqlForm.getDbPasswordText());
        settings.setEnableRunJdbcTestFlag(mapper2sqlForm.getEnableRunJdbcTestFlag());
    }

    @Override
    public void reset() {
        wrapperForm();
    }

    @Override
    public void disposeUIResources() {
        mapper2sqlForm = null;
    }

    private void wrapperForm() {
        Mapper2sqlSetting settings = Mapper2sqlSetting.getInstance();
        mapper2sqlForm.setDbTypeText(settings.getDbType());
        mapper2sqlForm.setEnableMockSqlParamFlag(settings.getEnableMockSqlParamFlag());
        mapper2sqlForm.setDriverNameText(settings.getDriverName());
        mapper2sqlForm.setJdbcUrlText(settings.getJdbcUrl());
        mapper2sqlForm.setDbUserNameText(settings.getDbUserName());
        mapper2sqlForm.setDbPasswordText(settings.getDbPassword());
        mapper2sqlForm.setEnableRunJdbcTestFlag(settings.getEnableRunJdbcTestFlag());
        setJdbcConfigEditable(mapper2sqlForm.getEnableRunJdbcTestFlag());
    }

    private void setJdbcConfigEditable(boolean editable) {
        mapper2sqlForm.getDriverNameJTextField().setEditable(editable);
        mapper2sqlForm.getDriverNameJTextField().setFocusable(editable);

        mapper2sqlForm.getJdbcUrlTextJTextField().setEditable(editable);
        mapper2sqlForm.getJdbcUrlTextJTextField().setFocusable(editable);

        mapper2sqlForm.getDbUserNameTextJTextField().setEditable(editable);
        mapper2sqlForm.getDbUserNameTextJTextField().setFocusable(editable);

        mapper2sqlForm.getDbPasswordTextJTextField().setEditable(editable);
        mapper2sqlForm.getDbPasswordTextJTextField().setFocusable(editable);
    }
}
