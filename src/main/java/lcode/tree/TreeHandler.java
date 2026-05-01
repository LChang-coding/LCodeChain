package lcode.tree;

public interface TreeHandler<T,D,R> {
    R apply(T request, D dynamic) throws Exception;

    @SuppressWarnings("rawtypes")
    TreeHandler DEFAULT = (request, dynamic) -> null;
}
