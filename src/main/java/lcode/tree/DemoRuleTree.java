package lcode.tree;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 规则树 Demo：订单处理路由
 *
 * 规则结构:
 *       路由器(按订单类型分发)
 *      /        |        \
 *  普通订单   VIP订单   闪购订单
 */
public class DemoRuleTree extends AbstractRouterClass<String, Map<String, Object>, String> {

    private final Map<String, TreeHandler<String, Map<String, Object>, String>> handlers = new ConcurrentHashMap<>();

    public DemoRuleTree() {
        // 注册规则：不同类型的订单走不同的处理器
        handlers.put("normal", (request, dynamic) -> {
            String user = (String) dynamic.get("user");
            return String.format("普通订单已处理，用户: %s，预计3天送达", user);
        });

        handlers.put("vip", (request, dynamic) -> {
            String user = (String) dynamic.get("user");
            return String.format("VIP订单已处理，用户: %s，预计1天送达", user);
        });

        handlers.put("flash", (request, dynamic) -> {
            String user = (String) dynamic.get("user");
            String item = (String) dynamic.getOrDefault("item", "商品");
            return String.format("闪购订单已处理，用户: %s，商品: %s，预计2小时送达", user, item);
        });
    }

    @Override
    public TreeHandler<String, Map<String, Object>, String> get(String request, Map<String, Object> dynamic) {
        return handlers.get(request);
    }

    @Override
    public void mulitWork(String request, Map<String, Object> dynamic) {
        // 多线程预加载：在实际场景中可在此预加载缓存、初始化资源等
    }

    @Override
    public String doApply(String request, Map<String, Object> dynamic) {
        return "订单处理流水线完成";
    }
}
