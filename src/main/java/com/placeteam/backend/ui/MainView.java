package com.placeteam.backend.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainView {
    public MainView() {
        open();
    }

    public void open() {
        Display display = Display.getDefault();
        Shell shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("SWT Application");


        shell.open();
        shell.layout();
        while (!display.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
