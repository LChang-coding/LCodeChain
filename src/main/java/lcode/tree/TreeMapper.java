package lcode.tree;

public interface TreeMapper<T,D,R> {
    TreeHandler<T, D, R> get(T request, D dynamic) throws Exception;
}
