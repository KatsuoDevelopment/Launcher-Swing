package fr.katsuo.launcher.launch;

import fr.katsuo.launcher.LauncherFrame;
import fr.katsuo.launcher.options.EOptions;
import fr.katsuo.launcher.options.Options;
import fr.katsuo.launcher.utils.Constants;
import fr.katsuo.launcher.utils.logger.ELogger;
import fr.katsuo.launcher.utils.logger.Logger;

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

            Logger logger = LauncherFrame.getInstance().getLogger();
            Options options = new Options();
            logger.log(ELogger.INFO, "Frame Size : " + options.getOptions(EOptions.WINDOW_SIZE));
            logger.log(ELogger.INFO, "Allowed Ram : " + options.getOptions(EOptions.RAM_SIZE) + "G");
            logger.log(ELogger.INFO, "Fullscreen : " + options.getOptions(EOptions.FULLSCREEN));

            System.exit(0);

            return p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
