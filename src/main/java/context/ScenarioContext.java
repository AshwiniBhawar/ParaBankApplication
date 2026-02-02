package context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

        private final Map<String, Object> context = new HashMap<String, Object>();

        public void setContext(String key, Object value) {
            context.put(key, value);
        }

        public <T> T getContext(String key) {
            return (T) context.get(key);
        }

        public boolean contains(String key){
            return context.containsKey(key);
        }

        public void clear(){
            context.clear();
        }
}

