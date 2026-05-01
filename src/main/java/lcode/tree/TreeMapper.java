package lcode.tree;

public interface TreeMapper<T,D,R> {
    //路由要紧
    TreeHandler get(T requst,D dnamic)throws  Exception;
}
