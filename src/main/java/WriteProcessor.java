import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
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

            CtElement unaryOperatorParent = element.getParent(e -> e instanceof CtUnaryOperator);
            CtElement operatorAssignmentParent = element.getParent(e -> e instanceof CtOperatorAssignment);
            CtOperatorAssignment opassignment = (CtOperatorAssignment) operatorAssignmentParent;
            BinaryOperatorKind binaryOp = null;

//            if(opassignment != null)
//            {
//                v.setSteppedInLoop();
//            }
            if(opassignment != null)
            {
                binaryOp = opassignment.getKind();
            }

            if(unaryOperatorParent != null || (opassignment != null && (true || (binaryOp == BinaryOperatorKind.PLUS || binaryOp == BinaryOperatorKind.MINUS)) ) )
            {
                v.setSteppedInLoop();
            }
        }
    }
}