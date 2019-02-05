import spoon.reflect.reference.CtVariableReference;

public class Variable
{
    private int timesRead = 0;
    private int timesWritten = 0;
    private boolean WrittenInLoop = false;
    private boolean ReadInLoop = false;
    private boolean stepped = false;
    private boolean field = false;
    private boolean isStatic = false;
    private boolean isPublic = false;
    private boolean returned = false;

    private CtVariableReference reference;

    public Variable(CtVariableReference ctVariableReference)
    {
        reference = ctVariableReference;
    }

    public void read()
    {
        timesRead++;
    }
    public void written()
    {
        timesWritten++;
    }
    public void writtenInLoop()
    {
        WrittenInLoop = true;
    }
    public void readInLoop()
    {
        ReadInLoop = true;
    }
    public void stepped()
    {
        stepped = true;
    }
    public void setfield()
    {
        field = true;
    }
    public void setStatic()
    {
        isStatic = true;
    }
    public void setPublic()
    {
        isPublic = true;
    }
    public void returned()
    {
        returned = true;
    }


    public boolean isReadOnly()
    {
        return timesRead > 0 && timesWritten == 0;
    }
    public boolean isWriteOnly()
    {
        return timesWritten > 0 && timesRead == 0;
    }
    public boolean isWriteOnce()
    {
        return timesWritten == 1;
    }
    public boolean isWrittenInLoop()
    {
        return WrittenInLoop;
    }
    public boolean isReadInLoop()
    {
        return ReadInLoop;
    }
    public boolean isStepped()
    {
        return stepped;
    }
    public boolean isField()
    {
        return field;
    }
    public boolean isStatic()
    {
        return isStatic;
    }
    public boolean isPublic()
    {
        return isPublic;
    }
    public boolean isReturned()
    {
        return returned;
    }


    public void printProperties()
    {
        System.out.println("Reference isReadOnly isWriteOnly isWriteOnce writtenInLoop readInLoop isStepped isField isStatic isPublic isReturned");
        System.out.print(reference + "\t\t" + isReadOnly() + "\t\t" + isWriteOnly() + "\t\t" + isWriteOnce() + "\t\t" + isWrittenInLoop() + "\t\t"+isReadInLoop()+ "\t\t");
        System.out.print(isStepped() + "\t\t" + isField() + "\t\t" + isStatic() +"\t" + isPublic() + "\t" + isReturned());
        System.out.println("\n");
//        System.out.println(reference + "    " + timesRead + "   " + timesWritten + "  " + WrittenInLoop());
    }

}