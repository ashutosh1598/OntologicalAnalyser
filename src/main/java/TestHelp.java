import spoon.Launcher;
import spoon.reflect.reference.CtVariableReference;
import spoon.support.gui.SpoonModelTree;

import java.util.HashMap;
import java.util.Map;

public class TestHelp
{
    public static Map<CtVariableReference, Variable> vars = new HashMap<CtVariableReference, Variable>();

    public static Variable getVariable(CtVariableReference reference)
    {
        if (TestHelp.vars.containsKey(reference)) {
            return TestHelp.vars.get(reference);
        } else {
            Variable v = new Variable(reference);
            TestHelp.vars.put(reference, v);
            return v;
        }
    }

    public static void analyse(String pathToFile,int rulekey) throws Exception
    {
        Launcher launcher = new Launcher();

        launcher.addInputResource(pathToFile);

        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setCommentEnabled(true);

        launcher.addProcessor(new ReadProcessor());
        launcher.addProcessor(new WriteProcessor());
        launcher.addProcessor(new DeclarationProcessor());
        launcher.run();

        for(Variable v: vars.values())
        {
            v.printProperties();
        }
//        new SpoonModelTree(launcher.getFactory());
    }

}