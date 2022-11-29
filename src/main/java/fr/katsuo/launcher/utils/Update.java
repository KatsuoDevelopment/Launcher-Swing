package fr.katsuo.launcher.utils;

import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.versions.VanillaVersion;
import fr.katsuo.launcher.Constants;

import java.nio.file.Paths;

public class Update {

    public static void update() throws Exception {
        VanillaVersion vanillaVersion = new VanillaVersion.VanillaVersionBuilder()
                .withName("1.8.8")
                .build();
        FlowUpdater flowUpdater = new FlowUpdater.FlowUpdaterBuilder()
                .withVanillaVersion(vanillaVersion)
                .build();
        flowUpdater.update(Paths.get(Constants.gameDir.toUri()));
    }
}
