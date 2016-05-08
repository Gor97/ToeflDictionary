package com.toefldictionary.DB.executors.functionality;

import com.toefldictionary.DB.executors.objects.Type;

import java.util.ArrayList;

public interface TypeFunctionality {
    Type addType(Type type);
    ArrayList<Type> getAllTypes();
}
