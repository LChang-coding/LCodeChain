package lcode.tree;

public interface TreeHandler<T,D,R> {
    //执行逻辑的接口
    TreeHandler DEFAULT=(T,D)->null;
    R apply(T requst,D dynamic)throws Exception;//执行业务逻辑的接口
}
