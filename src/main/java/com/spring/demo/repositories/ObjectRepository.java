package com.spring.demo.repositories;

import java.util.Map;

public interface ObjectRepository<T> {

        public void store(T t);

        public T retrieve(String id);

        public T search(String name);

        public T delete(String id);
}
