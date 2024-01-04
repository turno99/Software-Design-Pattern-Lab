// This interface forces any variant of parser, be it config or XML, will help the implemented
// class deal with different formats

public interface ConfigManager5_7 {
    
    public boolean hasMoreItems() throws InvalidConfigException5;

    public ConfigItem5_7 nextItem() throws InvalidConfigException5;


    public class InvalidConfigException5 extends Exception{
        public InvalidConfigException5(String err)
        {
            super(err);
        }
        public InvalidConfigException5(String err, Throwable cause)
        {
            super(err, cause);
        }
    }

}
