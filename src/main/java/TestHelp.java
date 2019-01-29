import spoon.Launcher;
import spoon.processing.Processor;
import spoon.reflect.reference.CtVariableReference;
import spoon.support.gui.SpoonModelTree;
import spoon.support.sniper.SniperJavaPrettyPrinter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class TestHelp
{

    private static Map<Integer, Class<? extends Processor>> rule;
    public static Map<CtVariableReference, Variable> vars = new HashMap<CtVariableReference, Variable>();

    public static void initmap() {
        rule = new HashMap<>();
        rule.putIfAbsent(1854, ReadOnlyProcessor.class);
        rule.putIfAbsent(1900, WriteOnlyProcessor.class);
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



        Class<?> processor = getProcessor(rulekey);
        Constructor<?> cons = processor.getConstructor();
        Object object = cons.newInstance();
        launcher.addProcessor((Processor) object);
        launcher.addProcessor(new WriteOnlyProcessor());
        launcher.run();
        System.out.println("Reference isReadOnly isWriteOnly");
        for(Variable v: vars.values())
        {
            System.out.println(v.reference + "   " + v.isReadOnly + "  " + v.isWriteOnly);
        }
//        new SpoonModelTree(launcher.getFactory());
    }

}