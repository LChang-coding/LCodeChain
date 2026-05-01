package tree.node;

import jakarta.annotation.Resource;
import lcode.tree.TreeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tree.TreeSupport;

@Slf4j
@Component
public class firstNode extends TreeSupport {
    @Resource
    private secondNode secondNode;

    @Override
    public Object doApply(Object request, Object dynamic) throws Exception {
        log.info("第一节点: 校验请求 [{}]", request);
        return router(request, dynamic);
    }

    @Override
    public TreeHandler get(Object request, Object dynamic) throws Exception {
        log.info("第一节点: 校验通过，路由到 secondNode");
        return secondNode;
    }
}
