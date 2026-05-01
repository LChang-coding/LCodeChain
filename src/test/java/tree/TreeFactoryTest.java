package tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tree.factory.TreeFactory;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = TestApplication.class)
class TreeFactoryTest {

    @Autowired
    private TreeFactory treeFactory;

    @Test
    void shouldProcessRequestThroughFullChain() throws Exception {
        TreeFactory.DynamicContext ctx = TreeFactory.DynamicContext.builder()
                .level(1)
                .build();

        ctx.setValue("traceId", "abc-123");

        String result = (String) treeFactory.handler().apply("测试订单", ctx);

        log.info("最终结果: {}", result);
        assertEquals("处理完成: 测试订单", result);
    }

    @Test
    void shouldReturnDifferentResultForDifferentRequest() throws Exception {
        String result = (String) treeFactory.handler().apply("退款申请",
                TreeFactory.DynamicContext.builder().level(2).build());

        assertEquals("处理完成: 退款申请", result);
    }

    @Test
    void shouldCarryDataThroughDynamicContext() throws Exception {
        TreeFactory.DynamicContext ctx = TreeFactory.DynamicContext.builder()
                .level(3)
                .build();

        ctx.setValue("userId", "u_10086");

        treeFactory.handler().apply("查询订单", ctx);

        // 验证 context 中的值在链式调用后仍可访问
        assertEquals(3, ctx.getLevel());
        assertEquals("u_10086", ctx.<String>getValue("userId"));
    }
}
