package tree.node;

import jakarta.annotation.Resource;
import lcode.tree.TreeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tree.TreeSupport;

@Slf4j
@Component
public class secondNode extends TreeSupport {
    @Resource
    private thirdNode thirdNode;

    @Override
    public Object doApply(Object request, Object dynamic) throws Exception {
        log.info("第二节点: 业务处理 [{}]", request);
        return router(request, dynamic);
    }

    @Override
    public TreeHandler get(Object request, Object dynamic) throws Exception {
        log.info("第二节点: 路由到 thirdNode");
        return thirdNode;
    }
}
