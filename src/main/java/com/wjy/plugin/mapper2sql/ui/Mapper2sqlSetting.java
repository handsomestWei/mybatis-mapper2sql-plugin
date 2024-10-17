package com.wjy.plugin.mapper2sql.ui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

/**
 * @author weijiayu
 * @date 2024/10/16 11:39
 */
@State(name = "Mapper2sqlSetting", storages = @Storage("mybatis-mapper2sql.xml"))
public class Mapper2sqlSetting implements PersistentStateComponent<Mapper2sqlSetting> {

    private String dbType = "mysql";
    private String driverName;
    private String jdbcUrl;
    private String dbUserName;
    private String dbPassword;
    private Boolean enableMockSqlParamFlag = false;
    private Boolean enableRunJdbcTestFlag = false;

    public static Mapper2sqlSetting getInstance() {
        return ApplicationManager.getApplication().getService(Mapper2sqlSetting.class);
    }

    @Nullable
    @Override
    public Mapper2sqlSetting getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull Mapper2sqlSetting state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public void setDbType(String dbType) {
        if (dbType == null || "".equals(dbType.trim())) {
            dbType = "mysql";
        }
        this.dbType = dbType;
    }

    public String getDbType() {
        return dbType == null ? "mysql" : dbType;
    }

    public String getDriverName() {
        return driverName == null ? "" : driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getJdbcUrl() {
        return jdbcUrl == null ? "" : jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDbUserName() {
        return dbUserName == null ? "" : dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword == null ? "" : dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public Boolean getEnableMockSqlParamFlag() {
        return enableMockSqlParamFlag == null ? false : enableMockSqlParamFlag;
    }

    public void setEnableMockSqlParamFlag(Boolean enableMockSqlParamFlag) {
        if (enableMockSqlParamFlag == null) {
            enableMockSqlParamFlag = false;
        }
        this.enableMockSqlParamFlag = enableMockSqlParamFlag;
    }

    public Boolean getEnableRunJdbcTestFlag() {
        return enableRunJdbcTestFlag == null ? false : enableRunJdbcTestFlag;
    }

    public void setEnableRunJdbcTestFlag(Boolean enableRunJdbcTestFlag) {
        if (enableRunJdbcTestFlag == null) {
            enableRunJdbcTestFlag = false;
        }
        this.enableRunJdbcTestFlag = enableRunJdbcTestFlag;
    }
}
