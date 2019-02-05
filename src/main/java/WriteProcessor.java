import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.CtVariableWrite;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.reference.CtVariableReference;


public class WriteProcessor extends AbstractProcessor<CtVariableWrite>
{
    @Override
    public void process(CtVariableWrite element)
    {
        CtVariableReference reference = element.getVariable();

        Variable v = TestHelp.getVariable(reference);

        v.written();

        CtElement loopParent = element.getParent(e -> e instanceof CtLoop);
        if(loopParent != null)
        {
            v.writtenInLoop();
        }

        CtElement unaryOperatorParent = element.getParent(e -> e instanceof CtUnaryOperator);
        if(unaryOperatorParent != null)
        {
            v.stepped();
        }
    }
}