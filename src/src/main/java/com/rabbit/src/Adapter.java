package com.rabbit.src;

public interface Adapter<ObjectType, IDType> {

    ObjectType get(IDType id);

    void put(ObjectType object);

    boolean contentsObj(IDType id);

    void deleteObj(IDType id);

}
