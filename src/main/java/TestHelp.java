import spoon.Launcher;
import spoon.processing.Processor;
import spoon.support.gui.SpoonModelTree;
import spoon.support.sniper.SniperJavaPrettyPrinter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class TestHelp
{

    private static Map<Integer, Class<? extends Processor>> rule;

    public static void initmap() {
        rule = new HashMap<>();
        rule.putIfAbsent(1854, ReadOnlyProcessor.class);
    }

    public static Class<?> getProcessor(int ruleKey) {
        if (rule == null) {
            initmap();
        }
        if (!rule.containsKey(ruleKey)) {
            System.out.println("Sorry. Repair not available for rule " + ruleKey);
            exit(0);
        }
        return rule.get(ruleKey);
    }

    public static void analyse(String pathToFile,int rulekey) throws Exception
    {
        Launcher launcher = new Launcher();
        /*
        launcher.getEnvironment().setPrettyPrinterCreator(() -> {
                    return new SniperJavaPrettyPrinter(launcher.getEnvironment());
                }
        );
        */

        launcher.addInputResource(pathToFile);

        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setCommentEnabled(true);
        launcher.getEnvironment().useTabulations(true);
        launcher.getEnvironment().setTabulationSize(4);



        Class<?> processor = getProcessor(rulekey);
        Constructor<?> cons = processor.getConstructor();
        Object object = cons.newInstance();
        launcher.addProcessor((Processor) object);
        launcher.run();
        new SpoonModelTree(launcher.getFactory());
    }

}