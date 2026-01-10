package com.example.Spring_Boot_Jpa_ER.common.exception;

import java.io.Serial;

public class ResourceException extends RuntimeException {

    // In Java, the 'serialVersionUID' is a unique identifier
    // for a serialized class.
    // It is a field that developers can explicitly declare
    // in a class to control versions during serialization and
    // deserialization.
    // When an object is serialized, the 'serialVersionUID' is
    // also written to the byte stream along with the object's data.
    // The main purpose of 'serialVersionUID' is to ensure that
    // the serialized form of a class is compatible between different
    // versions of that class.
    // This helps to detect incompatible changes made to the class that
    // prevent previously serialized objects from being successfully
    // deserialized.
    // When a class is serialized, its 'serialVersionUID' is included in the
    // serialized form. During deserialization, the JVM checks whether
    // the 'serialVersionUID' of the serialized object matches the
    // 'serialVersionUID' of the class definition. If they do not match,
    // an InvalidClassException is raised, indicating a compatibility problem.
    // This mechanism prevents the deserialization of objects that may be
    // incompatible due to class changes.
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceException(String msg) {
        super(msg);
    }
}
