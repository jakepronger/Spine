package me.jakepronger.spine.api.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PackageScanner {

    // todo: fix structuring so we can log errors in this method?

    public static List<Class<?>> scanPackage(String pkg) {
        List<Class<?>> classList = new ArrayList<>();

        try {
            URI location = PackageScanner.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            File file = new File(location);
            JarInputStream stream = new JarInputStream(new FileInputStream(file));
            String packageDirectory = pkg.replace('.', '/');

            while (true) {
                JarEntry entry = stream.getNextJarEntry();

                if (entry == null)
                    break;

                String entryName = entry.getName();

                if (!entryName.startsWith(packageDirectory) || !entryName.endsWith(".class") || entryName.contains("$"))
                    continue;

                Class<?> clazz = Class.forName(entryName
                        .replace(".class", "")
                        .replace('/', '.'));

                classList.add(clazz);
            }
        } catch (URISyntaxException | IOException | ClassNotFoundException exception) {
            //Bukkit.getConsoleSender().sendMessage("Failed to scan package " + pkg + ": " + exception.getMessage());
        }

        return classList;
    }

}
