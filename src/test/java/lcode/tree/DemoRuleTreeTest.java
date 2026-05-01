package lcode.tree;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class DemoRuleTreeTest {

    private final DemoRuleTree ruleTree = new DemoRuleTree();

    @Test
    void shouldProcessNormalOrder() throws Exception {
        String result = ruleTree.router("normal", Map.of("user", "张三"));
        assertEquals("普通订单已处理，用户: 张三，预计3天送达", result);
    }

    @Test
    void shouldProcessVipOrder() throws Exception {
        String result = ruleTree.router("vip", Map.of("user", "李四"));
        assertEquals("VIP订单已处理，用户: 李四，预计1天送达", result);
    }

    @Test
    void shouldProcessFlashOrder() throws Exception {
        String result = ruleTree.router("flash", Map.of("user", "王五", "item", "iPhone"));
        assertEquals("闪购订单已处理，用户: 王五，商品: iPhone，预计2小时送达", result);
    }

    @Test
    void shouldReturnNullForUnknownOrderType() throws Exception {
        String result = ruleTree.router("unknown", Map.of());
        assertNull(result);
    }

    @Test
    void shouldSupportCustomDefaultHandler() throws Exception {
        ruleTree.setDefaultHandler((request, dynamic) -> "未知订单类型: " + request);
        String result = ruleTree.router("unknown", Map.of());
        assertEquals("未知订单类型: unknown", result);
    }

    @Test
    void applyShouldCallMulitWorkAndDoApply() throws Exception {
        String result = ruleTree.apply("test", Map.of());
        assertEquals("订单处理流水线完成", result);
    }

    @Test
    void shouldHandleNullDynamicContext() throws Exception {
        ruleTree.setDefaultHandler((request, dynamic) -> "handled");
        String result = ruleTree.router("unknown", null);
        assertEquals("handled", result);
    }
}
