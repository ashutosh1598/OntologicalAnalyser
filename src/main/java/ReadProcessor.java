import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.reference.CtVariableReference;


public class ReadProcessor extends AbstractProcessor<CtVariableRead>
{
    @Override
    public void process(CtVariableRead element)
    {
//        System.out.println(element + " read " + element.getPosition().getLine());
        CtVariableReference reference = element.getVariable();
        Variable v = TestHelp.getVariable(reference);

        v.read();

        CtElement loopParent = element.getParent(e -> e instanceof CtLoop);
        if(loopParent != null)
        {
            v.readInLoop();
            CtAssignment normAssignment = element.getParent(e -> e instanceof CtAssignment);
            if(normAssignment != null)
            {
                CtExpression lhs = normAssignment.getAssigned();
                if(lhs.toString().equals(reference.toString()))
                {
                    v.setSteppedInLoop();
                }
            }
        }

        CtReturn ctReturn = element.getParent(e -> e instanceof CtReturn);
        if(ctReturn != null)
        {
            v.returned();
        }
    }
}