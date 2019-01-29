import spoon.reflect.declaration.CtVariable;
import spoon.reflect.reference.CtVariableReference;

public class Variable
{
    boolean isReadOnly;
    boolean isWriteOnly;
    CtVariableReference reference;
    public Variable()
    {
        isReadOnly=true;
        isWriteOnly=true;
    }
    public Variable(CtVariableReference ctVariableReference)
    {
        isReadOnly=true;
        isWriteOnly=true;
        reference=ctVariableReference;
    }
    public void read()
    {
        isWriteOnly=false;

    }
    public void write()
    {
        isReadOnly=false;
    }
}