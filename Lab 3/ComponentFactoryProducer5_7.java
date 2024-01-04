

import java.util.HashMap;
// singleton
public class ComponentFactoryProducer5_7 {
    private static ComponentFactoryProducer5_7 instance = null;
    // map for containing factories of individual component types
    private final HashMap<String, ComponentFactory5_7> factoryMap;

    private ComponentFactoryProducer5_7() {
        factoryMap = new HashMap<String, ComponentFactory5_7>();

        factoryMap.put("Button", new ButtonFactory5_7());
        factoryMap.put("EditBox", new EditBoxFactory5_7());
        factoryMap.put("TextBox", new TextBoxFactory5_7());
    }

    public static ComponentFactoryProducer5_7 getInstance() {
        if(instance == null)
        {
            instance = new ComponentFactoryProducer5_7();
        }
        return instance;
    }
    // provides factory of given component
    public ComponentFactory5_7 getFactory(String componentName)
    {
        return factoryMap.get(componentName);
    }
}
