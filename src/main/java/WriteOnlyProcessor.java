import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableWrite;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.reference.CtVariableReference;


public class WriteOnlyProcessor extends AbstractProcessor<CtVariableWrite>
{
    @Override
    public void process(CtVariableWrite element) {
        System.out.println(element + " write " + element.getPosition().getLine());
        CtVariableReference reference = element.getVariable();
        if (TestHelp.vars.containsKey(reference)) {
            TestHelp.vars.get(reference).write();
        } else {
            Variable v = new Variable(reference);
            v.write();
            TestHelp.vars.put(reference, v);
        }
    }
}