package com.example.springbootdemo.model.reponse;

import java.util.Optional;

public class Either<L,R> {
    public Either(Optional<L> left, Optional<R> right) {
        this.left = left;
        this.right = right;
    }

    final public Optional<L> left;
    final public Optional<R> right;

    public static <L,R> Either<L,R> Left(L value){return  new Either<>(Optional.of(value),Optional.<R>empty());}
    public static <L,R> Either<L,R> Right(R value){return  new Either<>(Optional.<L>empty(),Optional.of(value));}

}
