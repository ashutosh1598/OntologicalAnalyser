import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtVariable;


public class ReadOnlyProcessor extends AbstractProcessor<CtVariable> {

    @Override
    public void process(CtVariable element)
    {
        if(element!=null)
        {
            System.out.println(element.getSimpleName());
            System.out.println(element.getPosition().getLine());
        }
    }
}