package tree.node;

import lcode.tree.TreeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tree.TreeSupport;

@Slf4j
@Component
public class thirdNode extends TreeSupport {

    @Override
    public Object doApply(Object request, Object dynamic) throws Exception {
        log.info("终节点: 完成处理 [{}]", request);
        return "处理完成: " + request;
    }

    @Override
    public TreeHandler get(Object request, Object dynamic) throws Exception {
        return null;
    }
}
