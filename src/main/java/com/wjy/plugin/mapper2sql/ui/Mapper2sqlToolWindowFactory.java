package com.wjy.plugin.mapper2sql.ui;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

/**
 * @author weijiayu
 * @date 2024/10/17 0:33
 */
public class Mapper2sqlToolWindowFactory implements ToolWindowFactory {

    private static Mapper2sqlConsoleForm consoleForm;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        consoleForm = new Mapper2sqlConsoleForm();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(consoleForm.getPanel(), "", true);
        toolWindow.getContentManager().addContent(content);
    }

    public static void print(String content) {
        consoleForm.setMapper2sqlConsoleText(content);
    }
}
