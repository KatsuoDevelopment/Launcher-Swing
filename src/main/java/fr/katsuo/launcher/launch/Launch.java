package fr.katsuo.launcher.launch;

import fr.katsuo.launcher.Constants;
import fr.katsuo.launcher.ui.LauncherFrame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Launch {

    private final List<String> vmargs;
    private final List<String> args;


    public Launch(List<String> vmargs, List<String> args) {
        this.vmargs = vmargs;
        this.args = args;

        launch();
    }

    public Process launch() {
        ProcessBuilder pb = new ProcessBuilder();
        ArrayList<String> oneCommand = new ArrayList<String>();

        oneCommand.add("java ");
        oneCommand.addAll(vmargs);
        oneCommand.addAll(args);

        pb.command(oneCommand);
        pb.directory(new File(Constants.gameDir.toUri()));
        pb.redirectErrorStream(true);
        try {
            Process p = pb.start();

            LauncherFrame.getInstance().setVisible(false);
            System.exit(0);

            return p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
