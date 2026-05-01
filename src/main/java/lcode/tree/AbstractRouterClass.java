package lcode.tree;

public abstract class AbstractRouterClass<T,D,R> implements TreeHandler<T,D,R>, TreeMapper<T,D,R> {

    @SuppressWarnings("unchecked")
    protected TreeHandler<T, D, R> defaultHandler = TreeHandler.DEFAULT;

    public abstract void mulitWork(T request, D dynamic);

    public TreeHandler<T, D, R> getDefaultHandler() {
        return defaultHandler;
    }

    public void setDefaultHandler(TreeHandler<T, D, R> defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    public R router(T request, D dynamic) throws Exception {
        TreeHandler<T, D, R> handler = get(request, dynamic);
        if (handler != null) return handler.apply(request, dynamic);
        return defaultHandler.apply(request, dynamic);
    }

    @Override
    public R apply(T request, D dynamic) throws Exception {
        mulitWork(request, dynamic);
        return doApply(request, dynamic);
    }

    public abstract R doApply(T request, D dynamic) throws Exception;
}
