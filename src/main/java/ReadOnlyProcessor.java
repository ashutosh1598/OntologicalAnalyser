import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.reference.CtVariableReference;


public class ReadOnlyProcessor extends AbstractProcessor<CtVariableRead>
{
    @Override
    public void process(CtVariableRead element)
    {
        System.out.println(element + " read " + element.getPosition().getLine());
        CtVariableReference reference = element.getVariable();
        if(TestHelp.vars.containsKey(reference))
        {
            TestHelp.vars.get(reference).read();
        }
        else
        {
            Variable v = new Variable(reference);
            v.read();
            TestHelp.vars.put(reference,v);
        }

    }
}