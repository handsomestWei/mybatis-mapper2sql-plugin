package com.wjy.plugin.mapper2sql.ui;

import javax.swing.*;

/**
 * @author weijiayu
 * @date 2024/10/17 0:45
 */
public class Mapper2sqlConsoleForm {
    private JPanel outputMainPanel;
    private JTextArea outputTextArea;
    private JScrollPane outputScrollPane;

    public JPanel getPanel() {
        return outputMainPanel;
    }

    public void setMapper2sqlConsoleText(String content) {
        outputTextArea.setText(content);
    }
}
