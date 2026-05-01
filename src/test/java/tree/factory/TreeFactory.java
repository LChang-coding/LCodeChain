package tree.factory;

import lcode.tree.TreeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import tree.node.rootNode;

import java.util.HashMap;
import java.util.Map;

@Service
public class TreeFactory {


    private final rootNode rootNode;

    public TreeFactory(rootNode rootNode) {
        this.rootNode = rootNode;
    }

    public TreeHandler<String, DynamicContext, String> handler() {
        return rootNode;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {

        private int level;

        @Builder.Default
        private Map<String, Object> dataObjects = new HashMap<>();

        public <T> void setValue(String key, T value) {
            dataObjects.put(key, value);
        }

        @SuppressWarnings("unchecked")
        public <T> T getValue(String key) {
            return (T) dataObjects.get(key);
        }
    }
}
