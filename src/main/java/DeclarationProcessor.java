import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtVariable;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtVariableReference;

import java.util.Set;


public class DeclarationProcessor extends AbstractProcessor<CtVariable>
{
    @Override
    public void process(CtVariable element)
    {
        CtVariableReference reference = element.getReference();
        Variable v = TestHelp.getVariable(reference);
        if(element instanceof CtField)
        {
            v.setfield();
            Set<ModifierKind> modifiers = element.getModifiers();
            for(ModifierKind mf : modifiers)
            {
                if(mf == ModifierKind.STATIC)
                {
                    v.setStatic();
                }
                if(mf == ModifierKind.PUBLIC)
                {
                    v.setPublic();
                }
            }
        }
        CtTypeReference type = element.getType();
    }
}
