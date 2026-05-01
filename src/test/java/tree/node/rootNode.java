package tree.node;

import jakarta.annotation.Resource;
import lcode.tree.TreeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tree.TreeSupport;

@Slf4j
@Component
public class rootNode extends TreeSupport {
    @Resource
    private firstNode firstNode;

    @Override
    public Object doApply(Object request, Object dynamic) throws Exception {
        log.info("根节点: 接收入站请求 [{}]", request);
        return router(request, dynamic);
    }

    @Override
    public TreeHandler get(Object request, Object dynamic) throws Exception {
        log.info("根节点: 路由到 firstNode");
        return firstNode;
    }
}
