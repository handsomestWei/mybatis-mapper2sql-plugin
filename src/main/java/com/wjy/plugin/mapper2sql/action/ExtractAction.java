package com.wjy.plugin.mapper2sql.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.DbType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.wjy.mapper2sql.bo.JdbcConnProperties;
import com.wjy.mapper2sql.bo.MapperSqlInfo;
import com.wjy.plugin.mapper2sql.ui.Mapper2sqlSetting;
import com.wjy.plugin.mapper2sql.ui.Mapper2sqlToolWindowFactory;

/**
 * @author weijiayu
 * @date 2024/10/15 11:16
 */
public class ExtractAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        VirtualFile virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (virtualFile != null) {
            // PsiFile psiFile = PsiManager.getInstance(project).findFile(virtualFile);
            // System.out.println(psiFile.getText());
            try {
                String filePath = virtualFile.getPath();
                List<MapperSqlInfo> mapperSqlInfos = null;
                Mapper2sqlSetting settings = Mapper2sqlSetting.getInstance();
                String dbTypeName = settings.getDbType();
                DbType dbType = DbType.of(dbTypeName);
                if (dbType == null) {
                    Mapper2sqlToolWindowFactory.print("setting dbType=" + dbTypeName + " is un support");
                    return;
                }
                if (isConfigJdbcConnect(settings)) {
                    JdbcConnProperties connProperties = new JdbcConnProperties(settings.getDriverName(),
                        settings.getJdbcUrl(), settings.getDbUserName(), settings.getDbPassword());
                    mapperSqlInfos = com.wjy.mapper2sql.SqlUtil.parseMapperAndRunTest(filePath, dbType, connProperties);
                } else {
                    mapperSqlInfos =
                        com.wjy.mapper2sql.SqlUtil.parseMapper(filePath, dbType, settings.getEnableMockSqlParamFlag());
                }

                String content = "";
                for (MapperSqlInfo info : mapperSqlInfos) {
                    com.wjy.mapper2sql.util.OutPutUtil.toStdOut(info);
                    for (String s : formatMapperSqlInfo(info)) {
                        // JTextArea Line
                        content += s + "\n";
                    }
                }
                Mapper2sqlToolWindowFactory.print(content);
            } catch (Throwable t) {
                Mapper2sqlToolWindowFactory.print(t.getMessage());
                t.printStackTrace();
            }
        }
    }

    private Boolean isConfigJdbcConnect(Mapper2sqlSetting settings) {
        if (!settings.getEnableRunJdbcTestFlag()) {
            return false;
        }
        if (settings.getDriverName() == null || "".equals(settings.getDriverName())) {
            return false;
        }
        if (settings.getJdbcUrl() == null || "".equals(settings.getJdbcUrl())) {
            return false;
        }
        if (settings.getDbUserName() == null || "".equals(settings.getDbUserName())) {
            return false;
        }
        if (settings.getDbPassword() == null || "".equals(settings.getDbPassword())) {
            return false;
        }
        return true;
    }

    /**
     * @see com.wjy.mapper2sql.util.OutPutUtil#formatMapperSqlInfo(MapperSqlInfo)
     */
    private List<String> formatMapperSqlInfo(MapperSqlInfo info) {
        List<String> fmtList = new LinkedList<>();
        fmtList.add(String.format("---namespace=[%s], dbType=[%s], file=[%s]", info.getNamespace(),
            info.getDbTypeName(), info.getFilePath()));
        fmtList.add("");
        for (Map.Entry<String, String> entry : info.getSqlIdMap().entrySet()) {
            String sqlId = entry.getKey();
            String sql = entry.getValue();

            MapperSqlInfo.SqlTestResultInfo resultInfo = info.getSqlTestResultInfoMap().get(sqlId);
            String testResult = "unknown";
            String testResultMsg = "";
            if (resultInfo != null) {
                testResult = resultInfo.getResult().toString();
                testResultMsg = resultInfo.getMsg().replace("\n", "");
            }
            fmtList.add(String.format("---id=[%s], testResult=[%s], testMsg=[%s]", sqlId, testResult, testResultMsg));
            fmtList.add(sql);
            fmtList.add("");
        }
        return fmtList;
    }
}
