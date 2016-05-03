package com.toefldictionary.DB.executors.functionality;

import com.toefldictionary.DB.executors.objects.Description;

/**
 * Created by Gor on 27-Apr-16.
 */
public interface DescriptionFunctionality {
    Description addDescription(Description description);
    Description editDescription(Description description);
    void deleteDescription(int id);
}
